package janelas;

import app.Application;
import db.JDBCExecutor;
import db.SQLiteJDBCDriverConnection;
import dto.UsuarioDto;
import security.ValidarDigitais;
import utils.Progressbar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class JanelaLogin extends JFrame {
    private JanelaPrincipal janelaPrincipal;

    private Application application;

    protected JTextField txtNomeUsuario;
    public JButton btnEscanearDigital;
    private final JDialog jDialog;

    private ValidarDigitais validarDigitais;

    public JanelaLogin(JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
        jDialog = new JDialog(this.janelaPrincipal,true);
        setUpComponents();
        jDialog.setLocationRelativeTo(janelaPrincipal);
    }

    protected void setUpComponents() {
        JPanel contentPanel = new JPanel();
        JFrame mainWindow = this;

        jDialog.setTitle("Autenticação necessária");
        jDialog.setBounds(100,100,300,180);
        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jDialog.getContentPane().setLayout(new BorderLayout());
        jDialog.setLocationRelativeTo(null);
        jDialog.setResizable(false);

        contentPanel.setBorder(new EmptyBorder(6, 6, 6, 6));
        jDialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            JLabel lblNomeUsuario = new JLabel("Digite o seu nome de usuário:");
            lblNomeUsuario.setBounds(10, 12, 181, 16);
            contentPanel.add(lblNomeUsuario);
        }


        txtNomeUsuario = new JTextField();
        txtNomeUsuario.setBounds(11, 34, 257, 28);
        contentPanel.add(txtNomeUsuario);
        txtNomeUsuario.setColumns(10);

        btnEscanearDigital = new JButton("IMPORTAR DIGITAL SALVA");
        btnEscanearDigital.setBounds(11, 75, 257, 33);

        btnEscanearDigital.addActionListener(e -> {
           Progressbar progressbar = new Progressbar(true, mainWindow);
            UsuarioDto usuario = new UsuarioDto();
            if(!txtNomeUsuario.getText().isEmpty()){
                try {
                    Connection connection = new SQLiteJDBCDriverConnection().getConnection();
                    JDBCExecutor executor = new JDBCExecutor(connection);
                    usuario = executor.findUsuario(txtNomeUsuario.getText());
                    if(!Objects.equals(usuario.getNomeDeUsuario(), txtNomeUsuario.getText())){
                        JOptionPane.showMessageDialog(null,"Usuario ou chave incorretos.\n" +
                                "Em caso de erro contate o administrador do sistema." +
                                "\nPor segurança o programa sera encerrado","Erro de autenticação", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                    if(usuario != null ){
                        try {
                            Application.usuarioDto().setId(usuario.getId());
                            Application.usuarioDto().setNome(usuario.getNome());
                            Application.usuarioDto().setSobrenome(usuario.getSobrenome());
                            Application.usuarioDto().setNomeDeUsuario(usuario.getNomeDeUsuario());
                            Application.usuarioDto().setCaminhoArquivoDeDigital(usuario.getCaminhoArquivoDeDigital());
                            Application.usuarioDto().setNivelDeAcesso(usuario.getNivelDeAcesso());
                            Application.usuarioDto().setAdmin(usuario.getAdmin());
                            validarDigitais = new ValidarDigitais(txtNomeUsuario.getText(), mainWindow);
                            if(validarDigitais.autenticado){
                                this.janelaPrincipal.setPanel(Application.usuario);
                                jDialog.dispose();

                            }else{
                                JOptionPane.showMessageDialog(null,"Não foi possivel autenticar\nO programa será encerrado", "Erro", JOptionPane.ERROR_MESSAGE);
                                System.exit(0);
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                JOptionPane.showMessageDialog(null,"O nome de usuário nao pode ficar em branco", "Erro", JOptionPane.INFORMATION_MESSAGE);
            }

        });

        contentPanel.add(btnEscanearDigital);
    }
    public JDialog getJDialog(){
        return jDialog;
    }


}
