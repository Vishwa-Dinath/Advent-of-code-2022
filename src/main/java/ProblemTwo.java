import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ProblemTwo {

    public static final String [] DRAW_STRAT = new String[]{"A X","B Y","C Z"};
    public static final String [] WIN_STRAT = new String[]{"A Y","B Z","C X"};
    public static final String [] LOSS_STRAT = new String[]{"A Z","B X","C Y"};

    public static void main(String[] args) {
        System.out.println(pipeLine_1("/problem_2/sample_input.txt"));
        System.out.println(pipeLine_1("/problem_2/input.txt"));
    }

    public static ArrayList<String> readInputFile(String filePath) {
        InputStream is = ProblemTwo.class.getResourceAsStream(filePath);

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

    public static int getMarks(String key) { //TODO: this marks hashmap is getting created everytime it is called, seems redundant, needs to change
        HashMap<String, Integer> markSet = new HashMap<>();
        markSet.put("X", 1);
        markSet.put("Y", 2);
        markSet.put("Z", 3);
        markSet.put("lost", 0);
        markSet.put("draw", 3);
        markSet.put("won", 6);
        return markSet.get(key);
    }

    public static boolean checkIfAny(String [] arr, String target){
        for (int i = 0; i < arr.length; i++){
            if (arr[i].equals(target))
                return true;
        }
        return false;
    }

    public static int getMarksForRound(String[] winStrat, String[] lossStrat, String[] drawStrat, String strategy){
        int marksOfRounds = 0;
        String myResponse = strategy.split(" ")[1];
        if (checkIfAny(drawStrat, strategy)){
            marksOfRounds += getMarks("draw") + getMarks(myResponse);
        } else if (checkIfAny(winStrat, strategy)) {
            marksOfRounds += getMarks("won") + getMarks(myResponse);
        } else if (checkIfAny(lossStrat,strategy)) {
            marksOfRounds += getMarks("lost") + getMarks(myResponse);
        }else {
            System.out.println("Invalid strategy includes in the input file");
        }

        return marksOfRounds;
    }

    public static ArrayList<Integer> calculateMarksOfEachRound(String[] winStrat, String[] lossStrat,
                                                               String[] drawStrat, ArrayList<String> strategyList) {
        ArrayList<Integer> totalMarksOfRounds = new ArrayList<>();
        for (String strategy : strategyList) {
            totalMarksOfRounds.add(getMarksForRound(winStrat,
                                                    lossStrat,
                                                    drawStrat,
                                                    strategy));
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
                calculateMarksOfEachRound(WIN_STRAT, LOSS_STRAT, DRAW_STRAT,
                        readInputFile(filePath)
                )
        );
    }

}
