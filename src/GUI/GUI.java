package GUI;

import javax.swing.*;
import java.awt.*;

public class GUI{

    public static void main(String[] args) {

        ImageIcon image = new ImageIcon("src/com/HangmanGame/GUI/Logo.png");

        //New frame with label
        JLabel label = new JLabel();
        MyFrame myFrame = new MyFrame();

        label.setText("Hangman");
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT, CENTER or RIGHT of image icon
        label.setVerticalTextPosition(JLabel.TOP);//set text vertical position
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial Rounded MT Bold", Font.BOLD,50)); //Sets font and style of text
        label.setIconTextGap(100); //Sets space between text and image
        myFrame.add(label);

    }
}
