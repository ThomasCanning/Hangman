import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordGeneration {  //This class has a method that can be called which generates a Random word from the .txt file "word_library.txt"

    private static int Random(int listSize) {  //Method for generating Random number between 0 and list length
        return ((int) (Math.random() * (listSize + 1)));
    }

    public static String Generate() throws FileNotFoundException {
        File file = new File("res/word_library.txt");  //Reading file with words in
        Scanner scan = new Scanner(file);
        List<String> words = new ArrayList<>();

        for (int i = 0; scan.hasNextLine(); i++) {  //For loop to add all words in file to a list
            words.add(i, scan.nextLine());
        }
        return words.get(WordGeneration.Random(words.size()-1));
    }

    public static char[] SplitWord(String randomWord) {  //Turns Random word string into an array of characters that is easier to work with
        return ConvertWord(randomWord).toCharArray();
    }

    private static String ConvertWord(String randomWord) {
        String upperWord;
        upperWord = randomWord.toUpperCase();
        return upperWord;
    }

    public static boolean Check(char[] splitWord, char userGuess) {  //Method to check if the users guess is correct
        boolean test = false;
        for (int i : splitWord) {  //for each loop to loop through elements in split word array
            if (i == userGuess) {
                test = true;
                break;
            }
        }
        return test;  //Returns boolean of whether users guess was correct or not
    }

    public static char[] CorrectGuess(char userGuess, char[] splitWord, char[] playerGuesses) {  //This method takes the current user guess and array of previous correct guesses
        for (int i = 0; i < splitWord.length; i++) {
            if (userGuess == splitWord[i]) { //If userGuess is the same as a letter in the word then the _ is changed to that letter
                playerGuesses[i] = userGuess;  //Sets the _ to that letter
            }
        }
        return playerGuesses;
    }
}