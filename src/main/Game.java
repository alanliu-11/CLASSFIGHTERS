package main;

import java.io.IOException;

/**
 * The Game class is the main class where everything is run.
 * It implements the Runnable interface, which creates a thread.
 * It contains the game loop and handles the game logic.
 *
 * @author Alan Liu
 */
public class Game implements Runnable{
    // The speed of game ticks
    final double tickSpeed = 1000000000.0 / GameConstant.TICK_SPEED;

    // The speed of frames
    final double frameSpeed = 1000000000.0 / GameConstant.FPS_SET;

    // Flags to check if player 1 and player 2 can shoot
    boolean canMakeBullet1 = true;
    boolean canMakeBullet2 = true;

    // The last time player 1 and player 2 shot a bullet
    long lastBulletCheck1 = System.currentTimeMillis();
    long lastBulletCheck2 = System.currentTimeMillis();

    // The game panel
    private final static GamePanel gamePanel;

    // Initialize the game panel
    static {
        try {
            gamePanel = new GamePanel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // The game window
    public static GameWindow gameWindow = new GameWindow(gamePanel);

    /**
     * Constructs a new Game object.
     * It requests focus for the game panel and starts the game loop.
     */
    public Game(){
        gamePanel.requestFocus();
        startGameLoop();
    }

    /**
     * Starts the game loop by creating a new thread and starting it.
     */
    private void startGameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * The game loop.
     * It updates the game state and redraws the game panel at a fixed rate.
     * It also handles player shooting.
     */
    @Override
    public void run() {
        long lastFrame = System.nanoTime();
        long lastTick = System.nanoTime();
        long now;
        while (true) {
            if(!gamePanel.gamePaused()){
                now = System.nanoTime();
                if (now - lastTick >= tickSpeed) {
                    lastTick = now;
                    gamePanel.k.update();
                    gamePanel.getPlayer1().update();
                    gamePanel.getPlayer2().update();
                }
                if (now -lastFrame >= frameSpeed){
                    lastFrame = now;
                    gamePanel.repaint();
                }
                long currentTime = System.currentTimeMillis();
                if (gamePanel.k.shooting1() && canMakeBullet1) {
                    canMakeBullet1 = false;
                    gamePanel.getPlayer1().shoot(true);
                    System.out.println("Shooting");
                    System.out.println(canMakeBullet1);
                }
                if (gamePanel.k.shooting2() && canMakeBullet2) {
                    canMakeBullet2 = false;
                    gamePanel.getPlayer2().shoot(false);
                }
                if (!canMakeBullet1 && currentTime - lastBulletCheck1 > GameConstant.DELAY_BULLET){
                    lastBulletCheck1 = currentTime;
                    canMakeBullet1 = true;
                }
                if (!canMakeBullet2 && currentTime - lastBulletCheck2 > GameConstant.DELAY_BULLET){
                    lastBulletCheck2 = currentTime;
                    canMakeBullet2 = true;
                }
            }
        }
    }
}