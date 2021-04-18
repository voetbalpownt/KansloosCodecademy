package Quatro.codecademy.application.controllers;
import java.sql.*;

// Class for establishing a connection to the database
public abstract class Database {
    private String connectionUrl;
    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;

    // Constructor for Database
    public Database(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        this.connection = null;
        this.statement = null;
        this.resultSet = null;
    }

    // Establish connection to database through the JDBC driver
    public void connectDatabase() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            throw e;
        }
    }
}