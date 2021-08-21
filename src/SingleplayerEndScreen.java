import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class SingleplayerEndScreen extends JPanel {

    JButton newGameButton = new JButton("Play Again");
    JButton quitGameButton = new JButton("Quit Game");
    JButton multiplayerButton = new JButton("Multiplayer");
    ImageIcon endScreenImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("StartScreenImage.png")));

    public SingleplayerEndScreen() {
        multiplayerButton.setFont(new Font("Comic Sans", Font.BOLD, 30));
        newGameButton.setFont(new Font("Comic Sans", Font.BOLD, 30));
        quitGameButton.setFont(new Font("Comic Sans", Font.BOLD, 30));

        this.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(1000, 150));

        newGameButton.setFocusable(false);
        newGameButton.setPreferredSize(new Dimension(300, 100));
        bottomPanel.add(newGameButton, BorderLayout.WEST);

        quitGameButton.setFocusable(false);
        quitGameButton.setPreferredSize(new Dimension(300, 100));
        bottomPanel.add(quitGameButton, BorderLayout.CENTER);
        bottomPanel.add(multiplayerButton, BorderLayout.EAST);
        quitGameButton.setVerticalAlignment(JButton.CENTER);

        multiplayerButton.setFocusable(false);
        multiplayerButton.setPreferredSize(new Dimension(300,100));

        this.add(bottomPanel, BorderLayout.SOUTH);
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(endScreenImage.getImage(), 200, 300, null);
        g2D.setFont(new Font("Comic Sans", Font.BOLD, 150));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g2D.drawString("Final Score: " + GUI.GetGamesWon(), ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth("Final Score: " + GUI.GetGamesWon())) / 2), 350);
        try {
            if (GUI.gamesWon > GUI.ReadHighscoreFile()) g2D.setColor(new Color(0, 100, 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2D.drawString("High Score: " + GUI.GetHighScore(), ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth("High Score: " + GUI.GetHighScore())) / 2), 150);
        g2D.setBackground(Color.DARK_GRAY);
    }
}

