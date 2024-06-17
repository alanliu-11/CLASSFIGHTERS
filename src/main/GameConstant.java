package main;

import java.awt.*;

/**
 * Holds all the game constants
 * @author Alan Liu
 */
public class GameConstant {
    public static final String PLAYER1L_IMAGE_PATH = "src/res/TallKid1Left.png";
    public static final String PLAYER2L_IMAGE_PATH = "src/res/TallKid2Left.png";
    public static final String PLAYER1R_IMAGE_PATH = "src/res/TallKid1Right.png";
    public static final String PLAYER2R_IMAGE_PATH = "src/res/TallKid2Right.png";
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int SCREEN_MAX_HEIGHT = (int) SCREEN_SIZE.getHeight() ;
    public static final int SCREEN_MAX_WIDTH = (int) SCREEN_SIZE.getWidth();
    public static final int DELAY_BULLET = 500;
    public static final int PLAYER1_INIT_POSX = SCREEN_MAX_WIDTH/4;
    public static final int PLAYER1_INIT_POSY = SCREEN_MAX_HEIGHT;
    public static final int PLAYER2_INIT_POSX = SCREEN_MAX_WIDTH*3/4;
    public static final int PLAYER2_INIT_POSY = 0;
    public static final int DELAY = 25;
    public static final int FPS_SET = 120;
    public static final int TICK_SPEED = 120;
    public static final int INIT_PLAYER_LIVES = 5;
    public static final double GRAVITY = 0.5;

    public GameConstant() {
    }
}
