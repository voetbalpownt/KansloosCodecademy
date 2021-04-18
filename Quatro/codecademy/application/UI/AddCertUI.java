package Quatro.codecademy.application.UI;

import java.sql.SQLException;
import java.util.ArrayList;

import Quatro.codecademy.application.controllers.CertificateController;
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

// Class for adding a new certificate
public class AddCertUI implements InterfaceUI {

    // Connection to the required databases for this class
    private StudentController studentController = new StudentController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private EnrollmentController enrollmentController = new EnrollmentController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private CertificateController certificateController = new CertificateController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // Method which creates a new UI for adding a certificate
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(500, 300);
        layout.setStyle("-fx-background-color: #C8F8FA;");
        ComboBox<String> studentsComboBox = new ComboBox<String>();
        ArrayList<String> students = studentController.getStudents();
        for (String student : students) {
            studentsComboBox.getItems().add(student);
        }
        ComboBox<String> enrollmentComboBox = new ComboBox<String>();
        Label output = new Label();
        Label certificateCount = new Label();
        Button back = new Button("Back");
        Text studentText = new Text("Select Student");
        Text enrollmentText = new Text("Select Enrollment");
        Text gradeText = new Text("Grade (1-10)");
        TextField gradeField = new TextField();
        Text employeeText = new Text("Employee");
        TextField employeeField = new TextField();

        back.setOnAction((event) -> controller.switchUI("certificateUI"));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(studentText, 0, 0);
        gridPane.add(studentsComboBox, 1, 0);
        gridPane.add(enrollmentText, 0, 1);
        gridPane.add(enrollmentComboBox, 1, 1);
        gridPane.add(gradeText, 0, 2);
        gridPane.add(gradeField, 1, 2);
        gridPane.add(employeeText, 0, 3);
        gridPane.add(employeeField, 1, 3);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setMaxWidth(200);

        layout.setTop(gridPane);

        studentsComboBox.setOnAction((event) -> {
            ArrayList<String> enrollments = new ArrayList<>();
            String selected = String.valueOf(studentsComboBox.getSelectionModel().getSelectedItem());
            enrollmentComboBox.getSelectionModel().clearSelection();
            enrollmentComboBox.getItems().clear();
            if (selected != "null") {
                enrollments = enrollmentController.getAvailableCertificates(selected);
                for (String enrollment : enrollments) {
                    enrollmentComboBox.getItems().add(enrollment);
                }
            }
        });
        enrollmentComboBox.setOnAction((event) -> {
            String selected = String.valueOf(enrollmentComboBox.getSelectionModel().getSelectedItem());
            certificateCount.setText("Current certificates given for selected course: "
                    + String.valueOf(certificateController.getCount(selected)));
        });
        VBox vbox = new VBox();
        Button addCertificate = new Button("Add Certificate to Student");
        addCertificate.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        addCertificate.setMaxWidth(200);
        addCertificate.setOnAction((event) -> {
            String selectedStudent = String.valueOf(studentsComboBox.getSelectionModel().getSelectedItem());
            String selectedEnrollment = String.valueOf(enrollmentComboBox.getSelectionModel().getSelectedItem());
            if (selectedStudent != "null" && selectedEnrollment != "null" && employeeField.getText().length() > 0
                    && gradeField.getText().length() > 0
                    && Validate.validateRating(Double.valueOf(gradeField.getText()))) {
                try {
                    enrollmentController.addCertificate(selectedStudent, selectedEnrollment, employeeField.getText(),
                            Double.valueOf(gradeField.getText()));
                    output.setText("Certificate has been added to " + selectedStudent);
                    enrollmentComboBox.getSelectionModel().clearSelection();
                    enrollmentComboBox.getItems().clear();
                    studentsComboBox.getSelectionModel().clearSelection();
                } catch (NumberFormatException | SQLException e) {
                    e.printStackTrace();
                }
            } else {
                output.setText("Please verify all fields");
            }

        });
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setMaxWidth(200);
        vbox.getChildren().add(certificateCount);
        vbox.getChildren().add(addCertificate);
        vbox.getChildren().add(back);
        vbox.getChildren().add(output);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 10, 10));
        layout.setBottom(vbox);

        Scene scene = new Scene(layout);
        return scene;
    }

}
