import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class EndScreen extends JPanel {

    JButton newGameButton = new JButton("Play Again");
    JButton quitGameButton = new JButton("Quit Game");
    ImageIcon endScreenImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("StartScreenImage.png")));

    public EndScreen() {
        this.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(1000, 150));

        newGameButton.setFocusable(false);
        newGameButton.setPreferredSize(new Dimension(300, 100));
        bottomPanel.add(newGameButton);
        newGameButton.setVerticalAlignment(JButton.CENTER);

        quitGameButton.setFocusable(false);
        quitGameButton.setPreferredSize(new Dimension(300, 100));
        bottomPanel.add(quitGameButton);
        quitGameButton.setVerticalAlignment(JButton.CENTER);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(endScreenImage.getImage(), 200, 300, null);
        g2D.setFont(new Font("Comic Sans", Font.BOLD, 150));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g2D.drawString("Final Score: " + GUI.GetGamesWon(), ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth("Final Score: " + GUI.GetGamesWon())) / 2), 350);
        if (GUI.gainedNewHighscore) g2D.setColor(new Color(0, 100, 0));
        g2D.drawString("High Score: " + GUI.GetHighScore(), ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth("High Score " + GUI.GetHighScore())) / 2), 150);
        g2D.setBackground(Color.DARK_GRAY);
    }
}

