import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class StartScreen extends JPanel{

    JButton startSinglePlayerButton = new JButton("Singleplayer");
    JButton startMultiPlayerButton = new JButton("Multiplayer");
    JLabel label = new JLabel();
    JPanel bottomPanel = new JPanel();
    ImageIcon startScreenImageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("StartScreenImage.png")));
    Image startScreenImage = startScreenImageIcon.getImage().getScaledInstance((int) (GUI.screenWidth/1.50), (int) (GUI.screenHeight/1.90), Image.SCALE_DEFAULT);

    StartScreen() { //Start screen panel GUI
        this.setLayout(new BorderLayout());

        startScreenImageIcon = new ImageIcon(startScreenImage);

        label.setIcon(startScreenImageIcon);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setText("Hangman");
        label.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/9.60)));
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setForeground(new Color(64,64,64));

        startSinglePlayerButton.setFocusable(false);
        bottomPanel.add(startSinglePlayerButton);
        startSinglePlayerButton.setVerticalAlignment(JButton.CENTER);

        startMultiPlayerButton.setFocusable(false);
        bottomPanel.add(startMultiPlayerButton);
        startMultiPlayerButton.setVerticalAlignment(JButton.CENTER);

        UpdateSize();
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(label);
    }
    public void UpdateSize() {
        label.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenHeight/5.40)));
        label.setIconTextGap((int) (GUI.screenHeight/27.0));
        startSinglePlayerButton.setPreferredSize(new Dimension((int) (GUI.screenWidth/6.40), (int) (GUI.screenHeight/10.8)));
        startMultiPlayerButton.setPreferredSize((new Dimension((int) (GUI.screenWidth/6.40), (int) (GUI.screenHeight/10.8))));
        startMultiPlayerButton.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/64.0)));
        startSinglePlayerButton.setFont(new Font("Comic Sans", Font.BOLD, (int) (GUI.screenWidth/64.0)));
        bottomPanel.setPreferredSize(new Dimension((int) (GUI.screenWidth/1.92), (int) (GUI.screenHeight/7.20)));
        startScreenImage = startScreenImageIcon.getImage().getScaledInstance((int) (GUI.screenHeight/0.84), (int) (GUI.screenHeight/1.90), Image.SCALE_DEFAULT);
    }
}
