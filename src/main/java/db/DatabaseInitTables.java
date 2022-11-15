package db;

import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitTables {
    private DatabaseConnectionDriver databaseConnectionDriver;
    private DatabaseQueryExecutor databaseQueryExecutor;

    private static final String CREATE_ADMINISTRATOR = "INSERT INTO usuarios(nome, sobrenome, username, " +
            "caminhoArquivoDeDigital, nivelAcesso, isAdmin) VALUES ('Felipe', 'da Silva','administrador','adm',3,TRUE)";

    private static final  String CREATE_TABLE_USUARIOS = " CREATE TABLE IF NOT EXISTS usuarios(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome VARCHAR NOT NULL," +
            "sobrenome VARCHAR NOT NULL  ," +
            "username VARCHAR NOT NULL UNIQUE," +
            "caminhoArquivoDeDigital VARCHAR NOT NULL," +
            "nivelAcesso INTEGER NOT NULL," +
            "isAdmin BOOLEAN DEFAULT FALSE," +
            "created_at DATETIME DEFAULT CURRENT_TIMESTAMP )";

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

    public void createTables() throws SQLException {
        databaseConnectionDriver = new DatabaseConnectionDriver();
        Statement statement = databaseConnectionDriver.getConnection().createStatement();
        statement.execute(CREATE_TABLE_USUARIOS);
        statement.execute(CREATE_TABLE_AGROTOXICOS);
        statement.execute(CREATE_TABLE_PROPRIEDADES);
        statement.execute(CREATE_TABLE_PROPRIETARIOS);
    }
}
