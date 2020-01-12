package application.views;

import application.daoimpl.EventDao;
import application.models.Event;
import application.models.Model;
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
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class EventsView implements Initializable {


    @FXML
    private javafx.scene.layout.GridPane eventsContainer;

    @FXML
    private javafx.scene.control.ScrollPane scrollPaneEvents;

    @FXML
    private javafx.scene.control.TextField eventname;

    @FXML
    private javafx.scene.control.TextField eventtype;

    @FXML
    private javafx.scene.control.DatePicker eventdate;

    @FXML
    private javafx.scene.control.TextField eventlocal;


    @FXML
    private javafx.scene.control.TextArea eventdescp;



    static String eventName ,eventType , eventLocal , eventDescp;
    static LocalDate eventDate ;

    private List<Model> l;

    static int i = 0;
    static int j= 0;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private javafx.scene.layout.Pane addEventPane;

    public static void Events() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Home.class.getResource("event.fxml"));
        Image icon = new Image(Home.class.getResourceAsStream("../Images/ensa1.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("ENSAHub");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
        System.out.println("EVENTS : " + Home.getCurrentUser().toString());
    }
    public void coursesview() throws IOException {
        closelastButtonAction();
        showCourses();
    }
    public static void showCourses() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Home.class.getResource("CoursesView.fxml"));
        Image icon = new Image(Home.class.getResourceAsStream("../Images/ensa1.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("ENSAHub");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    public void AddEvent(){
        addEventPane.setVisible(true);
        scrollPaneEvents.setVisible(false);
    }

    public void DoneCLicked() throws Exception {

        eventName=eventname.getText();
        eventType=eventtype.getText();
        eventDate=eventdate.getValue();
        eventLocal=eventlocal.getText();
        eventDescp=eventdescp.getText();

        scrollPaneEvents.setVisible(true);
        addEventPane.setVisible(false);
        new EventDao().create(new Event(eventName, Date.valueOf(eventDate), eventType, eventDescp, eventLocal));

        try {
            l = new EventDao().getAll();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        set(l.get(l.size() - 1).getId(), false);
    }

    public void loadEvents() {
        EventDao event = new EventDao();
        List<Model> l =new ArrayList();
        try {
            l = event.getAll();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for(int k = 0; k<l.size();k++) {
            Event e = (Event) l.get(k);
            eventName = e.getNom();
            eventType = e.getType();
            eventLocal = e.getLocation();
            Date date = (Date) e.getDate();
            eventDate = date.toLocalDate();
            if (Home.getCurrentUser().getParticipatedEvents().contains(e))
                set(e.getId(), true);
            else set(e.getId(), false);
            l.get(l.size() - 1);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadEvents();
    }

    public void set(int eventId, boolean participated) {
        System.out.println(eventName + " "+eventType+" "+eventDate+ " " + eventLocal);
        eventsContainer.setPadding(new Insets(10,10,10,10));
        eventsContainer.setVgap(10);
        eventsContainer.setHgap(10);

        //Name event
        Label nameE = new Label("Name : "+eventName);
        GridPane.setConstraints(nameE,i,j);

       //Type event
        i++;
        Label typeE = new Label("Type : "+eventType);
        GridPane.setConstraints(typeE,i,j);



        // Local event

        i++;
        Label localE = new Label("Local : "+eventLocal);
        GridPane.setConstraints(localE,i,j);


        //Descp event

        i++;
        Label Descp = new Label("Description : "+eventDescp);
        GridPane.setConstraints(Descp,i,j);

        //Date event
        i++;
        DatePicker dateE = new DatePicker(eventDate);
        dateE.setDisable(true);
        GridPane.setConstraints(dateE , i,j);



        // Button discover

        i++;
        String buttonText;
        if (participated)
            buttonText = "Cancel";
        else buttonText = "Participate";
            Button interstedButton = new Button(buttonText);

        interstedButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (interstedButton.getText() == "Participate") {
                    try {
                        new EventDao().addParticipant(eventId, Home.getCurrentUser().getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    interstedButton.setText("Cancel");
                }
                else {
                    try {
                        new EventDao().cancelParticipant(eventId, Home.getCurrentUser().getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    interstedButton.setText("Participate");

                }


            }
        });
        GridPane.setConstraints(interstedButton,i,j );

        j++;
        i=0;

        Separator sp0 = new Separator();
        sp0.setOrientation(Orientation.HORIZONTAL);
        GridPane.setConstraints(sp0,i,j);

        i++;
        Separator sp1 = new Separator();
        sp1.setOrientation(Orientation.HORIZONTAL);
        GridPane.setConstraints(sp1,i,j);

        i++;
        Separator sp2 = new Separator();
        sp2.setOrientation(Orientation.HORIZONTAL);
        GridPane.setConstraints(sp2,i,j);

        i++;
        Separator sp3 = new Separator();
        sp3.setOrientation(Orientation.HORIZONTAL);
        GridPane.setConstraints(sp3,i,j);

        i++;
        Separator sp4 = new Separator();
        sp4.setOrientation(Orientation.HORIZONTAL);
        GridPane.setConstraints(sp4,i,j);

        j++;
        i=0;

        eventsContainer.getChildren().addAll(nameE , typeE, dateE,localE,Descp,interstedButton,sp0,sp1,sp2,sp3,sp4);
    }

    public void backToEvents(){
        addEventPane.setVisible(false);
        scrollPaneEvents.setVisible(true);
    }


    @FXML
    private void closelastButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    public void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warnning");
        alert.setHeaderText("Looks you want to close the program !");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            stage.close();
        } else {
            alert.close();

        }
    }
}
