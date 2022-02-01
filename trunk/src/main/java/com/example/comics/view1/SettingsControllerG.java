package com.example.comics.view1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import tools.FxmlLoader;

public class SettingsControllerG {

    @FXML
    private Button btnProfile;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button accountsettings;
    @FXML
    private Button aboutussettings;

    private static final String STYLE = ".button2";
    private static final String BLU_STYLE = "-fx-background-color : #5DADE2";

    public void initialize(){
        openProfileSettings();
    }

    public void openProfileSettings() {

        btnProfile.setStyle(BLU_STYLE);
        accountsettings.setStyle(STYLE);
        aboutussettings.setStyle(STYLE);

        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("profilesettings");
        mainPane.setCenter(view);
    }

    public void openAccountSettings() {

        btnProfile.setStyle(STYLE);
        accountsettings.setStyle(BLU_STYLE);
        aboutussettings.setStyle(STYLE);

        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("accountsettings");
        mainPane.setCenter(view);
    }

    public void openAboutUsSettings() {

        btnProfile.setStyle(STYLE);
        accountsettings.setStyle(STYLE);
        aboutussettings.setStyle(BLU_STYLE);

        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("aboutussettings");
        mainPane.setCenter(view);
    }
}
