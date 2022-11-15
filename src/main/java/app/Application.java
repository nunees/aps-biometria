package app;

import db.DatabaseInitTables;
import dto.UsuarioDto;
import janelas.JanelaLogin;
import janelas.JanelaPrincipal;
import utils.Log;
import utils.TipoLog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class Application {
    protected JanelaPrincipal janelaPrincipal;
    public static UsuarioDto usuario;

    private static File InitialSetupFile;
    public Application() throws IOException, SQLException {
        DatabaseInitTables databaseInitTables = new DatabaseInitTables();
        Log.print(TipoLog.INFO, "Procurando arquivo de configuracao");
        InitialSetupFile = new File("./app.dat");
        if(!InitialSetupFile.exists() && !InitialSetupFile.isDirectory()){
            Log.print(TipoLog.ALERTA, "File not exists!");
            boolean fileCreated = InitialSetupFile.createNewFile();
            if(fileCreated){
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(InitialSetupFile, true));
                bufferedWriter.append("1");
                bufferedWriter.close();
                Log.print(TipoLog.INFO, "File Created");
                Log.print(TipoLog.INFO, "Criando banco de dados");
                databaseInitTables.createTables();
            }
        }

        usuario = new UsuarioDto();
        janelaPrincipal = new JanelaPrincipal();
        janelaPrincipal.setVisible(true);
        JanelaLogin janelaLogin = new JanelaLogin(janelaPrincipal);
        janelaLogin.getJDialog().setVisible(true);

        while(janelaLogin.getJDialog().isShowing()){}

        janelaPrincipal.exibe(true);
    }

    public static void main(String[] args) throws SQLException, IOException {
        Log.print(TipoLog.INFO, "Aplicacao iniciada");
        new Application();
    }

    public static UsuarioDto usuarioDto(){
        return Application.usuario;
    }
}
