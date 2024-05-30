package com.bd2.bd2.tools;

public class Tools {
    public Tools() {
    }

    public static String extractBetweenOccurrences(String input, String delimiter) {
        int firstIndex = input.indexOf(delimiter);
        int secondIndex = input.indexOf(delimiter, firstIndex + delimiter.length());

        if (firstIndex != -1 && secondIndex != -1 && firstIndex < secondIndex) {
            return input.substring(firstIndex + delimiter.length(), secondIndex).trim();
        } else {
            return ""; // Return an empty string if the delimiters are not found or are in the wrong order
        }
    }
}
