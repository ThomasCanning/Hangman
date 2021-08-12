import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GUI extends JFrame{

    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;

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
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
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

        //Defines what happens when keyboard is pressed
        for(int i = 0; i < mainScreen.keyboardButtons.length; i++) {
            mainScreen.keyboardButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String keyboardInput =  e.getActionCommand();

                    //This character is player input
                    char playerGuess = keyboardInput.charAt(0);

                    for(int i =0; i < mainScreen.keyboardButtons.length; i++) {
                        if (mainScreen.keyboardButtons[i] == e.getSource()) {
                            mainScreen.keyboardButtons[i].setEnabled(false);
                        }
                    }
                }
            });
        }

        }
    }
