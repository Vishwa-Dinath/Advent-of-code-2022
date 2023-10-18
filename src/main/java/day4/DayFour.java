package day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DayFour {


    public interface IntersectionFunction {
        boolean intersect(Range firstElvRange,Range secondElvRange);
    }


    public static void main(String[] args) {
//        System.out.println(getAssignmentPairs("/problem_4/sample_input.txt"));

//        System.out.println(pipeLine_1("/problem_4/sample_input.txt"));
//        System.out.println(pipeLine_1("/problem_4/input.txt"));

        System.out.println(pipeLine_2("/problem_4/sample_input.txt"));
        System.out.println(pipeLine_2("/problem_4/input.txt"));
    }

    public static ArrayList<String> getAssignmentPairs(String filePath) {
        InputStream is = DayFour.class.getResourceAsStream(filePath);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
            ArrayList<String> assignmentsPairsList = new ArrayList<>();
            String line;
            while ((line= br.readLine())!=null){
                assignmentsPairsList.add(line);
            }
            return assignmentsPairsList;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<ArrayList<Range>> getAssignmentRanges(ArrayList<String> assignmentsPairsList) {
        ArrayList<ArrayList<Range>> assignmentRangeList = new ArrayList<>();
        for (String assignmentPair : assignmentsPairsList) {
            String[] assignmentRanges = assignmentPair.split(",");
            ArrayList<Range> rangeList = new ArrayList<>();
            rangeList.add(new Range(Integer.parseInt(assignmentRanges[0].split("-")[0]),
                    Integer.parseInt(assignmentRanges[0].split("-")[1])));
            rangeList.add(new Range(Integer.parseInt(assignmentRanges[1].split("-")[0]),
                    Integer.parseInt(assignmentRanges[1].split("-")[1])));
            assignmentRangeList.add(rangeList);
        }
        return assignmentRangeList;
    }

//    public static int findFullyContainedPairs(ArrayList<ArrayList<Range>> assignmentRangeList) {
//        int numberOfFullyContainedPairs = 0;
//        for (ArrayList<Range> ranges : assignmentRangeList) {
//            Range firstElfRange = ranges.get(0);
//            Range secondElfRange = ranges.get(1);
//            if (doesOneFullyContainOther(firstElfRange, secondElfRange)) {
//                numberOfFullyContainedPairs++;
//            }
//        }
//        return numberOfFullyContainedPairs;
//    }

    public static boolean doesOneFullyContainOther(Range firstElfRange, Range secondElfRange) {
        return (firstElfRange.start <= secondElfRange.start && firstElfRange.end >= secondElfRange.end) ||
                secondElfRange.start <= firstElfRange.start && secondElfRange.end >= firstElfRange.end;
    }



    public static int findOverlappedPairs(ArrayList<ArrayList<Range>> assignmentRangeList, IntersectionFunction intersectionFunction) {
        int numberOfAllOverlappedPairs = 0;
        for (ArrayList<Range> ranges : assignmentRangeList) {
            Range firstElfRange = ranges.get(0);
            Range secondElfRange = ranges.get(1);
            if (intersectionFunction.intersect(firstElfRange,secondElfRange)) {
                numberOfAllOverlappedPairs++;
            }
        }
        return numberOfAllOverlappedPairs;
    }

    public static boolean doesOneContainOther(Range firstElfRange, Range secondElfRange) {
        if ((firstElfRange.start <= secondElfRange.start && firstElfRange.end >= secondElfRange.start) ||
                secondElfRange.start <= firstElfRange.start && secondElfRange.end >= firstElfRange.start) {
            return true;
        }
        return false;
    }

    public static int pipeLine_1(String filePath) {
        return findOverlappedPairs(
                getAssignmentRanges(
                        getAssignmentPairs(filePath)
                ), DayFour::doesOneFullyContainOther
        );
    }

    public static int pipeLine_2(String filePath) {
        return findOverlappedPairs(
                getAssignmentRanges(
                        getAssignmentPairs(filePath)
                ),DayFour::doesOneContainOther
        );
    }
}

class Range{
    int start;
    int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
