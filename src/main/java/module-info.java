module dev.moises.pizzahot {
    requires static lombok;

    requires javafx.controls;
    requires javafx.fxml;


    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;

    opens dev.moises.pizzahot to javafx.fxml;

    exports dev.moises.pizzahot;
    exports dev.moises.pizzahot.manager.client;
    exports dev.moises.pizzahot.manager.items;
    exports dev.moises.pizzahot.manager.order;
}