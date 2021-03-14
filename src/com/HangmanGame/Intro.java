package com.HangmanGame;

public class Intro {  //THIS CLASS IS TEMPORARY AND WILL BE TURNED INTO A GUI AT A LATER POINT
        public static void Welcome(){
            System.out.println("Welcome to Hangman");
            System.out.println("You will be presented with a random word or phrase and told the layout and how many letters are in the word.");
            System.out.println("You will then have to guess the word by guessing what letters are in the word. For each correct guess you will ");
            System.out.println("be told all positions of that letter in the word. For each incorrect guess, a new piece of the Gallows and the ");
            System.out.println("Hangman will be constructed. After 7 incorrect guesses the man will be hung will and you will lose.");
            System.out.println("Good Luck!");
            System.out.println();
        }
}
