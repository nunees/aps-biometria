package janelas;

import db.DatabaseQueryExecutor;
import db.DatabaseConnectionDriver;
import dto.UsuarioDto;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.swing.*;


public class JanelaUsuarios extends JFrame{
    private JanelaPrincipal janelaPrincipal;
    private JPanel container;
    private List<String> usuarios;

    private JLabel lblNomeUsuario;
    private JLabel lblSobrenomeUsuario;
    private JLabel lblUsernameUsuario;
    private JLabel lblNivelUsuario;
    private JLabel lblAdminUsuario;
    private JLabel lblCreatedAtUsuario;

    private JLabel imagePreview;
    private ImageIcon imageIcon;
    private Image image;
    private Image novaImage;

    public JanelaUsuarios(JanelaPrincipal janela){
        this.janelaPrincipal = janela;
        setComponents();
    }

    public void setComponents(){
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("Usuarios");
        setResizable(false);
        setSize(new Dimension(500,700));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        container = new JPanel();
        container.setLayout(null);
        container.setBackground(new Color(255, 255, 255));
        container.setBounds(0, 0, 500, 700);
        getContentPane().add(container);

        JLabel lbltitulo = new JLabel();
        lbltitulo.setText("Listagem de Usuários");
        lbltitulo.setForeground(new Color(0, 128, 0));
        lbltitulo.setFont(new Font("Dialog", Font.BOLD, 20));
        lbltitulo.setBounds(150,0,300,50);
        container.add(lbltitulo);

        Choice ch = new Choice();
        ch.setBounds(100,50, 300, 16);
        try{
            Connection connection = new DatabaseConnectionDriver().getConnection();
            DatabaseQueryExecutor executor = new DatabaseQueryExecutor(connection);
            usuarios = executor.getAllUsernames();
            if(usuarios == null){
                JOptionPane.showMessageDialog(null,"Não foi encontrado nenhum usuario na base.\n" +
                        "Verifique a conexão com o banco de dados!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        ch.add("");
        for (String name : this.usuarios) {
            ch.add(name);
        }
        ch.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    Connection connection = new DatabaseConnectionDriver().getConnection();
                    DatabaseQueryExecutor executor = new DatabaseQueryExecutor(connection);
                    String username = e.getItem().toString();
                    if(!Objects.equals(username, "")){
                        UsuarioDto usuario = executor.findUsuario(username);
                        if(usuario != null){
                            lblNomeUsuario.setText(usuario.getNome());
                            lblSobrenomeUsuario.setText(usuario.getSobrenome());
                            lblUsernameUsuario.setText(usuario.getNomeDeUsuario());
                            lblNivelUsuario.setText(Integer.toString(usuario.getNivelDeAcesso()));
                            if(usuario.getAdmin()){
                                lblAdminUsuario.setText("Sim");
                            }else{
                                lblAdminUsuario.setText("Não");
                            }
                            Date created_at = usuario.getCreated_at();
                            lblCreatedAtUsuario.setText(created_at.toString());

                            imageIcon = new ImageIcon(usuario.getCaminhoArquivoDeDigital());
                            image = imageIcon.getImage();
                            novaImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                            imageIcon = new ImageIcon(novaImage);
                            imagePreview.setIcon(imageIcon);
                            imagePreview.setBounds(180,400,200,200);
                        }
                    }
                    System.out.println(username);
                }catch (SQLException sqlException){
                    sqlException.printStackTrace();
                    throw new RuntimeException(sqlException);
                }
            }
        });
        container.add(ch);

        // Nome
        JLabel lblNome = new JLabel();
        lblNome.setText("Nome:");
        lblNome.setForeground(new Color(0, 128, 0));
        lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNome.setBounds(30,100,50,16);
        container.add(lblNome);

        lblNomeUsuario = new JLabel();
        lblNomeUsuario.setText("");
        lblNomeUsuario.setForeground(new Color(0, 128, 0));
        lblNomeUsuario.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblNomeUsuario.setBounds(200,100,100,16);
        container.add(lblNomeUsuario);

        // Sobrenome
        JLabel lblSobrenome = new JLabel();
        lblSobrenome.setText("Sobrenome:");
        lblSobrenome.setForeground(new Color(0, 128, 0));
        lblSobrenome.setFont(new Font("Dialog", Font.BOLD, 14));
        lblSobrenome.setBounds(30,150,100,16);
        container.add(lblSobrenome);

        lblSobrenomeUsuario = new JLabel();
        lblSobrenomeUsuario.setText("");
        lblSobrenomeUsuario.setForeground(new Color(0, 128, 0));
        lblSobrenomeUsuario.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblSobrenomeUsuario.setBounds(200,150,100,16);
        container.add(lblSobrenomeUsuario);

        // Nome de usuario
        JLabel lblUsername = new JLabel();
        lblUsername.setText("Nome de usuário:");
        lblUsername.setForeground(new Color(0, 128, 0));
        lblUsername.setFont(new Font("Dialog", Font.BOLD, 14));
        lblUsername.setBounds(30,200,150,16);
        container.add(lblUsername);

        lblUsernameUsuario = new JLabel();
        lblUsernameUsuario.setText("");
        lblUsernameUsuario.setForeground(new Color(0, 128, 0));
        lblUsernameUsuario.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblUsernameUsuario.setBounds(200,200,150,16);
        container.add(lblUsernameUsuario);

        // Nivel do usuario
        JLabel lblNivel = new JLabel();
        lblNivel.setText("Nível:");
        lblNivel.setForeground(new Color(0, 128, 0));
        lblNivel.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNivel.setBounds(30,250,150,16);
        container.add(lblNivel);

        lblNivelUsuario = new JLabel();
        lblNivelUsuario.setText("");
        lblNivelUsuario.setForeground(new Color(0, 128, 0));
        lblNivelUsuario.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblNivelUsuario.setBounds(200,250,150,16);
        container.add(lblNivelUsuario);

        // Administrador
        JLabel lblAdmin = new JLabel();
        lblAdmin.setText("É administrador?");
        lblAdmin.setForeground(new Color(0, 128, 0));
        lblAdmin.setFont(new Font("Dialog", Font.BOLD, 14));
        lblAdmin.setBounds(30,300,150,16);
        container.add(lblAdmin);

        lblAdminUsuario = new JLabel();
        lblAdminUsuario.setText("");
        lblAdminUsuario.setForeground(new Color(0, 128, 0));
        lblAdminUsuario.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblAdminUsuario.setBounds(200,300,150,16);
        container.add(lblAdminUsuario);

        // Criado em
        JLabel lblCreatedAt = new JLabel();
        lblCreatedAt.setText("Criado em: ");
        lblCreatedAt.setForeground(new Color(0, 128, 0));
        lblCreatedAt.setFont(new Font("Dialog", Font.BOLD, 14));
        lblCreatedAt.setBounds(30,350,150,16);
        container.add(lblCreatedAt);

        lblCreatedAtUsuario = new JLabel();
        lblCreatedAtUsuario.setText("");
        lblCreatedAtUsuario.setForeground(new Color(0, 128, 0));
        lblCreatedAtUsuario.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblCreatedAtUsuario.setBounds(200,350,150,16);
        container.add(lblCreatedAtUsuario);

        imagePreview = new JLabel();
        imageIcon = new ImageIcon(Objects.requireNonNull(JanelaPrincipal.class.getClassLoader().getResource("images/preview.png")));
        image = imageIcon.getImage();
        novaImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(novaImage);
        imagePreview.setIcon(imageIcon);
        imagePreview.setBounds(180,400,200,200);
        container.add(imagePreview);
    }
}
