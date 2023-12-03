package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Scenes/Intro/Start.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(this::exitGame);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void exitGame(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Wyjście");
        alert.setHeaderText("Czy jesteś pewny, że chcesz wyjść?");
        alert.setContentText("Niezapisany postęp zostanie utracony");

        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            Platform.exit();
        } else {
            event.consume();
        }
    }
}
