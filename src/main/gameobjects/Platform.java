package main.gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Platform is a class that extends BaseObject and represents a platform in the game.
 * It contains properties like position and dimensions.
 */
public class Platform extends BaseObject {
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    /**
     * Constructs a new Platform object.
     *
     * @param x the x position
     * @param y the y position
     * @param width the width of the platform
     * @param height the height of the platform
     */
    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Checks if the platform is out of bounds.
     * In this case, platforms are never out of bounds, so it always returns false.
     *
     * @return false, because platforms are never out of bounds
     */
    @Override
    public boolean outOfBounds() {
        return false;
    }

    /**
     * Returns the bounds of the platform.
     * This is used for collision detection.
     *
     * @return a Rectangle representing the bounds of the platform
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Draws the platform.
     *
     * @param g the Graphics object to draw on
     */
    public void draw(Graphics g) {
        g.fillRect(x, y, width+26, height);
    }
}