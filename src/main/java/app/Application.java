package app;

import db.SQLiteJDBCDriverConnection;
import dto.UsuarioDto;
import janelas.JanelaLogin;
import janelas.JanelaPrincipal;

import java.io.IOException;
import java.sql.SQLException;

public class Application {
    protected JanelaPrincipal janelaPrincipal;
    public static UsuarioDto usuario;

    public Application() throws IOException {

        usuario = new UsuarioDto();
        janelaPrincipal = new JanelaPrincipal(usuario);
        janelaPrincipal.setVisible(true);
        JanelaLogin janelaLogin = new JanelaLogin(janelaPrincipal);
        janelaLogin.getJDialog().setVisible(true);

        while(janelaLogin.getJDialog().isShowing()){}

        janelaPrincipal.exibe(true);
    }

    public static void main(String[] args) throws SQLException, IOException {
        new SQLiteJDBCDriverConnection();
        new Application();
    }

    public static UsuarioDto usuarioDto(){
        return Application.usuario;
    }
}
