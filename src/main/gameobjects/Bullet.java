package main.gameobjects;

import main.GameConstant;
import java.awt.*;

/**
 * Bullet is a class that extends BaseObject and represents a bullet in the game.
 * It contains properties like direction and which player shot it.
 */
public class Bullet extends BaseObject {

    // The direction of the bullet
    private boolean goingRight;

    // Whether the bullet was shot by player 1
    private boolean shotByPlayer1;

    /**
     * Constructs a new Bullet object.
     *
     * @param x the x position
     * @param y the y position
     * @param goingRight the direction of the bullet
     * @param shotByPlayer1 whether the bullet was shot by player 1
     */
    public Bullet(int x, int y, boolean goingRight, boolean shotByPlayer1) {
        setXpos(x);
        setYpos(y);
        this.goingRight = goingRight;
        this.shotByPlayer1 = shotByPlayer1;
    }

    /**
     * Updates the position of the bullet.
     * This method is called in the GamePanel class.
     */
    public void update() {
        if (goingRight) {
            setXpos(getXpos() + 8);
        } else {
            setXpos(getXpos() - 8);
        }
    }

    /**
     * Returns the bounds of the bullet.
     *
     * @return a Rectangle representing the bounds
     */
    public Rectangle getBounds() {
        return new Rectangle(getXpos(), getYpos() - 10, 20, 10);
    }

    /**
     * Checks if the bullet is out of bounds.
     * Gives a little extra room just to make sure the bullet doesn't get deleted until fully out of screen.
     *
     * @return true if the bullet is out of bounds, false otherwise
     */
    @Override
    public boolean outOfBounds() {
        return getXpos() < -100 || getXpos() > GameConstant.SCREEN_MAX_WIDTH + 100 || getYpos() < -100 || getYpos() >  GameConstant.SCREEN_MAX_HEIGHT + 100;
    }

    /**
     * Returns the if the bullet was shot by player 1
     *
     * @return true if the bullet was shot by player 1, false otherwise
     */
    public boolean getShotByPlayer1() {
        return shotByPlayer1;
    }

    /**
     * Draws the bullet.
     *
     * @param g the Graphics from gamePanel
     */
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getXpos(), getYpos() - 10, 20, 10);
    }
}