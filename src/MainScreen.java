import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MainScreen extends JPanel {
    ImageIcon[] hangmanImages = new ImageIcon[8];
    final char[] QWERTY = {'q','w','e','r', 't','y','u','i','o','p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l','z','x','c','v','b','n','m'};

    private int incorrectGuesses = 0;

    private String word;
    private String playerGuesses;

    JButton[] keyboardButtons = new JButton[26];
    JButton quitRoundButton = new JButton("End Round");
    JButton nextPlayerButton = new JButton("Next Turn");
    JPanel bottomPanel = new JPanel();
    JButton[] blankButtons = new JButton[3];
    JButton nextRoundButton = new JButton("Next Round");

    {

        for(int i=1; i <= hangmanImages.length-1;i++) {
            hangmanImages[i] = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Hangman"+i+".png")));
        }
    }

    MainScreen() {

        nextPlayerButton.setFont(new Font("Comic Sans", Font.BOLD, 30));
        nextRoundButton.setFont(new Font("Comic Sans", Font.BOLD, 30));
        quitRoundButton.setFont(new Font("Comic Sans", Font.BOLD, 30));

        for(int i = 0; i < 3; i++) {
            blankButtons[i] = new JButton();
            blankButtons[i].setOpaque(false);
            blankButtons[i].setContentAreaFilled(false);
            blankButtons[i].setBorderPainted(false);
        }

        this.setLayout(new BorderLayout());

        bottomPanel.setPreferredSize(new Dimension(1000, 320));
        bottomPanel.setLayout((new FlowLayout()));
        this.add(bottomPanel, BorderLayout.SOUTH);
        int i;
        for (i = 0; i <= 25; i++) {
            if(QWERTY[i] == 'a') {
                blankButtons[0].setPreferredSize(new Dimension(20, 90));
                blankButtons[1].setPreferredSize(new Dimension(127, 90));
                bottomPanel.add(blankButtons[0]);
                bottomPanel.add(blankButtons[1]);
            }
            else if (QWERTY[i] == 'z') {
                blankButtons[2].setPreferredSize(new Dimension(85, 90));
                bottomPanel.add(blankButtons[2]);

            }
            keyboardButtons[i] = new JButton(String.valueOf(QWERTY[i]));
            keyboardButtons[i].setPreferredSize(new Dimension(170, 90));
            keyboardButtons[i].setActionCommand(String.valueOf(QWERTY[i]));
            keyboardButtons[i].setFocusable(false);
            keyboardButtons[i].setFont(new Font("Comic Sans",Font.PLAIN, 30));
            bottomPanel.add(keyboardButtons[i]);

        }
        quitRoundButton.setPreferredSize(new Dimension(300, 100));
        quitRoundButton.setFocusable(false);
        quitRoundButton.setVisible(true);
        nextRoundButton.setPreferredSize(new Dimension(300, 100));
        nextRoundButton.setFocusable(false);
        nextRoundButton.setVisible(true);
        UpdateHangman(incorrectGuesses);

        //multiplayer:
        nextPlayerButton.setPreferredSize(new Dimension(300, 100));

    }


    public void SetIncorrectGuesses(int newIncorrectGuesses) {this.incorrectGuesses = newIncorrectGuesses;}

    public void SetWord (String newWord) {
        this.word = newWord;
    }

    public void SetPlayerGuesses (char[] newPlayerGuesses) {
        this.playerGuesses = new String(newPlayerGuesses);

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        //clears hangman
        if (incorrectGuesses == -1) {
                if (GUI.gameMode=='s') {
                    g2D.setFont(new Font("Comic Sans", Font.BOLD, 150));
                    FontMetrics metrics = getFontMetrics(g.getFont());
                    g2D.drawString("Current Score: " + GUI.GetGamesWon(), ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth("Current Score: " + GUI.GetGamesWon())) / 2), 350);
                    try {
                        if (GUI.gamesWon > GUI.ReadHighscoreFile()){
                            g2D.setColor(new Color(0, 100, 0));
                            GUI.highScore=GUI.gamesWon;
                            GUI.WriteToHighscoreFile(GUI.highScore);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    g2D.drawString("High Score: " + GUI.GetHighScore(), ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth("High Score: " + GUI.GetHighScore())) / 2), 150);

                    g2D.setBackground(Color.DARK_GRAY);
                }
                else {
                    for(int i=3;i<=hangmanImages.length;i++)
                    g2D.drawImage(hangmanImages[i].getImage(), 200, -120, null);

                }
                g2D.setColor(new Color(0, 100, 0));
            }

        //used in multiplayer to display which player is guessing
            else if (incorrectGuesses == -2) {
                if (GUI.gameMode == 'm') {
                    g2D.drawImage(hangmanImages[1].getImage(), 200, -120, null);
                    g2D.setFont(new Font("Comic Sans", Font.BOLD, 80));
                    FontMetrics metrics = getFontMetrics(g.getFont());
                    switch (GUI.player) {
                        case "Player 1" -> g2D.drawString("Player 2 is guessing", ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth("Player 2 is guessing")) / 2), 530);
                        case "Player 2" -> g2D.drawString("Player 1 is guessing", ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth("Player 1 is guessing")) / 2), 530);
                    }

                }
            }
            //case 0 -> g2D.drawImage(hangman1.getImage(), 200, -120, null);

            else if (0 <= incorrectGuesses && incorrectGuesses<=5) {
            for (int i = 1; i <= incorrectGuesses + 1; i++) {
                g2D.drawImage(hangmanImages[i].getImage(), 200, -120, null);
            }
        }

            else {
            for (int i = 1; i <= 7; i++) {
                g2D.drawImage(hangmanImages[i].getImage(), 200, -120, null);
            }
            }
        if (playerGuesses != null) {

            g2D.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 100));
            FontMetrics metrics = getFontMetrics(g.getFont());

            if (incorrectGuesses != GUI.INCORRECT_GUESSES_LIMIT) {

                //Ensures characters that are displayed are of the correct case
                StringBuilder newDisplayWord = new StringBuilder(playerGuesses);
                for(int i = 0; i < word.length(); i++) {
                    if ((playerGuesses.charAt(i)!=word.charAt(i))&&(playerGuesses.charAt(i)!=' ')) {
                        newDisplayWord.setCharAt(i, Character.toLowerCase(playerGuesses.charAt(i)));
                    }
                    else
                        newDisplayWord.setCharAt(i, playerGuesses.charAt(i));
                }

                //Draws the word that has been guessed so far
                g2D.drawString(String.valueOf(newDisplayWord), ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth(playerGuesses)) / 2), 600);
                g2D.setBackground(Color.DARK_GRAY);
            }

            int spaceAlongLine = 0;

            for (int i = 0; i < word.length(); i++) {
                char character = word.charAt(i);
                if (character != ' ') {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect((((GUI.SCREEN_WIDTH / 2) - ((metrics.stringWidth(word)) + 4) / 2) + 6) + spaceAlongLine, 620, metrics.charWidth(character) - 6, 20);
                }
                spaceAlongLine = spaceAlongLine + metrics.charWidth(character);
            }
            if (incorrectGuesses == GUI.INCORRECT_GUESSES_LIMIT) {
                //What happens when fail
                g2D.setColor(Color.RED);
                g2D.drawString(word, ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth(word)) / 2), 600);
            }
        }
    }

    public void UpdateHangman(int incorrectGuesses) {
        SetIncorrectGuesses(incorrectGuesses);
        repaint();
    }

    public void DrawWordDisplay(char[] displayWord) {
        SetPlayerGuesses(displayWord);
        repaint();
    }
}
