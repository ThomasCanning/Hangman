package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GUI{

    public static void main(String[] args) {

        ImageIcon image = new ImageIcon("Logo.png");
        Border border = BorderFactory.createLineBorder(Color.gray,3);

        //New frame with label
        JLabel label = new JLabel();
        MyFrame myFrame = new MyFrame();

        label.setText("Hangman");
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT, CENTER or RIGHT of image icon
        label.setVerticalTextPosition(JLabel.TOP);//set text vertical position against image
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial Rounded MT Bold", Font.BOLD,50)); //Sets font and style of text
        label.setIconTextGap(100); //Sets space between text and image
        //label.setBackground(Color.); //sets background color to black
        //label.setOpaque(true);
        label.setBorder(border); //sets border of window
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(100,0,250,250); //Sets x,y and dimensions of label

        myFrame.add(label);
        myFrame.setLayout(null);
        myFrame.pack();

    }
}
