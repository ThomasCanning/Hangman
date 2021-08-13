import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//THIS CLASS IS NOT FINAL AND MAY HAVE QUALITY FEATURES SUCH AS CATEGORY SELECTION ADDED AT A LATER POINT

public class WordGeneration {  //This class has a method that can be called which generates a random word from the .txt file "word_library.txt"

    private static int random(int listSize) {  //Method for generating random number between 0 and list length
        return ((int) (Math.random() * (listSize + 1)));
    }

    public static String generate() throws FileNotFoundException {
        File file = new File("src/word_library.txt");  //Reading file with words in
        Scanner scan = new Scanner(file);
        List<String> words = new ArrayList<>();

        for (int i = 0; scan.hasNextLine(); i++) {  //For loop to add all words in file to a list
            words.add(i, scan.nextLine());
        }
        return words.get(WordGeneration.random(words.size()));
    }

    public static char[] splitWord(String randomWord) {  //Turns random word string into an array of characters that is easier to work with
        return convertWord(randomWord).toCharArray();
    }

    private static String convertWord(String randomWord) {
        String upperWord;
        upperWord = randomWord.toUpperCase();
        return upperWord;
    }

    public static boolean check(char[] splitWord, char userGuess) {  //Method to check if the users guess is correct
        boolean test = false;
        for (int i : splitWord) {  //for each loop to loop through elements in split word array
            if (i == userGuess) {
                test = true;
                break;
            }
        }
        return test;  //Returns boolean of whether users guess was correct or not
    }

    public static char[] correctGuess(char userGuess, char[] splitWord, char[] playerGuesses) {  //This method takes the current user guess and array of previous correct guesses
        for (int i = 0; i < splitWord.length; i++) {
            if (userGuess == splitWord[i]) { //If userGuess is the same as a letter in the word then the _ is changed to that letter
                playerGuesses[i] = userGuess;  //Sets the _ to that letter
            }
        }
        return playerGuesses;
    }
}