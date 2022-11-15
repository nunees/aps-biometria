package janelas;

import app.Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class JanelaSobre extends JFrame {

    private JanelaPrincipal janelaPrincipal;

    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JLabel lblLogoChat;
    public JanelaSobre(Window janela) throws IOException {
        this.janelaPrincipal = (JanelaPrincipal) janela;
        setComponents();
    }

    public void setComponents() throws IOException {
        Image logoImage = ImageIO.read(Objects.requireNonNull(JanelaPrincipal.class.getClassLoader().getResource("images/logo.png")));
        logoImage.getScaledInstance(64,64,0);
        setIconImage(logoImage);
        setTitle("Sobre");
        setSize(new Dimension(300, 500));
        setLayout(null);
        setLocationRelativeTo(janelaPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblLogoChat = new javax.swing.JLabel();
        lblLogoChat.setBackground(Color.WHITE);

        setPreferredSize(new Dimension(300, 500));
        setLayout(null);

        // redimensiona a logomarca
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(JanelaSobre.class.getClassLoader().getResource("images/preview.png")));
        Image image = imageIcon.getImage();
        Image novaImg = image.getScaledInstance(250, 200,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(novaImg);

        lblLogoChat.setIcon(imageIcon);
        lblLogoChat.setAlignmentX(0.5F);
        lblLogoChat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(lblLogoChat);
        lblLogoChat.setBounds(20, 10, 250, 200);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 13));
        jLabel2.setText("Versão:");
        add(jLabel2);
        jLabel2.setBounds(10, 200, 56, 17);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel3.setText("1.3");
        add(jLabel3);
        jLabel3.setBounds(70, 200, 20, 15);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 13));
        jLabel4.setText("Desenvolvedores:");
        add(jLabel4);
        jLabel4.setBounds(10, 220, 131, 17);

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel6.setText("Aluno 1");
        add(jLabel6);
        jLabel6.setBounds(10, 240, 213, 15);

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel7.setText("Aluno 2");
        add(jLabel7);
        jLabel7.setBounds(10, 260, 213, 15);

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel8.setText("Aluno 3");
        add(jLabel8);
        jLabel8.setBounds(10, 280, 213, 15);

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel9.setText("Aluno 4");
        add(jLabel9);
        jLabel9.setBounds(10, 300, 213, 15);






    }
}
