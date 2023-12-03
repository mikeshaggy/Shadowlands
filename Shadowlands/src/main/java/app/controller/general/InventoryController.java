package app.controller.general;

import app.model.Player;
import app.utility.GameStateManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {
    Player player = GameStateManager.getPlayer();
    @FXML
    private Label nameLabel;
    @FXML
    private Label raceLabel;
    @FXML
    private Label classLabel;
    @FXML
    private Label lvlLabel;
    @FXML
    private Label xpLabel;
    @FXML
    private Label goldLabel;
    @FXML
    private Label weaponLabel;
    @FXML
    private Label helmetLabel;
    @FXML
    private Label armorLabel;
    @FXML
    private Label trousersLabel;
    @FXML
    private Label bootsLabel;
    @FXML
    private Label strengthLabel;
    @FXML
    private Label healthLabel;
    @FXML
    private Label agilityLabel;
    @FXML
    private Label fortuneLabel;
    @FXML
    private Label healthPotionsLabel;
    @FXML
    private Button backButton;
    @FXML
    private Button backpackButton;
    @FXML
    private Button healButton;
    private Scene previousScene;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font defaultFont = Font.font("Skia", 25);
        nameLabel.setFont(Font.font("Skia", 35));
        nameLabel.setText(player.getName());
        backButton.setFont(Font.font("Skia", 15));
        healButton.setFont(Font.font("Skia", 15));
        backpackButton.setFont(defaultFont);
        raceLabel.setFont(defaultFont);
        raceLabel.setText("Rasa: " + player.getRace());
        classLabel.setFont(defaultFont);
        classLabel.setText("Klasa: " + player.getPlayerClass());
        lvlLabel.setFont(defaultFont);
        lvlLabel.setText("Poziom: " + player.getLevel());
        xpLabel.setFont(defaultFont);
        xpLabel.setText("PD: " + player.getExperience() + "/20");
        goldLabel.setFont(defaultFont);
        goldLabel.setText("Złoto: " + player.getGoldBalance());
        weaponLabel.setFont(defaultFont);
        weaponLabel.setText("Broń: " + ((player.getEquippedItem("Broń") == null) ? "Brak" : player.getEquippedItem("Broń").getName()));
        helmetLabel.setFont(defaultFont);
        helmetLabel.setText("Hełm: " + ((player.getEquippedItem("Hełm") == null) ? "Brak" : player.getEquippedItem("Hełm").getName()));
        armorLabel.setFont(defaultFont);
        armorLabel.setText("Zbroja: " + ((player.getEquippedItem("Zbroja") == null) ? "Brak" : player.getEquippedItem("Zbroja").getName()));
        trousersLabel.setFont(defaultFont);
        trousersLabel.setText("Spodnie: " + ((player.getEquippedItem("Spodnie") == null) ? "Brak" : player.getEquippedItem("Spodnie").getName()));
        bootsLabel.setFont(defaultFont);
        bootsLabel.setText("Buty: " + ((player.getEquippedItem("Buty") == null) ? "Brak" : player.getEquippedItem("Buty").getName()));
        strengthLabel.setFont(defaultFont);
        strengthLabel.setText("Siła: " + player.getStrength());
        healthLabel.setFont(defaultFont);
        healthLabel.setText("Punkty zdrowia: " + player.getHealth() + "/" + player.getDurability());
        agilityLabel.setFont(defaultFont);
        agilityLabel.setText("Zręczność: " + player.getAgility());
        fortuneLabel.setFont(defaultFont);
        fortuneLabel.setText("Szczęście: " + player.getFortune());
        healthPotionsLabel.setFont(defaultFont);
        healthPotionsLabel.setText("Eliksiry uzdrawiające: " + player.getHealthPotions());
    }
    public void setPreviousScene(Scene scene) {
        previousScene = scene;
    }
    public void goBackToPreviousScene() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previousScene);
        stage.show();
    }
    public void heal() {
        if (player.getHealthPotions() <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Nie masz już eliksirów uzdrawiających!");
            alert.setContentText(null);
            alert.showAndWait();
        } else if (player.getHealth() == player.getDurability()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Twoje punkty zdrowia są już pełne!");
            alert.setContentText(null);
            alert.showAndWait();
        } else {
            player.heal(20);
            player.setHealthPotions(player.getHealthPotions() - 1);
            if (player.getHealth() > player.getDurability()) {
                player.setHealth(player.getDurability());
            }
        }
        healthLabel.setText("Punkty zdrowia: " + player.getHealth() + "/" + player.getDurability());
        healthPotionsLabel.setText("Eliksiry uzdrawiające: " + player.getHealthPotions());
    }
}
