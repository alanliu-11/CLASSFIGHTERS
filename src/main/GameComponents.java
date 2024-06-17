package main;

import main.gameobjects.Player;
import java.awt.*;

/**
 * The GameComponents class is responsible for displaying important components in the game,
 * such as the end screen, pause screen, and lives/score in the game.
 */
public class GameComponents {

    /**
     * Displays the end screen with the winner and options to play again or exit.
     *
     * @param g the Graphics object to draw on
     * @param winner the winner of the game
     */
    public static void showEndScreen(Graphics g, String winner){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(100, 100, GameConstant.SCREEN_MAX_WIDTH-200, GameConstant.SCREEN_MAX_HEIGHT-200);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth("Game Finished! Player " + winner);
        g.drawString("Game Finished! Player " + winner, (GameConstant.SCREEN_MAX_WIDTH - textWidth) / 2, GameConstant.SCREEN_MAX_HEIGHT/2 -200);
        textWidth = fm.stringWidth("Press R to play again");
        g.drawString("Press R to play again", (GameConstant.SCREEN_MAX_WIDTH - textWidth) / 2, GameConstant.SCREEN_MAX_HEIGHT/2);
        textWidth = fm.stringWidth("Press P to exit");
        g.drawString("Press P to exit", (GameConstant.SCREEN_MAX_WIDTH - textWidth) / 2, GameConstant.SCREEN_MAX_HEIGHT/2 + 200);
    }

    /**
     * Displays the pause screen with options to unpause or exit the game.
     *
     * @param g the Graphics object to draw on
     */
    public static void showPauseScreen(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.drawString("Game Paused!", GameConstant.SCREEN_MAX_WIDTH/2 - 350, GameConstant.SCREEN_MAX_HEIGHT/2);
        g.setColor(Color.darkGray);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Press Esc to Unpause, press P to exit the application", GameConstant.SCREEN_MAX_WIDTH/2 - 625, GameConstant.SCREEN_MAX_HEIGHT/2 + 200);
    }

    /**
     * Displays the lives of both players.
     *
     * @param g the Graphics object to draw on
     * @param player the first player
     * @param player2 the second player
     */
    public static void showGameStuff(Graphics g, Player player, Player player2){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Orange Lives: " + player.getLives() + "                  " +
                "Blue Lives: " + player2.getLives(), 300, 50);
    }
}