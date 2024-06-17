package main;

import inputs.KeyboardInputs;
import main.gameobjects.Bullet;
import main.gameobjects.Platform;
import main.gameobjects.Player;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

import static main.GameComponents.showGameStuff;

/**
 * Essential Control Panel
 * It extends JPanel and handles game logic, user input, and rendering.
 * It also maintains the game state, such as the players, bullets, and platforms.
 *
 * @author Alan Liu
 */
public class GamePanel extends JPanel{
    public KeyboardInputs k = new KeyboardInputs(this);
    public Timer timer;
    static Player player1;
    static Player player2;
    public int p1Lives = GameConstant.INIT_PLAYER_LIVES;
    public int p2Lives = GameConstant.INIT_PLAYER_LIVES;
    static ConcurrentHashMap<Bullet, Integer> bullets = new ConcurrentHashMap<>();
    public static boolean gamePaused = false;
    public boolean lbToggled = false;
    Platform platform1 = new Platform(350, GameConstant.SCREEN_MAX_HEIGHT-200, 150, 20); // Adjust these values as needed
    Platform platform2 = new Platform(GameConstant.SCREEN_MAX_WIDTH-450, GameConstant.SCREEN_MAX_HEIGHT-200, 150, 20);
    Platform platform3 = new Platform(GameConstant.SCREEN_MAX_WIDTH / 2 - 100, GameConstant.SCREEN_MAX_HEIGHT / 2, 200, 20);

    /**
     * Constructs a new GamePanel object.
     * It initializes the players, sets the background color, adds a key listener, and starts the game timer.
     *
     * @throws IOException if an error occurs while initializing the players
     */
    public GamePanel() throws IOException {
        player1 = new Player(GameConstant.PLAYER1_INIT_POSX, GameConstant.PLAYER1_INIT_POSY, true);
        player2 = new Player(GameConstant.PLAYER2_INIT_POSX, GameConstant.PLAYER2_INIT_POSY, false);
        player1.initializeImage();
        player2.initializeImage();
        this.setBackground(new Color(70, 145, 199, 255));
        addKeyListener(k);
        timer = new Timer(GameConstant.DELAY, e -> {
            repaint();
            k.update();
        });
        timer.start();
    }

    /**
     * Checks for collision between each bullet and each player.
     * If a bullet intersects with a player, the player loses a life and the bullet is removed.
     *
     * @param j the bullet to check for collision
     */
    private void checkBulletCollision(Bullet j){
        if(j.getShotByPlayer1() && j.getBounds().intersects(player2.getBounds())){
            player2.loseLife();
            bullets.remove(j);
        }
        else if(!j.getShotByPlayer1()&& j.getBounds().intersects(player1.getBounds())){
            player1.loseLife();
            bullets.remove(j);
        }
    }

    /**
     * Pauses the game.
     */
    public void pauseGame(){
        gamePaused = true;
    }

    /**
     * Unpauses the game.
     */
    public void unpauseGame(){
        gamePaused = false;
    }

    /**
     * Returns whether the game is paused.
     *
     * @return true if the game is paused, false otherwise
     */
    public boolean gamePaused(){
        return gamePaused;
    }

    /**
     * Toggles the lbToggled flag.
     */
    public void toggleLB(){
        lbToggled = !lbToggled;
    }

    /**
     * Returns player 1.
     *
     * @return player 1
     */
    public Player getPlayer1(){
        return player1;
    }

    /**
     * Returns player 2.
     *
     * @return player 2
     */
    public Player getPlayer2(){
        return player2;
    }

    /**
     * Returns the bullets in the game.
     *
     * @return the bullets in the game
     */
    public static ConcurrentHashMap<Bullet, Integer> getBullets(){
        return bullets;
    }

    /**
     * Returns the number of lives player 1 has.
     *
     * @return the number of lives player 1 has
     */
    public int getP1Lives(){
        return p1Lives;
    }

    /**
     * Returns the number of lives player 2 has.
     *
     * @return the number of lives player 2 has
     */
    public int getP2Lives(){
        return p2Lives;
    }

    /**
     * Returns whether the game has ended.
     * The game ends when either player has no lives left.
     *
     * @return true if the game has ended, false otherwise
     */
    public boolean returnGameEnd(){
        return player1.getLives() == 0 || player2.getLives() == 0;
    }

    /**
     * Changes the x position of player 1 by a certain amount.
     *
     * @param num the amount to change the x position by
     */
    public void changePlayer1X(int num){
        player1.setXpos(player1.getXpos() + num);
    }

    /**
     * Changes the x position of player 2 by a certain amount.
     *
     * @param num the amount to change the x position by
     */
    public void changePlayer2X(int num){
        player2.setXpos(player2.getXpos() + num);
    }

    /**
     * Draws the game state on the screen.
     * This includes the players, bullets, platforms, and game screens (game stuff, pause screen, end screen).
     *
     * @param g the Graphics object to draw on
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if (!gamePaused && !returnGameEnd()){
            showGameStuff(g, player1, player2);
            for (Bullet i : bullets.keySet()){
                checkBulletCollision(i);
                i.update();
                if (i.outOfBounds()){
                    bullets.remove(i);
                }
                i.draw(g);
            }
            if (player1.getFacingRight()){
                g.drawImage(player1.getSprites().get(1),player1.getPlayerX(), player1.getPlayerY(), null);
            }
            else{
                g.drawImage(player1.getSprites().get(0),player1.getPlayerX(), player1.getPlayerY(), null);
            }
            if (player2.getFacingRight()){
                g.drawImage(player2.getSprites().get(1),player2.getPlayerX(), player2.getPlayerY(), null);
            }
            else{
                g.drawImage(player2.getSprites().get(0),player2.getPlayerX(), player2.getPlayerY(), null);
            }
            platform1.draw(g);
            platform2.draw(g);
            platform3.draw(g);
            while ((getPlayer1().collidesWith(platform1) || getPlayer1().collidesWith(platform2)|| getPlayer1().collidesWith(platform3)) && getPlayer1().getDy() > 0) {
                // Adjust player1's vertical position and velocity
                getPlayer1().setJumping(false);
                getPlayer1().setDy(0);
                getPlayer1().setYpos(getPlayer1().getPlayerY() - 1);
            }

            while ((getPlayer2().collidesWith(platform1) || getPlayer2().collidesWith(platform2) || getPlayer2().collidesWith(platform3))&& getPlayer2().getDy() > 0) {
                getPlayer2().setJumping(false);
                getPlayer2().setDy(0);
                getPlayer2().setYpos(getPlayer2().getPlayerY() - 1);
            }
        }
        else if (gamePaused()){
            GameComponents.showPauseScreen(g);
        }
        else{
            String winner;
            if (player1.getLives() == 0){
                winner = "Orange Wins!";
            }
            else{
                winner = "Blue Wins!";

            }
            GameComponents.showEndScreen(g, winner);

        }
    }

    /**
     * Resets the game by setting the players' lives back to the initial value.
     */
    public void resetGame(){
        player1.setPlayerLives(5);
        player2.setPlayerLives(5);
    }
}