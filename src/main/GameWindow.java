package main;
import javax.swing.*;
import java.awt.*;

/**
 * The GameWindow class creates a JFrame window for the game to be displayed in.
 * It sets the window to be focusable, maximized, and undecorated.
 * It also adds a GamePanel to the window, sets the window's location and preferred size, and makes the window visible.
 * Finally, it requests focus for the window.
 *
 * @author Alan Liu
 */
public class GameWindow {

    /**
     * Constructs a new GameWindow object.
     * It initializes a JFrame, sets its properties, adds a GamePanel to it, and makes it visible.
     *
     * @param gamePanel the GamePanel to be added to the JFrame
     */
    public GameWindow(GamePanel gamePanel){
        JFrame jframe;
        jframe = new JFrame();
        jframe.setFocusable(true);
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jframe.setUndecorated(true);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        jframe.setVisible(true);
        jframe.requestFocus();
    }
}