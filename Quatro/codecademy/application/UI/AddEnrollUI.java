package Quatro.codecademy.application.UI;

import java.sql.SQLException;
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

// Class for adding a new enrollment to a student
public class AddEnrollUI implements InterfaceUI {

    // Connection to the databases used for this class
    private StudentController studentController = new StudentController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private CourseController courseController = new CourseController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private EnrollmentController enrollmentController = new EnrollmentController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // Method which creates a new UI for adding an enrollment to a student
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(500, 300);
        layout.setStyle("-fx-background-color: #C8F8FA;");
        ComboBox<String> studentsComboBox = new ComboBox<String>();
        ArrayList<String> students = studentController.getStudents();
        for (String student : students) {
            studentsComboBox.getItems().add(student);
        }
        ComboBox<String> courseComboBox = new ComboBox<String>();
        ArrayList<String> courses = courseController.getCourses();
        for (String course : courses) {
            courseComboBox.getItems().add(course);
        }
        Label output = new Label();
        Button back = new Button("Back");
        Text textCourse = new Text("Select Course");
        Text textStudent = new Text("Select Student");

        back.setOnAction((event) -> controller.switchUI("enrollmentUI"));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(textCourse, 0, 0);
        gridPane.add(courseComboBox, 1, 0);
        gridPane.add(textStudent, 0, 1);
        gridPane.add(studentsComboBox, 1, 1);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setMaxWidth(200);

        layout.setTop(gridPane);
        VBox vbox = new VBox();
        Button addEnrollment = new Button("Add Enrollment to Student");
        addEnrollment.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        addEnrollment.setMaxWidth(200);
        addEnrollment.setOnAction((event) -> {
            String selectedStudent = String.valueOf(studentsComboBox.getSelectionModel().getSelectedItem());
            String selectedCourse = String.valueOf(courseComboBox.getSelectionModel().getSelectedItem());
            if (selectedStudent != "null" && selectedCourse != "null") {
                if (!enrollmentController.checkDuplicate(selectedStudent, selectedCourse)) {
                    try {
                        ArrayList<Integer> ids = courseController.getContentIds(selectedCourse);
                        for (int id : ids) {
                            courseController.addProgress(selectedStudent, id);
                        }
                        enrollmentController.addEnrollment(selectedStudent, selectedCourse);
                        output.setText("Succesfully added enrollment for " + selectedStudent);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    output.setText("Student is already enrolled for the selected course");
                }
            } else {
                output.setText("Please select a course and student");
            }

        });
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setMaxWidth(200);
        vbox.getChildren().add(output);
        vbox.getChildren().add(addEnrollment);
        vbox.getChildren().add(back);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 10, 10));
        layout.setBottom(vbox);

        Scene scene = new Scene(layout);
        return scene;
    }

}
