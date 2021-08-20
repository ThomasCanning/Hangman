import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class StartScreen extends JPanel{

    JButton startButton = new JButton();

    StartScreen(GUI parent) { //Start screen panel GUI
        this.setLayout(new BorderLayout());

        ImageIcon startScreenImage = new ImageIcon(getClass().getClassLoader().getResource("StartScreenImage.png"));

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

        startButton.setText("Start");
        startButton.setFocusable(false);
        startButton.setPreferredSize(new Dimension(300, 100));
        bottomPanel.add(startButton);
        startButton.setVerticalAlignment(JButton.CENTER);

        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(label);
    }
}
