import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class StartScreen extends JPanel{

    JButton startSinglePlayerButton = new JButton("Singleplayer");
    JButton startMultiPlayerButton = new JButton("Multiplayer");

    StartScreen() { //Start screen panel GUI
        this.setLayout(new BorderLayout());

        ImageIcon startScreenImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("StartScreenImage.png")));

        JLabel label = new JLabel();
        label.setIcon(startScreenImage);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setText("Hangman");
        label.setFont(new Font("Comic Sans", Font.BOLD, 200));
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setIconTextGap(40);
        label.setForeground(new Color(64,64,64));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(1000, 150));

        startSinglePlayerButton.setFocusable(false);
        startSinglePlayerButton.setPreferredSize(new Dimension(300, 100));
        bottomPanel.add(startSinglePlayerButton);
        startSinglePlayerButton.setVerticalAlignment(JButton.CENTER);
        startMultiPlayerButton.setFont(new Font("Comic Sans", Font.BOLD, 30));
        startSinglePlayerButton.setFont(new Font("Comic Sans", Font.BOLD, 30));


        startMultiPlayerButton.setFocusable(false);
        startMultiPlayerButton.setPreferredSize(new Dimension(300, 100));
        bottomPanel.add(startMultiPlayerButton);
        startMultiPlayerButton.setVerticalAlignment(JButton.CENTER);

        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(label);
    }
}
