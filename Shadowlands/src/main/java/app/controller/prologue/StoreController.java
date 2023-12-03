package app.controller.prologue;

import app.model.Player;
import app.model.item.Boots;
import app.model.item.CanvasTrousers;
import app.model.item.Item;
import app.model.item.mage.Stick;
import app.model.item.rogue.Sling;
import app.model.item.warrior.WoodenBaton;
import app.utility.GameStateManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class StoreController implements Initializable {
    @FXML
    private Label titleLabel;
    @FXML
    private Button exitButton;
    @FXML
    private Label bootsLabel;
    @FXML
    private Label bootsDescription;
    @FXML
    private Label bootsPrice;
    @FXML
    private Button bootsPurchaseButton;
    @FXML
    private Label healthPotionLabel;
    @FXML
    private Label healthPotionDescription;
    @FXML
    private Label healthPotionPrice;
    @FXML
    private Button healthPotionPurchaseButton;
    @FXML
    private Label canvasTrousersLabel;
    @FXML
    private Label canvasTrousersDescription;
    @FXML
    private Label canvasTrousersPrice;
    @FXML
    private Button canvasTrousersPurchaseButton;
    @FXML
    private Label woodenBatonLabel;
    @FXML
    private Label woodenBatonDescription;
    @FXML
    private Label woodenBatonStats;
    @FXML
    private Label woodenBatonPrice;
    @FXML
    private Button woodenBatonPurchaseButton;
    @FXML
    private Label stickLabel;
    @FXML
    private Label stickDescription;
    @FXML
    private Label stickStats;
    @FXML
    private Label stickPrice;
    @FXML
    private Button stickPurchaseButton;
    @FXML
    private Label slingLabel;
    @FXML
    private Label slingDescription;
    @FXML
    private Label slingStats;
    @FXML
    private Label slingPrice;
    @FXML
    private Button slingPurchaseButton;
    @FXML
    private Label balanceLabel;
    Player player = GameStateManager.getPlayer();
    private Item boots = new Boots();
    private Item canvasTrousers = new CanvasTrousers();
    private Item woodenBaton = new WoodenBaton();
    private Item stick = new Stick();
    private Item sling = new Sling();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font itemLabelFont = Font.font("Skia", 20);
        Font itemDescriptionFont = Font.font("Skia", 13);
        Font itemPriceFont = Font.font("Skia", 15);
        Font buttonFont = Font.font("Skia", 15);
        titleLabel.setFont(Font.font("Skia", 72));
        exitButton.setFont(Font.font("Skia", 36));
        bootsLabel.setFont(itemLabelFont);
        bootsDescription.setFont(itemDescriptionFont);
        bootsPrice.setFont(itemPriceFont);
        bootsPurchaseButton.setFont(buttonFont);
        healthPotionLabel.setFont(itemLabelFont);
        healthPotionDescription.setFont(itemDescriptionFont);
        healthPotionPrice.setFont(itemPriceFont);
        healthPotionPurchaseButton.setFont(buttonFont);
        canvasTrousersLabel.setFont(itemLabelFont);
        canvasTrousersDescription.setFont(itemDescriptionFont);
        canvasTrousersPrice.setFont(itemPriceFont);
        canvasTrousersPurchaseButton.setFont(buttonFont);
        woodenBatonLabel.setFont(itemLabelFont);
        woodenBatonDescription.setFont(itemDescriptionFont);
        woodenBatonStats.setFont(itemDescriptionFont);
        woodenBatonPrice.setFont(itemPriceFont);
        woodenBatonPurchaseButton.setFont(buttonFont);
        stickLabel.setFont(itemLabelFont);
        stickDescription.setFont(itemDescriptionFont);
        stickStats.setFont(itemDescriptionFont);
        stickPrice.setFont(itemPriceFont);
        stickPurchaseButton.setFont(buttonFont);
        slingLabel.setFont(itemLabelFont);
        slingDescription.setFont(itemDescriptionFont);
        slingStats.setFont(itemDescriptionFont);
        slingPrice.setFont(itemPriceFont);
        slingPurchaseButton.setFont(buttonFont);
        balanceLabel.setFont(itemLabelFont);
        balanceLabel.setText("Złoto: " + player.getGoldBalance());

        bootsPurchaseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                purchase(boots, "Buty");
            }
        });
        canvasTrousersPurchaseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                purchase(canvasTrousers, "Spodnie");
            }
        });
        woodenBatonPurchaseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                purchase(woodenBaton, "Broń");
            }
        });
        stickPurchaseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                purchase(stick, "Broń");
            }
        });
        slingPurchaseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                purchase(sling, "Broń");
            }
        });
    }
    public void purchase(Item item, String slot) {
        int itemPrice = item.getPrice();
        int goldBalance = player.getGoldBalance();

        if (itemPrice > goldBalance) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Za mało złota!");
            alert.setContentText("Nie stać cię na ten przedmiot");
            alert.showAndWait();
        } else if (player.getEquippedItems().containsValue(item)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Posiadasz już taki przedmiot!");
            alert.showAndWait();
        } else {
            player.equipItem(slot, item);
            player.spendGold(item.getPrice());
            balanceLabel.setText("Złoto: " + player.getGoldBalance());
        }
    }
    public void purchaseHealthPotion() {
        player.addHealthPotion();
        balanceLabel.setText("Złoto: " + player.getGoldBalance());
    }
    public void exit() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/Prologue/Scene8.fxml"));
            Parent root = loader.load();

            Scene nextScene = new Scene(root);
            Stage currentStage = (Stage) exitButton.getScene().getWindow();

            currentStage.setScene(nextScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
