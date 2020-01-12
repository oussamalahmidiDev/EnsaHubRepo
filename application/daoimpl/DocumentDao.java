package application.daoimpl;

import application.controllers.DatabaseConnection;
import application.dao.ModelDao;
import application.models.Document;
import application.models.Model;
import application.models.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DocumentDao implements ModelDao {

    @Override
    public boolean create(Model d) throws Exception {

        String query = "INSERT INTO documents(name,prof_id,type,date, attachement) VALUES(?, ?, ?,?, ?)";
        PreparedStatement preparedStmt = new DatabaseConnection().connect().prepareStatement(query);

        preparedStmt.setString (1, ((Document) d).getName());
        preparedStmt.setInt (2, ((Document) d).getProf().getId());
        preparedStmt.setString (3, ((Document) d).getType());
        preparedStmt.setDate(4, (Date) ((Document) d).getDate());
        preparedStmt.setString(5, ((Document) d).getAttachement());
        preparedStmt.execute();

        boolean status = preparedStmt.execute();

        System.out.println("DOC ADDED SUCCESSFULLY : " + ((Document) d).toString());
        return status;
    }

    @Override
    public Model find(int id) throws Exception {
        Document document = new Document();
        String query = "SELECT *  FROM documents WHERE id = " + id;

        ResultSet res = new DatabaseConnection().connect().createStatement().executeQuery(query);
        System.out.println(res);
        while (res.next())
        {
            document.setId(id);
            document.setName(res.getString("name"));
            document.setAttachement(res.getString("attachement"));
            document.setType(res.getString("type"));
            document.setProf((User) new UserDao().find(res.getInt("prof_id")));
            document.setDate(res.getDate("date"));
        }
        return document;
    }

    @Override
    public int delete (int id) throws Exception {
        String query = "DELETE FROM documents WHERE id = " + id;
        return new DatabaseConnection().connect().createStatement().executeUpdate(query);
    }

    @Override
    public List<Model> getAll() throws Exception {
        List<Model> documentsList = new ArrayList<>();

        String query = "SELECT *  FROM documents";

        ResultSet res = new DatabaseConnection().connect().createStatement().executeQuery(query);
        System.out.println(res);
        while (res.next())
        {
            documentsList.add(new Document(res.getInt("id"), res.getString("name"), (User) new UserDao().find(res.getInt("prof_id")), res.getString("type"), res.getDate("date"), res.getString("attachement")));
        }

        return documentsList;
    }
}
