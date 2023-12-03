package app.utility;

import app.model.Player;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class GameSaveManager {
    private List<GameSave> gameSaves;

    public GameSaveManager() {
        gameSaves = new ArrayList<>();
    }

    public void saveGame(String saveName, Player player, String filePath, String currentPath) {
        GameSave gameSave = new GameSave(saveName, player, currentPath);
        GameFileManager.saveGame(gameSave, filePath);
        gameSaves.add(gameSave);
    }

    public GameSave loadGame(String filePath) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            GameSave gameSave = (GameSave) objectIn.readObject();
            System.out.println("Wczytano stan gry z pliku: " + filePath);
            return gameSave;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Błąd podczas wczytywania stanu gry: " + e.getMessage());
        }
        return null;
    }

    public List<GameSave> getGameSaves() {
        return gameSaves;
    }
}
