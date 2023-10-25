package dev.moises.pizzahot;

import dev.moises.pizzahot.manager.cart.ShoppingCart;
import dev.moises.pizzahot.manager.client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.synedra.validatorfx.Validator;

import java.io.IOException;

public class AppController extends BaseController {
    @FXML
    public ImageView appClose;

    @FXML
    public ImageView appHide;

    @FXML
    public Button btnContinue;

    @FXML
    public TextField clientName;

    @FXML
    public TextField clientLastName;

    @FXML
    public TextField clientAddress;

    @FXML
    public TextField clientPhoneNumber;

    public void createClient(MouseEvent mouseEvent) throws IOException {
        Client client = new Client();

        if (checkField(clientName)) return;
        if (checkField(clientLastName)) return;
        if (checkField(clientAddress)) return;

        if (clientPhoneNumber.getText().length() != 10 || !clientPhoneNumber.getText().matches("[0-9]+")) {
            clientPhoneNumber.clear();
            clientPhoneNumber.setStyle("-fx-border-color: red");
            clientPhoneNumber.setPromptText("El número de teléfono debe tener 10 dígitos");
            return;
        }

        client.setName(clientName.getText());
        client.setLastName(clientLastName.getText());
        client.setFullAddress(clientAddress.getText());
        client.setPhoneNumber(clientPhoneNumber.getText());
        client.setShoppingCart(new ShoppingCart());

        App.setCurrentClient(client);
        App.changeFxml("order", (Stage) ((Button) mouseEvent.getSource()).getScene().getWindow());
    }

    public boolean checkField(TextField field) {
        if (field.getText().isEmpty() || field.getText().isBlank() || field.getText().length() < 3) {
            field.setStyle("-fx-border-color: red");
            field.setPromptText("Este campo es obligatorio");
            return true;
        } else {
            field.setStyle("-fx-border-color: black");
            return false;
        }
    }
}