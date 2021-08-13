import javax.swing.*;
import java.awt.*;

public class MainScreen extends JPanel {
    final ImageIcon hangman1 = new ImageIcon("Hangman1.png");
    final ImageIcon hangman2 = new ImageIcon("Hangman2.png");
    final ImageIcon hangman3 = new ImageIcon("Hangman3.png");
    final ImageIcon hangman4 = new ImageIcon("Hangman4.png");
    final ImageIcon hangman5 = new ImageIcon("Hangman5.png");
    final ImageIcon hangman6 = new ImageIcon("Hangman6.png");
    final ImageIcon hangman7 = new ImageIcon("Hangman7.png");

    private int incorrectGuesses = 0;

    private String word;
    private String playerGuesses;
    private char playerGuess;

    JButton[] keyboardButtons = new JButton[26];
    JButton resetButton = new JButton("RESET");

    MainScreen(GUI parent) {

        this.setLayout(new BorderLayout());
        JPanel bottomPanel = new JPanel();

        bottomPanel.setPreferredSize(new Dimension(1000, 320));
        bottomPanel.setLayout((new FlowLayout()));
        this.add(bottomPanel, BorderLayout.SOUTH);
        int i = 0;
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            keyboardButtons[i] = new JButton(String.valueOf(alphabet));
            keyboardButtons[i].setPreferredSize(new Dimension(170, 90));
            keyboardButtons[i].setActionCommand(String.valueOf(alphabet));
            bottomPanel.add(keyboardButtons[i]);
            i++;
        }
        UpdateHangman(incorrectGuesses);

        this.add(resetButton, BorderLayout.NORTH);
    }

    public void SetPlayerGuess(char newPlayerGuess) {
        this.playerGuess = newPlayerGuess;
        System.out.println("input " + playerGuess);
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
        switch (incorrectGuesses) {
            case 0:
                g2D.drawImage(hangman1.getImage(), 200, -100, null);
                break;
            case 1:
                g2D.drawImage(hangman1.getImage(), 200, -100, null);
                g2D.drawImage(hangman2.getImage(), 200, -100, null);
                break;
            case 2:
                g2D.drawImage(hangman1.getImage(), 200, -100, null);
                g2D.drawImage(hangman2.getImage(), 200, -100, null);
                g2D.drawImage(hangman3.getImage(), 200, -100, null);
                break;
            case 3:
                g2D.drawImage(hangman1.getImage(), 200, -100, null);
                g2D.drawImage(hangman2.getImage(), 200, -100, null);
                g2D.drawImage(hangman3.getImage(), 200, -100, null);
                g2D.drawImage(hangman4.getImage(), 200, -100, null);
                break;
            case 4:
                g2D.drawImage(hangman1.getImage(), 200, -100, null);
                g2D.drawImage(hangman2.getImage(), 200, -100, null);
                g2D.drawImage(hangman3.getImage(), 200, -100, null);
                g2D.drawImage(hangman4.getImage(), 200, -100, null);
                g2D.drawImage(hangman5.getImage(), 200, -100, null);
                break;
            case 5:
                g2D.drawImage(hangman1.getImage(), 200, -100, null);
                g2D.drawImage(hangman2.getImage(), 200, -100, null);
                g2D.drawImage(hangman3.getImage(), 200, -100, null);
                g2D.drawImage(hangman4.getImage(), 200, -100, null);
                g2D.drawImage(hangman5.getImage(), 200, -100, null);
                g2D.drawImage(hangman6.getImage(), 200, -100, null);
                break;
            case 6:
                g2D.drawImage(hangman1.getImage(), 200, -100, null);
                g2D.drawImage(hangman2.getImage(), 200, -100, null);
                g2D.drawImage(hangman3.getImage(), 200, -100, null);
                g2D.drawImage(hangman4.getImage(), 200, -100, null);
                g2D.drawImage(hangman5.getImage(), 200, -100, null);
                g2D.drawImage(hangman6.getImage(), 200, -100, null);
                g2D.drawImage(hangman7.getImage(), 200, -100, null);
                break;
        }

        System.out.println(word);
        if (playerGuesses != null) {

            g2D.setFont(new Font(Font.MONOSPACED, Font.ROMAN_BASELINE, 100));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g2D.drawString(playerGuesses, ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth(playerGuesses)) / 2), 600);
            g2D.setBackground(Color.DARK_GRAY);

            int spaceAlongLine = 0;
            for (int i = 0; i < word.length(); i++) {
                char character = word.charAt(i);
                if (character == ' ') {
                    spaceAlongLine = spaceAlongLine + metrics.charWidth(character);
                } else {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect((((GUI.SCREEN_WIDTH / 2) - ((metrics.stringWidth(word))+4) / 2) + 6) + spaceAlongLine, 620, metrics.charWidth(character) - 6, 20);
                    spaceAlongLine = spaceAlongLine + metrics.charWidth(character);
                }
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
