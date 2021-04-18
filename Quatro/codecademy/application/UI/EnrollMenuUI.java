package Quatro.codecademy.application.UI;

import Quatro.codecademy.application.controllers.UIController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EnrollMenuUI implements InterfaceUI {

    // Method which creates a new UI for the main page of the enrollments
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        VBox vBox = new VBox();
        layout.setStyle("-fx-background-color: #C8F8FA;");
        layout.setMinSize(500, 300);
        Text title = new Text("KansloosCodeCademy");
        Text subtitle = new Text("Enrollment Managing");
        vBox.getChildren().add(title);
        vBox.getChildren().add(subtitle);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        title.setFont(new Font(18));
        subtitle.setFont(new Font(18));
        Button enrollmentAdd = new Button("Add a new enrollment");
        enrollmentAdd.setMinWidth(400);
        enrollmentAdd.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        enrollmentAdd.setOnAction((event) -> controller.switchUI("addenrollUI"));
        VBox bottom = new VBox();
        bottom.setAlignment(Pos.CENTER);
        Button back = new Button("Back to home");
        back.setMinWidth(400);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setOnAction((event) -> controller.switchUI("main"));
        bottom.getChildren().add(enrollmentAdd);
        bottom.getChildren().add(back);
        bottom.setPadding(new Insets(0, 10, 10, 10));
        bottom.setSpacing(10);

        layout.setTop(vBox);
        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        return scene;
    }

}