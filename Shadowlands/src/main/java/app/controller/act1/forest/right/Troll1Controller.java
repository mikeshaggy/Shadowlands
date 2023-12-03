package app.controller.act1.forest.right;

import app.controller.general.InventoryController;
import app.controller.general.OptionsController;
import app.utility.GameStateManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Troll1Controller implements Initializable {
    @FXML
    private Button optionsButton;
    @FXML
    private Button inventoryButton;
    @FXML
    private Button backButton;
    private Scene previousScene;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Button fightButton;
    @FXML
    private Button exploreButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font buttonFont = Font.font("Skia", 20);
        optionsButton.setFont(buttonFont);
        inventoryButton.setFont(buttonFont);
        GameStateManager.setCurrentScenePath("/Scenes/Act1/Forest/Right/Troll1.fxml");
        backButton.setFont(Font.font("Skia", 15));
        label1.setFont(Font.font("Skia", 15));
        label2.setFont(Font.font("Skia", 15));
        label3.setFont(Font.font("Skia", 20));
        fightButton.setFont(Font.font("Skia", 25));
        exploreButton.setFont(Font.font("Skia", 25));
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
    public void setPreviousScene(Scene scene) {
        previousScene = scene;
    }
    public void goBackToPreviousScene() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(previousScene);
        stage.show();
    }
    public void fight() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Act1/Forest/Right/TrollBattle.fxml"));
            Parent root = loader.load();

            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) inventoryButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void explore() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Act1/Forest/Right/Troll2.fxml"));
            Parent root = loader.load();
            Scene currentScene = inventoryButton.getScene();

            Troll2Controller controller = loader.getController();
            controller.setPreviousScene(currentScene);
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) inventoryButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
