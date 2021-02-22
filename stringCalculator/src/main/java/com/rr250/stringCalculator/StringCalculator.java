package com.rr250.stringCalculator;

import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String numbers) {
        SeparatorAndNumbers separatorAndNum = getSeparatorAndNumbersFrom(numbers);
        return sum(separatorAndNum.extractNumberLine());
    }

    private SeparatorAndNumbers getSeparatorAndNumbersFrom(String numbers) {
        if (numbers.startsWith("//")) {
            String[] separatorAndNumbers = numbers.split("\n");
            return new SeparatorAndNumbers(extractSeparator(separatorAndNumbers[0]), separatorAndNumbers[1]);
        }

        return new SeparatorAndNumbers("[,\n]", numbers);
    }

    private String extractSeparator(String separator) {
        if (separator.substring(2).startsWith("[") && separator.endsWith("]")){
            StringBuilder s = new StringBuilder("");
            String[] separator1 = separator.substring(3,separator.length()-1).split(Pattern.quote("]["));
            for(String sepatator11:separator1){
                s.append(Pattern.quote(sepatator11));
                s.append("|");
            }
            return s.toString();
        }
        return Pattern.quote(separator.substring(2));
    }

    private int sum(String[] numbers) {
        int sum = 0;
        for (String addend : numbers) {
            sum += lessThan1000(nonNegativeValueOf(addend));
        }
        return sum;
    }

    private Integer nonNegativeValueOf(String str) {
        if (str.isEmpty()) {
            return 0;
        }

        int number = Integer.parseInt(str);
        if (number < 0) {
            throw new IllegalArgumentException();
        }
        return number;
    }

    private Integer lessThan1000(Integer number) {
        if (number >= 1000) {
            return 0;
        }
        return number;
    }

    private static class SeparatorAndNumbers {
        private final String separator;
        private final String numbers;

        public SeparatorAndNumbers(String separator, String numbers) {
            this.separator = separator;
            this.numbers = numbers;
        }

        public String[] extractNumberLine() {
            return numbers.split(separator);
        }
    }
}
