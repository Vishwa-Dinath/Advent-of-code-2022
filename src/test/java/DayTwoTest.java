import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DayTwoTest {

    @Test
    void readInputFileTest() {
        ArrayList<String> actual = DayTwo.readInputFile("/problem_2/sample_input.txt");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("A Y");
        expected.add("B X");
        expected.add("C Z");

        assertEquals(expected,actual);
    }

    @Test
    void getMarksTest() {
        assertEquals(0, DayTwo.getMarks("lost"));
        assertEquals(3, DayTwo.getMarks("draw"));
        assertEquals(6, DayTwo.getMarks("won"));
        assertEquals(1, DayTwo.getMarks("X"));
        assertEquals(2, DayTwo.getMarks("Y"));
        assertEquals(3, DayTwo.getMarks("Z"));
    }

    @Test
    void calculateMarksOfEachRoundTest() {
        ArrayList<String> strategyList = new ArrayList<>(Arrays.asList("A Y", "B X", "C Z"));

        ArrayList<Integer> actual = DayTwo.calculateMarksOfEachRound(strategyList);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(8, 1, 6));

        assertEquals(expected,actual);
    }

    @Test
    void calculateTotalMarksOfAllRoundsTest() {
        ArrayList<Integer> totalMarksOfRounds = new ArrayList<>(Arrays.asList(8, 1, 6));

        int actual = DayTwo.calculateTotalMarksOfAllRounds(totalMarksOfRounds);
        int expected = 15;

        assertEquals(expected,actual);
    }

    @Test
    void pipeLine_1_Test() {
        int actual = DayTwo.pipeLine_1("/problem_2/sample_input.txt");
        int expected = 15;

        assertEquals(expected,actual);
    }

}