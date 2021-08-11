import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GUI extends JFrame implements ActionListener {

    JFrame frame = new JFrame("Hangman");
    JPanel panelContent = new JPanel();
    JPanel panelMain = new JPanel();
    JPanel panelEnd = new JPanel();
    CardLayout cl = new CardLayout();

    ImageIcon image;
    JLabel label;
    JButton startButton;
    JPanel bottomPanel;
    static JPanel panelStart;

    public GUI() {
        panelContent.setLayout(cl);
        label = new JLabel();
        image = new ImageIcon("Logo1.png");
        startButton = new JButton();
        bottomPanel = new JPanel();
        panelStart = new JPanel();

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

        panelStart.setLayout(new BorderLayout());

        startButton.setText("Start");
        startButton.setFocusable(false);
        startButton.setPreferredSize(new Dimension(300, 100));
        bottomPanel.add(startButton);
        startButton.setVerticalAlignment(JButton.CENTER);

        panelStart.add(bottomPanel, BorderLayout.SOUTH);
        panelStart.add(label);

        panelContent.add(panelStart, "1");
        panelContent.add(panelMain, "2");
        panelContent.add(panelEnd, "3");
        cl.show(panelContent, "1");

        startButton.addActionListener(this);

        frame.add(panelContent);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == startButton) {
            cl.show(panelContent, "2");
        }
    }

}





