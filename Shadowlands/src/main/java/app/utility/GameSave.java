package app.utility;

import app.model.Player;

import java.io.Serializable;
import java.time.LocalDateTime;

public class GameSave implements Serializable {
    private String saveName;
    private LocalDateTime creationTime;
    private Player player;
    private String currentScenePath;

    public GameSave(String saveName, Player player, String currentScenePath) {
        this.saveName = saveName;
        this.creationTime = LocalDateTime.now();
        this.player = player;
        this.currentScenePath = currentScenePath;
    }

    public String getSaveName() {
        return saveName;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Player getPlayer() {
        return player;
    }

    public String getCurrentScenePath() {
        return currentScenePath;
    }
}
