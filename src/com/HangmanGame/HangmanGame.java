package com.HangmanGame;

import java.io.FileNotFoundException;
import java.util.Scanner;

/*THIS IS THE CURRENT MAIN GAME CLASS WHICH RUNS WHEN THE GAME IS RAN, MOST OF IT IS TEMPORARY AND IS PRACTISE AS THE
FINAL PROJECT WILL MAKE USE OF A GUI. THIS IS MOSTLY PRACTISE/WORKING OUT HOW WE CAN DO THINGS.

CURRENTLY WORKING ON THE SYSTEM FOR CORRECT GUESSES AND LETTING USER KNOW WHICH LETTERS ARE YET TO BE CORRECTLY GUESSES
 */



class HangmanGame {
    public static void main(String[] args) throws FileNotFoundException {
        //Intro.Welcome();   //This runs a method that prints out temporary intro text

//------------------------------------------Setting up and generating random word-------------------------------------------------
        String randomWord = WordGeneration.generate();  //Generates a random word from text file (TEXT FILE IS A PLACEHOLDER)
        System.out.println(randomWord);
        char[] splitWord = WordGeneration.splitWord(randomWord);  //Runs method that split word into an array of characters

        int wordLength = splitWord.length;  //Stores length of word as a variable

        char[] playerGuesses = new char[wordLength];  //For loop to create an array of _ for each character in splitWord to be used for user guesses
        for (int i=0; i< wordLength; i++) {
            playerGuesses[i] = '_';
            System.out.print(playerGuesses[i]);
        }

//--------------------------------------System that lets user guess and deals with incorrect/correct guesses------------------------------------
        boolean correctlyGuessed = false;

        for(int incorrectGuesses = 0 ; (incorrectGuesses < 8) || (correctlyGuessed == false);) {//Loop until correctly guessed or Hangman is hung

            Scanner input = new Scanner(System.in);  //Taking user input for a character
            char userGuess = (input.next().charAt(0));  //Saving user input as a variable
            userGuess = Character.toUpperCase(userGuess);  //Converting user input to upper case

            if (WordGeneration.check(splitWord, userGuess) == true) {//If guessed letter is in word then sets the corresponding "_"'s to the correct letter
                System.out.println("TRUE");
                playerGuesses = WordGeneration.correctGuess(userGuess, splitWord, playerGuesses);
                for (int i = 0; i < playerGuesses.length; i++) {
                    System.out.print(playerGuesses[i]);  //Prints out the word with correctly guessed "_" filled in
                }
//WORK IN PROGRESS
                //correctlyGuessed = true;
                //System.out.println(correctlyGuessed);
            }
            else {
                incorrectGuesses++;  //Increments incorrect guesses counter by 1, will be used to draw Hangman in GUI
                System.out.println(correctlyGuessed);
            }
            }

        System.out.println("OUT");
        }
    }
