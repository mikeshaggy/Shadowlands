package app.utility;

import app.model.Player;

public class GameStateManager {
    private static Player player;
    private static String currentScenePath;
    public static Player getPlayer() {
        return player;
    }
    public static void setPlayer(Player player) {
        GameStateManager.player = player;
    }
    public static String getCurrentScenePath() {
        return currentScenePath;
    }

    public static void setCurrentScenePath(String currentScenePath) {
        GameStateManager.currentScenePath = currentScenePath;
    }
}
