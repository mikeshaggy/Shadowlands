package app.controller.intro;

import app.model.Player;
import app.utility.GameStateManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CharacterCreationController implements Initializable {
    private int baseStrength = 0;
    private int baseDurability = 0;
    private int baseAgility = 0;
    private int baseFortune = 0;
    private Map<String, Integer[]> raceStats = new HashMap<>();
    @FXML
    private Label titleLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private Label raceLabel;
    @FXML
    private ChoiceBox<String> raceChoiceBox;
    @FXML
    private Label classLabel;
    @FXML
    private ChoiceBox<String> classChoiceBox;
    @FXML
    private Label statLabel;
    @FXML
    private Label strengthLabel;
    @FXML
    private Label durabilityLabel;
    @FXML
    private Label agilityLabel;
    @FXML
    private Label fortuneLabel;
    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;

    private String[] races = new String[] {"Człowiek", "Elf", "Krasnolud"};
    private String[] classes = new String[] {"Wojownik", "Łotrzyk", "Mag"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font titleFont = Font.font("Skia", 60);
        Font labelFont = Font.font("Skia", 30);
        titleLabel.setFont(titleFont);
        nameLabel.setFont(labelFont);
        raceLabel.setFont(labelFont);
        classLabel.setFont(labelFont);
        submitButton.setFont(Font.font("Skia", 45));
        statLabel.setFont(Font.font("Skia", 45));
        strengthLabel.setFont(labelFont);
        durabilityLabel.setFont(labelFont);
        agilityLabel.setFont(labelFont);
        fortuneLabel.setFont(labelFont);
        raceChoiceBox.getItems().addAll(races);
        raceChoiceBox.setOnAction(event -> selectRace());
        classChoiceBox.getItems().addAll(classes);
        classChoiceBox.setOnAction(event -> selectClass());

        raceStats.put("Człowiek", new Integer[]{20, 40, 5, 5}); // siła, wytrzymałość, zręczność, szczęście
        raceStats.put("Elf", new Integer[]{10, 30, 20, 20});
        raceStats.put("Krasnolud", new Integer[]{30, 50, 5, 5});

        updateStatLabels();
    }
    @FXML
    public void selectRace() {
        String race = raceChoiceBox.getValue();

        Integer[] stats = raceStats.get(race);
        baseStrength = stats[0];
        baseDurability = stats[1];
        baseAgility = stats[2];
        baseFortune = stats[3];

        updateStatLabels();
    }
    @FXML
    public void selectClass() {
        String playerClass = classChoiceBox.getValue();

        if (raceChoiceBox.getValue() != null && playerClass != null) {
            updateStatLabels();
        }
    }
    public void updateStatLabels() {
        strengthLabel.setText("Siła: " + calculateStatValue(baseStrength, "strength"));
        durabilityLabel.setText("Wytrzymałość: " + calculateStatValue(baseDurability, "durability"));
        agilityLabel.setText("Zręczność: " + calculateStatValue(baseAgility, "agility"));
        fortuneLabel.setText("Szczęście: " + calculateStatValue(baseFortune, "fortune"));
    }
    public int calculateStatValue(int baseValue, String stat) {
        String race = raceChoiceBox.getValue();
        String playerClass = classChoiceBox.getValue();

        int value = baseValue;
        if (playerClass != null) {
            switch (stat) {
                case "strength":
                    if (playerClass.equals("Wojownik")) {
                        value *= 2;
                    } else if (playerClass.equals("Łotrzyk")) {
                        value *= 0.5;
                    } else if (playerClass.equals("Mag")) {
                        value *= 2;
                    }
                    break;
                case "durability":
                    if (playerClass.equals("Wojownik")) {
                        value *= 2;
                    } else if (playerClass.equals("Łotrzyk")) {
                        value *= 0.5;
                    } else if (playerClass.equals("Mag")) {
                        value *= 0.5;
                    }
                    break;
                case "agility":
                    if (playerClass.equals("Wojownik")) {
                        value *= 0.5;
                    } else if (playerClass.equals("Łotrzyk")) {
                        value *= 2;
                    } else if (playerClass.equals("Mag")) {
                        value *= 0.5;
                    }
                    break;
                case "fortune":
                    if (playerClass.equals("Wojownik")) {
                        value *= 0.5;
                    } else if (playerClass.equals("Łotrzyk")) {
                        value *= 2;
                    } else if (playerClass.equals("Mag")) {
                        value *= 2;
                    }
                    break;
            }
        }
        return value;
    }
    public void createPlayer() {

        String playerName = nameTextField.getText();
        int playerStrength = Integer.parseInt(strengthLabel.getText().split(": ")[1]);
        int playerHealth = Integer.parseInt(durabilityLabel.getText().split(": ")[1]);
        int playerAgility = Integer.parseInt(agilityLabel.getText().split(": ")[1]);
        int playerFortune = Integer.parseInt(fortuneLabel.getText().split(": ")[1]);
        String playerRace = raceChoiceBox.getValue();
        String playerClass = classChoiceBox.getValue();

        if (playerName.isEmpty() || playerRace == null || playerClass == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Niekompletne informacje");
            alert.setContentText("Proszę uzupełnić wszystkie pola");
            alert.showAndWait();
            return;
        }

        Player player = new Player(playerName, playerStrength, playerHealth, playerAgility, playerFortune, playerRace, playerClass);
        GameStateManager.setPlayer(player);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Utworzono postać");
        alert.setHeaderText("Postać została utworzona pomyślnie");
        alert.setContentText("Witaj " + playerName + "!\n" + player.toString());
        alert.showAndWait();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Intro/Intro.fxml"));
            Parent root = loader.load();

            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) submitButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Intro/Start.fxml"));
            Parent root = loader.load();

            Scene previousScene = new Scene(root);
            Stage currentStage = (Stage) backButton.getScene().getWindow();

            currentStage.setScene(previousScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
