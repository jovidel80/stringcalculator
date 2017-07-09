package com.joseoliveros.tdd.stringcalculator;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {
    @Test
    public void addWithEmptyStringInput_ReturnZero() throws Exception {
        String result = StringCalculator.add("");
        assertEquals("0", result);
    }

    @Test
    public void addWithOneNumberInput_ReturnSameInput() throws Exception {
        String result = StringCalculator.add("1");
        assertEquals("1", result);
    }

    @Test
    public void addWithDifferentOneNumberInput_ReturnSameInput() throws Exception {
        String result = StringCalculator.add("2");
        assertEquals("2", result);
    }

    @Test
    public void addWithTwoNumbersInput_ReturnSumOfThey() throws Exception {
        String result = StringCalculator.add("16,27");
        assertEquals("43", result);
    }

    @Test
    public void addWithUnknownNumbersInput_ReturnSumOfThey() throws Exception {
        String result = StringCalculator.add("1,2,3,4");
        assertEquals("10", result);
    }

    @Test
    public void addWithNewLinesSeparatorNumbers_ReturnSumOfThey() throws Exception {
        String result = StringCalculator.add("1\n2,3");
        assertEquals("6", result);
    }

    @Test
    public void addWithPredefinedSeparator_ReturnSum() throws Exception {
        String result = StringCalculator.add("//;\n1;2,3\n4;5");
        assertEquals("15", result);
    }

    @Test
    public void addWithNegativeNumbersThrowInvalidNumberException() throws Exception {
        try {
            StringCalculator.add("-1,-4,-45");
            fail("InvalidNumberException expected");
        } catch (InvalidNumberException e) {
            assertEquals("-1 -4 -45 are negative numbers", e.getMessage());
        }
    }

    @Test
    public void addWithNumbersLongerThan1000AreIgnored() throws Exception {
        String result = StringCalculator.add("2,1001,3000");
        assertEquals("2", result);
    }

    @Test
    public void addWithAnyLenthDelimiter() throws Exception {
        String result = StringCalculator.add("//[***]\n1***2***3");
        assertEquals("6", result);
    }

    @Test
    public void addWithSeveralAnyLengthDelimiter() throws Exception {
        String result = StringCalculator.add("//[*][%]\n1*2%3");
        assertEquals("6", result);
    }
}
