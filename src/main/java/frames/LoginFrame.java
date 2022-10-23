package frames;

import app.Application;
import security.ValidateFingerprint;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginFrame extends JFrame {
    protected JTextField txtNomeUsuario;
    public JButton btnEscanearDigital;
    private JDialog jDialog;



    public LoginFrame(Application application, MainFrame mainFrame) {

        jDialog = new JDialog(mainFrame,true);
        setUpComponents();
        jDialog.setLocationRelativeTo(mainFrame);
    }

    protected void setUpComponents() {
        JPanel contentPanel = new JPanel();

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

        btnEscanearDigital = new JButton("Escanear digital");
        btnEscanearDigital.setBounds(11, 75, 257, 33);
        btnEscanearDigital.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ValidateFingerprint(txtNomeUsuario.getText());
                    if(ValidateFingerprint.isUserAllowed){
                        jDialog.dispose();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        contentPanel.add(btnEscanearDigital);
    }
    public JDialog getJDialog(){
        return jDialog;
    }
}
