package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Collections;
import javax.swing.border.MatteBorder;

/**
 * The Calculator class creates a view for the calculator that simulates the
 * standard OS calculator.
 * 
 */
public class Calculator {
    
    //Windows 10 colors and fonts
//    final static Color COLOR_DISPLAY = Color.decode("#F2F2F2");
//    final static Color COLOR_MENU_HORIZONTAL = Color.decode("#F2F2F2");
//    final static Color COLOR_MENU_VERTICAL = Color.decode("#E6E6E6");
//    final static Color COLOR_NUMBERS = Color.decode("#E6E6E6");
//    final static Color COLOR_FONT = Color.decode("#000000");
//    final static Font FONT = new Font("Segoe", Font.BOLD, 20);
//    final static MatteBorder OS_BORDER = BorderFactory.createMatteBorder(0, 0, 0, 0, COLOR_DISPLAY);

    
    //Mac Majove colors and fonts
    final static Color COLOR_DISPLAY = Color.decode("#383531");
    final static Color COLOR_MENU_HORIZONTAL = Color.decode("#4C4844");
    final static Color COLOR_MENU_VERTICAL = Color.decode("#F1A33C");
    final static Color COLOR_NUMBERS = Color.decode("#696662");
    final static Color COLOR_FONT = Color.decode("#FFFFFF");
    final static Font FONT = new Font("Avenir", Font.PLAIN, 20);
    final static MatteBorder OS_BORDER = BorderFactory.createMatteBorder(1, 1, 0, 0, COLOR_DISPLAY);

    final static int WIDTH = 233;
    final static int HIEGHT = 343;

    public static void main(String[] args) {
        try {
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "WikiTeX");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //Forcing Look and Feel uncomment below
            //IManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Create window
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(WIDTH, HIEGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        //Container for all other components
        JPanel mainPanel = new JPanel();       
        mainPanel.setSize(new Dimension(WIDTH, HIEGHT));
        mainPanel.setBackground(COLOR_DISPLAY);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Display setup
        JPanel backPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        backPanel.setBackground(COLOR_DISPLAY);
        backPanel.setPreferredSize(new Dimension(WIDTH, 80));

        JLabel displayText = new JLabel("", SwingConstants.RIGHT);
        displayText.setLayout(new BorderLayout());
        displayText.setVerticalAlignment(JLabel.BOTTOM);
        displayText.setPreferredSize(new Dimension(WIDTH, 80));
        displayText.setBackground(COLOR_DISPLAY);
        displayText.setForeground(COLOR_FONT);
        Font font = FONT;
        font = font.deriveFont(0, 50);
        font = font.deriveFont(
                Collections.singletonMap(
                        TextAttribute.WEIGHT, 0.2));
        displayText.setFont(font);
        displayText.setText("0");

        c.weighty = 1;
        c.anchor = GridBagConstraints.PAGE_START;

        c.gridx = 0;
        c.gridy = 0;
        backPanel.add(displayText, c);//Add text to display window
        mainPanel.add(backPanel, c); //Add display window to main panel
        
        //Create panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(new Dimension(WIDTH, WIDTH / 4));
        buttonPanel.setBackground(COLOR_DISPLAY);
        
        //Create buttons
        String[] buttonContent = {"AC", "±", "%", "÷", "7", "8", "9", "×", "4", "5", "6", "-",
            "1", "2", "3", "+", "0", ".", "="};        
        JButton[] button = new JButton[buttonContent.length];
        for (int i = 0; i < button.length; i++) {
            button[i] = new JButton(buttonContent[i]);
            button[i].setFont(FONT);
            button[i].setPreferredSize(new Dimension(WIDTH / 4, WIDTH / 4 - 10));
            
            //style buttons seperately
            if (buttonContent[i].equals("0")) {
                button[i].setPreferredSize(new Dimension((WIDTH / 4) * 2, WIDTH / 4 - 10));
            }
            if (i <= 2) {
                button[i].setFont(font.deriveFont(0, 18));
                button[i].setBackground(COLOR_MENU_HORIZONTAL);

            } else if ((i + 1) % 4 == 0 | i == button.length - 1) {
                button[i].setFont(font.deriveFont(0, 30));
                button[i].setBackground(COLOR_MENU_VERTICAL);
            } else {
                button[i].setBackground(COLOR_NUMBERS);
            }
            button[i].setForeground(COLOR_FONT);
            button[i].setBorder(BorderFactory.createEmptyBorder());
            button[i].setBorder(OS_BORDER);
            button[i].setOpaque(true);

        }
        
        //Add first 4 rows of buttons to buttonPanel
        buttonPanel.setLayout(new GridLayout(4, 4));
        for (int i = 0; i < button.length - 3; i++) {
            buttonPanel.add(button[i]);
        }

        c.gridy++;
        mainPanel.add(buttonPanel, c); //Add buttonPanel to mainPanel
        
        //Add last 3 buttons to new buttonPanel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 0;
        for (int i = button.length - 3; i < button.length; i++) {
            buttonPanel.add(button[i], c2);
            c2.gridx++;
        }
        c.gridy++;
        
        
        mainPanel.add(buttonPanel, c); //Add buttonPanel to mainPanel

        mainPanel.setVisible(true);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
}