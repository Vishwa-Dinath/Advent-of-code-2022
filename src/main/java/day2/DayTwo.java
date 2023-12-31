package day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class DayTwo {

    public static final HashMap<String, String> possibleResponseSet = new HashMap<>();

    public static void addItemsToPossibleResponseSet() {
        possibleResponseSet.put("A X", "Z");
        possibleResponseSet.put("A Y", "X");
        possibleResponseSet.put("A Z", "Y");
        possibleResponseSet.put("B X", "X");
        possibleResponseSet.put("B Y", "Y");
        possibleResponseSet.put("B Z", "Z");
        possibleResponseSet.put("C X", "Y");
        possibleResponseSet.put("C Y", "Z");
        possibleResponseSet.put("C Z", "X");
    }


    public static void main(String[] args) {
        addItemsToPossibleResponseSet();

//        System.out.println(pipeLine_1("/problem_2/sample_input.txt"));
        System.out.println(pipeLine_2("/problem_2/sample_input.txt"));
//        System.out.println(pipeLine_1("/problem_2/input.txt"));
        System.out.println(pipeLine_2("/problem_2/input2.txt"));
    }

    public static ArrayList<String> readInputFile(String filePath) {
        InputStream is = DayTwo.class.getResourceAsStream(filePath);

        ArrayList<String> strategyList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
            String line;
            while ((line= br.readLine())!=null){
                strategyList.add(line);
            }
            return strategyList;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static int getMarks(String key) {
        HashMap<String, Integer> markSet = new HashMap<>();
        markSet.put("X", 1);
        markSet.put("Y", 2);
        markSet.put("Z", 3);
        markSet.put("lost", 0);
        markSet.put("draw", 3);
        markSet.put("won", 6);

        return markSet.get(key);
    }


    public static ArrayList<Integer> calculateMarksOfEachRound(ArrayList<String> strategyList) {
        ArrayList<Integer> totalMarksOfRounds = new ArrayList<>();
        for (String strategy : strategyList) {
            int marksOfRounds = 0;
            String myResponse = strategy.split(" ")[1];
            if (strategy.equals("A X") || strategy.equals("B Y") || strategy.equals("C Z")){
                marksOfRounds += getMarks("draw") + getMarks(myResponse);
            } else if (strategy.equals("A Y") || strategy.equals("B Z") || strategy.equals("C X")) {
                marksOfRounds += getMarks("won") + getMarks(myResponse);
            } else if (strategy.equals("A Z") || strategy.equals("B X") || strategy.equals("C Y")) {
                marksOfRounds += getMarks("lost") + getMarks(myResponse);
            }else {
                System.out.println("Invalid strategy includes in the input file");
            }
            totalMarksOfRounds.add(marksOfRounds);
        }
        return totalMarksOfRounds;
    }

    public static ArrayList<Integer> calculateMarksOfEachRoundForSecondPart(ArrayList<String> strategyList) {
        ArrayList<Integer> totalMarksOfRounds = new ArrayList<>();
        for (String strategy : strategyList) {
            int marksOfRounds = 0;
            String myResponse = possibleResponseSet.get(strategy);
            if (strategy.equals("A X") || strategy.equals("B X") || strategy.equals("C X")){
                marksOfRounds += getMarks("lost") + getMarks(myResponse);
            } else if (strategy.equals("A Y") || strategy.equals("B Y") || strategy.equals("C Y")) {
                marksOfRounds += getMarks("draw") + getMarks(myResponse);
            } else if (strategy.equals("A Z") || strategy.equals("B Z") || strategy.equals("C Z")) {
                marksOfRounds += getMarks("won") + getMarks(myResponse);
            }else {
                System.out.println("Invalid strategy includes in the input file");
            }
            totalMarksOfRounds.add(marksOfRounds);
        }
        return totalMarksOfRounds;
    }

    public static int calculateTotalMarksOfAllRounds(ArrayList<Integer> totalMarksOfRounds) {
        int totalMarksOfAllRounds = 0;
        for (Integer totalMarksOfEachRound : totalMarksOfRounds) {
            totalMarksOfAllRounds += totalMarksOfEachRound;
        }
        return totalMarksOfAllRounds;
    }

    public static int pipeLine_1(String filePath) {
        return calculateTotalMarksOfAllRounds(
                calculateMarksOfEachRound(
                        readInputFile(filePath)
                )
        );
    }

    public static int pipeLine_2(String filePath) {
        return (calculateTotalMarksOfAllRounds(
                calculateMarksOfEachRoundForSecondPart(
                        readInputFile(filePath)
                )
        ));
    }

}
