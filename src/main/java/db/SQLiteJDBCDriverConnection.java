package db;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SQLiteJDBCDriverConnection {
    private final String url;
    private static final String host = "sqlite";
    private static final String database = "database";

    private static final String CREATE_TABLE_USUARIOS = " CREATE TABLE IF NOT EXISTS usuarios(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome VARCHAR NOT NULL," +
            "sobrenome VARCHAR NOT NULL  ," +
            "username VARCHAR NOT NULL UNIQUE," +
            "caminhoArquivoDeDigital VARCHAR NOT NULL," +
            "nivelAcesso INTEGER NOT NULL," +
            "isAdmin BOOLEAN DEFAULT FALSE," +
            "created_at DATETIME DEFAULT CURRENT_TIMESTAMP )";

    private static final String CREATE_ADMINISTRATOR = "INSERT INTO usuarios(nome, sobrenome, username, " +
            "caminhoArquivoDeDigital, nivelAcesso, isAdmin) VALUES ('Felipe', 'da Silva','administrador','adm',3,TRUE)";

    // Informacoes de nivel 1
    private static final String CREATE_TABLE_AGROTOXICOS = "CREATE TABLE IF NOT EXISTS agrotoxicos(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "empresaResponsavel VARCHAR NOT NULL," +
            "pais VARCHAR NOT NULL," +
            "situacao VARCHAR NOT NULL," +
            "created_at DATETIME DEFAULT CURRENT_TIMESTAMP)";

    // Informacoes de nivel 2
    private static final String CREATE_TABLE_PROPRIEDADES = "CREATE TABLE IF NOT EXISTS propriedades(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nomeFantasia VARCHAR NOT NULL," +
            "cnpj VARCHAR NOT NULL," +
            "endereco VARCHAR NOT NULL," +
            "estado VARCHAR NOT NULL," +
            "pais VARCHAR NOT NULL," +
            "telefone VARCHAR NOT NULL," +
            "created_at DATETIME DEFAULT CURRENT_TIMESTAMP)";

    // Informacoes de nivel 3
    private static final String CREATE_TABLE_PROPRIETARIOS = "CREATE TABLE IF NOT EXISTS proprietarios(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome VARCHAR NOT NULL," +
            "sobrenome VARCHAR NOT NULL," +
            "cpf VARCHAR NOT NULL," +
            "endereco VARCHAR NOT NULL," +
            "estado VARCHAR NOT NULL," +
            "municipio VARCHAR NOT NULL," +
            "pais VARCHAR NOT NULL," +
            "created_at DATETIME DEFAULT CURRENT_TIMESTAMP)";

    public SQLiteJDBCDriverConnection() throws SQLException {
        this.url = "jdbc:" + host + ":" +  database + ".db";
        this.CreateDatabase();
    }

    public void CreateDatabase() throws SQLException {
        Statement statement = this.getConnection().createStatement();

        // Cria tabelas no banco de dados
        statement.execute(CREATE_TABLE_USUARIOS);
        statement.execute(CREATE_TABLE_AGROTOXICOS);
        statement.execute(CREATE_TABLE_PROPRIEDADES);
        statement.execute(CREATE_TABLE_PROPRIETARIOS);

        // Insere valores de teste
        //statement.execute(CREATE_ADMINISTRATOR);
    }

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(this.url);
    }

}
