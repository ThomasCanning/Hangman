import javax.swing.*;
import java.awt.*;

public class ContinueScreen extends JPanel {

    JButton continueButton = new JButton();

    public ContinueScreen(GUI parent) {
        this.setLayout(new BorderLayout());
        ImageIcon continueScreenImage = new ImageIcon("StartScreenImage.png");
        JLabel label = new JLabel();
        label.setIcon(continueScreenImage);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(new Font("Comic Sans", Font.BOLD, 150));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(1000, 150));

        continueButton.setText("Continue");
        continueButton.setFocusable(false);
        continueButton.setPreferredSize(new Dimension(300, 100));
        bottomPanel.add(continueButton);
        continueButton.setVerticalAlignment(JButton.CENTER);

        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(label);
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.setFont(new Font("Comic Sans", Font.BOLD, 150));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g2D.drawString("Current Score: " + GUI.GetGamesWon(), ((GUI.SCREEN_WIDTH / 2) - (metrics.stringWidth("Current Score: " + GUI.GetGamesWon())) / 2), 200);
        g2D.setBackground(Color.DARK_GRAY);
    }
    public void DisplayGamesWon() {
        repaint();
    }
}
