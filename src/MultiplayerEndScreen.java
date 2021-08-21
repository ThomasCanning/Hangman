import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MultiplayerEndScreen extends JPanel {

    JButton newGameButton = new JButton("Play Again");
    JButton quitGameButton = new JButton("Quit Game");
    JButton singlePlayerButton = new JButton("SinglePlayer");
    ImageIcon endScreenImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("StartScreenImage.png")));

    public MultiplayerEndScreen() {
        this.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(1000, 150));

        newGameButton.setFocusable(false);
        newGameButton.setPreferredSize(new Dimension(300, 100));
        bottomPanel.add(newGameButton, BorderLayout.WEST);

        quitGameButton.setFocusable(false);
        quitGameButton.setPreferredSize(new Dimension(300, 100));
        bottomPanel.add(quitGameButton, BorderLayout.CENTER);

        singlePlayerButton.setFocusable(false);
        singlePlayerButton.setPreferredSize(new Dimension(300,100));
        bottomPanel.add(singlePlayerButton, BorderLayout.EAST);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(endScreenImage.getImage(), 200, 300, null);
        g2D.setFont(new Font("Comic Sans", Font.BOLD, 150));
        FontMetrics metrics = getFontMetrics(g.getFont());
        if (GUI.winner.equals("Draw")) {
            g2D.drawString("Both Guessed Correctly", ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth("Both Guessed Correctly")) / 2), 300);
        }
        else{
            g2D.drawString(GUI.winner + " is the winner", ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth(GUI.winner + " is the winner")) / 2), 350);
        }
    }
}

