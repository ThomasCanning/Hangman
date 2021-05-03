import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {

    ImageIcon image;
    JLabel label;
    JButton startButton;
    JPanel bottomPanel;
    JPanel mainPanel;

    boolean start = false;

    public MyFrame() {

            label = new JLabel();
            image = new ImageIcon("Logo1.png");
            startButton = new JButton();
            bottomPanel = new JPanel();
            mainPanel = new JPanel();

            this.setTitle("Hangman"); //Sets title of this at top
            this.setIconImage(image.getImage());//Sets this image to Image
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sets what happens when X is pressed (closes application)
            this.setMinimumSize(new Dimension(1000, 720));
            this.getContentPane().setBackground(new Color(237, 244, 237)); //Changes background color
            this.setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(1280, 720));

            label.setIcon(image);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.TOP);
            label.setText("Hangman");
            label.setFont(new Font("Comic Sans", Font.BOLD, 150));
            label.setVerticalTextPosition(JLabel.TOP);
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setIconTextGap(50);

            bottomPanel.setPreferredSize(new Dimension(1000, 150));

            mainPanel.setLayout(new BorderLayout());

            mainPanel.add(bottomPanel, BorderLayout.SOUTH);

            startButton.setText("Start");
            startButton.setFocusable(false);
            startButton.setPreferredSize(new Dimension(300, 100));
            bottomPanel.add(startButton);
            startButton.setVerticalAlignment(JButton.CENTER);
            startButton.addActionListener(this);

            mainPanel.add(label);
            this.add(mainPanel);
            this.pack();
            this.setVisible(true);

        }

        @Override
        public void actionPerformed (ActionEvent e){
            if (e.getSource() == startButton) {
                mainPanel.setVisible(false);
                System.out.println("Start");
                //Code to start game
            }
        }

    }
