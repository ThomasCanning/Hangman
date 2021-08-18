import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class GUI extends JFrame {

    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;
    public static final int INCORRECT_GUESSES_LIMIT = 6;

    private static int gamesWon = 0;
    private char playerGuess;
    public static boolean correctlyGuessed;
    private static char[] splitWord;
    private static char[] playerGuesses;
    private static int incorrectGuesses = 0;

    StartScreen startScreen = new StartScreen(this);
    MainScreen mainScreen = new MainScreen(this);
    ContinueScreen continueScreen = new ContinueScreen(this);
    EndScreen endScreen = new EndScreen(this);

    JPanel panelContent = new JPanel();  //Creates a panel to hold sub panels
    CardLayout cl = new CardLayout();   //creates a card layout

    //creates GUI elements
    ImageIcon taskbarImage = new ImageIcon(getClass().getClassLoader().getResource("TaskBarImage.png"));

    public GUI() throws IOException {

        //Sets up card layout that allows different panels to be swapped (game screens)
        panelContent.setLayout(cl);
        panelContent.add(startScreen, "1");
        panelContent.add(mainScreen, "2");
        panelContent.add(continueScreen, "3");
        cl.show(panelContent, "1");

        //Sets up the details of the main frame
        this.setTitle("Hangman");
        this.setIconImage(taskbarImage.getImage());//Sets this image to Image
        this.getContentPane().setBackground(new Color(237, 244, 237)); //Changes background color
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.add(panelContent);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sets what happens when X is pressed (closes application)
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        //Defines what happens when start button pressed
        startScreen.startButton.addActionListener(e -> {
            try {
                HangmanRound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            cl.show(panelContent, "2");
        });

        //Defines what happens when keyboard is pressed
        for (int i = 0; i < mainScreen.keyboardButtons.length; i++) {
            mainScreen.keyboardButtons[i].addActionListener(e -> {
                String keyboardInput = e.getActionCommand();

                //This character is player input
                playerGuess = keyboardInput.charAt(0);

                for (int i1 = 0; i1 < mainScreen.keyboardButtons.length; i1++) {
                    if (mainScreen.keyboardButtons[i1] == e.getSource()) {
                        mainScreen.keyboardButtons[i1].setEnabled(false);
                        HangmanGuess();
                    }
                }
            });
        }

        //Continue button
        continueScreen.continueButton.addActionListener(e -> {
            try {
                //Resets buttons
                for (int i = 0; i < mainScreen.keyboardButtons.length; i++) {
                    mainScreen.keyboardButtons[i].setEnabled(true);
                }
                HangmanRound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            cl.show(panelContent, "2");
        });
    }

    public void HangmanRound() throws FileNotFoundException {

        incorrectGuesses = 0;
        correctlyGuessed = false;
        String randomWord = WordGeneration.generate();  //Generates a random word from text file (TEXT FILE IS A PLACEHOLDER)
        splitWord = WordGeneration.splitWord(randomWord);  //Runs method that split word into an array of characters
        mainScreen.SetWord(randomWord);
        System.out.println(randomWord);

        int wordLength = splitWord.length;  //Stores length of word as a variable

        //Sets up an array of underscores for each of the characters in the word, and leaves gaps for spaces
        playerGuesses = new char[wordLength];
        for (int i = 0; i < wordLength; i++) {
            if (splitWord[i] == '\'') playerGuesses[i] = '\'';
            else playerGuesses[i] = ' ';
        }
        mainScreen.DrawWordDisplay(playerGuesses);
    }

    public void HangmanGuess() {

        //--------------------------------------System that lets user guess and deals with incorrect/correct guesses-----------------------------------

        if ((incorrectGuesses < INCORRECT_GUESSES_LIMIT) && (!correctlyGuessed)) {//Loop until Hangman is hung or word is correctly guessed

            playerGuess = Character.toUpperCase(playerGuess);  //Converting user input to upper case

            if (WordGeneration.check(splitWord, playerGuess)) {//checks if player guess was correct
                playerGuesses = WordGeneration.correctGuess(playerGuess, splitWord, playerGuesses); //Updates playerGuesses array with new guess
                mainScreen.DrawWordDisplay(playerGuesses);
                correctlyGuessed = Arrays.equals(splitWord, playerGuesses);  //Checks if word has been guessed
                if (correctlyGuessed) {
                    gamesWon++;
                    continueScreen.DisplayGamesWon();
                    cl.show(panelContent, "3");
                }
            }
            else{
                incorrectGuesses++;
                mainScreen.UpdateHangman(incorrectGuesses);
            }
        }
        else {
            mainScreen.UpdateHangman(incorrectGuesses);
        }

    }
    public static String GetGamesWon() {
        return new String(String.valueOf(gamesWon));
    }
}

