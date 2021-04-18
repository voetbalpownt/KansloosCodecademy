package Quatro.codecademy.application.UI;

import Quatro.codecademy.application.controllers.CertificateController;
import Quatro.codecademy.application.controllers.UIController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CertUI implements InterfaceUI {

    // connection to the required databases for this class
    private CertificateController certificateController = new CertificateController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // method which creates a new UI
    public Scene getUI(UIController controller) {
        BorderPane layout = new BorderPane();
        VBox vBox = new VBox();
        layout.setStyle("-fx-background-color: #C8F8FA;");
        layout.setMinSize(500, 300);
        Text title = new Text("KansloosCodeCademy");
        Text subtitle = new Text("Certificate Managing");
        vBox.getChildren().add(title);
        vBox.getChildren().add(subtitle);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        title.setFont(new Font(18));
        subtitle.setFont(new Font(18));
        Button certificateAdd = new Button("Add new certificate");
        certificateAdd.setMinWidth(400);
        certificateAdd.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        certificateAdd.setOnAction((event) -> controller.switchUI("addcertificateUI"));
        VBox bottom = new VBox();
        bottom.setAlignment(Pos.CENTER);
        Button back = new Button("Back to home");
        Text textTop3 = new Text("Top 3 given certificates:");
        Text textTop3Result = new Text("");
        textTop3Result.setText(certificateController.getTop());
        back.setMinWidth(400);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #00FFEE;");
        back.setOnAction((event) -> controller.switchUI("main"));
        bottom.getChildren().add(certificateAdd);
        bottom.getChildren().add(back);
        bottom.getChildren().add(textTop3);
        bottom.getChildren().add(textTop3Result);
        bottom.setPadding(new Insets(0, 10, 10, 10));
        bottom.setSpacing(10);

        layout.setTop(vBox);
        layout.setBottom(bottom);

        Scene scene = new Scene(layout);
        return scene;
    }

}