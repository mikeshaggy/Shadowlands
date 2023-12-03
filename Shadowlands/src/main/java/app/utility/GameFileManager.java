package app.utility;

import java.io.*;

public class GameFileManager {
    public static void saveGame(GameSave gameSave, String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(gameSave);
            System.out.println("Zapisano stan gry w pliku: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameSave loadGame(String filePath) {
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
}
