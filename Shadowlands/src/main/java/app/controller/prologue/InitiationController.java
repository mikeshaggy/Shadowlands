package app.controller.prologue;

import app.controller.general.InventoryController;
import app.controller.general.OptionsController;
import app.utility.GameStateManager;
import javafx.application.Platform;
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

public class InitiationController implements Initializable {
    @FXML
    private Label label1;
    @FXML
    private Button optionsButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button inventoryButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font buttonFont = Font.font("Skia", 20);
        label1.setFont(Font.font("Skia", 33));
        optionsButton.setFont(buttonFont);
        nextButton.setFont(buttonFont);
        inventoryButton.setFont(buttonFont);
        GameStateManager.setCurrentScenePath("/Scenes/Prologue/Initiation.fxml");

        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nowe funkcje");
            alert.setHeaderText("Odblokowałeś nowe funkcje!");
            alert.setContentText("Od teraz, w każdym momencie gry, możesz skorzystać z opcji (przycisk w prawym górnym rogu) oraz podejrzeć swój ekwipunek (przycisk w lewym dolnym rogu).");
            alert.showAndWait();

            alert.setTitle("Opcje");
            alert.setHeaderText("Przycisk \"Opcje\"");
            alert.setContentText("W tym miejscu masz możliwość zapisać stan swojej rozgrywki oraz zerkniąć na mapę Krainy Cieni.");
            alert.showAndWait();

            alert.setTitle("Ekwipunek");
            alert.setHeaderText("Przycisk \"Ekwipunek\"");
            alert.setContentText("W tym miejscu masz możliwość zajrzeć do swojego ekwipunku oraz podejrzeć statystyki swojej postaci.");
            alert.showAndWait();
        });
    }
    public void next() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Prologue/Scene9.fxml"));
            Parent root = loader.load();

            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) nextButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showInventory() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/General/Inventory.fxml"));
            Parent root = loader.load();
            Scene currentScene = nextButton.getScene();

            InventoryController controller = loader.getController();
            controller.setPreviousScene(currentScene);
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) nextButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showOptions() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/General/Options.fxml"));
            Parent root = loader.load();
            Scene currentScene = nextButton.getScene();

            OptionsController controller = loader.getController();
            controller.setPreviousScene(currentScene);
            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) nextButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
