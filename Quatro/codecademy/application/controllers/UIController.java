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

    // Method that is used to switch between the different UI's
    public void switchUI(String scene) {
        switch (scene) {
            case "main":
                stage.setScene((new MainUI().getUI(this)));
                break;
            // UI's related to the student
            case "studentUI":
                stage.setScene((new StudentMenUI().getUI(this)));
                break;
            case "addstudentUI":
                stage.setScene((new AddStudentUI().getUI(this)));
                break;
            default:
                break;
        }
    }

}
