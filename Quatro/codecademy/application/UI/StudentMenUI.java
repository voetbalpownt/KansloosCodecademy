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

// Class for everything UI related of the students
public class StudentMenUI implements InterfaceUI {

    // Method which creates a new UI for the main page of the students
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
        Button studentAdd = new Button("Add new student");
        Button studentView = new Button("View student");
        Button manageStudentProgess = new Button("Manage student progression");
        Button viewStudentProgress = new Button("View student progression");
        studentAdd.setMinWidth(200);
        studentAdd.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        studentView.setMinWidth(200);
        studentView.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        manageStudentProgess.setMinWidth(200);
        manageStudentProgess.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        viewStudentProgress.setMinWidth(200);
        viewStudentProgress.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        studentAdd.setOnAction((event) -> controller.switchUI("addstudentUI"));
        studentView.setOnAction((event) -> controller.switchUI("viewstudentUI"));
        manageStudentProgess.setOnAction((event) -> controller.switchUI("managestudentUI"));
        viewStudentProgress.setOnAction((event) -> controller.switchUI("viewstudentprogressionUI"));
        VBox bottom = new VBox();
        bottom.setAlignment(Pos.CENTER);
        Button back = new Button("Back to home");
        back.setMinWidth(400);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setOnAction((event) -> controller.switchUI("main"));
        bottom.getChildren().add(back);

        GridPane gridPane = new GridPane();
        bottom.setPadding(new Insets(0, 10, 10, 10));
        bottom.setSpacing(10);

        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(studentAdd, 0, 1);
        gridPane.add(studentView, 0, 2);
        gridPane.add(manageStudentProgess, 0, 3);
        gridPane.add(viewStudentProgress, 0, 4);

        layout.setTop(vBox);
        layout.setCenter(gridPane);
        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        return scene;
    }

}