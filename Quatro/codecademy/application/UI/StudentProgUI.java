package Quatro.codecademy.application.UI;

import java.util.ArrayList;

import Quatro.codecademy.application.controllers.CourseController;
import Quatro.codecademy.application.controllers.EnrollmentController;
import Quatro.codecademy.application.controllers.StudentController;
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

public class StudentProgUI implements InterfaceUI {

    // connection to the database used in this class
    private StudentController studentController = new StudentController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private EnrollmentController enrollmentController = new EnrollmentController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private CourseController courseController = new CourseController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // method which creates a new UI
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(500, 300);
        layout.setStyle("-fx-background-color: #C8F8FA;");
        ComboBox<String> studentComboBox = new ComboBox<String>();
        ArrayList<String> students = studentController.getStudents();
        for (String student : students) {
            studentComboBox.getItems().add(student);
        }
        ComboBox<String> courseComboBox = new ComboBox<String>();
        Label output = new Label();
        Button back = new Button("Back");
        Text studentText = new Text("Select Student");
        Text enrollmentText = new Text("Select Course");

        back.setOnAction((event) -> controller.switchUI("studentUI"));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(studentText, 0, 0);
        gridPane.add(studentComboBox, 1, 0);
        gridPane.add(enrollmentText, 0, 1);
        gridPane.add(courseComboBox, 1, 1);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setMaxWidth(200);
        layout.setTop(gridPane);

        studentComboBox.setOnAction((event) -> {
            String selected = String.valueOf(studentComboBox.getSelectionModel().getSelectedItem());
            courseComboBox.getSelectionModel().clearSelection();
            courseComboBox.getItems().clear();
            output.setText("");
            if (selected != "null") {
                ArrayList<String> courses = enrollmentController.getAvailableCertificates(selected);
                courses = enrollmentController.getAvailableCertificates(selected);
                for (String course : courses) {
                    courseComboBox.getItems().add(course);
                }
            }
        });
        courseComboBox.setOnAction((event) -> {
            String selectedCourse = String.valueOf(courseComboBox.getSelectionModel().getSelectedItem());
            String selectedStudent = String.valueOf(studentComboBox.getSelectionModel().getSelectedItem());
            String end = "";
            if (selectedCourse != "null" && selectedStudent != "null") {
                ArrayList<Integer> results = courseController.getContentIds(selectedCourse);
                ArrayList<String> contents = courseController.getContent(selectedCourse);
                ArrayList<Double> progress = new ArrayList<>();
                for (int result : results) {
                    progress.add(courseController.getProgress(result, selectedStudent));
                }
                for (int i = 0; i < results.size(); i++) {
                    end = end + contents.get(i) + " | Progress (%): " + progress.get(i) + "\n";
                }
                output.setText(end);
            }
        });
        VBox vbox = new VBox();
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setMaxWidth(200);
        output.setPrefHeight(100);
        vbox.getChildren().add(output);
        vbox.getChildren().add(back);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 10, 10));
        layout.setBottom(vbox);

        Scene scene = new Scene(layout);
        return scene;
    }

}
