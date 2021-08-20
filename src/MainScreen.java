import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MainScreen extends JPanel {
    final ImageIcon hangman1 = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Hangman1.png")));
    final ImageIcon hangman2 = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Hangman2.png")));
    final ImageIcon hangman3 = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Hangman3.png")));
    final ImageIcon hangman4 = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Hangman4.png")));
    final ImageIcon hangman5 = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Hangman5.png")));
    final ImageIcon hangman6 = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Hangman6.png")));
    final ImageIcon hangman7 = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Hangman7.png")));

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

    MainScreen() {

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

    public void SetNextPlayerButton (String player) {
        nextPlayerButton.setText(player + "'s Turn");
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        switch (incorrectGuesses) {
            case -1 -> {//clears hangman
                if (GUI.gameMode=='s') {
                    g2D.setFont(new Font("Comic Sans", Font.BOLD, 150));
                    FontMetrics metrics = getFontMetrics(g.getFont());
                    g2D.drawString("Current Score: " + GUI.GetGamesWon(), ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth("Current Score: " + GUI.GetGamesWon())) / 2), 350);
                    try {
                        if (GUI.gamesWon > GUI.ReadHighscoreFile()) g2D.setColor(new Color(0, 100, 0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    g2D.drawString("High Score: " + GUI.GetHighScore(), ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth("High Score: " + GUI.GetHighScore())) / 2), 150);

                    g2D.setBackground(Color.DARK_GRAY);
                }
                else {
                    g2D.drawImage(hangman3.getImage(), 200, 0, null);
                    g2D.drawImage(hangman4.getImage(), 200, 0, null);
                    g2D.drawImage(hangman5.getImage(), 200, 0, null);
                    g2D.drawImage(hangman6.getImage(), 200, 0, null);
                    g2D.drawImage(hangman7.getImage(), 200, 0, null);
                }
                g2D.setColor(new Color(0, 100, 0));
            }
            case 0 -> g2D.drawImage(hangman1.getImage(), 200, -100, null);
            case 1 -> {
                g2D.drawImage(hangman1.getImage(), 200, -100, null);
                g2D.drawImage(hangman2.getImage(), 200, -100, null);
            }
            case 2 -> {
                g2D.drawImage(hangman1.getImage(), 200, -100, null);
                g2D.drawImage(hangman2.getImage(), 200, -100, null);
                g2D.drawImage(hangman3.getImage(), 200, -100, null);
            }
            case 3 -> {
                g2D.drawImage(hangman1.getImage(), 200, -100, null);
                g2D.drawImage(hangman2.getImage(), 200, -100, null);
                g2D.drawImage(hangman3.getImage(), 200, -100, null);
                g2D.drawImage(hangman4.getImage(), 200, -100, null);
            }
            case 4 -> {
                g2D.drawImage(hangman1.getImage(), 200, -100, null);
                g2D.drawImage(hangman2.getImage(), 200, -100, null);
                g2D.drawImage(hangman3.getImage(), 200, -100, null);
                g2D.drawImage(hangman4.getImage(), 200, -100, null);
                g2D.drawImage(hangman5.getImage(), 200, -100, null);
            }
            case 5 -> {
                g2D.drawImage(hangman1.getImage(), 200, -100, null);
                g2D.drawImage(hangman2.getImage(), 200, -100, null);
                g2D.drawImage(hangman3.getImage(), 200, -100, null);
                g2D.drawImage(hangman4.getImage(), 200, -100, null);
                g2D.drawImage(hangman5.getImage(), 200, -100, null);
                g2D.drawImage(hangman6.getImage(), 200, -100, null);
            }
            default -> {
                g2D.drawImage(hangman1.getImage(), 200, -100, null);
                g2D.drawImage(hangman2.getImage(), 200, -100, null);
                g2D.drawImage(hangman3.getImage(), 200, -100, null);
                g2D.drawImage(hangman4.getImage(), 200, -100, null);
                g2D.drawImage(hangman5.getImage(), 200, -100, null);
                g2D.drawImage(hangman6.getImage(), 200, -100, null);
                g2D.drawImage(hangman7.getImage(), 200, -100, null);
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
