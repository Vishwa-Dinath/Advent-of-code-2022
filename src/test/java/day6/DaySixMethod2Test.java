package day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DaySixMethod2Test {
    @Test
    void getDataStreamBufferTest() {
        String[] actual = DaySixMethod2.getDataStreamBuffer("/problem_6/sample_input.txt");
        String[] expect = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg".split("");
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expect[i],actual[i]);
        }
    }

    @Test
    void duplicatesExistsTest() {
        boolean actual1 = DaySixMethod2.duplicatesExists("asdf");
        boolean expect1 = false;
        assertEquals(expect1,actual1);

        boolean actual2 = DaySixMethod2.duplicatesExists("asdfa");
        boolean expect2 = true;
        assertEquals(expect2,actual2);
    }

    @Test
    void findNumberOfCharactersBeforeFirstMarker() {
        int actual = DaySixMethod2.findNumberOfCharactersBeforeFirstMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg".split(""), 4);
        int expect = 10;

        assertEquals(expect,actual);
    }

}