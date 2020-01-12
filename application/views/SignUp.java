package application.views;

import application.daoimpl.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.models.User;


public class SignUp implements Initializable {

    @FXML
    private javafx.scene.control.TextField Name;
    @FXML
    private javafx.scene.control.TextField Last_name;
    @FXML
    private javafx.scene.control.TextField Number;
    @FXML
    private javafx.scene.control.TextField Password;

    @FXML
    private ComboBox<String> Type;
    ObservableList<String> list1 = FXCollections.observableArrayList("Etudiant", "Prof" );
    @FXML
    private javafx.scene.control.TextField Email;

    @FXML private javafx.scene.control.Button closeButton;

    private void closelastButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    public void back() throws IOException {
            closelastButtonAction();
            Home.Connect();
    }
    public void about() throws IOException {
        closelastButtonAction();
        About.showAbout();
    }

    public static void loadView() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Home.class.getResource("Sign_up.fxml"));
        Image icon = new Image(SignUp.class.getResourceAsStream("../Images/ensa1.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("ENSAHub");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }
    public void closeButtonAction()
    {

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

    public void signUpClicked() throws Exception {
        String nom = Name.getText();
        String last_name = Last_name.getText();
        String email = Email.getText();
        String number = Number.getText();
        String password = Password.getText();
        String type = Type.getValue();


        UserDao userDao = new UserDao();

        User u = new User (nom,last_name, email,number, password,type);
        System.out.println(u.toString());


        boolean status = userDao.create(u);

        System.out.println(status);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Type.setItems(list1);
    }
}


