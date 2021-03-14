package com.HangmanGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//THIS CLASS IS NOT FINAL AND MAY HAVE QUALITY FEATURES SUCH AS CATEGORY SELECTION ADDED AT A LATER POINT

public class WordGeneration {  //This class has a method that can be called which generates a random word from the .txt file "word_library.txt"

    private static int random(int listSize){  //Method for generating random number between 0 and list length
        int randomNumber = (int) (Math.random() * (listSize+1));
        return randomNumber;
    }

    public static String generate() throws FileNotFoundException {
        File file = new File("src/com/HangmanGame/word_library.txt");  //reading file with words in
        Scanner scan = new Scanner(file);
        List<String> words = new ArrayList<String>();

        for(int i = 0; scan.hasNextLine(); i++) {  //For loop to add all words in file to a list
            words.add(i, scan.nextLine());
        }

        String randomWord = words.get(WordGeneration.random(words.size())); //Using random method to get a random word from the list of words

        return randomWord;
    }

    public static char[] splitWord(String randomWord){
        char[] splitWord = convertWord(randomWord).toCharArray();
        return splitWord;
    }


    private static String convertWord(String randomWord) {
        String upperWord = "";
            upperWord = randomWord.substring(0 ,randomWord.length()).toUpperCase();
        return upperWord;
    }

    public static boolean check(char[] splitWord, char userGuess) {
        boolean test = false;
        for (int i : splitWord) {
            if (i == userGuess) {
                test = true;
                break;
            }
        }
        return test;
    }
    //WORK IN PROGRESS
    public static char[] correctGuess(char userGuess, char[] playerGuesses) {  //This method takes the current user guess and array of previous correct guesses
        char[] newPlayerGuesses = new char[playerGuesses.length];  //
        for (int i = 0; i<playerGuesses.length; i++) {
            if (userGuess == playerGuesses[i]) {
                newPlayerGuesses[i] = userGuess;
                System.out.println(userGuess);
                System.out.println(newPlayerGuesses[i]);
            }
        }
        return newPlayerGuesses;
    }
}