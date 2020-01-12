package application.daoimpl;

import application.controllers.DatabaseConnection;
import application.dao.ModelDao;
import application.models.Model;
import application.models.Event;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDao implements ModelDao {

    @Override
    public boolean create(Model e) throws Exception {
        String query = "INSERT INTO events(nom,description,type,date,location) VALUES(?,?,?, ?, ?)";
        PreparedStatement preparedStmt = new DatabaseConnection().connect().prepareStatement(query);
        preparedStmt.setString (1, ((Event) e).getNom());
        preparedStmt.setString (2, ((Event) e).getDescription());
        preparedStmt.setString (3, ((Event) e).getType());
        preparedStmt.setDate (4, (java.sql.Date) ((Event) e).getDate());
        preparedStmt.setString (5, ((Event) e).getLocation());
        preparedStmt.execute();

        System.out.println("USER ADDED SUCCESSFULLY : " + ((Event) e).toString());
        return true;
    }

    @Override
    public Model find(int id) throws Exception {
        Event event = new Event();
        String query = "SELECT *  FROM events WHERE id = " + id;

        ResultSet res = new DatabaseConnection().connect().createStatement().executeQuery(query);
        System.out.println(res);
        while (res.next())
        {
            event.setNom(res.getString("nom"));
            event.setDescription(res.getString("description"));
            event.setDate(res.getDate("date"));
            event.setLocation(res.getString("location"));
            event.setType(res.getString("type"));
            event.setId(res.getInt("id"));
        }
        return event;
    }

    @Override
    public int delete(int id) throws Exception {
        String query = "DELETE FROM events WHERE id = " + id;
        return new DatabaseConnection().connect().createStatement().executeUpdate(query);
    }

    @Override
    public List<Model> getAll() throws Exception {
        List<Model> eventsList = new ArrayList<>();

        String query = "SELECT * FROM events";

        ResultSet res = new DatabaseConnection().connect().createStatement().executeQuery(query);
        System.out.println(res);
        while (res.next())
        {
            eventsList.add(this.find(res.getInt("id")));
        }

        return eventsList;
    }

    public int addParticipant (int eventId, int participantId) throws SQLException {
        String query = "INSERT INTO events_participants(event_id,participant_id) VALUES (" + eventId + ", " + participantId + ")";
        int statement = new DatabaseConnection().connect().createStatement().executeUpdate(query);
        return statement;
    }

    public int cancelParticipant (int eventId, int participantId) throws SQLException {
        String query = "DELETE FROM events_participants WHERE event_id = " + eventId + " AND participant_id = " + participantId;
        int statement = new DatabaseConnection().connect().createStatement().executeUpdate(query);
        return statement;
    }
}
