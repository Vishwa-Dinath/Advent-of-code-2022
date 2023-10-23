package day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class DaySeven {
    private static final int TOTAL_DISK_SIZE = 70000000;
    private static final int REQUIRED_SIZE_FOR_UPDATE = 30000000;
    private static Node currentDirectory;
    private static Node rootDirectory;
    private static ArrayList<Integer> totalSizeList = new ArrayList<>();

    public static void main(String[] args) {
//        System.out.println(pipeLine1("/problem_7/sample_input.txt"));
//        System.out.println(pipeLine1("/problem_7/input.txt"));

//        System.out.println(pipeLine2("/problem_7/sample_input.txt"));
        System.out.println(pipeLine2("/problem_7/input.txt"));

    }

    public static ArrayList<ArrayList<String>> readInputFile(String filePath) {
        InputStream is = DaySeven.class.getResourceAsStream(filePath);
        ArrayList<ArrayList<String>> commandList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
            String line;
            ArrayList<String> commands = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (line.startsWith("$")){
                    commandList.add(commands);
                    commands = new ArrayList<>();
                }
                commands.add(line);
            }
            commandList.remove(0);
//            System.out.println(commandList);
            return commandList;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static Node executeCommands(ArrayList<ArrayList<String>> commandList) {
        for (ArrayList<String> command : commandList) {
            if (command.get(0).startsWith("$ cd")){
                String directory = command.get(0).replace("$ cd ", "");
                if (directory.equals("/")){
                    rootDirectory = createRootDirectory();
                    currentDirectory = rootDirectory;
                }else if(directory.matches("[A-Za-z]+")){
                    Node destination = null;
                    for (Node child : currentDirectory.getChildren()) {
                        if (child.getName().equals(directory)){
                            destination = child;
                        }
                    }
                    changeDirectory(destination);
                }else{
                    currentDirectory = currentDirectory.getParent();
                }

            } else if (command.get(0).equals("$ ls")){
                for (int i = 1; i < command.size(); i++) {
                    addChildItems(currentDirectory,command.get(i));
                }
            }
        }
        return rootDirectory;
    }

    public static Node createRootDirectory() {
        return new Node("root", null,0);
    }

    public static void addChildItems(Node parent,String file) {
        int size = 0;
        String fileName = file.split(" ")[1];
        if (!file.startsWith("dir")){
            size = Integer.parseInt(file.split(" ")[0]);
        }
        Node node = new Node(fileName,parent,size);
        parent.getChildren().add(node);
    }

    public static void changeDirectory(Node destination) {
        currentDirectory = destination;
    }

    public static ArrayList<Integer> calculateSizes(Node directory) {
        for (Node child : directory.getChildren()) {
            if (!child.isFile()) {
                totalSizeList.add(child.calculateSize());
                calculateSizes(child);
            }
        }
        return totalSizeList;
    }

    public static int calculateTotalSize(ArrayList<Integer> totalSizeList) {
        int totalSize = 0;
        for (Integer size : totalSizeList) {
            if (size <= 100000) {
                totalSize += size;
            }
        }
        return totalSize;
    }

    public static int findSizeOfSpaceToDelete(ArrayList<Integer> totalSizeList) {
        int consumedTotalSpace = rootDirectory.calculateSize();
        int availableSpace = TOTAL_DISK_SIZE - consumedTotalSpace;
        int minimumSpaceToDelete = 0;
        if (availableSpace < REQUIRED_SIZE_FOR_UPDATE){
            minimumSpaceToDelete = REQUIRED_SIZE_FOR_UPDATE - availableSpace;
        }
        ArrayList<Integer> acceptableSizes = new ArrayList<>();
        for (Integer size : totalSizeList) {
            if (size>=minimumSpaceToDelete) acceptableSizes.add(size);
        }
        if (acceptableSizes.isEmpty()) return consumedTotalSpace;
        else{
            return Collections.min(acceptableSizes);
        }
    }

    public static int pipeLine1(String fileName) {
        return calculateTotalSize(
                calculateSizes(
                        executeCommands(
                                readInputFile(fileName)
                        )
                )
        );
    }

    public static int pipeLine2(String fileName) {
        return findSizeOfSpaceToDelete(
                calculateSizes(
                        executeCommands(
                                readInputFile(fileName)
                        )
                )
        );
    }
}

class Node{
    private String name;
    private Node parent;
    private ArrayList<Node> children;
    private int size;
    private boolean isFile;

    public Node(String name, Node parent,int size) {
        this.name = name;
        this.parent = parent;
        this.size = size;
        this.children = new ArrayList<>();
        this.isFile = this.size!=0;
    }


    public String getName() {
        return name;
    }

    public Node getParent() {
        return parent;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public int getSize() {
        return size;
    }

    public boolean isFile() {
        return isFile;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", children=" + children +
                '}';
    }

    public int calculateSize() {
        if (children.isEmpty()) {
            return size;
        }else{
            int totalSize = size;
            for (Node child : children) {
                totalSize += child.calculateSize();
            }
            return totalSize;
        }
    }
}
