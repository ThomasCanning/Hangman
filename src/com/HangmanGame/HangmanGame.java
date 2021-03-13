package com.HangmanGame;

import java.io.FileNotFoundException;
import java.util.Scanner;

class HangmanGame {
    public static void main(String[] args) throws FileNotFoundException {
        //Intro.Welcome();   //This runs a method that prints out temporary intro text

        String randomWord = WordGeneration.generate();  //Generates a random word from text file
        char[] splitWord = WordGeneration.splitWord(randomWord);  //Runs method that split word into an array of characters

        int wordLength = splitWord.length;  //Stores length of word as a variable

        boolean correctlyGuessed = false;

        for(int incorrectGuesses = 0 ; (incorrectGuesses <= 8) || (correctlyGuessed = true); incorrectGuesses ++) {

            Scanner input = new Scanner(System.in);  //Taking user input for a character
            char userGuess = (input.next().charAt(0));
            userGuess = Character.toUpperCase(userGuess);  //Converting user input to upper case



            //something
            //incorrectGuesses ++;

            //something
            //correctlyGuessed = true;
        }
    }
}
