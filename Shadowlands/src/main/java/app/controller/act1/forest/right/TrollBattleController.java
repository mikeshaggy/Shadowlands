package app.controller.act1.forest.right;

import app.model.Player;
import app.model.enemy.Enemy;
import app.model.enemy.ForestTroll;
import app.utility.CombatSystem;
import app.utility.GameSave;
import app.utility.GameSaveManager;
import app.utility.GameStateManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TrollBattleController implements Initializable {
    private GameSaveManager gameSaveManager;
    @FXML
    private Label playerName;
    @FXML
    private Label playerHealth;
    @FXML
    private Label enemyName;
    @FXML
    private Label enemyHealth;
    @FXML
    private Button attackButton;
    @FXML
    private Label outputText;
    @FXML
    private Button nextButton;
    private final Player player = GameStateManager.getPlayer();
    private final Enemy enemy = new ForestTroll();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font labelFont = Font.font("Skia", 30);
        Font buttonFont = Font.font("Skia", 40);
        playerName.setFont(labelFont);
        playerName.setText(player.getName());
        playerHealth.setFont(labelFont);
        playerHealth.setText("HP: " + player.getHealth());
        enemyName.setFont(labelFont);
        enemyName.setText(enemy.getName());
        enemyHealth.setFont(labelFont);
        enemyHealth.setText("HP: " + enemy.getHealth());
        attackButton.setFont(buttonFont);
        nextButton.setFont(Font.font("Skia", 20));
        nextButton.setVisible(false);

        gameSaveManager = new GameSaveManager();
    }

    public void attack() {
        if (!CombatSystem.isBattleOver()) {
            CombatSystem.attack(enemy, outputText, playerHealth, enemyHealth);
            if (CombatSystem.isBattleOver()) {
                if (!CombatSystem.isBattleWon()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Przegrana");
                    alert.setHeaderText("Przegrałeś walkę!");
                    alert.setContentText("Wczytaj swój ostatni zapis");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        loadGame();
                    }
                } else {
                    nextButton.setVisible(true);
                    showEndMessage(CombatSystem.isBattleWon());
                    nextButton.setVisible(true);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Walka dobiegła końca!");
            alert.showAndWait();
        }
    }

    public void showEndMessage(boolean battleWon) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Koniec walki");
        if (battleWon) {
            alert.setHeaderText("Wygrałeś!");
            alert.setContentText("Pokonałeś przeciwnika i zyskałeś 40 punktów doświadczenia");
            player.gainExperience(40);
        } else {
            alert.setHeaderText("Przegrałeś!");
            alert.setContentText("Twój bohater został pokonany!");
            player.setHealth(0);
        }
        alert.showAndWait();
    }

    public void next() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Act1/Forest/Right/TrollSlain.fxml"));
            Parent root = loader.load();

            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) attackButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CombatSystem.setBattleState(false);
    }

    public void loadGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik z zapisem gry");

        File selectedFile = fileChooser.showOpenDialog(attackButton.getScene().getWindow());

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
            Stage currentStage = (Stage) attackButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
