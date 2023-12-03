package app.controller.act1.darkAlley;

import app.controller.general.InventoryController;
import app.controller.general.OptionsController;
import app.model.Player;
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

public class Scene1Controller implements Initializable {
    @FXML
    private Button optionsButton;
    @FXML
    private Button inventoryButton;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;
    private Scene previousScene;
    @FXML
    private Button backButton;
    private final Player player = GameStateManager.getPlayer();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font buttonFont = Font.font("Skia", 20);
        optionsButton.setFont(buttonFont);
        inventoryButton.setFont(buttonFont);
        GameStateManager.setCurrentScenePath("/Scenes/Act1/DarkAlley/Scene1.fxml");
        label1.setFont(Font.font("Skia", 15));
        label2.setFont(Font.font("Skia", 15));
        yesButton.setFont(Font.font("Skia", 24));
        noButton.setFont(Font.font("Skia", 24));
        backButton.setFont(Font.font("Skia", 15));
    }
    public void showInventory() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/General/Inventory.fxml"));
            Parent root = loader.load();
            Scene currentScene = inventoryButton.getScene();

            InventoryController controller = loader.getController();
            controller.setPreviousScene(currentScene);
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) inventoryButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showOptions() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/General/Options.fxml"));
            Parent root = loader.load();
            Scene currentScene = inventoryButton.getScene();

            OptionsController controller = loader.getController();
            controller.setPreviousScene(currentScene);
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) inventoryButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void giveMoney() {
        if (player.getGoldBalance() < 5) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Nie masz wystarczająco złota!");
            alert.showAndWait();
        } else {
            player.spendGold(5);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Act1/DarkAlley/Scene2A.fxml"));
                Parent root = loader.load();

                Scene nextScene = new Scene(root);
                Stage currentStage = (Stage) inventoryButton.getScene().getWindow();

                currentStage.setScene(nextScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void refuse() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Act1/DarkAlley/Scene2B.fxml"));
            Parent root = loader.load();

            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) inventoryButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setPreviousScene(Scene scene) {
        previousScene = scene;
    }
    public void goBackToPreviousScene() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previousScene);
        stage.show();
    }
}
