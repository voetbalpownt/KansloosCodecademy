package Quatro.codecademy.application.UI;

import java.util.ArrayList;

import Quatro.codecademy.application.controllers.CourseController;
import Quatro.codecademy.application.controllers.UIController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RelatedCourseUI implements InterfaceUI {

    // Connection to the required databases for this class
    private CourseController courseController = new CourseController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // Method which creates a new UI to view related courses
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(500, 300);
        layout.setStyle("-fx-background-color: #C8F8FA;");
        ComboBox<String> courseComboBox = new ComboBox<String>();
        ArrayList<String> courses = courseController.getCourses();
        for (String course : courses) {
            courseComboBox.getItems().add(course);
        }
        Label output = new Label();
        Button back = new Button("Back");
        Text courseText = new Text("Select Course");
        Text relatedText = new Text("Related Courses");

        back.setOnAction((event) -> controller.switchUI("courseUI"));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(courseText, 0, 0);
        gridPane.add(courseComboBox, 1, 0);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setMaxWidth(200);
        output.setPrefHeight(150);

        layout.setTop(gridPane);
        VBox vbox = new VBox();
        courseComboBox.setOnAction((event) -> {
            String selected = String.valueOf(courseComboBox.getSelectionModel().getSelectedItem());
            String rcourses = "";
            if (selected != "null") {
                ArrayList<String> related = courseController.getInterestingCourses(selected);
                for (String relatedc : related) {
                    rcourses = rcourses + relatedc + "\n";
                }
                output.setText(rcourses);
            }
        });
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setMaxWidth(200);
        vbox.getChildren().add(relatedText);
        vbox.getChildren().add(output);
        vbox.getChildren().add(back);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(0, 5, 5, 5));
        layout.setBottom(vbox);

        Scene scene = new Scene(layout);
        return scene;
    }

}

