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

        String randomWord = WordGeneration.generate();  //Generates a random word from text file
        System.out.println(randomWord);
        char[] splitWord = WordGeneration.splitWord(randomWord);  //Runs method that split word into an array of characters
        char[] playerGuesses = new char[splitWord.length];

        int wordLength = splitWord.length;  //Stores length of word as a variable

        boolean correctlyGuessed = false;

        for(int incorrectGuesses = 0 ; (incorrectGuesses < 8) || (correctlyGuessed == true);) {

            Scanner input = new Scanner(System.in);  //Taking user input for a character
            char userGuess = (input.next().charAt(0));
            userGuess = Character.toUpperCase(userGuess);  //Converting user input to upper case

            if (WordGeneration.check(splitWord, userGuess) == true) {
                System.out.println("TRUE");

                //DO SOMETHING TO KEEP TRACK OF CORRECTLY GUESSED LETTERS //WORK IN PROGRESS
                /* char[] newPlayerGuesses = WordGeneration.correctGuess(userGuess, playerGuesses);
                for (int i = 0; i < newPlayerGuesses.length; i++) {
                    System.out.println(newPlayerGuesses[i]);
                } */

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
