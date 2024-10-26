import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.Timer;

import java.awt.Color;

import java.awt.Container;


public class MainFrame extends JFrame{
    MenuScreen menuScreen = new MenuScreen(this);
    LevelBuilder levelBuilderObj = new LevelBuilder(this);
    JPanel levelBuilder = levelBuilderObj.levelBuilder();
    GamePanel game;
    Container contentPane;

    public MainFrame(){
        setTitle("BloxWorld");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 687);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = this.getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(menuScreen);

        setVisible(true);
    }

    public void switchMenuToLevelBuilder(){
        contentPane.removeAll();  // Remove all components
        contentPane.add(levelBuilder);
        contentPane.revalidate(); // Revalidate layout
        contentPane.repaint();    // Repaint to update UI

    }

    public void switchLevelBuilderToGame(){
        contentPane.removeAll();  // Remove all components
        GamePanel panel = new GamePanel(levelBuilderObj.getFilledCoords(), 
                        levelBuilderObj.getStartCoord(), levelBuilderObj.getGoalCoord(), false);
        panel.setPreferredSize(contentPane.getSize());  // Use contentPane's size
        panel.setBackground(Color.WHITE);

        contentPane.add(panel);  // Add panel to contentPane
        panel.setFocusable(true);  // Make panel focusable
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyChecker(panel.player));

        contentPane.revalidate(); // Revalidate layout
        contentPane.repaint(); 
        

    }
}
