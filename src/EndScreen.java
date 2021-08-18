import javax.swing.*;
import java.awt.*;

public class EndScreen extends JPanel {

    JButton newGameButton = new JButton("Play Again");
    JButton quitGameButton = new JButton("Quit Game");

    public EndScreen (GUI parent) {
        this.setLayout(new BorderLayout());
        ImageIcon continueScreenImage = new ImageIcon(getClass().getClassLoader().getResource("StartScreenImage.png"));
        JLabel label = new JLabel();
        label.setIcon(continueScreenImage);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(new Font("Comic Sans", Font.BOLD, 150));

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
        this.add(label);
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.setFont(new Font("Comic Sans", Font.BOLD, 150));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g2D.drawString("Final Score: " + GUI.GetGamesWon(), ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth("Final Score: " + GUI.GetGamesWon())) / 2), 200);
        g2D.setBackground(Color.DARK_GRAY);
    }
    public void DisplayGamesWon() {
        repaint();
    }
}

