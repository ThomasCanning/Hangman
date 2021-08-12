import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GUI extends JFrame{

    StartScreen startScreen = new StartScreen(this);
    MainScreen mainScreen = new MainScreen(this);
    JPanel panelContent = new JPanel();  //Creates a panel to hold sub panels
    CardLayout cl = new CardLayout();   //creates a card layout

    //creates GUI elements
    ImageIcon taskbarImage = new ImageIcon("TaskBarImage.png");

    public GUI() {

        //Sets up card layout that allows different panels to be swapped (game screens)
        panelContent.setLayout(cl);
        panelContent.add(startScreen, "1");
        panelContent.add(mainScreen, "2");
        cl.show(panelContent, "1");

        //Sets up the details of the main frame
        this.setTitle("Hangman");
        this.setIconImage(taskbarImage.getImage());//Sets this image to Image
        this.getContentPane().setBackground(new Color(237, 244, 237)); //Changes background color
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1920, 1080));
        this.add(panelContent);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sets what happens when X is pressed (closes application)
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        //Defines what happens when start button pressed
        startScreen.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContent, "2");
            }
        });
        }
    }
