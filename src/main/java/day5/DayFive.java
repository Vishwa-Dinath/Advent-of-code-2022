package day5;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

public class DayFive {

    public interface IntersectionFunction{
        void moveCrates(Procedure procedure);
    }

    public static ArrayList<Stack<String>> startingStack = new ArrayList<>();

    public static void main(String[] args) {
        initStartingStack();

//        System.out.println(pipeLine1("/problem_5/sample_input.txt"));
//        System.out.println(pipeLine2("/problem_5/sample_input.txt"));
//        System.out.println(pipeLine1("/problem_5/input.txt"));
        System.out.println(pipeLine2("/problem_5/input.txt"));
    }

    public static void initStartingStack() {
        int numberOfStacksSample = 3;
        int numberOfStacksActual = 9;
        int numberOfStacks = numberOfStacksActual;
        for (int i = 0; i < numberOfStacks; i++) {
            startingStack.add(new Stack<>());
        }
        String[][] stackCratesSample = new String[][]{{"Z","N"},{"M","C","D"},{"P"}};
        String[][] stackCratesActual = new String[][]{{"G","T","R","W"},{"G","C","H","P","M","S","V","W"},{"C","L","T","S","G","M"},
                {"J","H","D","M","W","R","F"},{"P","Q","L","H","S","W","F","J"},{"P","J","D","N","F","M","S"},{"Z","B","D","F","G","C","S","J"},
                {"R","T","B"},{"H","N","W","L","C"}};
        String[][] stackCrates = stackCratesActual;

        for (int i = 0; i < numberOfStacks; i++) {
            for (int j = 0; j < stackCrates[i].length; j++) {
                startingStack.get(i).push(stackCrates[i][j]);
            }
        }
    }

    public static ArrayList<Procedure> readInputFile(String filePath) {
        InputStream is = DayFive.class.getResourceAsStream(filePath);
        ArrayList<Procedure> procedureList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("[A-Za-z]+","").trim();
                String[] information = line.split("  ");
                int numberOfCrates = Integer.parseInt(information[0]);
                int source = Integer.parseInt(information[1])-1;
                int destination = Integer.parseInt(information[2])-1;
                procedureList.add(new Procedure(numberOfCrates,source,destination));
            }
            return procedureList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeProcedure(Procedure procedure) {
        for (int i = 0; i < procedure.numberOfCrates; i++) {
            String movedItem = startingStack.get(procedure.source).pop();
            startingStack.get(procedure.destination).push(movedItem);
        }
    }

    public static void executeMultipleCrateMovements(Procedure procedure) {
        Stack<String> temp = new Stack<>();
        for (int i = 0; i < procedure.numberOfCrates; i++) {
            temp.push(startingStack.get(procedure.source).pop());
        }
        for (int i = 0; i < procedure.numberOfCrates; i++) {
            startingStack.get(procedure.destination).push(temp.pop());
        }
    }

    public static ArrayList<Stack<String>> executeAllProcedures(ArrayList<Procedure> procedureList,IntersectionFunction intersectionFunction) {
        for (Procedure procedure : procedureList) {
            intersectionFunction.moveCrates(procedure);
        }
        return startingStack;
    }

    public static String findLastCratesOfStacks(ArrayList<Stack<String>> startingStack) {
        String lastCratesOfStacks = "";
        for (Stack<String> crateStack : startingStack) {
            lastCratesOfStacks += crateStack.lastElement();
        }
        return lastCratesOfStacks;
    }

    public static String pipeLine1(String filepath) {
        return findLastCratesOfStacks(
                executeAllProcedures(readInputFile(filepath), DayFive::executeProcedure)
        );
    }

    public static String pipeLine2(String filePath) {
        return findLastCratesOfStacks(
                executeAllProcedures(readInputFile(filePath), DayFive::executeMultipleCrateMovements)
        );
    }
}

class Procedure{
    public int numberOfCrates;
    public int source;
    public int destination;

    public Procedure(int numberOfCrates, int source, int destination) {
        this.numberOfCrates = numberOfCrates;
        this.source = source;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Procedure{"  + numberOfCrates +
                "," + source +
                "," + destination +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Procedure procedure = (Procedure) o;
        return numberOfCrates == procedure.numberOfCrates && source == procedure.source && destination == procedure.destination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfCrates, source, destination);
    }
}
