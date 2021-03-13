import java.io.FileNotFoundException;

class HangmanGame {
    public static void main(String[] args) throws FileNotFoundException {
        //Intro.Welcome();   //This runs a method that prints out temporary intro text

        String randomWord = WordGeneration.generate();  //Generates a random word from text file
        System.out.println(randomWord);  //Temporary test to print out randomly generated word
        char[] splitWord = WordGeneration.splitWord(randomWord);  //Runs method that split word into an array of characters

        int wordLength = splitWord.length;  //Stores length of word as a variable
        System.out.println(wordLength);
    }
}
