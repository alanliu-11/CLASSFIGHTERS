package inputs;

import main.GameComponents;
import main.GameConstant;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The KeyboardInputs class contains all the keyboard control logic.
 *
 * @author Alan Liu
 */
public class KeyboardInputs implements KeyListener {
    private final GamePanel gamePanel;
    private final boolean[] flag = new boolean[8];

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No implementation needed
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                flag[0] = true;
                break;
            case KeyEvent.VK_A:
                flag[1] = true;
                break;
            case KeyEvent.VK_S:
                flag[2] = true;
                break;
            case KeyEvent.VK_D:
                flag[3] = true;
                break;
            case KeyEvent.VK_UP:
                flag[4] = true;
                break;
            case KeyEvent.VK_LEFT:
                flag[5] = true;
                break;
            case KeyEvent.VK_DOWN:
                flag[6] = true;
                break;
            case KeyEvent.VK_RIGHT:
                flag[7] = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                flag[0] = false;
                break;
            case KeyEvent.VK_A:
                flag[1] = false;
                break;
            case KeyEvent.VK_S:
                flag[2] = false;
                break;
            case KeyEvent.VK_D:
                flag[3] = false;
                break;
            case KeyEvent.VK_UP:
                flag[4] = false;
                break;
            case KeyEvent.VK_LEFT:
                flag[5] = false;
                break;
            case KeyEvent.VK_DOWN:
                flag[6] = false;
                break;
            case KeyEvent.VK_RIGHT:
                flag[7] = false;
                break;
            case KeyEvent.VK_ESCAPE:
                if (gamePanel.getP1Lives() == 0 || gamePanel.getP2Lives() == 0) {
                    break;
                }
                if (gamePanel.gamePaused()) {
                    gamePanel.unpauseGame();
                } else {
                    gamePanel.pauseGame();
                }
                break;
            case KeyEvent.VK_R:
                if (gamePanel.returnGameEnd()) {
                    gamePanel.resetGame();
                }
                break;
            case KeyEvent.VK_L:
                if (gamePanel.returnGameEnd()) {
                    gamePanel.toggleLB();
                    gamePanel.repaint();
                }
                break;
            case KeyEvent.VK_P:
                if (gamePanel.gamePaused() || gamePanel.returnGameEnd()) {
                    System.exit(0);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Calls gamePanel to change the player x and y values when the w, a, s, and d keys are pressed.
     */
    public void update() {
        if (!gamePanel.gamePaused() && inBounds(gamePanel.getPlayer1().getPlayerX(), gamePanel.getPlayer1().getYpos() - 1) && flag[0] && !gamePanel.getPlayer1().getJumping()) {
            gamePanel.getPlayer1().jump();
        }
        if (!gamePanel.gamePaused() && inBounds(gamePanel.getPlayer1().getPlayerX() - 4, gamePanel.getPlayer1().getPlayerY()) && flag[1]) {
            gamePanel.changePlayer1X(-4);
            gamePanel.getPlayer1().setFacingRight(false);
        }
        if (!gamePanel.gamePaused() && inBounds(gamePanel.getPlayer1().getPlayerX() + 4, gamePanel.getPlayer1().getPlayerY()) && flag[3] && inBounds(gamePanel.getPlayer1().getPlayerX(), gamePanel.getPlayer1().getPlayerY())) {
            gamePanel.changePlayer1X(4);
            gamePanel.getPlayer1().setFacingRight(true);
        }
        if (!gamePanel.gamePaused() && inBounds(gamePanel.getPlayer2().getPlayerX() - 4, gamePanel.getPlayer2().getPlayerY()) && flag[4] && !gamePanel.getPlayer2().getJumping()) {
            gamePanel.getPlayer2().jump();
        }
        if (!gamePanel.gamePaused() && inBounds(gamePanel.getPlayer2().getPlayerX() - 4, gamePanel.getPlayer2().getPlayerY()) && flag[5]) {
            gamePanel.changePlayer2X(-4);
            gamePanel.getPlayer2().setFacingRight(false);
        }
        if (!gamePanel.gamePaused() && inBounds(gamePanel.getPlayer2().getPlayerX() + 1, gamePanel.getPlayer2().getPlayerY()) && flag[7] && inBounds(gamePanel.getPlayer2().getPlayerX(), gamePanel.getPlayer2().getPlayerY())) {
            gamePanel.changePlayer2X(4);
            gamePanel.getPlayer2().setFacingRight(true);
        }
    }

    /**
     * Checks if player is in bounds of the screen.
     */
    public boolean inBounds(double playerX, double playerY) {
        return playerX < gamePanel.getSize().width - 95 && playerX > -15 && playerY > 0;
    }

    public boolean shooting1() {
        return flag[2];
    }

    public boolean shooting2() {
        return flag[6];
    }
}