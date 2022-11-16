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


    // Insere dados nas tabelas

    // Agrotoxicos
    private final String POPULATE_AGROTOXICOS = "INSERT INTO agrotoxicos(empresaResponsavel, pais, situacao) VALUES " +
            "('AgroTest', 'Estados Unidos', 'Ativo')," +
            "('Darma', 'Russia', 'Ativo')," +
            "('RdAgro', 'Colombia', 'Ativo')";

    // Propriedades
    private final String POPULATE_PROPRIEDADES = "INSERT INTO propriedades (nomeFantasia, cnpj, endereco, estado, pais, telefone) VALUES" +
            "('Vale da montanha','87.458.943/0001-40','Rua Diamante, 30','Rio Grande S', 'BR', '11 4444-4444')," +
            "('Terra Verde','07.998.570/0001-61','Estrada Das Lagrimas, 234','São Paulo', 'BR', '11 3333-4444')," +
            "('Sol e Terra','68.455.528/0001-99','Avenida Gloria','Parana', 'BR', '11 2545-6585')," +
            "('Maresia','38.618.488/0001-98','Estrada Verde Oliva','São Paulo', 'BR', '11 4457-4353')";

    // Proprietarios
    private final String POPULATE_PROPRIETARIOS = "INSERT INTO proprietarios (nome, sobrenome, cpf, endereco, estado, municipio, pais) VALUES " +
            "('Rodrigo', 'Garcia Almeida', '377.590.220-18', 'Rua da Consolacao, 34','São Paulo', 'São Paulo','BR'), " +
            "('Andressa', 'da Silva Souza', '254.301.600-75', 'Rua Cristiano Angeli, 456','São Paulo', 'São Bernardo','BR'), " +
            "('Amanda', 'Stuchi Gomez', '828.660.210-29', 'Rua da Mata, 2345','São Paulo', 'Diadema','BR')," +
            "('Ricardo', 'Vieira Costa', '488.594.310-858', 'Rua da Esperança','São Paulo', 'Maua','BR')";


    public void createTables() throws SQLException {
        databaseConnectionDriver = new DatabaseConnectionDriver();
        Statement statement = databaseConnectionDriver.getConnection().createStatement();
        statement.execute(CREATE_TABLE_USUARIOS);
        statement.execute(CREATE_TABLE_AGROTOXICOS);
        statement.execute(CREATE_TABLE_PROPRIEDADES);
        statement.execute(CREATE_TABLE_PROPRIETARIOS);
    }

    public void populateTables() throws SQLException{
        databaseConnectionDriver = new DatabaseConnectionDriver();
        Statement statement = databaseConnectionDriver.getConnection().createStatement();
        statement.execute(POPULATE_AGROTOXICOS);
        statement.execute(POPULATE_PROPRIEDADES);
        statement.execute(POPULATE_PROPRIETARIOS);
    }
}
