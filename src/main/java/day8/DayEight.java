package day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class DayEight {
    public static int numberOfRows;
    public static int numberOfColumns;

    public static void main(String[] args) {
//        System.out.println(pipeLine1("/problem_8/sample_input.txt"));
//        System.out.println("Total Visible trees in the arrangement: "+pipeLine1("/problem_8/input.txt"));
//        System.out.println(pipeLine2("/problem_8/sample_input.txt"));
        System.out.println(pipeLine2("/problem_8/input.txt"));
    }

    public static ArrayList<ArrayList<Integer>> getHeightList(String filePath) {
        InputStream is = DayEight.class.getResourceAsStream(filePath);
        ArrayList<ArrayList<Integer>> heightList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<Integer> heightsOfRow = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    heightsOfRow.add(Integer.parseInt(line.substring(i, i + 1)));
                }
                heightList.add(heightsOfRow);
            }
            return heightList;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static int findVisibleTrees(ArrayList<ArrayList<Integer>> heightList) {
        int numberOfVisibleTrees = 0;
        numberOfRows = heightList.size();
        numberOfColumns = heightList.get(0).size();

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j <numberOfColumns; j++) {
                if (isEdgeTree(i,j)){
                    numberOfVisibleTrees += 1;
                }else{
                    if (isTreeVisible(i,j,heightList)) {
                        numberOfVisibleTrees += 1;
                    }
                }
            }
        }
        return numberOfVisibleTrees;
    }

    public static boolean isEdgeTree(int rowIndex,int columnIndex) {
        return (rowIndex == 0 || columnIndex == 0 || rowIndex == numberOfRows-1 || columnIndex == numberOfColumns-1);
    }

    public static boolean isTreeVisible(int rowIndex, int columnIndex,ArrayList<ArrayList<Integer>> heightList) {
        boolean visible = true;
        int count = 0;
        int treeHeight = heightList.get(rowIndex).get(columnIndex);
        for (int i = 0; i < columnIndex; i++) {
            if (treeHeight <= heightList.get(rowIndex).get(i)) {
                visible = false;
                break;
            }
        }
        if (visible) count++;
        visible = true;
        for (int i = columnIndex+1; i < numberOfColumns; i++) {
            if (treeHeight <= heightList.get(rowIndex).get(i)) {
                visible = false;
                break;
            }
        }
        if (visible) count++;
        visible = true;
        for (int i = 0; i < rowIndex; i++) {
            if (treeHeight <= heightList.get(i).get(columnIndex)) {
                visible = false;
                break;
            }
        }
        if (visible) count++;
        visible = true;
        for (int i = rowIndex+1; i < numberOfRows; i++) {
            if (treeHeight <= heightList.get(i).get(columnIndex)) {
                visible = false;
                break;
            }
        }
        if (visible) count++;

        return count > 0;
    }

    public static int pipeLine1(String filePath) {
        return findVisibleTrees(
                getHeightList(filePath)
        );
    }

    public static int findScenicScoreOfTrees(ArrayList<ArrayList<Integer>> heightList) {
        numberOfRows = heightList.size();
        numberOfColumns = heightList.get(0).size();
        ArrayList<Integer> scenicScoreList = new ArrayList<>();
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j <numberOfColumns; j++) {
                if (!isEdgeTree(i,j)){
                    scenicScoreList.add(calculateScenicScoreOfEachTree(i, j, heightList));
                }
            }
        }
//        System.out.println(scenicScoreList);
        return Collections.max(scenicScoreList);
    }

    public static int calculateScenicScoreOfEachTree(int rowIndex, int columnIndex, ArrayList<ArrayList<Integer>> heightList) {
        int treeHeight = heightList.get(rowIndex).get(columnIndex);
        int count = 0;
        int scenicScore = 1;
        for (int i = columnIndex-1; i>=0; i--) {
            if (heightList.get(rowIndex).get(i) < treeHeight) {
                count++;
            }else{
                count++;
                break;
            }
        }
        scenicScore *= count;
        count = 0;

        for (int i = columnIndex+1; i < numberOfColumns; i++) {
            if (heightList.get(rowIndex).get(i) < treeHeight) {
                count++;
            }else{
                count++;
                break;
            }
        }
        scenicScore *= count;
        count = 0;

        for (int i = rowIndex-1; i >= 0; i--) {
            if (heightList.get(i).get(columnIndex) < treeHeight) {
                count++;
            }else{
                count++;
                break;
            }
        }
        scenicScore *= count;
        count = 0;

        for (int i = rowIndex+1; i < numberOfRows; i++) {
            if (heightList.get(i).get(columnIndex) < treeHeight) {
                count++;
            }else{
                count++;
                break;
            }
        }
        scenicScore *= count;
        return scenicScore;
    }

    public static int pipeLine2(String filePath) {
        return findScenicScoreOfTrees(
                getHeightList(filePath)
        );
    }

}
