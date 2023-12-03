package app.controller.prologue;

import app.model.Player;
import app.model.enemy.BasicGoblin;
import app.model.enemy.Enemy;
import app.utility.CombatSystem;
import app.utility.GameStateManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BasicGoblinBattleController implements Initializable {
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
    private final Enemy enemy = new BasicGoblin();
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
    }
    public void attack() {
        if (!CombatSystem.isBattleOver()) {
            CombatSystem.attack(enemy, outputText, playerHealth, enemyHealth);
            if (CombatSystem.isBattleOver()) {
                nextButton.setVisible(true);
                showEndMessage(CombatSystem.isBattleWon());
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
            alert.setContentText("Pokonałeś przeciwnika i zyskałeś 20 punktów doświadczenia");
            player.gainExperience(20);
        } else {
            alert.setHeaderText("Przegrałeś!");
            alert.setContentText("Twój bohater został pokonany! Zyskałeś 10 punktów doświadczenia");
            player.gainExperience(20);
        }
        alert.showAndWait();
    }
    public void next() {
        if (CombatSystem.isBattleWon()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Prologue/Scene4A.fxml"));
                Parent root = loader.load();

                Scene nextScene = new Scene(root);
                Stage currentStage = (Stage) attackButton.getScene().getWindow();

                currentStage.setScene(nextScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Prologue/Scene4B.fxml"));
                Parent root = loader.load();

                Scene nextScene = new Scene(root);
                Stage currentStage = (Stage) attackButton.getScene().getWindow();

                currentStage.setScene(nextScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        CombatSystem.setBattleState(false);
    }
}
