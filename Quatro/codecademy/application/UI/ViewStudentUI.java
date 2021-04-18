package Quatro.codecademy.application.UI;

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

import java.sql.SQLException;
import java.util.ArrayList;

import Quatro.codecademy.application.controllers.StudentController;
import Quatro.codecademy.application.controllers.UIController;

public class ViewStudentUI implements InterfaceUI {

    // Connection to the database used for the class
    private StudentController studentController = new StudentController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // Method which creates a new UI to view a student
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(500, 300);
        layout.setStyle("-fx-background-color: #C8F8FA;");

        Text textStudents = new Text("Student");
        ComboBox<String> studentComboBox = new ComboBox<String>();
        ArrayList<String> students = studentController.getStudents();
        for (String student : students) {
            studentComboBox.getItems().add(student);
        }
        Label output = new Label();
        Button delete = new Button("Delete Student");
        Button back = new Button("Go Back");
        Button addCertificate = new Button("Add a Certificate");
        Text textName = new Text("Name");
        Text textDoB = new Text("Date of Birth");
        Text textGender = new Text("Gender");
        Text textCity = new Text("City");
        Text textAddress = new Text("Address");
        Text textCountry = new Text("Country");
        Text textPostalCode = new Text("Postalcode");
        Text certificates = new Text("");
        TextField nameField = new TextField();
        TextField doBField = new TextField();
        TextField genderField = new TextField();
        TextField cityField = new TextField();
        TextField addressField = new TextField();
        TextField countryField = new TextField();
        TextField postalCodeField = new TextField();
        nameField.setEditable(false);
        doBField.setEditable(false);
        genderField.setEditable(false);
        cityField.setEditable(false);
        addressField.setEditable(false);
        countryField.setEditable(false);
        postalCodeField.setEditable(false);

        studentComboBox.setOnAction((event) -> {
            String selected = String.valueOf(studentComboBox.getSelectionModel().getSelectedItem());
            if (selected != "null") {
                certificates.setText(studentController.getCertificatesByEmail(selected));
                nameField.setText(studentController.getName(selected));
                doBField.setText(studentController.getInfoByEmail(selected, "DateOfBirth"));
                genderField.setText(studentController.getInfoByEmail(selected, "Gender"));
                cityField.setText(studentController.getInfoByEmail(selected, "City"));
                addressField.setText(studentController.getInfoByEmail(selected, "Address"));
                countryField.setText(studentController.getInfoByEmail(selected, "Country"));
                postalCodeField.setText(studentController.getInfoByEmail(selected, "PostalCode"));
            }
        });

        back.setOnAction((event) -> controller.switchUI("studentUI"));
        addCertificate.setOnAction((event) -> controller.switchUI("addcertificateUI"));

        delete.setOnAction((event) -> {
            String selected = String.valueOf(studentComboBox.getSelectionModel().getSelectedItem());
            if (selected != "null") {
                try {
                    studentController.removeStudent("DELETE FROM Student WHERE EmailAddress = " + "\'" + selected + "\'");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                output.setText("Student has been deleted");
                System.out.println(selected);
                studentComboBox.getItems().clear();
                ArrayList<String> newStudents = studentController.getStudents();
                for (String student : newStudents) {
                    studentComboBox.getItems().add(student);
                }
            } else {
                output.setText("No student has been selected");
            }
        });

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(textStudents, 0, 0);
        gridPane.add(studentComboBox, 1, 0);
        gridPane.add(textName, 0, 1);
        gridPane.add(nameField, 1, 1);
        gridPane.add(textDoB, 0, 2);
        gridPane.add(doBField, 1, 2);
        gridPane.add(textGender, 0, 3);
        gridPane.add(genderField, 1, 3);
        gridPane.add(textCountry, 0, 4);
        gridPane.add(countryField, 1, 4);
        gridPane.add(textCity, 0, 5);
        gridPane.add(cityField, 1, 5);
        gridPane.add(textAddress, 0, 6);
        gridPane.add(addressField, 1, 6);
        gridPane.add(textPostalCode, 0, 7);
        gridPane.add(postalCodeField, 1, 7);
        VBox vbox = new VBox();
        Text textCertificates = new Text("Certificates");
        addCertificate.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        addCertificate.setMaxWidth(200);
        vbox.getChildren().add(textCertificates);
        vbox.getChildren().add(certificates);
        vbox.getChildren().add(addCertificate);
        vbox.getChildren().add(delete);
        vbox.getChildren().add(back);
        vbox.getChildren().add(output);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20, 10, 10, 10));
        // Styling nodes
        delete.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        delete.setMaxWidth(200);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setMaxWidth(200);

        layout.setTop(gridPane);
        layout.setBottom(vbox);

        Scene scene = new Scene(layout);
        return scene;
    }

}
