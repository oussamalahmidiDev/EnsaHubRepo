package application.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class About {


    @FXML
    private javafx.scene.control.Button closeButton;

    private void closelastButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public static void showAbout() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(About.class.getResource("About.fxml"));
        Image icon = new Image(Home.class.getResourceAsStream("../Images/ensa1.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("ENSAHub");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    public void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warnning");
        alert.setHeaderText("Look you want to close the program !");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            stage.close();
        } else {
            alert.close();

        }

    }

    public void back() throws IOException {
        closelastButtonAction();
        if (Home.getCurrentUser() != null)
            CoursesView.load();
        else Home.Connect();
    }

}
