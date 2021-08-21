import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class ChoseWordScreen extends JPanel {

    JPasswordField enterWord;
    JLabel label;
    JButton submit;

    ChoseWordScreen() { //Start screen panel GUI

        this.setLayout(null);

        submit = new JButton("Submit");

        enterWord = new JPasswordField("Enter Word:");
        enterWord.setEchoChar((char)0);
        enterWord.setFont(new Font("Comic Sans", Font.BOLD, 120));
        enterWord.setForeground(Color.BLACK);
        enterWord.setBounds(GUI.SCREEN_WIDTH / 2 - 600, GUI.SCREEN_HEIGHT - 350, 1200, 180);
        enterWord.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        submit.setBounds(GUI.SCREEN_WIDTH / 2 - 785, GUI.SCREEN_HEIGHT - 350, 180, 180);
        submit.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        submit.setFont(new Font("Comic Sans", Font.BOLD, 30));
        submit.setBackground(Color.WHITE);
        submit.setForeground(Color.BLACK);
        submit.setFocusable(false);

        this.add(enterWord);
        this.add(submit);
    }

    public void ResetScreen () {
        this.enterWord.setEchoChar((char)0);
        this.enterWord.setText("Enter Word:");
        GUI.gameMode = 'm';
        GUI.playerTurn = 1;
        GUI.player = "Player 1";
        GUI.winner = null;
        GUI.player1Correct = false;
        GUI.player2Correct = false;
        GUI.incorrectGuesses = 0;
    }

    public void ResetEnterWord() {
        this.requestFocusInWindow();
        enterWord.setEchoChar((char)0);
        enterWord.setText("Enter Word:");
    }

    protected void paintComponent(Graphics gp) {
        super.paintComponent(gp);
        Graphics2D g2D = (Graphics2D) gp.create();
        g2D.setFont(new Font("Comic Sans", Font.BOLD, 100));
        FontMetrics metrics = g2D.getFontMetrics();
        g2D.setColor(new Color(64, 64, 64));
        g2D.drawString("Chose Word: " + GUI.player, ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth("Chose Word: " + GUI.player) / 2)), 350);
    }
}
