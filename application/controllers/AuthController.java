package application.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.daoimpl.UserDao;
import application.models.Model;

public class AuthController {

    public static boolean verifyCredentials(String email, String password) throws SQLException {

        DatabaseConnection Dbc = new DatabaseConnection();
        Dbc.connect();

        Statement stmt = Dbc.connection.createStatement();
        String query = "SELECT count(*) AS total FROM users WHERE email = '" + email + "' AND password = '" + password + "'";
        System.out.println("QUERY : " + query);
        ResultSet rs = stmt.executeQuery(query);
        System.out.println(rs);
        while (rs.next()) {
            int total = rs.getInt("total");
            System.out.println("COUNT : " + total);
            if (total != 0) return true;
            else return false;
        }
        System.out.println(rs);
        return false;
    }

    public static Model getUserDetails(String email) throws Exception {
        String query = "SELECT *  FROM users WHERE email = '" + email + "'";

        ResultSet res = new DatabaseConnection().connect().createStatement().executeQuery(query);

        while (res.next())
        {
            return new UserDao().find(res.getInt("id"));
        }
        return null;
    }

}
