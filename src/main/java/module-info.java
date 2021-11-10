module com.example.coomics {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    exports com.example.comics.view1;
    exports com.example.comics.model;
    opens com.example.comics.view1 to javafx.fxml;
}