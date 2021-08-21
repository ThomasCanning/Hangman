import javax.swing.*;
import java.awt.*;

public class ChoseWordScreen extends JPanel {

    JPasswordField enterWord;
    JButton submit;

    ChoseWordScreen() { //Start screen panel GUI

        this.setLayout(null);

        submit = new JButton("Submit");

        enterWord = new JPasswordField("Enter Word:");
        enterWord.setEchoChar((char)0);
        enterWord.setFont(new Font("Comic Sans", Font.BOLD, GUI.screenWidth/16));
        enterWord.setForeground(Color.BLACK);
        enterWord.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        submit.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        submit.setFont(new Font("Comic Sans", Font.BOLD, (int)GUI.screenWidth/64));
        submit.setBackground(Color.WHITE);
        submit.setForeground(Color.BLACK);
        submit.setFocusable(false);

        UpdateSize();

        this.add(enterWord);
        this.add(submit);
    }
    public void UpdateSize() {
        enterWord.setFont(new Font("Comic Sans", Font.BOLD, GUI.screenWidth/16));
        enterWord.setBounds((int) ((GUI.screenWidth / 2) - (GUI.screenWidth/3.2)), (int) (GUI.screenHeight - GUI.screenHeight/3.10), (int) (GUI.screenWidth/1.6), (int)GUI.screenHeight/6);
        submit.setBounds((int) (GUI.screenWidth / 2 - GUI.screenWidth/2.45), (int) (GUI.screenHeight - GUI.screenHeight/3.10),(int) (GUI.screenWidth/10.7) , (int)GUI.screenHeight/6);
        submit.setFont(new Font("Comic Sans", Font.BOLD, (int)GUI.screenWidth/64));
        repaint();
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
        g2D.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/19.2)));
        FontMetrics metrics = g2D.getFontMetrics();
        g2D.setColor(new Color(64, 64, 64));
        g2D.drawString("Chose Word: " + GUI.player, ((GUI.screenWidth / 2) - (metrics.stringWidth("Chose Word: " + GUI.player) / 2)), (int) (GUI.screenHeight/3.10));
    }
}
