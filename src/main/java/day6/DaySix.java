package day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DaySix {
    public static int sizeOfPacketMarker = 4;
    public static int sizeOfMessageMarker = 14;

    public static void main(String[] args) {
        System.out.println(pipeLine1("/problem_6/sample_input.txt"));
        System.out.println(pipeLine1("/problem_6/input.txt"));

        System.out.println(pipeLine2("/problem_6/sample_input.txt"));
        System.out.println(pipeLine2("/problem_6/input.txt"));

//        System.out.println(getDataStreamBuffer("/problem_6/input.txt"));
    }

    public static String[] getDataStreamBuffer(String filePath) {
        InputStream is = DaySix.class.getResourceAsStream(filePath);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
            return br.readLine().split("");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static int findNumberOfCharactersBeforeFirstMarker(String[] dataStreamBuffer,int sizOfMarker) {
        for (int i = 0; i < dataStreamBuffer.length- sizOfMarker +1; i++) {
            String temp = "";
            for (int j = 0; j < sizOfMarker; j++) {
                if (!temp.contains(dataStreamBuffer[j+i])){
                    temp += dataStreamBuffer[j+i];
                }
            }
            if (temp.length()== sizOfMarker){
                return i+ sizOfMarker;
            }
        }
        return -1;
    }


    public static int pipeLine1(String filePath) {
        return findNumberOfCharactersBeforeFirstMarker(getDataStreamBuffer(filePath),sizeOfPacketMarker);
    }

    public static int pipeLine2(String filePath) {
        return findNumberOfCharactersBeforeFirstMarker(getDataStreamBuffer(filePath), sizeOfMessageMarker);
    }
}
