import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ProblemTwo {
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

}
