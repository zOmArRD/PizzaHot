package dev.moises.pizzahot;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BaseController {
    @FXML
    public Pane barContainer;

    @FXML
    public ImageView appClose;

    @FXML
    public ImageView appHide;

    public double x, y;

    public void init(Stage stage) {
        barContainer.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        barContainer.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        appClose.setOnMouseClicked(mouseEvent -> stage.close());
        appHide.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }

}
