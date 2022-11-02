package app;

import db.SQLiteJDBCDriverConnection;
import dto.UsuarioDto;
import janelas.JanelaLogin;
import janelas.JanelaPrincipal;

import java.sql.SQLException;

public class Application {
    protected JanelaPrincipal janelaPrincipal;
    public static UsuarioDto usuario;

    public Application() throws SQLException {

        usuario = new UsuarioDto();
        janelaPrincipal = new JanelaPrincipal(usuario);
        janelaPrincipal.setVisible(true);
        JanelaLogin janelaLogin = new JanelaLogin(janelaPrincipal);
        janelaLogin.getJDialog().setVisible(true);

        while(janelaLogin.getJDialog().isShowing()){}

        janelaPrincipal.exibe(true);
    }

    public static void main(String[] args) throws SQLException {
        new SQLiteJDBCDriverConnection();
        Application application = new Application();
    }

    public static UsuarioDto usuarioDto(){
        return Application.usuario;
    }
}
