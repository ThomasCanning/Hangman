package com.HangmanGame;

import java.io.FileNotFoundException;
import java.util.Scanner;

class HangmanGame {
    public static void main(String[] args) throws FileNotFoundException {
        //Intro.Welcome();   //This runs a method that prints out temporary intro text

        String randomWord = WordGeneration.generate();  //Generates a random word from text file
        System.out.println(randomWord);
        char[] splitWord = WordGeneration.splitWord(randomWord);  //Runs method that split word into an array of characters
        System.out.println(splitWord[3]);

        int wordLength = splitWord.length;  //Stores length of word as a variable

        boolean correctlyGuessed = false;

        for(int incorrectGuesses = 0 ; (incorrectGuesses < 8) || (correctlyGuessed == true);) {

            Scanner input = new Scanner(System.in);  //Taking user input for a character
            char userGuess = (input.next().charAt(0));
            userGuess = Character.toUpperCase(userGuess);  //Converting user input to upper case

            if (WordGeneration.check(splitWord, userGuess) == true) {
                System.out.println("TRUE");
                //DO SOMETHING TO KEEP TRACK OF CORRECTLY GUESSED LETTERS
            }
            else {
                incorrectGuesses++;
                System.out.println(incorrectGuesses);
                System.out.println(correctlyGuessed);
            }
            }
        System.out.println("OUT");
        }
    }
