module com.example.coomics {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.sql;

    exports com.example.comics.view1;
    opens com.example.comics.view1 to javafx.fxml;
    exports com.example.comics.view2;
    opens com.example.comics.view2 to javafx.fxml;
}