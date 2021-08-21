import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MainScreen extends JPanel {
    ImageIcon[] hangmanImages = new ImageIcon[8];
    Image[] hangmanImage = new Image[8];
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
            hangmanImage[i] = hangmanImages[i].getImage().getScaledInstance((int) (GUI.screenWidth/1.50), (int) (GUI.screenHeight/1.90), Image.SCALE_DEFAULT);
        }
    }

    MainScreen() {

        for(int i=1; i <= hangmanImages.length-1;i++) {
            hangmanImages[i] = new ImageIcon(hangmanImage[i]);
        }

        nextPlayerButton.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/64.0)));
        nextRoundButton.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/64.0)));
        quitRoundButton.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/64.0)));
        bottomPanel.setPreferredSize(new Dimension((int) (GUI.screenWidth/1.92), (int) (GUI.screenHeight/7.20)));

        for(int i = 0; i < 3; i++) {
            blankButtons[i] = new JButton();
            blankButtons[i].setOpaque(false);
            blankButtons[i].setContentAreaFilled(false);
            blankButtons[i].setBorderPainted(false);
        }

        this.setLayout(new BorderLayout());

        bottomPanel.setLayout((new FlowLayout()));
        this.add(bottomPanel, BorderLayout.SOUTH);
        for (int i = 0; i <= 25; i++) {
            if(QWERTY[i] == 'a') {
                bottomPanel.add(blankButtons[0]);
                bottomPanel.add(blankButtons[1]);
            }
            else if (QWERTY[i] == 'z') {
                bottomPanel.add(blankButtons[2]);

            }
            keyboardButtons[i] = new JButton(String.valueOf(QWERTY[i]));
            keyboardButtons[i].setActionCommand(String.valueOf(QWERTY[i]));
            keyboardButtons[i].setFocusable(false);
            keyboardButtons[i].setFont(new Font("Comic Sans",Font.PLAIN, (int) (GUI.screenWidth/64.0)));
            bottomPanel.add(keyboardButtons[i]);
        }
        UpdateSize();
        quitRoundButton.setFocusable(false);
        quitRoundButton.setVisible(true);
        nextRoundButton.setFocusable(false);
        nextRoundButton.setVisible(true);
        UpdateHangman(incorrectGuesses);
    }

    public void UpdateSize() {
        nextPlayerButton.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/64.0)));
        nextRoundButton.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/64.0)));
        quitRoundButton.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/64.0)));
        bottomPanel.setPreferredSize(new Dimension((int) (GUI.screenWidth/1.92), (int) (GUI.screenHeight/7.20)));
        for (int i = 0; i <= 25; i++) {
            keyboardButtons[i].setPreferredSize(new Dimension((int) (GUI.screenWidth/11.3),
                    GUI.screenHeight/12));
            keyboardButtons[i].setFont(new Font("Comic Sans",Font.PLAIN, (int) (GUI.screenWidth/64.0)));
            if(QWERTY[i] == 'a') {
                blankButtons[0].setPreferredSize(new Dimension((int) (GUI.screenWidth/96.0), GUI.screenHeight/12));
                blankButtons[1].setPreferredSize(new Dimension((int) (GUI.screenWidth/14), GUI.screenHeight/12));
            }
            else if (QWERTY[i] == 'z') {
                blankButtons[2].setPreferredSize(new Dimension((int) (GUI.screenWidth/14), GUI.screenHeight/12));
            }
        }
        quitRoundButton.setPreferredSize(new Dimension((int) (GUI.screenWidth/6.40), (int) (GUI.screenHeight/10.8)));
        nextRoundButton.setPreferredSize(new Dimension((int) (GUI.screenWidth/6.40), (int) (GUI.screenHeight/10.8)));
        nextPlayerButton.setPreferredSize(new Dimension((int) (GUI.screenWidth/6.40), (int) (GUI.screenHeight/10.8)));
        repaint();
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
                    g2D.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/12.8)));
                    FontMetrics metrics = getFontMetrics(g.getFont());
                    g2D.drawString("Current Score: " + GUI.GetGamesWon(), ((GUI.screenWidth / 2) - (metrics.stringWidth("Current Score: " + GUI.GetGamesWon())) / 2), (int) (GUI.screenHeight/3.10));
                    try {
                        if (GUI.gamesWon > GUI.ReadHighscoreFile()){
                            g2D.setColor(new Color(0, 100, 0));
                            GUI.highScore=GUI.gamesWon;
                            GUI.WriteToHighscoreFile(GUI.highScore);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    g2D.drawString("High Score: " + GUI.GetHighScore(), ((GUI.screenWidth / 2) - (metrics.stringWidth("High Score: " + GUI.GetHighScore())) / 2), (int) (GUI.screenHeight/7.20));

                    g2D.setBackground(Color.DARK_GRAY);
                }
                else {
                    for(int i=3;i<=hangmanImages.length;i++)
                    g2D.drawImage(hangmanImages[i].getImage(), (int) (GUI.screenWidth/9.6), (int) -GUI.screenHeight/9, null);

                }
                g2D.setColor(new Color(0, 100, 0));
            }

        //used in multiplayer to display which player is guessing
            else if (incorrectGuesses == -2) {
                if (GUI.gameMode == 'm') {
                    g2D.drawImage(hangmanImages[1].getImage(), (int) (GUI.screenWidth/9.6), -GUI.screenHeight/9, null);
                    g2D.setFont(new Font("Comic Sans", Font.BOLD, GUI.screenWidth/24));
                    FontMetrics metrics = getFontMetrics(g.getFont());
                    switch (GUI.player) {
                        case "Player 1" -> g2D.drawString("Player 2 is guessing", ((GUI.screenWidth / 2) - (metrics.stringWidth("Player 2 is guessing")) / 2), (int) (GUI.screenHeight/2.10));
                        case "Player 2" -> g2D.drawString("Player 1 is guessing", ((GUI.screenWidth / 2) - (metrics.stringWidth("Player 1 is guessing")) / 2), (int) (GUI.screenHeight/2.10));
                    }
                }
            }

            else if (0 <= incorrectGuesses && incorrectGuesses<=5) {
            for (int i = 1; i <= incorrectGuesses + 1; i++) {
                g2D.drawImage(hangmanImages[i].getImage(), (int) (GUI.screenWidth/9.6), -GUI.screenHeight/9, null);
            }
        }

            else {
            for (int i = 1; i <= 7; i++) {
                g2D.drawImage(hangmanImages[i].getImage(), (int) (GUI.screenWidth/9.6), -GUI.screenHeight/9, null);
            }
            }
        if (playerGuesses != null) {

            g2D.setFont(new Font(Font.MONOSPACED, Font.PLAIN, (int) (GUI.screenWidth/19.2)));
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
                g2D.drawString(String.valueOf(newDisplayWord), ((GUI.screenWidth / 2) - (metrics.stringWidth(playerGuesses)) / 2), (int) (GUI.screenHeight/1.86));
                g2D.setBackground(Color.DARK_GRAY);
            }

            int spaceAlongLine = 0;

            for (int i = 0; i < word.length(); i++) {
                char character = word.charAt(i);
                if (character != ' ') {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect((((GUI.screenWidth / 2) - ((metrics.stringWidth(word)) + 4) / 2) + 6) + spaceAlongLine, (int) (GUI.screenHeight/1.80), metrics.charWidth(character) - 6, GUI.screenHeight/54);
                }
                spaceAlongLine = spaceAlongLine + metrics.charWidth(character);
            }
            if (incorrectGuesses == GUI.INCORRECT_GUESSES_LIMIT) {
                //What happens when fail
                g2D.setColor(Color.RED);
                g2D.drawString(word, ((GUI.screenWidth / 2) - (metrics.stringWidth(word)) / 2), 600);
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
