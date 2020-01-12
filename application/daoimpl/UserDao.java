package application.daoimpl;

import application.models.Document;
import application.models.Event;
import application.models.Model;
import application.controllers.DatabaseConnection;
import application.models.User;
import application.dao.ModelDao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserDao implements ModelDao {

    @Override
    public boolean create(Model u) throws Exception {

        String query = "INSERT INTO users(nom,prenom,email,numero,password,type) VALUES(?,?,?, ?, ?,?)";
        PreparedStatement preparedStmt = new DatabaseConnection().connect().prepareStatement(query);
        preparedStmt.setString (1, ((User) u).getName());
        preparedStmt.setString (2, ((User) u).getPrenom());
        preparedStmt.setString (3, ((User) u).getEmail());
        preparedStmt.setString (4, ((User) u).getNumero());
        preparedStmt.setString (5, ((User) u).getPassword());
        preparedStmt.setString (6, ((User) u).getType());
        preparedStmt.execute();

        System.out.println("USER ADDED SUCCESSFULLY : " + ((User) u).toString());
        return true;
    }

    @Override
    public Model find(int id) throws Exception {
        User user = new User();
        String query = "SELECT *  FROM users WHERE id = " + id;

        ResultSet res = new DatabaseConnection().connect().createStatement().executeQuery(query);
        System.out.println(res);
        while (res.next())
        {
            user.setId(id);
            user.setName(res.getString("nom"));
            user.setPrenom(res.getString("prenom"));
            user.setEmail(res.getString("email"));
            user.setPassword(res.getString("password"));
            user.setNumero(res.getString("numero"));
            user.setType(res.getString("type"));
            query = "SELECT *  FROM events_participants WHERE participant_id = " + id;
            List<Event> events = new ArrayList<>();
            ResultSet resEvents = new DatabaseConnection().connect().createStatement().executeQuery(query);
            while (resEvents.next()) {
                events.add((Event) new EventDao().find(resEvents.getInt("event_id")));
            }
            user.setParticipatedEvents(events);
        }
        return user;
    }

    @Override
    public int delete (int id) throws Exception {
        String query = "DELETE FROM users WHERE id = " + id;
        return new DatabaseConnection().connect().createStatement().executeUpdate(query);
    }

    @Override
    public List<Model> getAll() throws Exception {
        List<Model> usersList = new ArrayList<>();

        String query = "SELECT *  FROM users";

        ResultSet res = new DatabaseConnection().connect().createStatement().executeQuery(query);
        System.out.println(res);
        while (res.next())
        {
            usersList.add(new User(res.getInt("id"), res.getString("nom"), res.getString("prenom"), res.getString("email"), res.getString("numero"),res.getString("password"), res.getString("type")));
        }

        return usersList;
    }

    public List<Model> getDocuments(User user) throws Exception {
        String query = "SELECT *  FROM documents WHERE prof_id = " + user.getId();
        List<Model> documents = new ArrayList<>();
        ResultSet resDocuments = new DatabaseConnection().connect().createStatement().executeQuery(query);
        while (resDocuments.next()) {
            documents.add((Document) new DocumentDao().find(resDocuments.getInt("id")));
        }
        return documents;
    }

}
