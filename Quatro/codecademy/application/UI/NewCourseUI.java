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

import Quatro.codecademy.application.controllers.ContentController;
import Quatro.codecademy.application.controllers.CourseController;
import Quatro.codecademy.application.controllers.UIController;

public class NewCourseUI implements InterfaceUI {

    // Connection to the required databases for this class
    private ContentController contentController = new ContentController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");
    private CourseController courseController = new CourseController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // Method which creates a new UI for adding a new course to the database
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        layout.setMinSize(500, 300);
        layout.setStyle("-fx-background-color: #C8F8FA;");

        Text courseNameText = new Text("Course Name");
        Text subjectText = new Text("Subject");
        Text difficultyText = new Text("Difficulty");
        Text introductionText = new Text("Introduction Text");
        Text moduleText = new Text("Select First Module");

        TextField fieldCourseName = new TextField();
        TextField fieldSubject = new TextField();
        ComboBox<String> difficulty = new ComboBox<String>();
        ArrayList<String> difficulties = new ArrayList<>();
        difficulties.add("Easy");
        difficulties.add("Medium");
        difficulties.add("Hard");
        for (String difficulty1 : difficulties) {
            difficulty.getItems().add(difficulty1);
        }
        TextField fieldIntroduction = new TextField();
        ComboBox<String> modules = new ComboBox<String>();
        ArrayList<String> modulelist = contentController.getAvailableModules();
        for (String module : modulelist) {
            modules.getItems().add(module);
        }
        fieldCourseName.setPromptText("Course Name");
        fieldSubject.setPromptText("Subject");
        fieldIntroduction.setPromptText("Introduction Text");
        Label output = new Label();
        Button submit = new Button("Submit");
        Button back = new Button("Back");

        submit.setOnAction((event) -> {
            String selectedDifficulty = String.valueOf(difficulty.getSelectionModel().getSelectedItem());
            String selectedModule = String.valueOf(modules.getSelectionModel().getSelectedItem());
            System.out.println(selectedDifficulty);
            if (fieldCourseName.getText().isEmpty() || fieldSubject.getText().isEmpty()
                    || fieldIntroduction.getText().isEmpty() || selectedDifficulty == "null"
                    || selectedModule == "null") {
                output.setText("Verify all fields");
            } else {
                if (!courseController.checkDuplicate(fieldCourseName.getText())) {
                    try {
                        courseController.addCourse(fieldCourseName.getText(), fieldSubject.getText(), selectedDifficulty,
                                fieldIntroduction.getText());
                        contentController.editModule(selectedModule, fieldCourseName.getText());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    output.setText("Succesfully added a new course");
                }
            }
        });

        back.setOnAction((event) -> controller.switchUI("courseUI"));

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(courseNameText, 0, 0);
        gridPane.add(fieldCourseName, 1, 0);
        gridPane.add(subjectText, 0, 1);
        gridPane.add(fieldSubject, 1, 1);
        gridPane.add(difficultyText, 0, 2);
        gridPane.add(difficulty, 1, 2);
        gridPane.add(introductionText, 0, 3);
        gridPane.add(fieldIntroduction, 1, 3);
        gridPane.add(moduleText, 0, 4);
        gridPane.add(modules, 1, 4);
        VBox texts = new VBox();
        texts.getChildren().add(submit);
        texts.getChildren().add(back);
        texts.getChildren().add(output);
        texts.setAlignment(Pos.BASELINE_CENTER);
        texts.setSpacing(10);
        texts.setPadding(new Insets(0, 10, 10, 10));
        // Styling nodes
        submit.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        submit.setMaxWidth(200);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setMaxWidth(200);

        layout.setTop(gridPane);
        layout.setBottom(texts);

        Scene scene = new Scene(layout);
        return scene;
    }

}