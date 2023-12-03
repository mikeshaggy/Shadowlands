package app.controller.prologue;

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

public class Scene1Controller implements Initializable {
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Button readyButton;
    @FXML
    private Button notReadyButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label1.setFont(Font.font("Skia", 15));
        label2.setFont(Font.font("Skia", 15));
        label3.setFont(Font.font("Skia", 15));
        label4.setFont(Font.font("Skia", 20));
        readyButton.setFont(Font.font("Skia", 20));
        notReadyButton.setFont(Font.font("Skia", 20));
    }
    public void ready() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Prologue/Scene2A.fxml"));
            Parent root = loader.load();

            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) readyButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void notReady() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Prologue/Scene2B.fxml"));
            Parent root = loader.load();

            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) notReadyButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
