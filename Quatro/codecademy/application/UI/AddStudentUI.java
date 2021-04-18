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

import Quatro.codecademy.application.controllers.CertificateController;
import Quatro.codecademy.application.controllers.UIController;
import Quatro.codecademy.application.entity.*;
import Quatro.codecademy.application.logic.Gender;

public class AddStudentUI implements InterfaceUI {

    // Connection to the Database used for the class
    private CertificateController certificateController = new CertificateController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // Method which creates a new UI for adding a student to the database
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(500, 300);
        layout.setStyle("-fx-background-color: #C8F8FA;");

        Text textEmail = new Text("Email");
        Text textName = new Text("Name");
        Text textDoB = new Text("Date of Birth");
        Text textGender = new Text("Gender");
        Text textAddress = new Text("Address");
        Text textCity = new Text("City");
        Text textCountry = new Text("Country");
        Text textPostalCode = new Text("Postalcode");

        TextField emailField = new TextField();
        TextField nameField = new TextField();
        TextField dayField = new TextField();
        dayField.setPrefWidth(40);
        dayField.setMaxWidth(40);
        TextField monthField = new TextField();
        monthField.setPrefWidth(40);
        monthField.setMaxWidth(40);
        TextField yearField = new TextField();
        yearField.setPrefWidth(60);
        yearField.setMaxWidth(60);
        ComboBox<Gender> genders = new ComboBox<Gender>();
        genders.getItems().addAll(Gender.values());
        TextField cityField = new TextField();
        TextField addressField = new TextField();
        TextField countryField = new TextField();
        TextField postalCodeField = new TextField();
        emailField.setPromptText("Email address");
        nameField.setPromptText("Name");
        dayField.setPromptText("Day");
        monthField.setPromptText("Month");
        yearField.setPromptText("Year");
        addressField.setPromptText("Address");
        cityField.setPromptText("City");
        countryField.setPromptText("Country");
        postalCodeField.setPromptText("Postalcode");
        Label output = new Label();
        Label countCompleted = new Label();
        Button submit = new Button("Submit");
        Button back = new Button("Back");

        genders.setOnAction((event) -> {
            countCompleted.setText(String.valueOf(certificateController.getPercentage(genders.getValue()))
                    + "% of students with this gender\nreceived a certificate after an enrollment");
        });

        submit.setOnAction((event) -> {
            if (emailField.getText().isEmpty() || nameField.getText().isEmpty() || dayField.getText().isEmpty()
                    || monthField.getText().isEmpty() || yearField.getText().isEmpty()
                    || genders.getSelectionModel().isEmpty() || countryField.getText().isEmpty()
                    || cityField.getText().isEmpty() || addressField.getText().isEmpty()
                    || postalCodeField.getText().isEmpty()) {
                output.setText("Verify all fields");
            } else {
                try {
                    output.setText(Student.addStudent(emailField.getText(), nameField.getText(),
                            Integer.valueOf(dayField.getText()), Integer.valueOf(monthField.getText()),
                            Integer.valueOf(yearField.getText()), String.valueOf(genders.getValue()),
                            countryField.getText(), cityField.getText(), addressField.getText(),
                            postalCodeField.getText()));
                } catch (NumberFormatException | SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        back.setOnAction((event) -> controller.switchUI("studentUI"));

        GridPane gridPane = new GridPane();

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(textEmail, 0, 0);
        gridPane.add(emailField, 1, 0);
        gridPane.add(textName, 0, 1);
        gridPane.add(nameField, 1, 1);
        gridPane.add(textDoB, 0, 2);
        gridPane.add(dayField, 1, 2);
        gridPane.add(monthField, 1, 2);
        gridPane.add(yearField, 1, 2);
        gridPane.add(textGender, 0, 3);
        gridPane.add(genders, 1, 3);
        gridPane.add(countCompleted, 1, 4);
        gridPane.add(textCountry, 0, 5);
        gridPane.add(countryField, 1, 5);
        gridPane.add(textCity, 0, 6);
        gridPane.add(cityField, 1, 6);
        gridPane.add(textAddress, 0, 7);
        gridPane.add(addressField, 1, 7);
        gridPane.add(textPostalCode, 0, 8);
        gridPane.add(postalCodeField, 1, 8);
        VBox texts = new VBox();
        texts.getChildren().add(submit);
        texts.getChildren().add(back);
        texts.getChildren().add(output);
        texts.setAlignment(Pos.BASELINE_CENTER);
        texts.setSpacing(10);
        texts.setPadding(new Insets(10, 10, 10, 10));
        // Styling nodes
        submit.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        submit.setMaxWidth(200);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setMaxWidth(200);
        GridPane.setMargin(monthField, new Insets(0, 0, 0, 45));
        GridPane.setMargin(yearField, new Insets(0, 0, 0, 90));

        layout.setTop(gridPane);
        layout.setBottom(texts);

        Scene scene = new Scene(layout);
        return scene;
    }

}