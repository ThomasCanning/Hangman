import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class GUI extends JFrame {

    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;
    public static final int INCORRECT_GUESSES_LIMIT = 6;
    public static final int MAX_WORD_LENGTH = 30;

    public static int gamesWon = 0;//
    public static int highScore;
    private char playerGuess;
    private static boolean correctlyGuessed;
    private static char[] splitWord;
    private static char[] playerGuesses;
    public static int incorrectGuesses = 0;//
    private static String word;

    public static char gameMode;
    public static int playerTurn;
    public static String player;
    public static String winner;

    public static Boolean player1Correct = false;
    public static Boolean player2Correct = false;

    //creates GUI elements
    StartScreen startScreen = new StartScreen();
    MainScreen mainScreen = new MainScreen();
    SingleplayerEndScreen singleplayerEndScreen = new SingleplayerEndScreen();
    ChoseWordScreen chooseWordScreen = new ChoseWordScreen();
    MultiplayerEndScreen multiplayerEndScreen = new MultiplayerEndScreen();
    JPanel panelContent = new JPanel();  //Creates a panel to hold sub panels
    CardLayout cl = new CardLayout();   //creates a card layout
    ImageIcon taskbarImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("TaskBarImage.png")));

    public GUI() throws IOException {

        highScore = ReadHighscoreFile();

        //Sets up card layout that allows different panels to be swapped (game screens)
        panelContent.setLayout(cl);
        panelContent.add(startScreen, "1");
        panelContent.add(mainScreen, "2");
        panelContent.add(singleplayerEndScreen, "3");
        panelContent.add(chooseWordScreen, "4");
        panelContent.add(multiplayerEndScreen, "5");
        cl.show(panelContent, "1");

        //Sets up the details of the main frame
        this.setTitle("Hangman");
        this.setIconImage(taskbarImage.getImage());//Sets this image to Image
        this.getContentPane().setBackground(new Color(237, 244, 237)); //Changes background color
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.add(panelContent, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sets what happens when X is pressed (closes application)
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        //What happens when various buttons are pressed:-------------------

        //Start Screen-----------
        //Defines what happens when Singleplayer button pressed
        startScreen.startSinglePlayerButton.addActionListener(e -> {
            gameMode = 's';
            try {
              HangmanRound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            cl.show(panelContent, "2");
        });

        //Main Screen-----------------
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
                        //What happens when fail
                        if (incorrectGuesses == INCORRECT_GUESSES_LIMIT) {
                            IncorrectGuess();
                        }
                    }
                }
            });
        }

        //main screen quit button
        mainScreen.quitRoundButton.addActionListener(e -> {
            cl.show(panelContent, "3");
            mainScreen.bottomPanel.remove(mainScreen.nextRoundButton);
            mainScreen.bottomPanel.remove(mainScreen.quitRoundButton);
        });

        //main screen next round button
        mainScreen.nextRoundButton.addActionListener(e -> {
            mainScreen.bottomPanel.remove(mainScreen.nextRoundButton);
            mainScreen.bottomPanel.remove(mainScreen.quitRoundButton);
            ShowKeyboard();
            cl.show(panelContent, "2");
            mainScreen.UpdateHangman(0);

            try {
                HangmanRound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        //Single player end screen---------
        //Single player end screen quit game button
        singleplayerEndScreen.quitGameButton.addActionListener(e -> System.exit(0));

        //Single player end screen play again button
        singleplayerEndScreen.newGameButton.addActionListener(e -> {
            gamesWon = 0;
            mainScreen.UpdateHangman(0);
            cl.show(panelContent, "2");
            ShowKeyboard();

            try {
               HangmanRound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        //End screen multiplayer button that changes game mode
        singleplayerEndScreen.multiplayerButton.addActionListener(e -> {
            chooseWordScreen.ResetScreen();
            cl.show(panelContent, "4");
            mainScreen.UpdateHangman(0);
            ShowKeyboard();
        });

        //Multiplayer

        //Defines what happens when Multiplayer button pressed
        startScreen.startMultiPlayerButton.addActionListener(e -> {
            chooseWordScreen.ResetScreen();
            cl.show(panelContent, "4");

        });

        //removes "Enter Word:" from textbox when clicked
        chooseWordScreen.enterWord.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                chooseWordScreen.enterWord.setText("");
                chooseWordScreen.enterWord.setEchoChar('*');
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        //Makes sure only letters are typed
        chooseWordScreen.enterWord.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char character = e.getKeyChar();
                if (!Character.isLetter(character)) {
                    e.setKeyChar(Character.MIN_VALUE);
                }
            }
        });

        //Submit button
        chooseWordScreen.submit.addActionListener(e -> {
            word = String.valueOf(chooseWordScreen.enterWord.getPassword());
            try {
                if (word.isEmpty() || !WordGeneration.CheckValidWord(word)) {
                    //ensures valid word is entered so makes user enter another word
                    chooseWordScreen.ResetEnterWord();
                }

                else {
                    splitWord = WordGeneration.SplitWord(word);  //Runs method that split word into an array of characters
                    mainScreen.SetWord(word);

                    int wordLength = splitWord.length;  //Stores length of word as a variable

                    //Sets up an array of underscores for each of the characters in the word, and leaves gaps for spaces
                    playerGuesses = new char[wordLength];
                    for (int i = 0; i < wordLength; i++) {
                        if (splitWord[i] == '\'') playerGuesses[i] = '\'';
                        else if (splitWord[i] == ',') playerGuesses[i] = ',';
                        else playerGuesses[i] = ' ';
                    }
                    mainScreen.DrawWordDisplay(playerGuesses);
                    try {
                        HangmanRound();
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                    cl.show(panelContent, "2");
                }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        //next players turn
        mainScreen.nextPlayerButton.addActionListener(e -> {
            mainScreen.nextPlayerButton.setVisible(false);
            ShowKeyboard();
            mainScreen.UpdateHangman(0);
            chooseWordScreen.enterWord.setText("Enter Word: ");
            cl.show(panelContent, "4");
            });

        //multiplayer end screen singleplayer button that changes gamemode
        multiplayerEndScreen.singlePlayerButton.addActionListener(e -> {
            gameMode = 's';
            mainScreen.UpdateHangman(0);
            ShowKeyboard();
            incorrectGuesses = 0;
            try {
                HangmanRound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            cl.show(panelContent, "2");
        });

        //multiplayer end screen play again
        multiplayerEndScreen.newGameButton.addActionListener(e -> {
            cl.show(panelContent, "4");
            mainScreen.UpdateHangman(0);
            chooseWordScreen.ResetScreen();
            ShowKeyboard();
        });

        //multiplayer end screen quit game
        multiplayerEndScreen.quitGameButton.addActionListener(e -> System.exit(0));
    }

    private void HangmanRound() throws FileNotFoundException {

        mainScreen.bottomPanel.setPreferredSize(new Dimension(1000, 320));
        incorrectGuesses = 0;
        correctlyGuessed = false;
        if (gameMode == 's') {word = WordGeneration.Generate();}  //Generates a random word from text file (TEXT FILE IS A PLACEHOLDER)
            splitWord = WordGeneration.SplitWord(word);  //Runs method that split word into an array of characters
            mainScreen.SetWord(word);
            System.out.println("Word is: " + word);

            int wordLength = splitWord.length;  //Stores length of word as a variable

            //Sets up an array of underscores for each of the characters in the word, and leaves gaps for spaces
            playerGuesses = new char[wordLength];
            for (int i = 0; i < wordLength; i++) {
                if (splitWord[i] == '\'') playerGuesses[i] = '\'';
                else if (splitWord[i] == ',') playerGuesses[i] = ',';
                else playerGuesses[i] = ' ';
            }
            mainScreen.DrawWordDisplay(playerGuesses);
    }

    private void HangmanGuess() {

        //--------------------------------------System that lets user guess and deals with incorrect/correct guesses-----------------------------------

        if ((incorrectGuesses < INCORRECT_GUESSES_LIMIT) && (!correctlyGuessed)) {//Loop until Hangman is hung or word is correctly guessed

            playerGuess = Character.toUpperCase(playerGuess);  //Converting user input to upper case

            if (WordGeneration.Check(splitWord, playerGuess)) {//checks if player guess was correct
                WordGeneration.CorrectGuess(playerGuess, splitWord, playerGuesses);//Updates playerGuesses array with new guess
                mainScreen.DrawWordDisplay(playerGuesses);

                //What happens when word is correctly guessed:
                if (Arrays.equals(splitWord, playerGuesses)) {
                    CorrectGuess();
                }
            }
            else {
                incorrectGuesses++;
                mainScreen.UpdateHangman(incorrectGuesses);
            }
        }
        else {
            mainScreen.UpdateHangman(incorrectGuesses);
        }

    }

    //What happens when an incorrect guess is made
    private void IncorrectGuess() {

        HideKeyboard();
        if (gameMode=='s') {
            mainScreen.bottomPanel.add(mainScreen.quitRoundButton);
        }
        else if (gameMode=='m') {
            if (playerTurn == 1) {
                System.out.println("1");
                player = "Player 2";
                playerTurn = 2;
                //next round
                mainScreen.SetNextPlayerButton(player);
                mainScreen.bottomPanel.add(mainScreen.nextPlayerButton);
                mainScreen.nextPlayerButton.setVisible(true);
            }
            else if (playerTurn == 2) {
                if(player2Correct && !player1Correct) {
                    winner = "Player 2";
                    cl.show(panelContent, "5");
                }
                player = "Player 1";
                playerTurn = 1;
            }
        }
    }

    //What happens when a correct guess is made
    private void CorrectGuess() {
        //What happens in Singleplayer if correct
        if (gameMode == 's') {
            gamesWon++;
            mainScreen.UpdateHangman(-1);
            mainScreen.bottomPanel.add(mainScreen.nextRoundButton);
            mainScreen.bottomPanel.add(mainScreen.quitRoundButton);
            mainScreen.bottomPanel.setPreferredSize(new Dimension(1000, 270));
            //What happens if new highscore
        }

        //What happens in multiplayer when correct
        else if (gameMode == 'm') {
            if (playerTurn == 1) {
                System.out.println("player1 turn");
                chooseWordScreen.ResetEnterWord();
                player2Correct = true;
                player = "Player 2";
                playerTurn = 2; }
            else if (playerTurn == 2) {
                chooseWordScreen.ResetEnterWord();
                player1Correct = true;
                player = "Player 1";
                playerTurn = 1;}
            //player 1 wins
            if(player1Correct && !player2Correct) {
                winner = "Player 1";
                cl.show(panelContent, "5");
            }
            //Draw
            else if(player1Correct&&player2Correct) {
                winner = "Draw";
                cl.show(panelContent, "5");
            }
            //next round, only player 2 has guessed so far
            else {
                mainScreen.SetNextPlayerButton(player);
                mainScreen.bottomPanel.add(mainScreen.nextPlayerButton);
                mainScreen.nextPlayerButton.setVisible(true);
            }

        }
        HideKeyboard();
    }

    private void ShowKeyboard() {
        for (int i = 0; i < mainScreen.keyboardButtons.length; i++) {
            mainScreen.keyboardButtons[i].setVisible(true);
            mainScreen.keyboardButtons[i].setEnabled(true);
        }
        for (int i2 = 0; i2 < mainScreen.blankButtons.length; i2++) {
            mainScreen.blankButtons[i2].setVisible(true);
        }
    }

    private void HideKeyboard() {
        for (int i = 0; i < mainScreen.keyboardButtons.length; i++) {
            mainScreen.keyboardButtons[i].setVisible(false);
        }
        for (int i = 0; i < mainScreen.blankButtons.length; i++) {
            mainScreen.blankButtons[i].setVisible(false);
        }
    }

    public static String GetGamesWon() {
        return String.valueOf(gamesWon);
    }

    public static String GetHighScore() {
        return String.valueOf(highScore);
    }

    public static void WriteToHighscoreFile(int highScore) {
        try {
            FileWriter highscoreWriter = new FileWriter("res/highscores.txt");
            highscoreWriter.write(highScore + "");
            highscoreWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int ReadHighscoreFile() throws IOException {
        int data = 0;
        try {
            File highscoreFile = new File("res/highscores.txt");
            highscoreFile.createNewFile();
            Scanner highscoreReader = new Scanner(highscoreFile);
            if(highscoreReader.hasNextLine()) data = Integer.parseInt(highscoreReader.nextLine());
            highscoreReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            data = highScore;
        }
        return data;
    }
}


