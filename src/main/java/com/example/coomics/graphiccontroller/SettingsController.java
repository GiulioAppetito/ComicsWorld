package com.example.coomics.graphiccontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tools.FxmlLoader;

public class SettingsController {

    @FXML
    private Button profile;
    @FXML
    private VBox settingsBox;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button accountsettings;
    @FXML
    private Button preferencessettings;
    @FXML
    private Button badgessettings;
    @FXML
    private Button aboutussettings;

    public void openProfileSettings() {
        System.out.println("Clicked profile settings");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("profilesettings");
        mainPane.setCenter(view);
    }

    public void openAccountSettings() {
        System.out.println("Clicked account settings");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("accountsettings");
        mainPane.setCenter(view);
    }

    public void openPreferencesSettings() {
        System.out.println("Clicked on preferences settings");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("preferencessettings");
        mainPane.setCenter(view);
    }

    public void openMyBadgesSettings() {
        System.out.println("Clicked on my badges settings");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("badgessettings");
        mainPane.setCenter(view);
    }

    public void openAboutUsSettings() {
        System.out.println("Clicked on about us settings");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("aboutussettings");
        mainPane.setCenter(view);
    }
}
