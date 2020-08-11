package com.lavrenin.ir;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SentenceUtils {
    public static class WordDetails {
        private String word;
        private int length;

        public String getWord() {
            return word;
        }

        public int getLength() {
            return length;
        }

        public WordDetails(String word, int length) {
            this.word = word;
            this.length = length;
        }

        @Override
        public String toString() {
            return String.format("Word: %s, length: %d", word, length);
        }
    }

    public static WordDetails findLongestWord(String sentence) {

        //VERIFICATION OF NULL VALUES
        if (sentence == null) {
            throw new IllegalArgumentException("Input sentences can't be null");
        }
        //VERIFICATION OF EMPTY OR BLANK VALUES
        if (sentence.isEmpty() || sentence.isBlank()) {
            throw new IllegalArgumentException("Input String can't be blank");
        }
        //FILTER OF LATIN LETTERS AND NUMBERS
        List<String> words = Arrays.stream(sentence.split("[^A-Za-z0-9]+")).filter(w->isNotNumber(w)).collect(Collectors.toList());
        if (words.size() == 0) {
            throw new IllegalArgumentException("Input String doesn't contains any word");
        }
        //DETECTION OF LONGEST WORD
        int longestWordIndex = 0;
        for (int i = 1; i < words.size(); i++) {
            if (words.get(i).length() > words.get(longestWordIndex).length()) {
                longestWordIndex = i;
            }
        }
        return new WordDetails(words.get(longestWordIndex), words.get(longestWordIndex).length());
    }

    //IS NOT NUMBER VERIFICATION
    private static boolean isNotNumber(String input) {
        for (int i = 0; i < input.length(); i++) {
            if(Character.isAlphabetic(input.charAt(i))){
                return true;
            }
        }
        return false;
    }
}
