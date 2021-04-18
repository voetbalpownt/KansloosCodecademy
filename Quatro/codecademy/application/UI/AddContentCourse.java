package Quatro.codecademy.application.UI;

import java.sql.SQLException;
import java.util.ArrayList;

import Quatro.codecademy.application.controllers.CourseController;
import Quatro.codecademy.application.controllers.ContentController;
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

// Class for adding content to a course
public class AddContentCourse implements InterfaceUI {

    // Connection to the required databases for this class
    private CourseController courseController = new CourseController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private ContentController contentController = new ContentController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // Method which creates a new UI for adding content to a course
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(500, 300);
        layout.setStyle("-fx-background-color: #C8F8FA;");
        ComboBox<String> courseComboBox = new ComboBox<String>();
        ArrayList<String> courses = courseController.getCourses();
        for (String course : courses) {
            courseComboBox.getItems().add(course);
        }
        ComboBox<String> contentComboBox = new ComboBox<String>();
        ArrayList<String> contents = contentController.getAvailableContent();
        for (String content : contents) {
            contentComboBox.getItems().add(content);
        }
        Label output = new Label();
        Button back = new Button("Back");
        Text courseText = new Text("Select Course");
        Text contentText = new Text("Select Content");

        back.setOnAction((event) -> controller.switchUI("courseUI"));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(courseText, 0, 0);
        gridPane.add(courseComboBox, 1, 0);
        gridPane.add(contentText, 0, 1);
        gridPane.add(contentComboBox, 1, 1);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setMaxWidth(200);

        layout.setTop(gridPane);
        VBox vbox = new VBox();
        Button addContent = new Button("Add Content to Course");
        addContent.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        addContent.setMaxWidth(200);
        addContent.setOnAction((event) -> {
            String selectedContent = String.valueOf(contentComboBox.getSelectionModel().getSelectedItem());
            String selectedCourse = String.valueOf(courseComboBox.getSelectionModel().getSelectedItem());
            if (selectedContent != "null" && selectedCourse != "null") {
                try {
                    contentController.editModule(selectedContent, selectedCourse);
                } catch (SQLException e) {
                    e.printStackTrace();
                } 
                output.setText("Content has been added to course");
            } else {
                output.setText("Please select a course and content");
            }
        });
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setMaxWidth(200);
        vbox.getChildren().add(addContent);
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