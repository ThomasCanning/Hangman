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

    public static int gamesWon = 0;
    private static int highScore;
    private char playerGuess;
    private static boolean correctlyGuessed;
    private static char[] splitWord;
    private static char[] playerGuesses;
    private static int incorrectGuesses = 0;
    private static String word;

    public static char gameMode;
    public static int playerTurn;
    public static String player;
    public static String winner;

    private static Boolean player1Correct = false;
    private static Boolean player2Correct = false;

    StartScreen startScreen = new StartScreen();
    MainScreen mainScreen = new MainScreen();
    SingleplayerEndScreen singleplayerEndScreen = new SingleplayerEndScreen();
    ChoseWordScreen chooseWordScreen = new ChoseWordScreen();
    MultiplayerEndScreen multiplayerEndScreen = new MultiplayerEndScreen();

    JPanel panelContent = new JPanel();  //Creates a panel to hold sub panels
    CardLayout cl = new CardLayout();   //creates a card layout

    //creates GUI elements
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

        //Defines what happens when keyboard is pressed
        for (int i = 0; i < mainScreen.keyboardButtons.length; i++) {
            mainScreen.keyboardButtons[i].addActionListener(e -> {
                String keyboardInput = e.getActionCommand();

                //This character is player input
                playerGuess = keyboardInput.charAt(0);

                for (int i1 = 0; i1 < mainScreen.keyboardButtons.length; i1++) {
                    if (mainScreen.keyboardButtons[i1] == e.getSource()) {
                        mainScreen.keyboardButtons[i1].setEnabled(false);
                        try {
                            HangmanGuess();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        //What happens when fail
                        if (incorrectGuesses == INCORRECT_GUESSES_LIMIT) {

                            for (int i2 = 0; i2 < mainScreen.keyboardButtons.length; i2++) {
                                mainScreen.keyboardButtons[i2].setVisible(false);
                             }
                                for (int i2 = 0; i2 < mainScreen.blankButtons.length; i2++) {
                                    mainScreen.blankButtons[i2].setVisible(false);
                            }
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
                                        if(player2Correct && player1Correct==false) {
                                            winner = "Player 2";
                                            cl.show(panelContent, "5");
                                        }
                                        player = "Player 1";
                                        playerTurn = 1;
                                    }
                                }
                        }
                    }
                }
            });
        }


        //main screen quit button
        mainScreen.quitRoundButton.addActionListener(e -> cl.show(panelContent, "3"));

        //main screen next round button
        mainScreen.nextRoundButton.addActionListener(e -> {
            mainScreen.bottomPanel.remove(mainScreen.nextRoundButton);
            mainScreen.bottomPanel.remove(mainScreen.quitRoundButton);
            for (int i = 0; i < mainScreen.keyboardButtons.length; i++) {
                mainScreen.keyboardButtons[i].setVisible(true);
            }
            for (int i = 0; i < mainScreen.blankButtons.length; i++) {
                mainScreen.blankButtons[i].setVisible(true);
            }

            try {
                cl.show(panelContent, "2");
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
        //end screen quit game button
        singleplayerEndScreen.quitGameButton.addActionListener(e -> System.exit(0));

        //end screen play again button
        singleplayerEndScreen.newGameButton.addActionListener(e -> {
            gamesWon = 0;
            mainScreen.UpdateHangman(0);
            cl.show(panelContent, "2");
            mainScreen.bottomPanel.remove(mainScreen.quitRoundButton);
            mainScreen.bottomPanel.remove(mainScreen.nextRoundButton);
            for (int i2 = 0; i2 < mainScreen.keyboardButtons.length; i2++) {
                mainScreen.keyboardButtons[i2].setVisible(true);
                mainScreen.keyboardButtons[i2].setEnabled(true);
            }
            for (int i2 = 0; i2 < mainScreen.blankButtons.length; i2++) {
                mainScreen.blankButtons[i2].setVisible(true);
            }

            try {
               HangmanRound();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        //End screen multiplayer button that changes gamemode
        singleplayerEndScreen.multiplayerButton.addActionListener(e -> {
            mainScreen.bottomPanel.remove(mainScreen.quitRoundButton);
            mainScreen.bottomPanel.remove(mainScreen.nextRoundButton);
            gameMode = 'm';
            playerTurn = 1;
            player = "Player 1";
            winner = null;
            player1Correct = false;
            player2Correct = false;
            cl.show(panelContent, "4");
            mainScreen.UpdateHangman(0);
            for (int i2 = 0; i2 < mainScreen.keyboardButtons.length; i2++) {
                mainScreen.keyboardButtons[i2].setVisible(true);
                mainScreen.keyboardButtons[i2].setEnabled(true);
            }
            for (int i2 = 0; i2 < mainScreen.blankButtons.length; i2++) {
                mainScreen.blankButtons[i2].setVisible(true);
            }
            incorrectGuesses = 0;
        });

        //Multiplayer

        //Defines what happens when Multiplayer button pressed
        startScreen.startMultiPlayerButton.addActionListener(e -> {
            gameMode = 'm';
            playerTurn = 1;
            player = "Player 1";
            winner = null;
            cl.show(panelContent, "4");
        });

        //removes "Enter Word:" from textbox when clicked
        chooseWordScreen.enterWord.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                chooseWordScreen.enterWord.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(chooseWordScreen.enterWord.equals("")) {
                    chooseWordScreen.enterWord.setText("Enter Word:");
                }
            }
        });

        //Makes sure only letters are typed
        chooseWordScreen.enterWord.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char character = e.getKeyChar();
                if (Character.isLetter(character)==false) {
                    e.setKeyChar(Character.MIN_VALUE);
                }
            }
        });

        //Submit button
        chooseWordScreen.submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chooseWordScreen.enterWord.getText().isEmpty()) {
                    chooseWordScreen.requestFocusInWindow();
                    chooseWordScreen.enterWord.setText("Enter Word:");
                }
                else {
                    word = chooseWordScreen.enterWord.getText();
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
            }
        });
        mainScreen.nextPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreen.nextPlayerButton.setVisible(false);
                mainScreen.bottomPanel.remove(mainScreen.nextRoundButton);
                mainScreen.bottomPanel.remove(mainScreen.quitRoundButton);
                for (int i = 0; i < mainScreen.keyboardButtons.length; i++) {
                    mainScreen.keyboardButtons[i].setVisible(true);
                }
                for (int i = 0; i < mainScreen.blankButtons.length; i++) {
                    mainScreen.blankButtons[i].setVisible(true);
                }
                //Resets keyboard
                for (int i = 0; i < mainScreen.keyboardButtons.length; i++) {
                    mainScreen.keyboardButtons[i].setEnabled(true);
                }
                mainScreen.UpdateHangman(0);
                chooseWordScreen.enterWord.setText("Enter Word: ");
                cl.show(panelContent, "4");
                }
        });

        //multiplayer end screen singleplayer button that changes gamemode
        multiplayerEndScreen.singlePlayerButton.addActionListener(e -> {
            gameMode = 's';
            mainScreen.UpdateHangman(0);
            for (int i2 = 0; i2 < mainScreen.keyboardButtons.length; i2++) {
                mainScreen.keyboardButtons[i2].setVisible(true);
                mainScreen.keyboardButtons[i2].setEnabled(true);
            }
            for (int i2 = 0; i2 < mainScreen.blankButtons.length; i2++) {
                mainScreen.blankButtons[i2].setVisible(true);
            }
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
            gameMode = 'm';
            playerTurn = 1;
            player = "Player 1";
            winner = null;
            player1Correct = false;
            player2Correct = false;
            cl.show(panelContent, "4");
            mainScreen.UpdateHangman(0);
            for (int i2 = 0; i2 < mainScreen.keyboardButtons.length; i2++) {
                mainScreen.keyboardButtons[i2].setVisible(true);
                mainScreen.keyboardButtons[i2].setEnabled(true);
            }
            for (int i2 = 0; i2 < mainScreen.blankButtons.length; i2++) {
                mainScreen.blankButtons[i2].setVisible(true);
            }
            incorrectGuesses = 0;
        });

        //multiplayer end screen quit game
        multiplayerEndScreen.quitGameButton.addActionListener(e -> {
            System.exit(0);
        });
    }

    public void HangmanRound() throws FileNotFoundException {

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

    public void HangmanGuess() throws IOException {

        //--------------------------------------System that lets user guess and deals with incorrect/correct guesses-----------------------------------

        if ((incorrectGuesses < INCORRECT_GUESSES_LIMIT) && (!correctlyGuessed)) {//Loop until Hangman is hung or word is correctly guessed

            playerGuess = Character.toUpperCase(playerGuess);  //Converting user input to upper case

            if (WordGeneration.Check(splitWord, playerGuess)) {//checks if player guess was correct
                WordGeneration.CorrectGuess(playerGuess, splitWord, playerGuesses);//Updates playerGuesses array with new guess
                mainScreen.DrawWordDisplay(playerGuesses);
                correctlyGuessed = Arrays.equals(splitWord, playerGuesses);  //Checks if word has been guessed

                //What happens when word is correctly guessed:
                if (correctlyGuessed) {
                    mainScreen.UpdateHangman(-1);
                    for (int i2 = 0; i2 < mainScreen.keyboardButtons.length; i2++) {
                        mainScreen.keyboardButtons[i2].setVisible(false);
                    }
                    for (int i2 = 0; i2 < mainScreen.blankButtons.length; i2++) {
                        mainScreen.blankButtons[i2].setVisible(false);
                    }
                    if (gameMode == 's') {
                        gamesWon++;
                        mainScreen.bottomPanel.add(mainScreen.nextRoundButton);
                        mainScreen.bottomPanel.add(mainScreen.quitRoundButton);
                        mainScreen.bottomPanel.setPreferredSize(new Dimension(1000, 270));
                        //What happens if new highscore
                        if (gamesWon > ReadHighscoreFile()) {
                            highScore = gamesWon;
                            WriteToHighscoreFile(highScore);
                        }
                    }
                    else if (gameMode == 'm') {
                        if (playerTurn == 1) {
                            player2Correct = true;
                            player = "Player 2";
                            playerTurn = 2; }
                        else if (playerTurn == 2) {
                            player1Correct = true;
                            player = "Player 1";
                            playerTurn = 1;}
                        System.out.println(player1Correct);
                        System.out.println(player2Correct);
                        if(player1Correct && player2Correct==false) {
                            winner = "Player 1";
                            cl.show(panelContent, "5");
                        }
                        //Draw
                        else if(player1Correct&&player2Correct) {
                            winner = "Draw";
                            cl.show(panelContent, "5");
                       }
                        //next round
                        else {
                            mainScreen.SetNextPlayerButton(player);
                            mainScreen.bottomPanel.add(mainScreen.nextPlayerButton);
                            mainScreen.nextPlayerButton.setVisible(true);
                        }

                    }
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

    public static String GetGamesWon() {
        return String.valueOf(gamesWon);
    }

    public static String GetHighScore() {
        return String.valueOf(highScore);
    }

    private static void WriteToHighscoreFile(int highScore) {
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


