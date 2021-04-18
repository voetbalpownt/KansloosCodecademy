package Quatro.codecademy.application.UI;

import javafx.scene.Scene;
import Quatro.codecademy.application.controllers.UIController;

// Interface for the UI
public interface InterfaceUI {
    
    // This is used in all other UI classes
    public Scene getUI(UIController uicontroller);
}
