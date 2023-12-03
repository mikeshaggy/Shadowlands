package app.controller.intro;

import app.model.Player;
import app.utility.GameSave;
import app.utility.GameSaveManager;
import app.utility.GameStateManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class StartController {
    @FXML
    private Button newGameButton;
    @FXML
    private Button loadGameButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label titleLabel;
    private GameSaveManager gameSaveManager;

    public void initialize() {
        Font buttonFont = Font.loadFont(getClass().getResourceAsStream("/skia.ttf"), 36);
        Font labelFont = Font.font("Skia", 96);
        newGameButton.setFont(buttonFont);
        loadGameButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);
        titleLabel.setFont(labelFont);

        gameSaveManager = new GameSaveManager();
    }
    public void startNewGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Intro/CharacterCreation.fxml"));
            Parent root = loader.load();

            Scene characterCreatorScene = new Scene(root);
            Stage currentStage = (Stage) newGameButton.getScene().getWindow();

            currentStage.setScene(characterCreatorScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadGame() {
        FileChooser fileChooser = new FileChooser();

        File initialDirectory = new File(System.getProperty("user.dir") + "/src/data");
        fileChooser.setInitialDirectory(initialDirectory);

        File selectedFile = fileChooser.showOpenDialog(loadGameButton.getScene().getWindow());

        if (selectedFile != null) {
            GameSave gameSave = gameSaveManager.loadGame(selectedFile.getAbsolutePath());

            if (gameSave != null) {
                Player loadedPlayer = gameSave.getPlayer();
                String currentScenePath = gameSave.getCurrentScenePath();
                GameStateManager.setPlayer(loadedPlayer);
                GameStateManager.setCurrentScenePath(currentScenePath);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Wystąpił błąd przy próbie wczytania zapisu");
                alert.setContentText(null);
                alert.showAndWait();
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(GameStateManager.getCurrentScenePath()));
            Parent root = loader.load();

            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) loadGameButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Wyjście");
        alert.setHeaderText("Czy jesteś pewny, że chcesz wyjść?");
        alert.setContentText("Niezapisany postęp zostanie utracony");

        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        ButtonType response = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (response == ButtonType.OK) {
            Platform.exit();
        }
    }
}

