import org.junit.jupiter.api.Test;

import java.lang.management.PlatformLoggingMXBean;

import static org.junit.jupiter.api.Assertions.*;

class WordGenerationTest {
    @Test
    public void testsplitWord() {//Test to check that words are being converted to the correct String.upper format
        assertEquals("TEST", new String(WordGeneration.splitWord("test")));
    }
    @Test //Test to check the method that checks the user guess is correct is working
    public void testcheck() {
        boolean check = WordGeneration.check((WordGeneration.splitWord("word test")), 'T');
        assertTrue(check);
    }
    @Test
    public void testcorrectGuess() {  //Test to check that the previous correct guesses are updated after each correct guess
        char[] charArray = WordGeneration.splitWord("test");
        char[] playerGuesses = {'_','_','_','_'};
        WordGeneration.correctGuess('T', charArray, playerGuesses);
        System.out.println(playerGuesses);
        assertEquals("T__T", new String(playerGuesses));


    }
}