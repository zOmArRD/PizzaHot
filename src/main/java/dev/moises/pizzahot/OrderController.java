package dev.moises.pizzahot;

import dev.moises.pizzahot.manager.cart.CartItem;
import dev.moises.pizzahot.manager.cart.PizzaCartItem;
import dev.moises.pizzahot.manager.cart.ShoppingCart;
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

        btnOrder.setOnMouseClicked(event -> showOrderConfirmation(stage));

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

        CartItem[] items = {
                new PizzaCartItem("Pizza Pepperoni", "La clásica de 8 pedazos", 59.99, "pizzape.png", 8),
                new PizzaCartItem("Pizza Pepperoni", "La clásica de 10 pedazos", 100.99, "pizzape.png", 10),
                new PizzaCartItem("Pizza Pepperoni", "La clásica de 12 pedazos", 199.99, "pizzape.png", 12),

                new PizzaCartItem("Pizza BBQ", "La de la salsa, 8 pedazos", 89.99, "pizzabbq.png", 8),
                new PizzaCartItem("Pizza BBQ", "La de la salsa, 10 pedazos", 130.99, "pizzabbq.png", 10),
                new PizzaCartItem("Pizza BBQ", "La de la salsa, 12 pedazos", 229.99, "pizzabbq.png", 12),

                new PizzaCartItem("Pizza Teriyaki", "La exótica, 8 pedazos", 99.99, "pizzateri.png", 8),
                new PizzaCartItem("Pizza Teriyaki", "La exótica, 10 pedazos", 140.99, "pizzateri.png", 10),
                new PizzaCartItem("Pizza Teriyaki", "La exótica, 12 pedazos", 239.99, "pizzateri.png", 12),

                new PizzaCartItem("Pizza Vegetariana", "La de los vegetales, 8 pedazos", 89.99, "pizzaveg.png", 8),
                new PizzaCartItem("Pizza Vegetariana", "La de los vegetales, 10 pedazos", 130.99, "pizzaveg.png", 10),
                new PizzaCartItem("Pizza Vegetariana", "La de los vegetales, 12 pedazos", 229.99, "pizzaveg.png", 12),

                new PizzaCartItem("Pizza Pollo", "La de pollo, 8 pedazos", 70.99, "pizzapollo.png", 8),
                new PizzaCartItem("Pizza Pollo", "La de pollo, 10 pedazos", 110.99, "pizzapollo.png", 10),
                new PizzaCartItem("Pizza Pollo", "La de pollo, 12 pedazos", 209.99, "pizzapollo.png", 12),


                new PizzaCartItem("Pizza Queso", "La golosa, 8 pedazos", 95.99, "pizzaque.png", 8),
                new PizzaCartItem("Pizza Queso", "La golosa, 10 pedazos", 135.99, "pizzaque.png", 10),
                new PizzaCartItem("Pizza Queso", "La golosa, 12 pedazos", 234.99, "pizzaque.png", 12),

                new PizzaCartItem("Pizza Jamón", "La de jamón, 8 pedazos", 60.99, "pizzajam.png", 8),
                new PizzaCartItem("Pizza Jamón", "La de jamón, 10 pedazos", 100.99, "pizzajam.png", 10),
                new PizzaCartItem("Pizza Jamón", "La de jamón, 12 pedazos", 199.99, "pizzajam.png", 12),

                new PizzaCartItem("Pizza Hawaiana", "La de piña, 8 pedazos", 100.99, "pizzahaw.png", 8),
                new PizzaCartItem("Pizza Hawaiana", "La de piña, 10 pedazos", 140.99, "pizzahaw.png", 10),
                new PizzaCartItem("Pizza Hawaiana", "La de piña, 12 pedazos", 239.99, "pizzahaw.png", 12),

                new CartItem("Agua", "Agua mineral", 20.00, "agua.png"),
                new CartItem("Pepsi", "Botella pequeña", 30.00, "pepsi.png"),
                new CartItem("7up", "Botella pequeña", 30.00, "7up.png"),
                new CartItem("Coca Cola", "Botella pequeña", 30.00, "cocacola.png"),
                new CartItem("Sprite", "Botella pequeña", 30.00, "sprite.png"),
                new CartItem("Fanta", "Botella pequeña", 30.00, "fanta.png"),
        };

        HBox currentHBox = new HBox();
        currentHBox.setSpacing(50);

        currentHBox.setAlignment(Pos.CENTER);

        int itemsInCurrentHBox = 0;

        for (CartItem item : items) {
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

            Label itemDesc = new Label(item.getDescription());
            itemDesc.setStyle("-fx-font-size: 13px");
            itemVBox.getChildren().add(itemDesc);

            Label itemPrice = new Label("DOP $" + item.calculatePrice());
            itemPrice.setStyle("-fx-font-size: 14px; -fx-font-weight: normal");
            itemVBox.getChildren().add(itemPrice);


            itemVBox.setOnMouseClicked(event -> {
                getShoppingCart().addItem(item);
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

    private void setupCart() {
        cartVBox.setSpacing(10);
        cartVBox.setPadding(new Insets(10));
        cartVBox.setAlignment(Pos.CENTER);
        updateCartUI();
    }

    private void updateCartUI() {
        cartVBox.getChildren().clear();

        for (Map.Entry<CartItem, Integer> entry : getShoppingCart().getItems().entrySet()) {
            CartItem item = entry.getKey();
            int quantity = entry.getValue();
            Label cartItemLabel = new Label(quantity + " x " + item.getName() + " - DOP $" + Math.round((item.calculatePrice() * quantity) * 100.0) / 100.0);
            cartVBox.getChildren().add(cartItemLabel);
        }

        Label totalLabel = new Label("Total: DOP $" + getShoppingCart().calculateTotalPrice());
        cartVBox.getChildren().add(totalLabel);
    }


    private void clearCart() {
        getShoppingCart().clearAllItems();
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

            fadeOut.setOnFinished(eventB -> orderConfirmationStage.close());
            fadeOut.play();
        });
        return formTransition;
    }

    public static ShoppingCart getShoppingCart() {
        return App.getCurrentClient().getShoppingCart();
    }
}
