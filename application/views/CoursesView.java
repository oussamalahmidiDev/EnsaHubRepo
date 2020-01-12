package application.views;

import application.controllers.FileController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import application.daoimpl.*;
import application.models.*;


import java.awt.*;
import java.io.*;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CoursesView implements Initializable {

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private javafx.scene.control.Button uploadButton;

    @FXML
    private javafx.scene.control.TextField courseName;
    @FXML
    private javafx.scene.control.TextField profName;
    @FXML
    private javafx.scene.control.DatePicker courseDate;
    @FXML
    private javafx.scene.control.ScrollPane scrollpaneCourses;

    @FXML
    private javafx.scene.control.Label nCourse;
    @FXML
    private javafx.scene.control.Label nProf;
    @FXML
    private javafx.scene.control.Label tCourse;
    @FXML
    private javafx.scene.control.DatePicker dCourse;

    @FXML
    Button addButton;

    @FXML
    private javafx.scene.control.Label typeUser;

    @FXML
    private javafx.scene.control.Label evnt;

    @FXML
    private javafx.scene.control.Label userName;

    @FXML
    private javafx.scene.control.Label welcomeText;

    @FXML
    private javafx.scene.layout.GridPane coursesContainer;
    @FXML
    private javafx.scene.layout.Pane addCoursePane;

    @FXML
    private javafx.scene.layout.Pane CoursePane;
    @FXML
    private javafx.scene.layout.Pane discoverPane;


    @FXML
    private ComboBox<String> courseType;
    ObservableList<String> list1 = FXCollections.observableArrayList("Courses", "TD", "Old EXAMS");

    static String nameCourse, nameProf, cType;
    static LocalDate datecourse;
    static String NameforDownload;

    static String uploadedFile;


    static int i = 0;
    static int j = 0;


    public static void load() throws IOException {
        Stage primaryStage = new Stage();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Parent root = FXMLLoader.load(Home.class.getResource("CoursesView.fxml"));
        Image icon = new Image(Home.class.getResourceAsStream("../Images/ensa1.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("ENSAHub - Cours");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }
    public void Logout() throws IOException {
        closelastButtonAction();
        Home.Connect();
    }

    public void home() throws IOException {
        closelastButtonAction();
        load();
    }

    public void events() throws IOException {
        closelastButtonAction();

        EventsView.Events();
    }

    public void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warnning");
        alert.setHeaderText("Looks you want to close the program !");
        alert.setContentText("Are you sure ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            stage.close();
        } else {
            alert.close();

        }
    }


    @FXML
    private void closelastButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void about() throws IOException {
        closelastButtonAction();
        About.showAbout();
    }

    public void addcourseevent() throws IOException {
        addCoursePane.setVisible(true);
        scrollpaneCourses.setVisible(false);
    }

    public void closeCoursePane() {
        addCoursePane.setVisible(false);
        scrollpaneCourses.setVisible(true);
        CoursePane.setVisible(true);
    }

    public void DoneClicked() throws Exception {
        addCoursePane.setVisible(false);
        nameCourse = courseName.getText();
        nameProf = profName.getText();
        datecourse = courseDate.getValue();
        cType = courseType.getValue();
        Date date = Date.valueOf(datecourse);
        System.out.println(nameCourse + " " + Home.getCurrentUser().getName() + " " + cType + " " + datecourse);
        try {
            DocumentDao d = new DocumentDao();
            Document newDoc = new Document(nameCourse, Home.getCurrentUser(), cType, date, uploadedFile);
            d.create(newDoc);
            set(newDoc);
            scrollpaneCourses.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void downloadCourse() {
        if (new FileController().downloadFile(NameforDownload)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Course is downloaded successfully in " + new FileController().getDownloadPath());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong ...");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            }
        }
    }

    public void uploadCourse() {
        FileChooser fileChooser = new FileChooser();
        Stage stage1 = (Stage) uploadButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage1);
        uploadedFile = new FileController().uploadFile(selectedFile, courseName.getText());
        System.out.println("FIle uploaded : " + uploadedFile);
    }

    public void set(Document d) {
        System.out.println(d.getName() + " " + d.getProf().getName() + " " + d.getType() + " " + d.getDate());
        coursesContainer.setPadding(new Insets(10, 10, 10, 10));
        coursesContainer.setVgap(10);
        coursesContainer.setHgap(10);

        courseType.setItems(list1);
        Label nameC = new Label(d.getName());
        GridPane.setConstraints(nameC, i, j);

        //Prof Name
        i++;
        Label nameP = new Label(d.getProf().getName());
        GridPane.setConstraints(nameP, i, j);


        // Type
        i++;
        Label typeC = new Label(d.getType());
        GridPane.setConstraints(typeC, i, j);


        //Date course
        i++;
        Date date = (Date) d.getDate();
        DatePicker dateC = new DatePicker(date.toLocalDate());
        dateC.setDisable(true);
        GridPane.setConstraints(dateC, i, j);


        // Button discover
        i++;
        Button buttonDiscover = new Button("Discover");
        buttonDiscover.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    discoverClicked(d);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        GridPane.setConstraints(buttonDiscover, i, j);

        j++;
        i = 0;

        Separator sp0 = new Separator();
        sp0.setOrientation(Orientation.HORIZONTAL);
        GridPane.setConstraints(sp0, i, j);

        i++;
        Separator sp1 = new Separator();
        sp1.setOrientation(Orientation.HORIZONTAL);
        GridPane.setConstraints(sp1, i, j);

        i++;
        Separator sp2 = new Separator();
        sp2.setOrientation(Orientation.HORIZONTAL);
        GridPane.setConstraints(sp2, i, j);

        i++;
        Separator sp3 = new Separator();
        sp3.setOrientation(Orientation.HORIZONTAL);
        GridPane.setConstraints(sp3, i, j);

        i++;
        Separator sp4 = new Separator();
        sp4.setOrientation(Orientation.HORIZONTAL);
        GridPane.setConstraints(sp4, i, j);


        j++;
        i = 0;
        coursesContainer.getChildren().addAll(nameC, nameP, typeC, dateC, buttonDiscover, sp0, sp1, sp2, sp3, sp4);
    }

    public void discoverClicked(Document d) throws IOException {
        nCourse.setText("COURSE NAME : " + d.getName());
        nProf.setText("PROF NAME : " + d.getProf().getName());
        tCourse.setText("TYPE COURSE : " + d.getType());
        Date date = (Date) d.getDate();
        dCourse.setValue(date.toLocalDate());
        dCourse.setDisable(true);
        CoursePane.setVisible(false);
        scrollpaneCourses.setVisible(false);
        discoverPane.setVisible(true);
        NameforDownload = d.getAttachement();
        System.out.println("CHOSEN ITEM : " + d.toString());
    }

    public void reCourseView() {
        CoursePane.setVisible(true);
        scrollpaneCourses.setVisible(true);
        discoverPane.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        typeUser.setText(Home.getCurrentUser().getType());
        userName.setText(Home.getCurrentUser().getName());

        if (Home.getCurrentUser().getType().equals("Etudiant")) {
            addButton.setVisible(false);
            welcomeText.setText("Here you'll find latest Courses and Tds and Exams");
        }
        else {
            addButton.setVisible(true);
            welcomeText.setText("Your added courses :");
        }
        DocumentDao d = new DocumentDao();
        List<Model> l = new ArrayList();
         try {
                if (Home.getCurrentUser().getType().equals("Etudiant"))
                    l = d.getAll();
                else {
                    l = new UserDao().getDocuments(Home.getCurrentUser());
                    evnt.setDisable(true);
                    evnt.setText("-");
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            for (int k = 0; k < l.size(); k++) {
                Document r = (Document) l.get(k);
                nameCourse = r.getName();
                nameProf = r.getProf().getName();
                cType = r.getType();
                Date date = (Date) r.getDate();
                datecourse = date.toLocalDate();
                NameforDownload = r.getAttachement();
                set(r);
            }
    }
}

