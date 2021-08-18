import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        panelContent.add(endScreen, "4");
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
                        if(incorrectGuesses == INCORRECT_GUESSES_LIMIT) {
                            for (int i2 = 0; i2 < mainScreen.keyboardButtons.length; i2++) {
                                mainScreen.keyboardButtons[i2].setVisible(false);
                            }
                            for(int i2 = 0; i2 < mainScreen.blankButtons.length; i2++) {
                                mainScreen.blankButtons[i2].setVisible(false);
                            }

                            mainScreen.bottomPanel.add(mainScreen.endRoundButton);
                        }
                    }
                }
            });
        }

        //Continue button
        continueScreen.continueButton.addActionListener(e -> {
            try {
                cl.show(panelContent, "2");
                System.out.println("new round");
                //Resets keyboard
                for (int i = 0; i < mainScreen.keyboardButtons.length; i++) {
                    mainScreen.keyboardButtons[i].setEnabled(true);
                }
                mainScreen.UpdateHangman(0);
                HangmanRound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        //main screen endRoundButton
        mainScreen.endRoundButton.addActionListener(e -> {
            cl.show(panelContent, "4");
        });

        //Continue screen end round button
        continueScreen.endRoundButton.addActionListener(e -> {
            cl.show(panelContent, "4");
        });

        //end screen quit game button
        endScreen.quitGameButton.addActionListener(e -> {
            System.exit(0);
        });

        //end screen play again button
        endScreen.newGameButton.addActionListener(e -> {
            cl.show(panelContent, "2");
            mainScreen.bottomPanel.remove(mainScreen.endRoundButton);
            for (int i2 = 0; i2 < mainScreen.keyboardButtons.length; i2++) {
                mainScreen.keyboardButtons[i2].setVisible(true);
                mainScreen.keyboardButtons[i2].setEnabled(true);
            }
            for(int i2 = 0; i2 < mainScreen.blankButtons.length; i2++) {
                mainScreen.blankButtons[i2].setEnabled(true);
            }


            try {
                HangmanRound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
    }

    public void HangmanRound() throws FileNotFoundException {

        incorrectGuesses = 0;
        correctlyGuessed = false;
        String randomWord = WordGeneration.Generate();  //Generates a random word from text file (TEXT FILE IS A PLACEHOLDER)
        splitWord = WordGeneration.SplitWord(randomWord);  //Runs method that split word into an array of characters
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

            if (WordGeneration.Check(splitWord, playerGuess)) {//checks if player guess was correct
                playerGuesses = WordGeneration.CorrectGuess(playerGuess, splitWord, playerGuesses); //Updates playerGuesses array with new guess
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

