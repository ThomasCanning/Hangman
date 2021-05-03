package GUI;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    MyFrame() {
        this.setTitle("Hangman"); //Sets title of this at top
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sets what happens when X is pressed (closes application)
        this.setResizable(true); //Allows this to be resizeable
        this.setSize(500, 500); //sets dimensions of this
        this.setVisible(true); //makes this visible

        ImageIcon Image = new ImageIcon("Logo.png");
        this.setIconImage(Image.getImage());//Sets this image to Image
        this.getContentPane().setBackground(new Color(237, 244, 237)); //Changes background color
    }
}
