import javax.swing.*;
import java.awt.*;

public class MainScreen extends JPanel {
    final ImageIcon hangman1 = new ImageIcon(getClass().getClassLoader().getResource("Hangman1.png"));
    final ImageIcon hangman2 = new ImageIcon(getClass().getClassLoader().getResource("Hangman2.png"));
    final ImageIcon hangman3 = new ImageIcon(getClass().getClassLoader().getResource("Hangman3.png"));
    final ImageIcon hangman4 = new ImageIcon(getClass().getClassLoader().getResource("Hangman4.png"));
    final ImageIcon hangman5 = new ImageIcon(getClass().getClassLoader().getResource("Hangman5.png"));
    final ImageIcon hangman6 = new ImageIcon(getClass().getClassLoader().getResource("Hangman6.png"));
    final ImageIcon hangman7 = new ImageIcon(getClass().getClassLoader().getResource("Hangman7.png"));

    final char[] QWERTY = {'q','w','e','r', 't','y','u','i','o','p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l','z','x','c','v','b','n','m'};

    private int incorrectGuesses = 0;

    private String word;
    private String playerGuesses;

    JButton[] keyboardButtons = new JButton[26];
    JButton endRoundButton = new JButton("Continue");
    JPanel bottomPanel = new JPanel();
    JButton[] blankButtons = new JButton[3];

    MainScreen(GUI parent) {

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
        int i = 0;
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
            bottomPanel.add(keyboardButtons[i]);

        }
        endRoundButton.setPreferredSize(new Dimension(300, 100));
        endRoundButton.setFocusable(false);
        endRoundButton.setVisible(true);
        UpdateHangman(incorrectGuesses);

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
            case 6 -> {
                g2D.drawImage(hangman1.getImage(), 200, -100, null);
                g2D.drawImage(hangman2.getImage(), 200, -100, null);
                g2D.drawImage(hangman3.getImage(), 200, -100, null);
                g2D.drawImage(hangman4.getImage(), 200, -100, null);
                g2D.drawImage(hangman5.getImage(), 200, -100, null);
                g2D.drawImage(hangman6.getImage(), 200, -100, null);
                g2D.drawImage(hangman7.getImage(), 200, -100, null);
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
                g2D.drawString(playerGuesses, ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth(playerGuesses)) / 2), 600);
                g2D.setBackground(Color.DARK_GRAY);
            }

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
            if (incorrectGuesses == GUI.INCORRECT_GUESSES_LIMIT) {
                //What happens when fail
                g2D.setColor(Color.RED);
                g2D.drawString(word, ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth(word)) / 2), 600);

                //remove keyboard and replace with continue button
                for (int i = 0; i < this.keyboardButtons.length; i++) {

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
