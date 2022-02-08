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
    requires java.desktop;
    requires javax.jms;
    requires mail;
    requires AnimateFX;


    exports com.example.comics.view1;
    opens com.example.comics.view1 to javafx.fxml;
    exports com.example.comics.view2;
    opens com.example.comics.view2 to javafx.fxml;
    exports com.example.comics.fakepaypal;
    opens com.example.comics.fakepaypal to javafx.fxml;
    exports com.example.comics.controller;
    opens com.example.comics.controller to org.junit.jupiter.api;
    exports com.example.comics.view1.beans;
    opens com.example.comics.view1.beans to org.junit.jupiter.api;
    exports com.example.comics.model;
    opens com.example.comics.model to org.junit.jupiter.api;
    exports com.example.comics.model.fagioli;
    opens com.example.comics.model.fagioli to org.junit.jupiter.api;
    exports com.example.comics.model.exceptions;
    opens com.example.comics.model.exceptions to org.junit.jupiter.api;
    exports com.example.comics.model.dao;
    opens com.example.comics.model.dao to org.junit.jupiter.api;
    exports com.example.comics.view2.beans;
    opens com.example.comics.view2.beans to org.junit.jupiter.api;
    exports com.example.comics.controller.boundaries;
    opens com.example.comics.controller.boundaries to org.junit.jupiter.api;


}