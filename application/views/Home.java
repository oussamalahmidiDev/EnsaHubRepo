package application.views;

import application.controllers.*;
import application.models.*;
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

public class Home {

    @FXML
    private javafx.scene.control.TextField Password;

    @FXML
    private javafx.scene.control.TextField Email;
    @FXML private javafx.scene.control.Button closeButton;
    @FXML private javafx.scene.control.ProgressIndicator loading;
    static String email;
    static String password;

    private static User currentUser;
    public static User getCurrentUser() {
        return currentUser;
    }

    private void closelastButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void showSignUp() throws IOException {
        closelastButtonAction();
        SignUp.loadView();
    }
    public void turnOnTheLights() throws IOException {
        closelastButtonAction();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Home.class.getResource("home_light.fxml"));
        Image icon = new Image(Home.class.getResourceAsStream("../Images/ensa1.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("ENSAHub");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }
    public void turnOffTheLights() throws IOException {
        closelastButtonAction();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Home.class.getResource("HOME.fxml"));
        Image icon = new Image(Home.class.getResourceAsStream("../Images/ensa1.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("ENSAHub");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }


    public void loginAction() throws Exception {
        email = Email.getText();
        password = Password.getText();
        if (AuthController.verifyCredentials(email, password)) {
        	System.out.println("WELCOME ");
            currentUser = (User) AuthController.getUserDetails(email);
            Stage stage = (Stage) closeButton.getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bienvenue");
            alert.setHeaderText("Welcome to EnsaHub");
            alert.setContentText("Click OK to continue.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                stage.close();
                redirect();
                closelastButtonAction();
            } else {
                alert.close();
            }
        }
        else{
            closelastButtonAction();
            showErrorPage();
        }

    }

    public void about() throws IOException {
        closelastButtonAction();
        About.showAbout();
    }

    public static void Connect() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Home.class.getResource("HOME.fxml"));
        Image icon = new Image(Home.class.getResourceAsStream("../Images/ensa1.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("ENSAHub");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    public void redirect() throws Exception {
        CoursesView c = new CoursesView();
        c.load();
    }

    public static void showErrorPage() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Home.class.getResource("Errordata.fxml"));
        Image icon = new Image(Home.class.getResourceAsStream("../Images/ensa1.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("ENSAHub - Error");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    public void closeButtonAction(){

        Stage stage = (Stage) closeButton.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warnning");
        alert.setHeaderText("Look you want to close the program !");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            stage.close();
        } else {
            alert.close();

        }

    }

}

