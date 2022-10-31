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
        setBounds(100, 100, 450, 328);
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
        JMenuItem novoMenuItem = new JMenuItem("Novo");
        JMenuItem abrirMenuItem = new JMenuItem("Abrir");
        JMenuItem sairMenuItem = new JMenuItem("Sair");
        menuBar.add(arquivoMenu);
        arquivoMenu.add(novoMenuItem);
        arquivoMenu.add(abrirMenuItem);
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


        JLabel lblNome = new JLabel("Nome de usuário:");
        lblNome.setBounds(10, 35, 49, 16);
        getContentPane().add(lblNome);

        lblNomeUsuario = new JLabel(usuarioDto.getNome());
        lblNomeUsuario.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblNomeUsuario.setBounds(56, 35, 376, 16);
        getContentPane().add(lblNomeUsuario);

        JLabel lblNivelAcesso = new JLabel("Nível de acesso:");
        lblNivelAcesso.setBounds(10, 55, 101, 16);
        getContentPane().add(lblNivelAcesso);

        lblNivelAcessoUsuario = new JLabel(Integer.toString(usuarioDto.getNivelDeAcesso()));
        lblNivelAcessoUsuario.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblNivelAcessoUsuario.setBounds(110, 55, 55, 16);
        getContentPane().add(lblNivelAcessoUsuario);

        JLabel lblUtimaAtualizacaoDB = new JLabel("Última atualização no banco de dados:");
        lblUtimaAtualizacaoDB.setBounds(10, 274, 221, 16);
        getContentPane().add(lblUtimaAtualizacaoDB);

        JLabel lblDataUltAtualizacaoDB = new JLabel(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        lblDataUltAtualizacaoDB.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblDataUltAtualizacaoDB.setBounds(232, 274, 84, 16);
        getContentPane().add(lblDataUltAtualizacaoDB);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 81, 444, 2);
        getContentPane().add(separator);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(0, 81, 444, 184);
        getContentPane().add(panel);
        panel.setLayout(null);

        JButton btnProprietarios = new JButton("proprietários");
        btnProprietarios.setBounds(13, 85, 130, 38);
        panel.add(btnProprietarios);

        JButton btnAgrotoxicos = new JButton("agrotóxicos");
        btnAgrotoxicos.setBounds(156, 85, 130, 38);
        panel.add(btnAgrotoxicos);

        JButton btnPropriedades = new JButton("propriedades");
        btnPropriedades.setBounds(299, 85, 130, 38);
        panel.add(btnPropriedades);

        JLabel lblMsgNivelAcesso = new JLabel("Você tem permissão para acessar arquivos de nível");
        lblMsgNivelAcesso.setBounds(11, 7, 300, 16);
        panel.add(lblMsgNivelAcesso);
        lblMsgNivelAcesso.setForeground(new Color(0, 128, 0));

        lblMsgNivelAcessoUser = new JLabel("");
        lblMsgNivelAcessoUser.setForeground(new Color(0, 128, 0));
        lblMsgNivelAcessoUser.setBounds(310, 7, 55, 16);
        panel.add(lblMsgNivelAcessoUser);

        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(0, 29, 444, 2);
        panel.add(separator_2);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 265, 444, 2);
        getContentPane().add(separator_1);
    }
}
