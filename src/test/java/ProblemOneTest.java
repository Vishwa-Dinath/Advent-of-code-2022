import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProblemOneTest {

    @Test
    void readInputFileTest() {
        ArrayList<ArrayList<Integer>> actual = ProblemOne.readInputFile("/problem_1/input.txt");
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList(1000,2000,3000)));
        expected.add(new ArrayList<>(Arrays.asList(4000)));
        expected.add(new ArrayList<>(Arrays.asList(5000,6000)));
        expected.add(new ArrayList<>(Arrays.asList(7000,8000,9000)));
        expected.add(new ArrayList<>(Arrays.asList(10000)));

        assertEquals(expected,actual);
    }

    @Test
    void calculateSumsOfCaloriesOfElvesTest() {
        ArrayList<ArrayList<Integer>> caloriesList = new ArrayList<>();
        caloriesList.add(new ArrayList<>(Arrays.asList(1000, 2000, 3000)));
        caloriesList.add(new ArrayList<>(Arrays.asList(4000)));
        caloriesList.add(new ArrayList<>(Arrays.asList(5000, 6000)));
        caloriesList.add(new ArrayList<>(Arrays.asList(7000, 8000, 9000)));
        caloriesList.add(new ArrayList<>(Arrays.asList(10000)));

        ArrayList<Integer> actual = ProblemOne.calculateSumsOfCaloriesOfElves(caloriesList);

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(6000, 4000, 11000, 24000, 10000));

        assertEquals(expected,actual);
    }

    @Test
    void findMaxTotalCaloriesTest() {
        ArrayList<Integer> sumOfCalories = new ArrayList<>(Arrays.asList(6000, 4000, 11000, 24000, 10000));

        int actual = ProblemOne.findMaxTotalCalories(sumOfCalories);
        int expected = 24000;

        assertEquals(expected,actual);
    }

    @Test
    void findThreeMaxCaloriesSumsTest() {
        ArrayList<Integer> sumOfCalories = new ArrayList<>(Arrays.asList(6000, 4000, 11000, 24000, 10000));

        int actual = ProblemOne.findThreeMaxCaloriesSums(sumOfCalories);
        int expected = 45000;

        assertEquals(expected,actual);
    }

    @Test
    void pipeLine_1Test() {
        int actual = ProblemOne.pipeLine_1("/problem_1/input.txt");
        int expected = 24000;
        assertEquals(expected,actual);
    }

    @Test
    void pipeLine_2Test() {
        int actual = ProblemOne.pipeLine_2("/problem_1/input.txt");
        int expected = 45000;

        assertEquals(expected,actual);
    }

    @Test
    void fileNotFoundTest() {
        assertThrows(RuntimeException.class,()->{
            ProblemOne.pipeLine_1("/input1.txt");
        });
    }

}