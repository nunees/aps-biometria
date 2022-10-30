package frames;

import security.ValidateFingerprint;
import utils.Progressbar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginFrame extends JFrame {
    protected JTextField txtNomeUsuario;
    public JButton btnEscanearDigital;
    private final JDialog jDialog;

    private ValidateFingerprint validateFingerprint;


    public LoginFrame(MainFrame mainFrame) {
        jDialog = new JDialog(mainFrame,true);
        setUpComponents();
        jDialog.setLocationRelativeTo(mainFrame);
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

        btnEscanearDigital = new JButton("Importar digital");
        btnEscanearDigital.setBounds(11, 75, 257, 33);

        btnEscanearDigital.addActionListener(e -> {
           Progressbar progressbar = new Progressbar(true, mainWindow);
            try {
                validateFingerprint = new ValidateFingerprint(txtNomeUsuario.getText(), mainWindow);
                if(validateFingerprint.isUserAllowed){
                    jDialog.dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"Não foi possivel autenticar\nO programa será encerrado", "Erro", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        contentPanel.add(btnEscanearDigital);
    }
    public JDialog getJDialog(){
        return jDialog;
    }


}
