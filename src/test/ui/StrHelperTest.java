package test.ui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import main.java.ui.StrHelper;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.*;


class StrHelperTest {

    @Test
    void center() {
        String text = "Hello";
        String expected = " " + text + " ";
        String actual = StrHelper.center(text , text.length() + 2);
        assertEquals(expected,actual);
    }

    @ParameterizedTest
    @CsvSource(value = {"mehdi,*mehdi*",
                        "123,*123*"})
    void centerWithCharacter(String value, String expected) {
        String actual = StrHelper.center(value , value.length() + 2 , '*');
        assertEquals(expected , actual);
    }

    @Test
    void join() {
        String[] arr = {"A" , "B" , "C" , "D"};
        String expected = "A,B,C,D";
        String actual = StrHelper.join(arr , ",");
        assertEquals(expected , actual);
    }

    @Test
    void repeat() {
        String text = "ABC";
        String expected = "ABCABC";
        String actual = StrHelper.repeat(text , 2);
        assertEquals(expected , actual);
    }
}