package day5;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DayFive {

    public interface IntersectionFunction{
        void moveCrates(Procedure procedure);
    }

    public static ArrayList<Stack<String>> startingStack = new ArrayList<>();

    public static void main(String[] args) {
//        initStartingStack();
        initActualStartingStack();

//        System.out.println(pipeLine1("/problem_5/sample_input.txt"));
//        System.out.println(pipeLine1("/problem_5/input.txt"));
        System.out.println(pipeLine2("/problem_5/input.txt"));
    }

    public static void initStartingStack() {
        int numberOfStacks = 3;
        for (int i = 0; i < numberOfStacks; i++) {
            startingStack.add(new Stack<>());
        }
        startingStack.get(0).push("Z");
        startingStack.get(0).push("N");

        startingStack.get(1).push("M");
        startingStack.get(1).push("C");
        startingStack.get(1).push("D");

        startingStack.get(2).push("P");
    }

    public static void initActualStartingStack() {
        int numberOfStacks = 9;
        for (int i = 0; i < numberOfStacks; i++) {
            startingStack.add(new Stack<>());
        }
        startingStack.get(0).push("G");startingStack.get(0).push("T");
        startingStack.get(0).push("R");startingStack.get(0).push("W");

        startingStack.get(1).push("G");startingStack.get(1).push("C");startingStack.get(1).push("H");startingStack.get(1).push("P");
        startingStack.get(1).push("M");startingStack.get(1).push("S");startingStack.get(1).push("V");startingStack.get(1).push("W");

        startingStack.get(2).push("C");startingStack.get(2).push("L");startingStack.get(2).push("T");
        startingStack.get(2).push("S");startingStack.get(2).push("G");startingStack.get(2).push("M");

        startingStack.get(3).push("J");startingStack.get(3).push("H");startingStack.get(3).push("D");
        startingStack.get(3).push("M");startingStack.get(3).push("W");startingStack.get(3).push("R");
        startingStack.get(3).push("F");

        startingStack.get(4).push("P");startingStack.get(4).push("Q");startingStack.get(4).push("L");
        startingStack.get(4).push("H");startingStack.get(4).push("S");startingStack.get(4).push("W");
        startingStack.get(4).push("F");startingStack.get(4).push("J");

        startingStack.get(5).push("P");startingStack.get(5).push("J");startingStack.get(5).push("D");
        startingStack.get(5).push("N");startingStack.get(5).push("F");startingStack.get(5).push("M");
        startingStack.get(5).push("S");

        startingStack.get(6).push("Z");startingStack.get(6).push("B");startingStack.get(6).push("D");
        startingStack.get(6).push("F");startingStack.get(6).push("G");startingStack.get(6).push("C");
        startingStack.get(6).push("S");startingStack.get(6).push("J");

        startingStack.get(7).push("R");startingStack.get(7).push("T");startingStack.get(7).push("B");

        startingStack.get(8).push("H");startingStack.get(8).push("N");startingStack.get(8).push("W");
        startingStack.get(8).push("L");startingStack.get(8).push("C");

    }

    public static ArrayList<Procedure> readInputFile(String filePath) {
        InputStream is = DayFive.class.getResourceAsStream(filePath);
        ArrayList<Procedure> procedureList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("[A-Za-z]+","").trim();
                String[] information = line.split("  ");
                int numberOfCrates = Integer.parseInt(information[0]);
                int source = Integer.parseInt(information[1]);
                int destination = Integer.parseInt(information[2]);
                procedureList.add(new Procedure(numberOfCrates,source,destination));
            }
            return procedureList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeProcedure(Procedure procedure) {
        for (int i = 0; i < procedure.numberOfCrates; i++) {
            String movedItem = startingStack.get(procedure.source-1).pop();
            startingStack.get(procedure.destination-1).push(movedItem);
        }
    }

    public static void executeMultipleCrateMovements(Procedure procedure) {
        Stack<String> temp = new Stack<>();
        for (int i = 0; i < procedure.numberOfCrates; i++) {
            temp.push(startingStack.get(procedure.source - 1).pop());
        }
        for (int i = 0; i < procedure.numberOfCrates; i++) {
            startingStack.get(procedure.destination - 1).push(temp.pop());
        }
    }

    public static ArrayList<Stack<String>> executeAllProcedures(ArrayList<Procedure> procedureList,IntersectionFunction intersectionFunction) {
        for (Procedure procedure : procedureList) {
            intersectionFunction.moveCrates(procedure);
        }
        return startingStack;
    }

    public static String findLastCratesOfStacks(ArrayList<Stack<String>> startingStack) {
        String lastCratesOfStacks = "";
        for (Stack<String> crateStack : startingStack) {
            lastCratesOfStacks += crateStack.lastElement();
        }
        return lastCratesOfStacks;
    }

    public static String pipeLine1(String filepath) {
        return findLastCratesOfStacks(
                executeAllProcedures(readInputFile(filepath), DayFive::executeProcedure)
        );
    }

    public static String pipeLine2(String filePath) {
        return findLastCratesOfStacks(
                executeAllProcedures(readInputFile(filePath), DayFive::executeMultipleCrateMovements)
        );
    }
}

class Procedure{
    public int numberOfCrates;
    public int source;
    public int destination;

    public Procedure(int numberOfCrates, int source, int destination) {
        this.numberOfCrates = numberOfCrates;
        this.source = source;
        this.destination = destination;
    }
}
