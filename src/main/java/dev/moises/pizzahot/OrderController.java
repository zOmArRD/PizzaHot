package dev.moises.pizzahot;

import dev.moises.pizzahot.manager.items.Item;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class OrderController extends BaseController {
    @FXML
    public ScrollPane itemsContainer;

    @FXML
    public ImageView appLogo;

    @FXML
    public Text title;

    @FXML
    public Button btnOrder;

    @FXML
    public VBox cartVBox;

    @SuppressWarnings("DataFlowIssue")
    @Override
    public void init(Stage stage) {
        super.init(stage);

        btnOrder.setOnMouseClicked(event -> {
            showOrderConfirmation(stage);
        });

        setupCart();

        TranslateTransition t1 = new TranslateTransition(Duration.seconds(3), appLogo);
        t1.setFromX(-50);
        t1.setToX(50);
        t1.setCycleCount(TranslateTransition.INDEFINITE);
        t1.setAutoReverse(true);
        t1.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(3), title);
        translateTransition.setFromX(-50);
        translateTransition.setToX(50);
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setAutoReverse(true);
        translateTransition.play();

        VBox vBox = new VBox();
        vBox.setSpacing(50);

        Item[] items = {
                new Item("Pizza Pepperoni", 299.99, "pizzape.png"),
                new Item("Pizza BBQ", 349.99, "pizzabbq.png"),
                new Item("Pizza Teriyaki", 369.99, "pizzateri.png"),
                new Item("Pizza Vegetariana", 320.00, "pizzaveg.png"),
                new Item("Pizza Pollo", 315.00, "pizzapollo.png"),
                new Item("Pizza Queso", 319.99, "pizzaque.png"),
                new Item("Pizza Hawaiana", 399.99, "pizzahaw.png"),
                new Item("Pizza Jamón", 250.00, "pizzajam.png"),
                new Item("Pepsi", 25.00, "pepsi.png"),
                new Item("7up", 25.00, "7up.png"),
                new Item("Coca Cola", 25.00, "cocacola.png"),
                new Item("Sprite", 25.00, "sprite.png"),
                new Item("Fanta", 35.00, "fanta.png"),
                new Item("Agua", 20.00, "agua.png"),
        };

        HBox currentHBox = new HBox();
        currentHBox.setSpacing(50);

        currentHBox.setAlignment(Pos.CENTER);

        int itemsInCurrentHBox = 0;

        for (Item item : items) {
            if (itemsInCurrentHBox == 3) {
                vBox.getChildren().add(currentHBox);

                currentHBox = new HBox();
                currentHBox.setSpacing(50);
                currentHBox.setAlignment(Pos.CENTER);

                itemsInCurrentHBox = 0;
            }

            VBox itemVBox = new VBox(10);
            itemVBox.setAlignment(Pos.CENTER);
            itemVBox.getStyleClass().add("item-vbox");

            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("images/" + item.getImage()), 100, 100, true, true));

            itemVBox.getChildren().add(imageView);

            Label itemName = new Label(item.getName());
            itemName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
            itemVBox.getChildren().add(itemName);

            Label itemPrice = new Label("DOP $" + item.getPrice());
            itemPrice.setStyle("-fx-font-size: 14px; -fx-font-weight: normal");
            itemVBox.getChildren().add(itemPrice);


            itemVBox.setOnMouseClicked(event -> {
                addToCart(item);
                updateCartUI();
            });

            currentHBox.getChildren().add(itemVBox);
            itemsInCurrentHBox++;
        }

        if (itemsInCurrentHBox > 0) {
            vBox.getChildren().add(currentHBox);
        }

        itemsContainer.setContent(vBox);
        itemsContainer.setFitToWidth(true);

        setupCart();
    }

    private final Map<Item, Integer> cart = new HashMap<>();
    private double totalPrice = 0.0;

    private void setupCart() {
        cartVBox.setSpacing(10);
        cartVBox.setPadding(new Insets(10));
        cartVBox.setAlignment(Pos.CENTER);
        updateCartUI();
    }

    private void updateCartUI() {
        cartVBox.getChildren().clear();

        for (Map.Entry<Item, Integer> entry : cart.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();
            Label cartItemLabel = new Label(quantity + " x " + item.getName() + " - DOP $" + Math.round((item.getPrice() * quantity) * 100.0) / 100.0);
            cartVBox.getChildren().add(cartItemLabel);
        }

        Label totalLabel = new Label("Total: DOP $" + Math.round(totalPrice * 100.0) / 100.0);
        cartVBox.getChildren().add(totalLabel);
    }

    private void addToCart(Item item) {
        if (cart.containsKey(item)) {
            cart.put(item, cart.get(item) + 1);
        } else {
            cart.put(item, 1);
        }

        totalPrice += item.getPrice();
    }

    private void clearCart() {
        cart.clear();
        totalPrice = 0.0;
        updateCartUI();
    }

    private void showOrderConfirmation(Stage parentStage) {
        Stage orderConfirmationStage = new Stage();
        orderConfirmationStage.initModality(Modality.APPLICATION_MODAL);
        orderConfirmationStage.initStyle(StageStyle.TRANSPARENT);
        orderConfirmationStage.initOwner(parentStage);

        StackPane confirmationPane = new StackPane();
        confirmationPane.setStyle("-fx-background-color: linear-gradient(to bottom right, #f112ff, #723ab6, #7b62c4, #4a6ec0); -fx-background-radius: 28;");
        Scene confirmationScene = new Scene(confirmationPane, 300, 150);
        confirmationScene.setFill(Color.TRANSPARENT);

        Label confirmationLabel = new Label("¡Orden procesada!");
        confirmationLabel.setStyle("-fx-font-size: 32px; -fx-text-fill: white");

        confirmationPane.getChildren().add(confirmationLabel);

        orderConfirmationStage.setScene(confirmationScene);
        orderConfirmationStage.centerOnScreen();

        TranslateTransition formTransition = getTranslateTransition(confirmationPane, orderConfirmationStage);

        orderConfirmationStage.show();
        formTransition.play();
        clearCart();
    }

    private static TranslateTransition getTranslateTransition(StackPane confirmationPane, Stage orderConfirmationStage) {
        TranslateTransition formTransition = new TranslateTransition(Duration.seconds(2), confirmationPane);
        formTransition.setFromY(-150);
        formTransition.setToY(0);

        formTransition.setOnFinished(event -> {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), confirmationPane);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setCycleCount(1);

            fadeOut.setOnFinished(eventB -> {
                orderConfirmationStage.close();
            });

            fadeOut.play();
        });
        return formTransition;
    }
}
