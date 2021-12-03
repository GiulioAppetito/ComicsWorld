package com.example.comics.view1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tools.FxmlLoader;

public class SettingsControllerG {

    @FXML
    private Button btnProfile;
    @FXML
    private VBox settingsBox;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button accountsettings;
    @FXML
    private Button preferencessettings;
    @FXML
    private Button aboutussettings;

    public void initialize(){
        openProfileSettings();
    }

    public void openProfileSettings() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("profilesettings");
        mainPane.setCenter(view);
    }

    public void openAccountSettings() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("accountsettings");
        mainPane.setCenter(view);
    }

    public void openPreferencesSettings() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("preferencessettings");
        mainPane.setCenter(view);
    }

    public void openAboutUsSettings() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("aboutussettings");
        mainPane.setCenter(view);
    }
}
