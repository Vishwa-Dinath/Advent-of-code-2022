package day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class DaySixMethod2 {
    public static int sizeOfPacketMarker = 4;
    public static int sizeOfMessageMarker = 14;

    public static List<String> characterList;
    public static HashSet<String> characterSet;

    public static void main(String[] args) {
        System.out.println(pipeLine1("/problem_6/sample_input.txt"));
        System.out.println(pipeLine1("/problem_6/input.txt"));

        System.out.println(pipeLine2("/problem_6/sample_input.txt"));
        System.out.println(pipeLine2("/problem_6/input.txt"));

    }

    public static String[] getDataStreamBuffer(String filePath) {
        InputStream is = DaySixMethod2.class.getResourceAsStream(filePath);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
            return br.readLine().split("");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static int findNumberOfCharactersBeforeFirstMarker(String[] dataStreamBuffer,int sizOfMarker) {
        String message = "";
        for (int i = 0; i < sizOfMarker; i++) {
            message += dataStreamBuffer[i];
        }
        if (!duplicatesExists(message)) return sizOfMarker;

        for (int i = sizOfMarker; i < dataStreamBuffer.length; i++) {
            message = message.substring(1) + dataStreamBuffer[i];
            if (!duplicatesExists(message)) return i+1;
        }
        return -1;
    }

    public static boolean duplicatesExists(String message) {
        characterList = Arrays.asList(message.split(""));
        characterSet = new HashSet<>(characterList);
        return (characterSet.size() < characterList.size());
    }


    public static int pipeLine1(String filePath) {
        return findNumberOfCharactersBeforeFirstMarker(getDataStreamBuffer(filePath),sizeOfPacketMarker);
    }

    public static int pipeLine2(String filePath) {
        return findNumberOfCharactersBeforeFirstMarker(getDataStreamBuffer(filePath), sizeOfMessageMarker);
    }
}
