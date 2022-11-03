package janelas;

import db.JDBCExecutor;
import db.SQLiteJDBCDriverConnection;
import dto.UsuarioDto;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class JanelaCadastro extends JFrame {
    private JanelaPrincipal janelaPrincipal;

    private JPanel container;
    private ImageIcon imageIcon;
    private JLabel imagePreview;

    private BufferedImage image;
    private Image novaImage;

    private final String fingerPrintsPath = "src/main/java/security/fingerprints/";

    public JanelaCadastro(JanelaPrincipal janelaPrincipal){
        this.janelaPrincipal = janelaPrincipal;
        setComponents();
    }

    public void setComponents() {
        setLayout(null);
        setLocationRelativeTo(janelaPrincipal);
        setTitle("Cadastro");
        setResizable(false);
        setSize(new Dimension(500,700));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
        btnUpload.addActionListener(e -> {
            try {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Apenas arquivos .png", "png");
                jFileChooser.addChoosableFileFilter(restrict);
                jFileChooser.setAcceptAllFileFilterUsed(false);

                int status = jFileChooser.showOpenDialog(null);
                String path = jFileChooser.getSelectedFile().getAbsolutePath();

                if (status == JFileChooser.APPROVE_OPTION) {

                    image = ImageIO.read(new File(path));
                    imageIcon.setImage(image);
                    novaImage.getScaledInstance(400,300,Image.SCALE_SMOOTH);
                    imagePreview.setBounds(50,280,400,300);
                    imagePreview.revalidate();
                    imagePreview.repaint();

                }
            }catch (Exception ie){
                ie.printStackTrace();
                throw new RuntimeException(ie);
            }
        });
        container.add(btnUpload);

        imagePreview = new JLabel();
        String imagePath = "src/main/java/images/Preview.png";
        imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        novaImage = image.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(novaImage);
        imagePreview.setIcon(imageIcon);
        imagePreview.setBounds(50,280,400,300);
        container.add(imagePreview);


        JButton btnCadastrar = new JButton();
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.setFont(new Font("Dialog", Font.BOLD, 14));
        btnCadastrar.setBounds(150, 600, 200, 30);
        btnCadastrar.addActionListener(e -> {
            String filePath = fingerPrintsPath + txtNomeUsuario.getText() + ".png";
            try{
                Connection connection = new SQLiteJDBCDriverConnection().getConnection();
                JDBCExecutor executor = new JDBCExecutor(connection);
                UsuarioDto usuario = new UsuarioDto();
                usuario = executor.findUsuario(txtNomeUsuario.getText());
                if(!Objects.equals(usuario.getNomeDeUsuario(), txtNomeUsuario.getText())){
                    UsuarioDto usuarioDto = new UsuarioDto();
                    usuarioDto.setNome(txtNome.getText());
                    usuarioDto.setSobrenome(txtSobreNome.getText());
                    usuarioDto.setNomeDeUsuario(txtNomeUsuario.getText());
                    usuarioDto.setCaminhoArquivoDeDigital(filePath);
                    usuarioDto.setNivelDeAcesso(Integer.parseInt(chAcesso.getSelectedItem()));
                    usuarioDto.setAdmin(Boolean.parseBoolean(chAdmin.getSelectedItem()));
                    usuario = executor.create(usuarioDto);
                    try{
                        ImageIO.write(this.image, "png",new File(filePath));
                    }catch (IOException ioe){
                        ioe.printStackTrace();
                        throw new RuntimeException(ioe);
                    }

                    JOptionPane.showMessageDialog(null,"Usuario criado com sucesso","Criado",JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"Usuario ja existe","Erro",JOptionPane.INFORMATION_MESSAGE);
                }
            }catch (SQLException sqlException){
                sqlException.printStackTrace();
                throw new RuntimeException(sqlException);
            }



        });
        container.add(btnCadastrar);
    }
}
