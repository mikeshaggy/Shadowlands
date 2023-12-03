package app.controller.general;

import app.model.Player;
import app.utility.GameSaveManager;
import app.utility.GameStateManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionsController implements Initializable {
    private final String DATA_PATH = "/src/data";
    @FXML
    private Label optionsLabel;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;
    private Scene previousScene;
    @FXML
    private TextField fileNameTextField;
    @FXML
    private Button confirmButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font buttonFont = Font.font("Skia", 36);
        optionsLabel.setFont(Font.font("Skia", 48));
        saveButton.setFont(buttonFont);
        backButton.setFont(buttonFont);
        fileNameTextField.setVisible(false);
        confirmButton.setVisible(false);
    }
    public void setPreviousScene(Scene scene) {
        previousScene = scene;
    }
    public void goBackToPreviousScene() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previousScene);
        stage.show();
    }
    private void createDataFolderIfNotExists() {
        File dataFolder = new File(DATA_PATH);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }
    public void saveGame() {
        createDataFolderIfNotExists();
        String fileName = fileNameTextField.getText();
        String saveDataDirectory = System.getProperty("user.dir") + DATA_PATH;
        String filePath = saveDataDirectory + "/" + fileName;

        Player player = GameStateManager.getPlayer();

        GameSaveManager gameSaveManager = new GameSaveManager();

        gameSaveManager.saveGame(fileName, player, filePath, GameStateManager.getCurrentScenePath());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukces");
        alert.setHeaderText("Pomyślnie zapisano stan gry");
        alert.setContentText("Pliki zapisywane są w folderze /src/data w folderze źródłowym gry");
        alert.showAndWait();

        fileNameTextField.clear();
        fileNameTextField.setVisible(false);
        confirmButton.setVisible(false);
    }
    public void showTextField() {
        fileNameTextField.setVisible(true);
        confirmButton.setVisible(true);
    }
}
