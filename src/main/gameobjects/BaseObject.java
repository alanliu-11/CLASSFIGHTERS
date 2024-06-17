package main.gameobjects;

import java.awt.image.BufferedImage;

/**
 * BaseObject is an abstract class that represents a basic game object.
 * It contains common properties like position and image that all game objects have.
 * It also declares an abstract method for checking if the object is out of bounds.
 */
public abstract class BaseObject {

    // The x and y coordinates of the object
    private int xPos;
    private int yPos;

    // The image of the object
    public BufferedImage playerImage;

    /**
     * Returns the x position of the object.
     *
     * @return the x position
     */
    public int getXpos() {
        return xPos;
    }

    /**
     * Sets the x position of the object.
     *
     * @param x the new x position
     */
    public void setXpos(int x) {
        this.xPos = x;
    }

    /**
     * Returns the y position of the object.
     *
     * @return the y position
     */
    public int getYpos() {
        return yPos;
    }

    /**
     * Sets the y position of the object.
     *
     * @param ypos the new y position
     */
    public void setYpos(int ypos) {
        this.yPos = ypos;
    }

    /**
     * Sets the image of the object.
     *
     * @param image the new image
     */
    public void setImage(BufferedImage image) {
        this.playerImage = image;
    }

    /**
     * Checks if the object is out of bounds.
     * This method needs to be implemented in each child class,
     * as different objects may have different logic for determining if they are out of bounds.
     *
     * @return true if the object is out of bounds, false otherwise
     */
    public abstract boolean outOfBounds();
}