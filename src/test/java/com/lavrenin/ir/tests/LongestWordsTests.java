package com.lavrenin.ir.tests;

import com.lavrenin.ir.SentenceUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

public class LongestWordsTests {

    private static Stream<Arguments> provideSentencesForLongestWordLength() {
        return Stream.of(
                //FIRST EXAMPLE
                Arguments.of("The cow jumped over the moon.", 6),
                //APOSTROPHE
                Arguments.of("Buffalo! It's a noun! It's a city!", 7),
                //ABBREVIATION/LETTERS AND DIGITS
                Arguments.of("In 2016, CS50 became available to high school students as an AP course.", 9),
                //UPPER CASE
                Arguments.of("LIFE IS BEAUTIFUL!",9 )
        );
    }


    @ParameterizedTest
    @MethodSource("provideSentencesForLongestWordLength")
    public void simpleVerificationLengthOfWord(String sentence, int expectedLength) {
        Assertions.assertEquals(expectedLength, SentenceUtils.findLongestWord(sentence).getLength());
    }


    private static Stream<Arguments> provideSentencesForLongestCorrectWord() {
        return Stream.of(
                Arguments.of("The cow jumped over the moon.", "jumped"),
                Arguments.of("Hello World and people!", "people"),
                Arguments.of("Buffalo! It's a noun! It's a city!", "Buffalo"),
                Arguments.of("In 2016, CS50 became available to high school students as an AP course.", "available"),
                Arguments.of("LIFE IS BEAUTIFUL!", "BEAUTIFUL")
        );
    }

    @ParameterizedTest
    @MethodSource("provideSentencesForLongestCorrectWord")
    public void simpleVerificationCorrectWord(String sentence, String expectedWord) {
        Assertions.assertEquals(expectedWord, SentenceUtils.findLongestWord(sentence).getWord());
    }

    //VERIFICATION OF CORRECT EXCEPTION FOR NULL SENTENCES
    @Test
    public void verifyNullSentences(){
        Assertions.assertThrows(IllegalArgumentException.class, ()-> SentenceUtils.findLongestWord(null));
    }

    //VERIFICATION OF CORRECT EXCEPTION FOR BLANK SENTENCES
    @Test
    public void verifyBlankSentences(){
        Assertions.assertThrows(IllegalArgumentException.class, ()-> SentenceUtils.findLongestWord("  "));
    }

    //VERIFICATION OF CORRECT EXCEPTION FOR SPECIAL CHARACTERS WITH NO LETTERS OR DIGITS
    @Test
    public void verifySpecialCharacter(){
        Assertions.assertThrows(IllegalArgumentException.class, ()-> SentenceUtils.findLongestWord("`@!@#$%^&*()_-+=<.?,:;'"));
    }

    //VERIFICATION OF CORRECT EXCEPTION FOR DIGITS WITH NO LETTERS
    @Test
    public void verifyNumbersAreNotWords(){
        Assertions.assertThrows(IllegalArgumentException.class, ()-> SentenceUtils.findLongestWord("1234567890 20 47 84"));
    }

    //VERIFICATION OF CORRECT EXCEPTION FOR ABBREVIATION: LETTERS + DIGITS
    @Test
    public void verifyAbbreviation(){
        Assertions.assertEquals("R2D2", SentenceUtils.findLongestWord("R2D2").getWord());
    }

    //VERIFICATION OF CORRECT EXCEPTION FOR NON-LATIN ALPHABET
    @Test
    public void verifyOnlyEnglishCharacters(){
        Assertions.assertThrows(IllegalArgumentException.class, ()-> SentenceUtils.findLongestWord("Привет, мир! 日本語のキーボード"));
    }

}
