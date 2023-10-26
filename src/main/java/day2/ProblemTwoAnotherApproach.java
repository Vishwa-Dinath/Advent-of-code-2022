package day2;

import day2.DayTwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProblemTwoAnotherApproach {

    public static void main(String[] args) {
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
        return WinRules.valueOf(opponentResponse).getValue().equals(playerResponse);
    }

    public static boolean doesGameDraw(String opponentResponse, String playerResponse) {
        return DrawRules.valueOf(opponentResponse).getValue().equals(playerResponse);
    }

    public static String[] getResponses(String strategy) {
        String opponentResponse = Reference.valueOf(strategy.split(" ")[0]).getValue();
        String playerResponse = Reference.valueOf(strategy.split(" ")[1]).getValue();
        return new String[]{opponentResponse, playerResponse};
    }

    public static String[] getResponsesForPartTwo(String strategy) {
        String opponentResponse = strategy.split(" ")[0];
        String playerResponse = strategy.split(" ")[1];

        if (playerResponse.equals("X")){
            opponentResponse = Reference.valueOf(opponentResponse).getValue();
            playerResponse = LostRules.valueOf(opponentResponse).getValue();
        } else if (playerResponse.equals("Y")) {
            opponentResponse = Reference.valueOf(opponentResponse).getValue();
            playerResponse = DrawRules.valueOf(opponentResponse).getValue();
        }else{
            opponentResponse = Reference.valueOf(opponentResponse).getValue();
            playerResponse = WinRules.valueOf(opponentResponse).getValue();
        }
        return new String[]{opponentResponse, playerResponse};
    }

    public static int calculateMarksOfEachRound(String[] responses) {
        String opponentResponse = responses[0];
        String playerResponse = responses[1];

        int marksOfRounds = 0;
        if (doesPlayerWin(opponentResponse,playerResponse)){
            marksOfRounds += Score.valueOf(playerResponse).score() + Score.WON.score();
        } else if (doesGameDraw(opponentResponse, playerResponse)) {
            marksOfRounds += Score.valueOf(playerResponse).score() + Score.DRAW.score();
        }else{
            marksOfRounds += Score.valueOf(playerResponse).score() + Score.LOST.score();
        }
        return marksOfRounds;
    }


    public static ArrayList<Integer> calculateMarksOfAllRounds(ArrayList<String> strategyList) {
        ArrayList<Integer> totalMarksOfRounds = new ArrayList<>();
        for (String strategy : strategyList) {
            totalMarksOfRounds.add(calculateMarksOfEachRound(getResponses(strategy))); // --> part one
//            totalMarksOfRounds.add(calculateMarksOfEachRound(getResponsesForPartTwo(strategy))); // --> part two
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
