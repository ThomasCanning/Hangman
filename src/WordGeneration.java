import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordGeneration {  //This class has a method that can be called which generates a Random word from the .txt file "word_library.txt"

    //Creates an int that is half the length of the word_library text file
    private static int pastWordsLength;
    static {
        try {
            pastWordsLength = (int) ((ReadWordList().toArray().length)*0.75);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final String[] pastWords = new String[pastWordsLength];
    private static int pastWordsIndex = 0;

    //Reads words from word_library text file
    private static List<String> ReadWordList() throws FileNotFoundException {
        File file = new File("res/word_library.txt");  //Reading file with words in
        Scanner scan = new Scanner(file);
        List<String> words = new ArrayList<>();

        for (int i = 0; scan.hasNextLine(); i++) {  //For loop to add all words in file to a list
            words.add(i, scan.nextLine());
        }
        return words;
    }

    //Used to check if word has come up recently
    private static boolean CheckIfPastWord(String generatedWord) {
        for (int i = 0; i < WordGeneration.pastWords.length; i++) {
            if ((generatedWord.equals(WordGeneration.pastWords[i])) && (WordGeneration.pastWords[i] != null)) {
                return (false);
            }
        }
        return true; //returns true if the word is not a past word
    }

    //-----------
    //Following methods are used to generate words and check the player guesses during the hangman rounds
    private static int Random(int listSize) {  //Method for generating Random number between 0 and list length
        return ((int) (Math.random() * (listSize + 1)));
    }

    public static String Generate() throws FileNotFoundException {
        List<String> words = ReadWordList();
        String generatedWord;
        while (true) {
            generatedWord = words.get(WordGeneration.Random(words.size()-1));
            if ((generatedWord.length()<=GUI.MAX_WORD_LENGTH)&&(CheckIfPastWord(generatedWord))){
                pastWords[pastWordsIndex] = generatedWord;
                if (pastWordsIndex<pastWords.length-1) pastWordsIndex++;
                else pastWordsIndex = 0;
                pastWords[pastWordsIndex] = null;
                return generatedWord;
            }
        }

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

    public static void CorrectGuess(char userGuess, char[] splitWord, char[] playerGuesses) {  //This method takes the current user guess and array of previous correct guesses
        for (int i = 0; i < splitWord.length; i++) {
            if (userGuess == splitWord[i]) { //If userGuess is the same as a letter in the word then the _ is changed to that letter
                playerGuesses[i] = userGuess;  //Sets the _ to that letter
            }
        }
    }
}