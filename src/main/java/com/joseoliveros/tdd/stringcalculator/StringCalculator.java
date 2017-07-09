package com.joseoliveros.tdd.stringcalculator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public static String add(String numbers) {
        if (isDelimiter(numbers)) {
            String delimiter = getDelimiters(numbers);
            return addWithDelimiter(numbers, delimiter);
        } else {
            return addWithDefaultDelimiter(numbers);
        }
    }

    private static boolean isDelimiter(String numbers) {
        try {
            return numbers.substring(0, 2).equals("//");
        } catch (StringIndexOutOfBoundsException e) {
            return false;
        }
    }

    private static List<String> getDelimiters(String numbers) {
        Pattern pattern1 = Pattern.compile("//\\[.*]*|.\n");
        Pattern pattern2 = Pattern.compile("\\[.*]|\\[.*]\n");
        Matcher matcher1 = pattern1.matcher(numbers);
        Matcher matcher2 = pattern2.matcher(numbers);
        List<String> delimiters = new ArrayList<String>();
        boolean firstPass = true;
        matcher1.find();
        if (matcher1.end() == 4) {
            delimiters.add(numbers.substring(2, 3));
            return delimiters;
//            return numbers.substring(2, 3);
        } else {
            if (firstPass) {
                delimiters.add(numbers.substring(3, matcher1.end() - 1));
                firstPass = false;
            }

//            return numbers.substring(3, matcher1.end() - 1);
        }
    }

    @NotNull
    private static String addWithDelimiter(String numbers, String delimiter) {
        if (delimiter.length() == 1) {
            return addWithSingleCharDelimiter(numbers, delimiter);
        } else {
            return addWithMultipleCharDelimiter(numbers, delimiter);
        }
    }

    private static String addWithSingleCharDelimiter(String numbers, CharSequence delimiter) {
            numbers = numbers.substring(4);
            numbers = numbers.replace(delimiter, ",");
        return parseAndAdd(numbers);
    }

    private static String addWithMultipleCharDelimiter(String numbers, CharSequence delimiter) {
        numbers = numbers.substring(4 + delimiter.length() + 1);
        numbers = numbers.replace(delimiter, ",");
        return parseAndAdd(numbers);
    }

    @NotNull
    private static String addWithDefaultDelimiter(String numbers) {
        if (numbers.length() == 0) {
            return "0";
        } else {
            return parseAndAdd(numbers);
        }
    }

    @NotNull
    private static String parseAndAdd(String numbers) {
        int result = 0;
        numbers = numbers.replace('\n', ',');
        String[] inputs = numbers.split(",");
        if (!(negativeNumbers(inputs) == null)) {
            throw new InvalidNumberException(negativeNumbers(inputs) + "are negative numbers");
        }
        for (String s : inputs) {
            if (Integer.valueOf(s) > 1000) {
                s = "0";
            }
            result += Integer.valueOf(s);
        }
        return String.valueOf(result);
    }

    @Nullable
    private static String negativeNumbers(String[] inputs) {
        boolean isNegative = false;
        StringBuilder negativeNumbers = new StringBuilder();
        for (String input : inputs) {
            if (Integer.valueOf(input) < 0) {
                isNegative = true;
                negativeNumbers.append(input).append(" ");
            }
        }
        if (isNegative) {
            return negativeNumbers.toString();

        }
        return null;
    }
}
