package main.gameobjects;

import main.GameConstant;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Player class represents a player in the game.
 * It extends the BaseObject class and contains properties like position, lives, jumping state, and sprites.
 * It also contains methods for updating the player's state and handling actions like jumping and shooting.
 *
 * @author Alan Liu
 */
public class Player extends BaseObject {
    private long lastLifeLossTime;
    private static final long LIFE_LOSS_DELAY = 250;

    /**
     * Returns the height of the player's image.
     *
     * @return the height of the player's image
     */
    private int getHeight() {
        return this.playerImage.getHeight();
    }

    private int playerLives = GameConstant.INIT_PLAYER_LIVES;
    private double dy = 0;
    private boolean isJumping;
    private boolean p1;
    private ArrayList<BufferedImage> sprites;
    private boolean facingRight;

    /**
     * Returns the width of the player's image.
     *
     * @return the width of the player's image
     */
    public int getWidth() {
        return this.playerImage.getWidth()-20;
    }

    /**
     * Constructs a new Player object.
     *
     * @param posx the x position
     * @param posy the y position
     * @param p1 whether the player is player 1
     */
    public Player(int posx, int posy, boolean p1) {
        setXpos(posx);
        setYpos(posy);
        setPlayer(p1);
    }

    /**
     * Sets whether the player is player 1.
     *
     * @param isOrange whether the player is player 1
     */
    private void setPlayer(boolean isOrange){
        p1 = isOrange;
    }

    /**
     * Sets the jumping state of the player.
     *
     * @param jumping the new jumping state
     */
    public void setJumping (boolean jumping){
        isJumping = jumping;
    }

    /**
     * Returns the jumping state of the player.
     *
     * @return the jumping state of the player
     */
    public boolean getJumping() {
        return isJumping;
    }

    /**
     * Initializes the player's image.
     * This is called when the image is initialized in GamePanel.
     *
     * @throws IOException if an error occurs while reading the image file
     */
    public void initializeImage() throws IOException {
        BufferedImage image;
        sprites = new ArrayList<>();
        if (p1){
            sprites.add(ImageIO.read(new File(GameConstant.PLAYER1L_IMAGE_PATH)));
            sprites.add(ImageIO.read(new File(GameConstant.PLAYER1R_IMAGE_PATH)));
            image = ImageIO.read(new File(GameConstant.PLAYER1L_IMAGE_PATH));
        }
        else{
            sprites.add(ImageIO.read(new File(GameConstant.PLAYER2L_IMAGE_PATH)));
            sprites.add(ImageIO.read(new File(GameConstant.PLAYER2R_IMAGE_PATH)));
            image = ImageIO.read(new File(GameConstant.PLAYER2L_IMAGE_PATH));
        }
        this.setImage(image);
    }

    /**
     * Checks if the player is out of bounds.
     *
     * @return true if the player is out of bounds, false otherwise
     */
    @Override
    public boolean outOfBounds(){
        return this.getYpos() > GameConstant.SCREEN_MAX_HEIGHT - getHeight() || this.getXpos() > GameConstant.SCREEN_MAX_WIDTH - getWidth();
    }

    /**
     * Returns the x position of the player.
     * If the player's x position is greater than the screen width, it sets the x position to the screen width minus the player's width.
     *
     * @return the x position of the player
     */
    public int getPlayerX(){
        if (this.getXpos() > GameConstant.SCREEN_MAX_WIDTH + 20)
            this.setXpos(GameConstant.SCREEN_MAX_WIDTH -getWidth()) ;
        return this.getXpos();
    }

    /**
     * Returns the y position of the player.
     * If the player's y position is greater than the screen height, it sets the y position to the screen height minus the player's height.
     *
     * @return the y position of the player
     */
    public int getPlayerY(){
        if (this.getYpos() > GameConstant.SCREEN_MAX_HEIGHT + 20)
            this.setYpos(GameConstant.SCREEN_MAX_HEIGHT -getHeight());
        return this.getYpos();
    }

    /**
     * Decreases the player's lives by 1 if enough time has passed since the last life loss.
     */
    public void loseLife() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastLifeLossTime >= LIFE_LOSS_DELAY) {
            playerLives--;
            lastLifeLossTime = currentTime;
        }
    }

    /**
     * Returns the number of lives the player has.
     *
     * @return the number of lives the player has
     */
    public int getLives(){
        return playerLives;
    }

    /**
     * Sets the number of lives the player has.
     *
     * @param set the new number of lives
     */
    public void setPlayerLives(int set){
        playerLives = set;
    }

    /**
     * Makes the player jump if the player is not already jumping.
     */
    public void jump() {
        if (!isJumping){
            dy = -16;
            isJumping = true;
        }
    }

    /**
     * Sets the vertical velocity of the player.
     *
     * @param newDy the new vertical velocity
     */
    public void setDy (double newDy){
        dy = newDy;
    }

    /**
     * Returns the vertical velocity of the player.
     *
     * @return the vertical velocity of the player
     */
    public double getDy () {
        return dy;
    }

    /**
     * Makes the player shoot a bullet.
     *
     * @param shotByP1 whether the bullet was shot by player 1
     */
    public void shoot(boolean shotByP1) {
        boolean goingRight = this.getFacingRight();
        double bulletX = goingRight ? this.getXpos() + this.getWidth() : this.getXpos();
        double bulletY = this.getYpos() + (double) this.getHeight() / 2;
        GamePanel.getBullets().put(new Bullet((int) bulletX, (int) bulletY, goingRight, shotByP1), 0);
    }

    /**
     * Updates the player's state.
     * This includes updating the player's position based on the vertical velocity and handling jumping and falling.
     */
    public void update() {
        this.setYpos((int)(this.getYpos() + dy));

        // Prevent player from falling through the floor
        if (this.getYpos() > GameConstant.SCREEN_MAX_HEIGHT - this.getHeight() - 20) {
            dy = 0;
            isJumping = false;
            this.setYpos(GameConstant.SCREEN_MAX_HEIGHT - this.getHeight() - 20);
        }
        else{
            dy += GameConstant.GRAVITY;
        }
        if (dy != 0){
            isJumping = true;
        }
    }

    /**
     * Sets whether the player is facing right.
     *
     * @param facingRight whether the player is facing right
     */
    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    /**
     * Returns whether the player is facing right.
     *
     * @return whether the player is facing right
     */
    public boolean getFacingRight() {
        return facingRight;
    }

    /**
     * Returns the player's sprites.
     *
     * @return the player's sprites
     */
    public ArrayList<BufferedImage> getSprites(){
        return sprites;
    }

    /**
     * Returns the bounds of the player.
     * This is used for collision detection.
     *
     * @return a Rectangle representing the bounds of the player
     */
    public Rectangle getBounds() {
        return new Rectangle(getXpos(), getYpos(), getWidth(), getHeight());
    }

    /**
     * Checks if the player collides with a platform.
     *
     * @param platform the platform to check collision with
     * @return true if the player collides with the platform, false otherwise
     */
    public boolean collidesWith(Platform platform) {
        return getBounds().intersects(platform.getBounds());
    }
}