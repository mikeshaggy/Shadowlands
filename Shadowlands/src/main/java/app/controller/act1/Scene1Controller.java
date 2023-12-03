package app.controller.act1;

import app.controller.act1.forest.ForestController;
import app.controller.general.InventoryController;
import app.controller.general.OptionsController;
import app.utility.GameStateManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button storeButton;
    @FXML
    private Button darkAlleyButton;
    @FXML
    private Button forestButton;
    @FXML
    private Button townHallButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font buttonFont = Font.font("Skia", 20);
        optionsButton.setFont(buttonFont);
        inventoryButton.setFont(buttonFont);
        GameStateManager.setCurrentScenePath("/Scenes/Act1/Scene1.fxml");

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
    public void goToStore() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Act1/Store.fxml"));
            Parent root = loader.load();
            Scene currentScene = inventoryButton.getScene();

            StoreController controller = loader.getController();
            controller.setPreviousScene(currentScene);
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) inventoryButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToDarkAlley() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Act1/DarkAlley/Scene1.fxml"));
            Parent root = loader.load();
            Scene currentScene = inventoryButton.getScene();

            app.controller.act1.darkAlley.Scene1Controller controller = loader.getController();
            controller.setPreviousScene(currentScene);
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) inventoryButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToForest() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Act1/Forest/Forest.fxml"));
            Parent root = loader.load();
            Scene currentScene = inventoryButton.getScene();

            ForestController controller = loader.getController();
            controller.setPreviousScene(currentScene);
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) inventoryButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
