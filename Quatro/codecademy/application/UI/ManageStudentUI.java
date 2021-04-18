package Quatro.codecademy.application.UI;

import java.sql.SQLException;
import java.util.ArrayList;

import Quatro.codecademy.application.controllers.CourseController;
import Quatro.codecademy.application.controllers.EnrollmentController;
import Quatro.codecademy.application.controllers.StudentController;
import Quatro.codecademy.application.controllers.UIController;
import Quatro.codecademy.application.utils.Validate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

// Class for managing student's progress in the UI
public class ManageStudentUI implements InterfaceUI {

    // Connection to the database used for this class
    private StudentController studentController = new StudentController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private EnrollmentController enrollmentController = new EnrollmentController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private CourseController courseController = new CourseController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // Method which creates a new UI to manage a student's progression
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
        ComboBox<String> moduleComboBox = new ComboBox<String>();
        Label output = new Label();
        Button back = new Button("Back");
        Text studentText = new Text("Select Student");
        Text enrollmentText = new Text("Select Course");
        Text moduleText = new Text("Select Module");
        Text progressText = new Text("Progression (in %)");
        TextField progressField = new TextField();

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
        gridPane.add(moduleText, 0, 2);
        gridPane.add(moduleComboBox, 1, 2);
        gridPane.add(progressText, 0, 3);
        gridPane.add(progressField, 1, 3);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setMaxWidth(200);
        layout.setTop(gridPane);

        studentComboBox.setOnAction((event) -> {
            String selected = String.valueOf(studentComboBox.getSelectionModel().getSelectedItem());
            courseComboBox.getSelectionModel().clearSelection();
            courseComboBox.getItems().clear();
            if (selected != "null") {
                ArrayList<String> courses = enrollmentController.getAvailableCertificates(selected);
                courses = enrollmentController.getAvailableCertificates(selected);
                for (String course : courses) {
                    courseComboBox.getItems().add(course);
                }
            }
        });
        courseComboBox.setOnAction((event) -> {
            String selected = String.valueOf(courseComboBox.getSelectionModel().getSelectedItem());
            moduleComboBox.getSelectionModel().clearSelection();
            moduleComboBox.getItems().clear();
            if (selected != "null") {
                ArrayList<String> modules = courseController.getContent(selected);
                for (String module : modules) {
                    moduleComboBox.getItems().add(module);
                }
            }
        });
        VBox vbox = new VBox();
        Button setProgression = new Button("Set Progression");
        setProgression.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        setProgression.setMaxWidth(200);
        setProgression.setOnAction((event) -> {
            String studentSelected = String.valueOf(studentComboBox.getSelectionModel().getSelectedItem());
            String courseSelected = String.valueOf(courseComboBox.getSelectionModel().getSelectedItem());
            String contentSelected = String.valueOf(moduleComboBox.getSelectionModel().getSelectedItem());
            if (studentSelected != "null" && courseSelected != "null" && contentSelected != "null") {
                double percentage = Double.valueOf(progressField.getText());
                if (Validate.validatePercentage(percentage)) {
                    String type[] = contentSelected.split("] ");
                    int id = 0;
                    if (type[0].contains("Webcast")) {
                        id = courseController.getContentId(type[1], "Webcast");
                    } else {
                        id = courseController.getContentId(type[1], "Module");
                    }
                    try {
                        courseController.editProgress(studentSelected, id, percentage);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    output.setText("Succesfully editted progress percentage for selected content");
                } else {
                    output.setText("Please enter a valid percentage");
                }
            } else {
                output.setText("Please verify all fields");
            }

        });
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setMaxWidth(200);
        vbox.getChildren().add(output);
        vbox.getChildren().add(setProgression);
        vbox.getChildren().add(back);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 10, 10));
        layout.setBottom(vbox);

        Scene scene = new Scene(layout);
        return scene;
    }

}
