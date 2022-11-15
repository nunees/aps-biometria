package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionDriver {
    private final String url;
    private static final String host = "sqlite";
    private static final String database = "database";

    public DatabaseConnectionDriver() throws SQLException {
        this.url = "jdbc:" + host + ":" +  database + ".db";
        //this.CreateDatabase();
    }
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(this.url);
    }

}
