package janelas;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class JanelaCadastro extends JFrame {
    private JanelaPrincipal janelaPrincipal;

    private JPanel container;

    public JanelaCadastro(JanelaPrincipal janelaPrincipal){
        this.janelaPrincipal = janelaPrincipal;
        setComponents();
    }

    public void setComponents() {
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("Cadastro");
        setResizable(false);
        setSize(new Dimension(500,700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        container = new JPanel();
        container.setLayout(null);
        container.setBackground(new Color(255, 255, 255));
        container.setBounds(0, 0, 500, 700);
        getContentPane().add(container);

        // Nome
        JLabel lblNome = new JLabel();
        lblNome.setText("Nome:");
        lblNome.setForeground(new Color(0, 128, 0));
        lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNome.setBounds(30,20,50,16);
        container.add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(140,20,200,25);
        txtNome.setColumns(10);
        container.add(txtNome);

        // Sobrenome
        JLabel lblSobreNome = new JLabel();
        lblSobreNome.setText("Sobrenome:");
        lblSobreNome.setForeground(new Color(0, 128, 0));
        lblSobreNome.setFont(new Font("Dialog", Font.BOLD, 14));
        lblSobreNome.setBounds(30,50,100,16);
        container.add(lblSobreNome);

        JTextField txtSobreNome = new JTextField();
        txtSobreNome.setBounds(140,50,200,25);
        txtSobreNome.setColumns(10);
        container.add(txtSobreNome);

        //Nome de usuario
        JLabel lblNomeUsuario = new JLabel();
        lblNomeUsuario.setText("Usuario:");
        lblNomeUsuario.setForeground(new Color(0, 128, 0));
        lblNomeUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNomeUsuario.setBounds(30,80,100,16);
        container.add(lblNomeUsuario);

        JTextField txtNomeUsuario = new JTextField();
        txtNomeUsuario.setBounds(140,80,200,25);
        txtNomeUsuario.setColumns(10);
        container.add(txtNomeUsuario);

        //Nivel de acesso
        JLabel lblNivelAcesso = new JLabel();
        lblNivelAcesso.setText("Nivel:");
        lblNivelAcesso.setForeground(new Color(0, 128, 0));
        lblNivelAcesso.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNivelAcesso.setBounds(30,110,90,16);
        container.add(lblNivelAcesso);

        Choice chAcesso = new Choice();
        chAcesso.add("1");
        chAcesso.add("2");
        chAcesso.add("3");
        chAcesso.setBounds(140, 110, 200, 25);
        container.add(chAcesso);

        // Administrador
        JLabel lblAdministrador = new JLabel();
        lblAdministrador.setText("Administrador:");
        lblAdministrador.setForeground(new Color(0, 128, 0));
        lblAdministrador.setFont(new Font("Dialog", Font.BOLD, 14));
        lblAdministrador.setBounds(30,140,110,16);
        container.add(lblAdministrador);

        Choice chAdmin = new Choice();
        chAdmin.add("Sim");
        chAdmin.add("Não");
        chAdmin.setBounds(140, 140, 200, 25);
        container.add(chAdmin);

        // Separator
        JSeparator separatorOne = new JSeparator();
        separatorOne.setBounds(0, 190, 500, 2);
        container.add(separatorOne);

        JLabel lblArquivo = new JLabel();
        lblArquivo.setText("Digitais");
        lblArquivo.setForeground(new Color(0, 128, 0));
        lblArquivo.setFont(new Font("Dialog", Font.BOLD, 14));
        lblArquivo.setBounds(30,230,200,16);
        container.add(lblArquivo);

        JButton btnUpload = new JButton();
        btnUpload.setText("Importar");
        btnUpload.setFont(new Font("Dialog", Font.BOLD, 14));
        btnUpload.setBounds(150,220,200,30);
        container.add(btnUpload);

        JLabel imagePreview = new JLabel();
        String imagePath = "src/main/java/images/Preview.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image novaImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(novaImage);
        imagePreview.setIcon(imageIcon);
        imagePreview.setBounds(180,280,200,200);
        container.add(imagePreview);


        JButton btnCadastrar = new JButton();
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.setFont(new Font("Dialog", Font.BOLD, 14));
        btnCadastrar.setBounds(150, 520, 200, 30);
        container.add(btnCadastrar);
    }
}
