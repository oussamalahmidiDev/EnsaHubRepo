package application.controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private String host;
    private int port;
    private String username;
    private String password;
    private String dbName;

    public Connection connection;

    public DatabaseConnection(String host, int port, String username, String password, String dbName) {
        super();
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
    }


    // DEFAULT DB CONFIG
    public DatabaseConnection() {
        super();
        this.host = "127.0.0.1";
        this.port = 3306;
        this.username = "root";
        this.password = "oussama14";
        this.dbName = "ensahub";
    }


    public Connection connect() {
        try {
            this.connection = DriverManager.getConnection(
                "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbName + "?useSSL=false", this.username, this.password);
            return connection;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }


}
