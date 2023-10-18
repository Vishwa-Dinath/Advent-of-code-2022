package day5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DayFiveTest {
    @Test
    void readInputFileTest() {
        ArrayList<Procedure> expect = new ArrayList<>();
        expect.add(new Procedure(1, 2, 1));
        expect.add(new Procedure(3, 1, 3));
        expect.add(new Procedure(2, 2, 1));
        expect.add(new Procedure(1, 1, 2));

        ArrayList<Procedure> actual = DayFive.readInputFile("/problem_5/sample_input.txt");

        assertEquals(expect,actual);
    }

}