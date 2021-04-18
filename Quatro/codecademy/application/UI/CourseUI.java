package Quatro.codecademy.application.UI;

import java.util.ArrayList;

import Quatro.codecademy.application.controllers.UIController;
import Quatro.codecademy.application.controllers.WebcastController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

// Class for everything UI related of the courses
public class CourseUI implements InterfaceUI {

    // Connection to the required databases for this class
    private WebcastController webcastController = new WebcastController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // Method which creates a new UI for the main page of the courses
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        VBox vBox = new VBox();
        layout.setStyle("-fx-background-color: #C8F8FA;");
        layout.setMinSize(500, 300);
        Text title = new Text("KansloosCodeCademy");
        Text subtitle = new Text("Course Managing");
        vBox.getChildren().add(title);
        vBox.getChildren().add(subtitle);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        title.setFont(new Font(18));
        subtitle.setFont(new Font(18));
        Button courseNew = new Button("New Course");
        courseNew.setFont(new Font(11));
        courseNew.setMinWidth(200);
        courseNew.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        Button courseEdit = new Button("Add new content to an existing course");
        courseEdit.setFont(new Font(11));
        courseEdit.setMinWidth(200);
        courseEdit.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        Button courseRelated = new Button("View related courses");
        courseRelated.setMinWidth(200);
        courseRelated.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        Button avgProgress = new Button("Average progression per course");
        avgProgress.setMinWidth(200);
        avgProgress.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");

        courseNew.setOnAction((event) -> controller.switchUI("newcourseUI"));
        courseEdit.setOnAction((event) -> controller.switchUI("addcontenttocourseUI"));
        courseRelated.setOnAction((event) -> controller.switchUI("relatedcourseUI"));
        avgProgress.setOnAction((event) -> controller.switchUI("avgprogUI"));

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(20, 20, 20, 20));

        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(courseNew, 0, 1);
        gridPane.add(courseEdit, 0, 2);
        gridPane.add(courseRelated, 0, 3);
        gridPane.add(avgProgress, 0, 4);
        VBox bottom = new VBox();
        bottom.setAlignment(Pos.CENTER);
        Button back = new Button("Back to home");
        back.setMinWidth(400);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setOnAction((event) -> controller.switchUI("main"));
        bottom.setPadding(new Insets(0, 10, 10, 10));
        bottom.getChildren().add(back);
        Label top3Webcasts = new Label();
        ArrayList<String> casts = webcastController.getViewCount();
        String webcasts = "";
        Label webcastsText = new Label("Top 3 webcasts:");
        webcastsText.setPadding(new Insets(10, 0, 0, 0));
        for (String webcast : casts) {
            webcasts = webcasts + webcast + "\n";
        }
        top3Webcasts.setText(webcasts);
        bottom.getChildren().add(webcastsText);
        bottom.getChildren().add(top3Webcasts);
        layout.setTop(vBox);
        layout.setCenter(gridPane);
        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        return scene;
    }

}
