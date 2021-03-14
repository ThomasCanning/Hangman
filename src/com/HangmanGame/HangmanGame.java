package com.HangmanGame;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*THIS IS THE CURRENT MAIN GAME CLASS WHICH RUNS WHEN THE GAME IS RAN, MOST OF IT IS TEMPORARY AS THE
FINAL PROJECT WILL MAKE USE OF A GUI. THIS IS MOSTLY PRACTISE/WORKING OUT HOW WE CAN DO THINGS.

S0 FAR IMPLEMENTED A SIMPLE VERSION OF THE GAME THAT WORKS IN THE RUN WINDOW, GENERATING A RANDOM WORD FROM 1 PLACEHOLDER TEXT FILE OF WORDS,
AND ALLOWING THE USER TO GUESS A LETTER EACH GO IN THE RUN WINDOW. THE USER HAS 8 INCORRECT LETTER GUESSES TO REPRESENT THE WORD. SUCCESSFULLY GUESSING THE
WORD WILL PRINT TRUE, NOT GUESSING THE WORD AFTER 7 INCORRECT GUESSES WILL PRINT FALSE. OUT PRINTS AFTER THE GUESSING FOR LOOP FINISHES. THIS BASIC VERSION
OF THE GAME WORKS FOR 1 ROUND. THE RANDOM WORD IS PRINTED OUT AT THE START FOR TESTING PURPOSES. THE TEXT FILE word_library IS A PLACEHOLDER AND CURRENTLY
CONSISTS OF A LIST OF COUNTRIES AS AN EXAMPLE

*/

class HangmanGame {
    public static void main(String[] args) throws FileNotFoundException {
        Intro.Welcome();   //This runs a method that prints out temporary intro text

//------------------------------------------Setting up and generating random word-------------------------------------------------
        String randomWord = WordGeneration.generate();  //Generates a random word from text file (TEXT FILE IS A PLACEHOLDER)
        //System.out.println(randomWord);
        char[] splitWord = WordGeneration.splitWord(randomWord);  //Runs method that split word into an array of characters

        int wordLength = splitWord.length;  //Stores length of word as a variable

        char[] playerGuesses = new char[wordLength];  //For loop to create an array of _ for each character in splitWord to be used for user guesses
        for (int i = 0; i < wordLength; i++) {
            if (splitWord[i] == ' ') playerGuesses[i] = ' ';
            else if (splitWord[i] == '\'') playerGuesses[i] = '\'';
            else playerGuesses[i] = '_';
            System.out.print(playerGuesses[i]);
        }

//--------------------------------------System that lets user guess and deals with incorrect/correct guesses------------------------------------
        boolean correctlyGuessed = false;

        for (int incorrectGuesses = 0; (incorrectGuesses < 7) && (!correctlyGuessed); ) {//Loop until Hangman is hung or word is correctly guessed

            Scanner input = new Scanner(System.in);  //Taking user input for a character
            char userGuess = (input.next().charAt(0));  //Saving user input as a variable
            userGuess = Character.toUpperCase(userGuess);  //Converting user input to upper case

            if (WordGeneration.check(splitWord, userGuess)) {//If guessed letter is in word then sets the corresponding "_"'s to the correct letter
                System.out.println("CORRECT GUESS");
                playerGuesses = WordGeneration.correctGuess(userGuess, splitWord, playerGuesses);
                for (char playerGuess : playerGuesses) { //For each loop to cycle through playerGuesses array
                    System.out.print(playerGuess);  //Prints out the word with correctly guessed "_" filled in
                }
                correctlyGuessed = Arrays.equals(splitWord, playerGuesses);  //Checks if word has been guessed
            }
            else {
                incorrectGuesses++;
                System.out.println("You have made " + incorrectGuesses + " incorrect guesses");  //Increments incorrect guesses counter by 1, will be used to draw Hangman in GUI
                System.out.println("INCORRECT GUESS");//PLACEHOLDER for GUI hangman being drawn
            }
        }
            System.out.println();
            System.out.println(correctlyGuessed);  //TEST to see if word has been guessed
            System.out.println("OUT");  //TEST to see if for loop has ended
        }
    }