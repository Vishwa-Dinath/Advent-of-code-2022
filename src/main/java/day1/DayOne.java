package day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class DayOne {
    public static void main(String[] args) {

//        System.out.println(pipeLine_1("/input.txt"));
//        System.out.println(pipeLine_2("/input.txt"));

        System.out.println(pipeLine_1("/problem_1/actual_input.txt"));
        System.out.println(pipeLine_2("/problem_1/actual_input.txt"));
    }
    public static ArrayList<ArrayList<Integer>> readInputFile(String filePath) {
        InputStream is = DayOne.class.getResourceAsStream(filePath);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
            String line;
            ArrayList<ArrayList<Integer>> caloriesList = new ArrayList<>();
            ArrayList<Integer> caloriesOfElves = new ArrayList<>();
            while ((line= br.readLine())!=null){
                if (line.isEmpty()){
                    caloriesList.add(caloriesOfElves);
                    caloriesOfElves = new ArrayList<>();
                }else{
                    caloriesOfElves.add(Integer.parseInt(line));
                }
            }
            return caloriesList;
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    public static ArrayList<Integer> calculateSumsOfCaloriesOfElves (ArrayList<ArrayList<Integer>> caloriesList) {
        ArrayList<Integer> sumOfCaloriesOfElves = new ArrayList<>();
        for (ArrayList<Integer> caloriesOfElf : caloriesList) {
            int sumOfCaloriesOfEachElv = 0;
            for (Integer calorie : caloriesOfElf) {
                sumOfCaloriesOfEachElv += calorie;
            }
            sumOfCaloriesOfElves.add(sumOfCaloriesOfEachElv);
        }
        return sumOfCaloriesOfElves;
    }

    public static int findMaxTotalCalories(ArrayList<Integer> sumOfCaloriesOfElves) {
        int maxCaloriesTotal = sumOfCaloriesOfElves.get(0);
        for (int i = 1; i < sumOfCaloriesOfElves.size(); i++) {
            if (sumOfCaloriesOfElves.get(i)>maxCaloriesTotal){
                maxCaloriesTotal = sumOfCaloriesOfElves.get(i);
            }
        }

        return maxCaloriesTotal;
    }

    public static int findThreeMaxCaloriesSums (ArrayList<Integer> sumOfCaloriesOfElves){
        int sumOfThreeMaxCaloriesTotals = 0;
        TreeSet<Integer> sortedSumOfCaloriesOfElves = new TreeSet<>(sumOfCaloriesOfElves);
        int n = sortedSumOfCaloriesOfElves.size();
        Integer[] sumOfCalories = new Integer[n];
        sumOfCalories = sortedSumOfCaloriesOfElves.toArray(sumOfCalories);

        sumOfThreeMaxCaloriesTotals = sumOfCalories[n - 1] + sumOfCalories[n - 2] + sumOfCalories[n - 3];
        return sumOfThreeMaxCaloriesTotals;
    }

    public static int pipeLine_1(String filePath) {

        return findMaxTotalCalories(
                calculateSumsOfCaloriesOfElves(
                        readInputFile(filePath)
                ));
    }

    public static int pipeLine_2(String filePath) {
        return findThreeMaxCaloriesSums(
                calculateSumsOfCaloriesOfElves(
                        readInputFile(filePath)
                ));
    }
}
