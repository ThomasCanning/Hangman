import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class SingleplayerEndScreen extends JPanel {

    JButton newGameButton = new JButton("Play Again");
    JButton quitGameButton = new JButton("Quit Game");
    JButton multiplayerButton = new JButton("Multiplayer");
    ImageIcon endScreenImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("StartScreenImage.png")));
    Image endScreenImageSource = endScreenImage.getImage().getScaledInstance((int) (GUI.screenWidth/1.50), (int) (GUI.screenHeight/1.90), Image.SCALE_DEFAULT);
    JPanel bottomPanel = new JPanel();

    public SingleplayerEndScreen() {

        endScreenImage = new ImageIcon(endScreenImageSource);
        multiplayerButton.setFont(new Font("Comic Sans", Font.BOLD, GUI.screenWidth/64));
        newGameButton.setFont(new Font("Comic Sans", Font.BOLD, GUI.screenWidth/64));
        quitGameButton.setFont(new Font("Comic Sans", Font.BOLD, GUI.screenWidth/64));

        this.setLayout(new BorderLayout());

        newGameButton.setFocusable(false);
        bottomPanel.add(newGameButton, BorderLayout.WEST);

        quitGameButton.setFocusable(false);
        bottomPanel.add(quitGameButton, BorderLayout.CENTER);
        bottomPanel.add(multiplayerButton, BorderLayout.EAST);
        quitGameButton.setVerticalAlignment(JButton.CENTER);

        multiplayerButton.setFocusable(false);

        this.add(bottomPanel, BorderLayout.SOUTH);
        UpdateSize();
    }

    public void UpdateSize() {
        multiplayerButton.setFont(new Font("Comic Sans", Font.BOLD, GUI.screenWidth/64));
        newGameButton.setFont(new Font("Comic Sans", Font.BOLD, GUI.screenWidth/64));
        quitGameButton.setFont(new Font("Comic Sans", Font.BOLD, GUI.screenWidth/64));
        bottomPanel.setPreferredSize(new Dimension((int) (GUI.screenWidth/1.92), (int) (GUI.screenHeight/7.20)));
        newGameButton.setPreferredSize(new Dimension((int) (GUI.screenWidth/6.40), (int) (GUI.screenHeight/10.8)));
        quitGameButton.setPreferredSize(new Dimension((int) (GUI.screenWidth/6.40), (int) (GUI.screenHeight/10.8)));
        multiplayerButton.setPreferredSize(new Dimension((int) (GUI.screenWidth/6.40), (int) (GUI.screenHeight/10.8)));
        repaint();
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(endScreenImage.getImage(), (int) (GUI.screenWidth/9.6), (int) (GUI.screenHeight/3.6), null);
        g2D.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/12.8)));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g2D.drawString("Final Score: " + GUI.GetGamesWon(), ((GUI.screenWidth / 2) - (metrics.stringWidth("Final Score: " + GUI.GetGamesWon())) / 2), (int) (GUI.screenHeight/3.10));
        try {
            if (GUI.gamesWon > GUI.ReadHighscoreFile()) g2D.setColor(new Color(0, 100, 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2D.drawString("High Score: " + GUI.GetHighScore(), ((GUI.screenWidth / 2) - (metrics.stringWidth("High Score: " + GUI.GetHighScore())) / 2), (int) (GUI.screenHeight/7.2));
        g2D.setBackground(Color.DARK_GRAY);
    }
}

