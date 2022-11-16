package janelas;

import app.Application;
import dto.UsuarioDto;
import utils.Log;
import utils.TipoLog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class JanelaPrincipal extends JFrame {

    public JanelaPrincipal() {
        try {
            Image logoImage = ImageIO.read(Objects.requireNonNull(JanelaPrincipal.class.getClassLoader().getResource("images/logo.png")));
            logoImage.getScaledInstance(64, 64, 0);
            setIconImage(logoImage);
        }catch (IOException | NullPointerException ioe){
            Log.print(TipoLog.ERRO, ioe.toString());
            throw new RuntimeException(ioe);
        }

        setTitle("Banco de dados - Ministério do Meio Ambiente");
        setBounds(100, 100, 500, 328);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
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

        if(Application.usuarioDto().getAdmin()) {
            JMenu pesquisarMenu = new JMenu("Usuarios");
            JMenuItem cadastrarUsuario = new JMenuItem("Cadastrar");
            cadastrarUsuario.addActionListener(e -> {
                JanelaCadastro janelaCadastro = new JanelaCadastro(this);
                janelaCadastro.setVisible(true);
            });
            JMenuItem pesquisarUsuario = new JMenuItem("Listar");
            pesquisarUsuario.addActionListener(e -> {
                JanelaUsuarios janelaUsuarios = new JanelaUsuarios(this);
                janelaUsuarios.setVisible(true);
            });
            menuBar.add(pesquisarMenu);
            pesquisarMenu.add(pesquisarUsuario);
            pesquisarMenu.add(cadastrarUsuario);
        }

        JMenu sobreMenu = new JMenu("Ajuda");
        JMenuItem sobrePrograma = new JMenuItem("Sobre");
        sobrePrograma.addActionListener(e -> {
            JanelaSobre janelaSobre = null;
            try {
                janelaSobre = new JanelaSobre(this);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            janelaSobre.setVisible(true);
        });
        menuBar.add(sobreMenu);
        sobreMenu.add(sobrePrograma);


        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 5, 100, 16);
        getContentPane().add(lblNome);

        JLabel lblNomeUsuario = new JLabel();
        lblNomeUsuario.setText(usuarioDto.getNome());
        lblNomeUsuario.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblNomeUsuario.setBounds(50, 5, 300, 16);
        getContentPane().add(lblNomeUsuario);

        JLabel lblNivelAcesso = new JLabel("Nível de acesso:");
        lblNivelAcesso.setBounds(10, 25, 101, 16);
        getContentPane().add(lblNivelAcesso);

        JLabel lblNivelAcessoUsuario = new JLabel();
        lblNivelAcessoUsuario.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblNivelAcessoUsuario.setBounds(110, 25, 50, 16);
        lblNivelAcessoUsuario.setText(Integer.toString(usuarioDto.getNivelDeAcesso()));
        getContentPane().add(lblNivelAcessoUsuario);

        JLabel lblUtimaAtualizacaoDB = new JLabel("Ultima atualização:");
        lblUtimaAtualizacaoDB.setBounds(10, 50, 221, 16);
        getContentPane().add(lblUtimaAtualizacaoDB);

        JLabel lblDataUltAtualizacaoDB = new JLabel(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
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
        btnProprietarios.addActionListener(e -> {
            if(Application.usuarioDto().getNivelDeAcesso() >= 1){
                JanelaProprietarios janelaProprietarios = new JanelaProprietarios(this);
                janelaProprietarios.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,"Você não tem permissão de acesso!" +
                        "\nContate um administrador", "Permissão Negada", JOptionPane.WARNING_MESSAGE);
                Log.print(TipoLog.ERRO, "Permissao negada para: " + usuarioDto.getNomeDeUsuario());
            }

        });
        panel.add(btnProprietarios);

        JButton btnAgrotoxicos = new JButton("Agrotóxicos");
        btnAgrotoxicos.setBounds(156, 85, 130, 38);
        btnAgrotoxicos.addActionListener(e -> {
            if(Application.usuarioDto().getNivelDeAcesso() >= 2){
                JanelaAgrotoxicos janelaAgrotoxicos = new JanelaAgrotoxicos(this);
                janelaAgrotoxicos.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,"Você não tem permissão de acesso!" +
                        "\nContate um administrador", "Permissão Negada", JOptionPane.WARNING_MESSAGE);
                Log.print(TipoLog.ERRO, "Permissao negada para: " + usuarioDto.getNomeDeUsuario());
            }

        });
        panel.add(btnAgrotoxicos);

        JButton btnPropriedades = new JButton("Propriedades");
        btnPropriedades.setBounds(299, 85, 130, 38);
        btnPropriedades.addActionListener(e -> {
            if(Application.usuarioDto().getNivelDeAcesso() >= 3){
                JanelaPropriedades janelaPropriedades = new JanelaPropriedades(this);
                janelaPropriedades.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,"Você não tem permissão de acesso!" +
                        "\nContate um administrador", "Permissão Negada", JOptionPane.WARNING_MESSAGE);
                Log.print(TipoLog.ERRO, "Permissao negada para: " + usuarioDto.getNomeDeUsuario());
            }
        });
        panel.add(btnPropriedades);

        JLabel lblMsgNivelAcesso = new JLabel("Você tem permissão para acessar informacoes de nível");
        lblMsgNivelAcesso.setBounds(11, 7, 400, 16);
        panel.add(lblMsgNivelAcesso);
        lblMsgNivelAcesso.setForeground(new Color(0, 128, 0));

        JLabel lblMsgNivelAcessoUser = new JLabel();
        lblMsgNivelAcessoUser.setText(Integer.toString(usuarioDto.getNivelDeAcesso()));
        lblMsgNivelAcessoUser.setForeground(new Color(0, 128, 0));
        lblMsgNivelAcessoUser.setBounds(335, 7, 100, 16);
        panel.add(lblMsgNivelAcessoUser);


        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(0, 29, 500, 2);
        panel.add(separator_2);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 265, 500, 2);
        getContentPane().add(separator_1);

    }
}
