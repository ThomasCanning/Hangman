import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MainScreen extends JPanel {
    final ImageIcon hangman1 = new ImageIcon("Hangman1.png");
    final ImageIcon hangman2 = new ImageIcon("Hangman2.png");
    final ImageIcon hangman3 = new ImageIcon("Hangman3.png");
    final ImageIcon hangman4 = new ImageIcon("Hangman4.png");
    final ImageIcon hangman5 = new ImageIcon("Hangman5.png");
    final ImageIcon hangman6 = new ImageIcon("Hangman6.png");
    final ImageIcon hangman7 = new ImageIcon("Hangman7.png");
    private int incorrectGuesses = 0;
    private int wordlength = 5;

    MainScreen(GUI parent) {

        this.setLayout(new BorderLayout());
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(1000, 320));
        bottomPanel.setLayout((new FlowLayout()));
        this.add(bottomPanel, BorderLayout.SOUTH);
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            System.out.println(alphabet);
            JButton button = new JButton(String.valueOf(alphabet));
            button.setPreferredSize(new Dimension(170, 90));
            bottomPanel.add(button);
        }
        UpdateHangman(incorrectGuesses);
        DrawWordDisplay(wordlength);


    }

    public void SetIncorrectGuesses(int newIncorrectGuesses) {this.incorrectGuesses = newIncorrectGuesses;}

    public void SetWordLength (int newWordLength) {this.wordlength = newWordLength;}

    public void paint(Graphics g) {
        super.paint(g);
       Graphics2D g2D = (Graphics2D) g;
       switch(incorrectGuesses) {
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


        }

    }

    public void UpdateHangman(int incorrectGuesses) {
        SetIncorrectGuesses(incorrectGuesses);
        repaint();
    }

    public void DrawWordDisplay(int wordlength) {
        SetWordLength(wordlength);
        repaint();
    }

}
