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
    private JDialog jDialog;
    private MainFrame mainFrame;

    private ValidateFingerprint validateFingerprint;


    public LoginFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        jDialog = new JDialog(this.mainFrame,true);
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

        JFrame ownerWindow = this;
        btnEscanearDigital.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Progressbar progressbar = new Progressbar(true, ownerWindow);
                 class Task extends Thread{
                     boolean isDone = false;
                    public void run(){
                       while(!isDone){
                           if(progressbar.operationStatus) {
                               progressbar.display();
                           }else{
                               progressbar.dispose();
                           }
                           isDone = false;
                       }

                    }
                }

                Task task = new Task();

                try {
                    task.start();
                    validateFingerprint = new ValidateFingerprint(txtNomeUsuario.getText(), ownerWindow);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                task.stop();
            }
        });
        contentPanel.add(btnEscanearDigital);
    }
    public JDialog getJDialog(){
        return jDialog;
    }


}
