package app.controller.prologue;

import app.model.Player;
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

public class Scene6Controller implements Initializable {
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Button nextButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label1.setFont(Font.font("Skia", 15));
        label2.setFont(Font.font("Skia", 15));
        label3.setFont(Font.font("Skia", 20));
        nextButton.setFont(Font.font("Skia", 20));
    }
    public void next() {
        Player player = GameStateManager.getPlayer();
        player.earnGold(50);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Prologue/Scene7.fxml"));
            Parent root = loader.load();

            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) nextButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}