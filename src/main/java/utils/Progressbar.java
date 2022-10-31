package utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Progressbar {

    private final JFrame janelaReferencia;
    private JDialog jProgressDialog;
    public boolean statusDeOperacao;

    public Progressbar(boolean status, JFrame owner){
        this.statusDeOperacao = status;
        this.janelaReferencia = owner;
    }

    public void display(){
        if(jProgressDialog == null){
            jProgressDialog = new JDialog(janelaReferencia, Dialog.ModalityType.APPLICATION_MODAL);
            jProgressDialog.setTitle("Autenticando usuário");
            jProgressDialog.setResizable(false);
            jProgressDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            jProgressDialog.setBounds(5,5, 400,100);
            jProgressDialog.setPreferredSize(new Dimension(400,100));
            jProgressDialog.setLocationRelativeTo(null);
            jProgressDialog.getContentPane().setLayout(new BorderLayout());

            JPanel contentPanel = new JPanel();
            contentPanel.setBackground(Color.YELLOW);
            contentPanel.setBorder(new EmptyBorder(5,5,5,5));

            jProgressDialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
            contentPanel.setLayout(new GridLayout(0,1,0,0));

            JLabel lblProgressStatus = new JLabel("Carregando imagem");
            contentPanel.add(lblProgressStatus);

            JProgressBar progressBar = new JProgressBar();
            progressBar.setString("0");
            progressBar.setStringPainted(true);
            progressBar.setForeground(SystemColor.textHighlight);
            progressBar.setIndeterminate(true);

            contentPanel.add(progressBar);

            jProgressDialog.add(contentPanel);
            jProgressDialog.setLocationRelativeTo(janelaReferencia);
            jProgressDialog.pack();
        }

        if(statusDeOperacao){
            jProgressDialog.setVisible(true);
        }else{
            jProgressDialog.dispose();
        }
    }

}
