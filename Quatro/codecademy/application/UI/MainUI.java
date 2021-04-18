package Quatro.codecademy.application.UI;

import Quatro.codecademy.application.controllers.UIController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainUI implements InterfaceUI {

 // Method which creates the first page of the UI
 public Scene getUI(UIController controller) {
    BorderPane layout = new BorderPane();
    VBox vBox = new VBox();
    layout.setStyle("-fx-background-color: #C8F8FA;");
    layout.setMinSize(500, 300);
    Text title = new Text("KansloosCodecademy");
    Text subtitle = new Text("Home");
    vBox.getChildren().add(title);
    vBox.getChildren().add(subtitle);
    vBox.setAlignment(Pos.BASELINE_CENTER);
    title.setFont(new Font(16));
    subtitle.setFont(new Font(16));
    Button student = new Button("Student");
    Button course = new Button("Course");
    Button enrollment = new Button("Enrollment");
    Button certificate = new Button("Certificate");
    student.setMinWidth(200);
    student.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
    course.setMinWidth(200);
    course.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
    enrollment.setMinWidth(200);
    enrollment.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
    certificate.setMinWidth(200);
    certificate.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");

    student.setOnAction((event) -> controller.switchUI("studentUI"));
    course.setOnAction((event) -> controller.switchUI("courseUI"));
    enrollment.setOnAction((event) -> controller.switchUI("enrollmentUI"));
    certificate.setOnAction((event) -> controller.switchUI("certificateUI"));


    GridPane gridPane = new GridPane();

    gridPane.setVgap(10);
    gridPane.setHgap(10);

    gridPane.setAlignment(Pos.CENTER);
    gridPane.add(student, 0, 1);
    gridPane.add(course, 0, 2);
    gridPane.add(enrollment, 0, 3);
    gridPane.add(certificate, 0, 4);

    layout.setTop(vBox);
    layout.setCenter(gridPane);

    Scene scene = new Scene(layout);
    return scene;
}


}
