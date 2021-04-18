package Quatro.codecademy.application.UI;

import Quatro.codecademy.application.controllers.UIController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class StudentMenUI implements InterfaceUI {

    // method which creates a new UI
    public Scene getUI(UIController controller){
        BorderPane layout = new BorderPane();
        VBox vBox = new VBox();
        layout.setStyle("-fx-background-color: #C8F8FA;");
        layout.setMinSize(500, 300);
        Text title = new Text("KansloosCodeCademy");
        Text subtitle = new Text("Student Managing");
        vBox.getChildren().add(title);
        vBox.getChildren().add(subtitle);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        title.setFont(new Font(18));
        subtitle.setFont(new Font(18));
        Button addStudent = new Button("Add new student");
        Button viewStudent = new Button("View student");
        Button manageStudentProgession = new Button("Manage student progression");
        Button viewStudentProgression = new Button("View student progression");
        addStudent.setMinWidth(200);
        addStudent.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        viewStudent.setMinWidth(200);
        viewStudent.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        manageStudentProgession.setMinWidth(200);
        manageStudentProgession.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        viewStudentProgression.setMinWidth(200);
        viewStudentProgression.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");

        VBox bottom = new VBox();
        bottom.setAlignment(Pos.CENTER);
        Button back = new Button("Back to home");
        back.setMinWidth(400);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setOnAction((event) -> controller.switchUI("main"));
        bottom.getChildren().add(back);

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(20, 20, 20, 20));
        bottom.setPadding(new Insets(0, 10, 10, 10));
        bottom.setSpacing(10);

        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(addStudent, 0, 1);
        gridPane.add(viewStudent, 0, 2);
        gridPane.add(manageStudentProgession, 0, 3);
        gridPane.add(viewStudentProgression, 0, 4);

        layout.setTop(vBox);
        layout.setCenter(gridPane);
        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        return scene;
    }

}