import org.junit.jupiter.api.Test;

import java.lang.management.PlatformLoggingMXBean;

import static org.junit.jupiter.api.Assertions.*;

class WordGenerationTest {
    @Test
    void testsplitWord() {//Test to check that words are being converted to the correct String.upper format
        assertEquals("TEST", new String(WordGeneration.SplitWord("test")));
    }
    @Test //Test to check the method that checks the user guess is correct is working
    void testcheck() {
        boolean check = WordGeneration.Check((WordGeneration.SplitWord("word test")), 'T');
        assertTrue(check);
    }
    @Test
    void testcorrectGuess() {  //Test to check that the previous correct guesses are updated after each correct guess
        char[] charArray = WordGeneration.SplitWord("test");
        char[] playerGuesses = {'_','_','_','_'};
        WordGeneration.CorrectGuess('T', charArray, playerGuesses);
        System.out.println(playerGuesses);
        assertEquals("T__T", new String(playerGuesses));


    }
}