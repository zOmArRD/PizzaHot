package dev.moises.pizzahot;

import dev.moises.pizzahot.manager.client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class App extends Application {

    @Getter
    @Setter
    public static Client currentClient = null;

    @SuppressWarnings("DataFlowIssue")
    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/logo.png")));
        stage.setTitle("Pizza Hot");
        stage.initStyle(StageStyle.TRANSPARENT);
        changeFxml("home", stage, false);
    }

    public static void changeFxml(String fxml, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("views/" + fxml + ".fxml"));
        Scene scene = new Scene(loader.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        ((BaseController) loader.getController()).init(stage);
        stage.show();
    }

    public static void changeFxml(String fxml, Stage stage, boolean resizable) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("views/" + fxml + ".fxml"));
        Scene scene = new Scene(loader.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        if (!resizable) stage.setResizable(false);
        ((BaseController) loader.getController()).init(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}