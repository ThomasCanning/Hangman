import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MultiplayerEndScreen extends JPanel {

    JButton newGameButton = new JButton("Play Again");
    JButton quitGameButton = new JButton("Quit Game");
    JButton singlePlayerButton = new JButton("SinglePlayer");
    ImageIcon endScreenImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("StartScreenImage.png")));
    Image endScreenImageSource = endScreenImage.getImage().getScaledInstance((int) (GUI.screenWidth/1.50), (int) (GUI.screenHeight/1.90), Image.SCALE_DEFAULT);
    JPanel bottomPanel = new JPanel();

    public MultiplayerEndScreen() {
        endScreenImage = new ImageIcon(endScreenImageSource);
        singlePlayerButton.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/64.0)));
        quitGameButton.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/64.0)));
        newGameButton.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/64.0)));

        this.setLayout(new BorderLayout());

        newGameButton.setFocusable(false);
        bottomPanel.add(newGameButton, BorderLayout.WEST);

        quitGameButton.setFocusable(false);
        bottomPanel.add(quitGameButton, BorderLayout.CENTER);

        singlePlayerButton.setFocusable(false);
        bottomPanel.add(singlePlayerButton, BorderLayout.EAST);

        UpdateSize();

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void UpdateSize() {
        singlePlayerButton.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/64.0)));
        quitGameButton.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/64.0)));
        newGameButton.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/64.0)));
        bottomPanel.setPreferredSize(new Dimension((int) (GUI.screenWidth/1.92), (int) (GUI.screenHeight/7.20)));
        newGameButton.setPreferredSize(new Dimension((int) (GUI.screenWidth/6.4), (int) (GUI.screenHeight/10.8)));
        quitGameButton.setPreferredSize(new Dimension((int) (GUI.screenWidth/6.40), (int) (GUI.screenHeight/10.8)));
        singlePlayerButton.setPreferredSize(new Dimension((int) (GUI.screenWidth/6.40), (int) (GUI.screenHeight/10.8)));
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(endScreenImage.getImage(), (int) (GUI.screenWidth/9.6), (int) (GUI.screenHeight/3.6), null);
        g2D.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/12.8)));
        FontMetrics metrics = getFontMetrics(g.getFont());
        if (GUI.winner.equals("Draw")) {
            g2D.drawString("Both Guessed Correctly", ((GUI.screenWidth / 2) - (metrics.stringWidth("Both Guessed Correctly")) / 2), (int) (GUI.screenHeight/3.6));
        }
        else{
            g2D.drawString(GUI.winner + " is the winner", ((GUI.screenWidth / 2) - (metrics.stringWidth(GUI.winner + " is the winner")) / 2), (int) (GUI.screenHeight/3.10));
        }
    }
}

