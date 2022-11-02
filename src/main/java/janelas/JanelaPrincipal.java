package janelas;

import app.Application;
import dto.UsuarioDto;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JanelaPrincipal extends JFrame {

    private JLabel lblNomeUsuario;
    private JLabel lblNivelAcessoUsuario;
    private JLabel lblMsgNivelAcessoUser;

    private Application application;

    public JanelaPrincipal(UsuarioDto usuarioDto){
        setTitle("Banco de dados - Ministério do Meio Ambiente");
        setBounds(100, 100, 500, 328);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setPanel(usuarioDto);
        exibe(false);
    }

    public void exibe(boolean op){
        getContentPane().setVisible(op);
    }

    protected void setPanel(UsuarioDto usuarioDto) {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu arquivoMenu = new JMenu("Arquivo");
        JMenuItem sairMenuItem = new JMenuItem("Sair");
        sairMenuItem.addActionListener(e -> {
            System.exit(0);
        });
        menuBar.add(arquivoMenu);
        arquivoMenu.add(sairMenuItem);

        JMenu editarMenu = new JMenu("Editar");
        menuBar.add(editarMenu);

        JMenu pesquisarMenu = new JMenu("Pesquisar");
        JMenuItem pesquisarUsuario = new JMenuItem("Usuario");
        JMenuItem pesquisardigitais = new JMenuItem("Digitais");
        menuBar.add(pesquisarMenu);
        pesquisarMenu.add(pesquisarUsuario);
        pesquisarMenu.add(pesquisardigitais);

        JMenu sobreMenu = new JMenu("Sobre");
        menuBar.add(sobreMenu);


        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 5, 100, 16);
        getContentPane().add(lblNome);

        lblNomeUsuario = new JLabel();
        lblNomeUsuario.setText(usuarioDto.getNome());
        lblNomeUsuario.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblNomeUsuario.setBounds(50, 5, 300, 16);
        getContentPane().add(lblNomeUsuario);

        JLabel lblNivelAcesso = new JLabel("Nível de acesso:");
        lblNivelAcesso.setBounds(10, 25, 101, 16);
        getContentPane().add(lblNivelAcesso);

        lblNivelAcessoUsuario = new JLabel();
        lblNivelAcessoUsuario.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblNivelAcessoUsuario.setBounds(110, 25, 100, 16);
        lblNivelAcessoUsuario.setText(Integer.toString(Application.usuarioDto().getNivelDeAcesso()));
        getContentPane().add(lblNivelAcessoUsuario);


        JLabel lblUtimaAtualizacaoDB = new JLabel("Ultima atualização:");
        lblUtimaAtualizacaoDB.setBounds(10, 50, 221, 16);
        getContentPane().add(lblUtimaAtualizacaoDB);

        JLabel lblDataUltAtualizacaoDB = new JLabel(new SimpleDateFormat("dd/MM/yyyy")
                .format(new Date()));
        lblDataUltAtualizacaoDB.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblDataUltAtualizacaoDB.setBounds(120, 50, 500, 16);
        getContentPane().add(lblDataUltAtualizacaoDB);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 81, 500, 2);
        getContentPane().add(separator);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(0, 81, 500, 184);
        getContentPane().add(panel);
        panel.setLayout(null);

        JButton btnProprietarios = new JButton("Proprietários");
        btnProprietarios.setBounds(13, 85, 130, 38);
        panel.add(btnProprietarios);

        JButton btnAgrotoxicos = new JButton("Agrotóxicos");
        btnAgrotoxicos.setBounds(156, 85, 130, 38);
        panel.add(btnAgrotoxicos);

        JButton btnPropriedades = new JButton("Propriedades");
        btnPropriedades.setBounds(299, 85, 130, 38);
        panel.add(btnPropriedades);

        JLabel lblMsgNivelAcesso = new JLabel("Você tem permissão para acessar arquivos de nível");
        lblMsgNivelAcesso.setBounds(11, 7, 300, 16);
        panel.add(lblMsgNivelAcesso);
        lblMsgNivelAcesso.setForeground(new Color(0, 128, 0));

        lblMsgNivelAcessoUser = new JLabel();
        lblMsgNivelAcessoUser.setForeground(new Color(0, 128, 0));
        lblMsgNivelAcessoUser.setBounds(310, 7, 100, 16);
        lblMsgNivelAcessoUser.setText(Integer.toString(Application.usuarioDto().getNivelDeAcesso()));
        panel.add(lblMsgNivelAcessoUser);


        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(0, 29, 500, 2);
        panel.add(separator_2);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 265, 500, 2);
        getContentPane().add(separator_1);

        System.out.println(Application.usuarioDto().toString());

    }
}
