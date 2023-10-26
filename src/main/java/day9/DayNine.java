package day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DayNine {
    public static void main(String[] args) {

    }

    public static ArrayList<ArrayList<String>> getInstructions(String filePath) {
        ArrayList<ArrayList<String>> instructionList = new ArrayList<>();
        InputStream is = DayNine.class.getResourceAsStream(filePath);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
            String line;
            while ((line= br.readLine())!=null){
                ArrayList<String> instruction = new ArrayList<>();
                instruction.add(line.split(" ")[0]);
                instruction.add(line.split(" ")[1]);
                instructionList.add(instruction);
            }
            return instructionList;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
