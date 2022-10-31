package app;

import db.SQLiteJDBCDriverConnection;
import dto.UsuarioDto;
import janelas.JanelaLogin;
import janelas.JanelaPrincipal;

public class Application {
    protected JanelaPrincipal janelaPrincipal;
    public static UsuarioDto usuario;

    public Application(){
        usuario = new UsuarioDto();
        //SQLiteJDBCDriverConnection.connect();
        janelaPrincipal = new JanelaPrincipal(usuario);
        janelaPrincipal.setVisible(true);
        //application = this;
        JanelaLogin janelaLogin = new JanelaLogin(janelaPrincipal);
        janelaLogin.getJDialog().setVisible(true);

        // aguarda o o termino do login
        while(janelaLogin.getJDialog().isShowing()){}

        janelaPrincipal.exibe(true);
    }

    public static void main(String[] args) {
        Application application = new Application();
    }

    public static UsuarioDto usuarioDto(){
        return Application.usuario;
    }
}
