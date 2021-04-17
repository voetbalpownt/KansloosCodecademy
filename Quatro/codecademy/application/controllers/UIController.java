package Quatro.codecademy.application.controllers;

import Quatro.codecademy.application.UI.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class UIController extends Application {

    private Stage stage = null;

    // The application is started here and it shows the UI
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        MainUI mainUI = new MainUI();
        stage.setTitle("Liam Gevaerts (2172564) | Thijs van Rijsbergen (2174096) | Ricardo van de Worp (2174445)");
        stage.setScene(mainUI.getUI(this));
        stage.show();
    }

}
