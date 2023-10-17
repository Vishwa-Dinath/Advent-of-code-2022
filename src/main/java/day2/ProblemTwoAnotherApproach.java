package day2;

import day2.DayTwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ProblemTwoAnotherApproach {

    public static final HashMap<String, String> rulesForPlayerToWin = new HashMap<>();
    public static final HashMap<String, String> rulesForGameToDraw = new HashMap<>();
    public static final HashMap<String, String> rulesForPlayerToLose = new HashMap<>();
    public static final HashMap<String, String> reference = new HashMap<>();

    public static final HashMap<String, Integer> markSet = new HashMap<>();

    public static void main(String[] args) {
        reference.put("A", "O_ROCK");
        reference.put("B", "O_PAPER");
        reference.put("C", "O_SCISSOR");
        reference.put("X", "P_ROCK");
        reference.put("Y", "P_PAPER");
        reference.put("Z", "P_SCISSOR");

        markSet.put("P_ROCK", 1);
        markSet.put("P_PAPER", 2);
        markSet.put("P_SCISSOR", 3);
        markSet.put("LOST", 0);
        markSet.put("DRAW", 3);
        markSet.put("WON", 6);

        rulesForPlayerToWin.put("O_ROCK", "P_PAPER");
        rulesForPlayerToWin.put("O_PAPER", "P_SCISSOR");
        rulesForPlayerToWin.put("O_SCISSOR", "P_ROCK");

        rulesForGameToDraw.put("O_ROCK", "P_ROCK");
        rulesForGameToDraw.put("O_PAPER", "P_PAPER");
        rulesForGameToDraw.put("O_SCISSOR", "P_SCISSOR");

        rulesForPlayerToLose.put("O_ROCK", "P_SCISSOR");
        rulesForPlayerToLose.put("O_PAPER", "P_ROCK");
        rulesForPlayerToLose.put("O_SCISSOR", "P_PAPER");

        System.out.println(pipeLine_1("/problem_2/input.txt"));

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

    public static boolean doesPlayerWin(String opponentResponse,String playerResponse) {
        return rulesForPlayerToWin.get(opponentResponse).equals(playerResponse);
    }

    public static boolean doesGameDraw(String opponentResponse, String playerResponse) {
        return rulesForGameToDraw.get(opponentResponse).equals(playerResponse);
    }

    public static int getMarks(String key) {
        return markSet.get(key);
    }

    public static String[] getResponses(String strategy) {
        String opponentResponse = reference.get(strategy.split(" ")[0]);
        String playerResponse = reference.get(strategy.split(" ")[1]);
        return new String[]{opponentResponse, playerResponse};
    }

    public static String[] getResponsesForPartTwo(String strategy) {
        String opponentResponse = strategy.split(" ")[0];
        String playerResponse = strategy.split(" ")[1];

        if (playerResponse.equals("X")){
            opponentResponse = reference.get(opponentResponse);
            playerResponse = rulesForPlayerToLose.get(opponentResponse);
        } else if (playerResponse.equals("Y")) {
            opponentResponse = reference.get(opponentResponse);
            playerResponse = rulesForGameToDraw.get(opponentResponse);
        }else{
            opponentResponse = reference.get(opponentResponse);
            playerResponse = rulesForPlayerToWin.get(opponentResponse);
        }
        return new String[]{opponentResponse, playerResponse};
    }

    public static int calculateMarksOfEachRound(String[] responses) {
        String opponentResponse = responses[0];
        String playerResponse = responses[1];

        int marksOfRounds = 0;
        if (doesPlayerWin(opponentResponse,playerResponse)){
            marksOfRounds += getMarks(playerResponse) + getMarks("WON");
        } else if (doesGameDraw(opponentResponse, playerResponse)) {
            marksOfRounds += getMarks(playerResponse) + getMarks("DRAW");
        }else{
            marksOfRounds += getMarks(playerResponse) + getMarks("LOST");
        }
        return marksOfRounds;
    }


    public static ArrayList<Integer> calculateMarksOfAllRounds(ArrayList<String> strategyList) {
        ArrayList<Integer> totalMarksOfRounds = new ArrayList<>();
        for (String strategy : strategyList) {
//            totalMarksOfRounds.add(calculateMarksOfEachRound(getResponses(strategy)));
            totalMarksOfRounds.add(calculateMarksOfEachRound(getResponsesForPartTwo(strategy)));
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
                calculateMarksOfAllRounds(readInputFile(filePath))
        );
    }
}
