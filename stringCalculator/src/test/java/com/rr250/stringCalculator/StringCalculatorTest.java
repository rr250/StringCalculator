package com.rr250.stringCalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    private final StringCalculator calculator = new StringCalculator();

    @Test
    public void emptyString() {
        assertEquals(calculator.add(""), 0);
    }

    @Test
    public void oneNumber() {
        assertEquals(calculator.add("3"), 3);
        assertEquals(calculator.add("5"), 5);
        assertEquals(calculator.add("7"), 7);
    }

    @Test
    public void twoNumbers() {
        assertEquals(calculator.add("1,2"), 3);
        assertEquals(calculator.add("2,4"), 6);
        assertEquals(calculator.add("15,31"), 46);
    }

    @Test
    public void manyNumbers() {
        assertEquals(calculator.add("1,2,3"), 6);
        assertEquals(calculator.add("2,3,4"), 9);
        assertEquals(calculator.add("1,2,3,4"), 10);
    }

    @Test
    public void newLinesAsSeparators() {
        assertEquals(calculator.add("1\n2,3,4"), 10);
        assertEquals(calculator.add("1\n2\n3\n4"), 10);
    }

    @Test
    public void negativeNumbers() {
        assertThrows(IllegalArgumentException.class,()->{calculator.add("-1,2,3,4");});
    }

    @Test
    public void customSeparatorManyNumbers() {
        assertEquals(calculator.add("//,\n1,3,5,7"), 16);
        assertEquals(calculator.add("//_\n1_3_5_7"), 16);
        assertEquals(calculator.add("//|\n1|3|5|7"), 16);
    }

    @Test
    public void moreThan1000() {
        assertEquals(calculator.add("1000,2"), 2);
    }

    @Test
    public void consecutiveSeparators() {
        assertEquals(calculator.add("//[,,,]\n1,,,3,,,5,,,7"), 16);
        assertEquals(calculator.add("//[__]\n1__3__5__7"), 16);
        assertEquals(calculator.add("//[,_]\n1,_3,_5,_7"), 16);
    }

    @Test
    public void multipleSeparators() {
        assertEquals(calculator.add("//[,][_]\n1,3_5_7"), 16);
        assertEquals(calculator.add("//[,][_]\n1,3,5_7"), 16);
        assertEquals(calculator.add("//[,][_]\n1,3_5,7"), 16);
    }

    @Test
    public void multipleConsecutiveSeparators() {
        assertEquals(calculator.add("//[,,][__]\n1,,3__5__7"), 16);
        assertEquals(calculator.add("//[,][__]\n1,3__5__7"), 16);
        assertEquals(calculator.add("//[,,][___]\n1,,3___5___7"), 16);
    }
}