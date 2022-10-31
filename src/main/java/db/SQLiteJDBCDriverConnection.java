package db;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SQLiteJDBCDriverConnection {
    private final String url;

    private static final String CREATE_USUARIOS_TABLE = " CREATE TABLE IF NOT EXISTS usuarios(" +
            "id INTEGER PRIMARY KEY," +
            "nome VARCHAR NOT NULL," +
            "sobrenome VARCHAR NOT NULL," +
            "username VARCHAR NOT NULL," +
            "caminhoArquivoDeDigital VARCHAR NOT NULL," +
            "nivelAcesso INTEGER NOT NULL," +
            "isAdmin BOOLEAN DEFAULT 0 )";

    private static final String CREATE_ARQUIVOS = "CREATE TABLE IF NOT EXISTS arquivos(" +
            "id INTEGER PRIMARY KEY," +
            "titulo VARCHAR NOT NULL," +
            "conteudo VARCHAR NOT NULL," +
            "nivelAcesso INTEGER NOT NULL)";

    public SQLiteJDBCDriverConnection(String host, String database) throws SQLException {
        this.url = "jdbc:" + host + ":" + database + ".db";
        Statement statement = getConnection().createStatement();
        statement.execute(CREATE_USUARIOS_TABLE);
        statement.execute(CREATE_ARQUIVOS);
    }

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(this.url);
    }

}
