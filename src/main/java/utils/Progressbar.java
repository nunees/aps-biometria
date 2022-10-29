package utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Progressbar {

    private JFrame ownerWindow;
    private JDialog jProgressDialog;
    private JProgressBar progressBar;
    private JPanel contentPanel;
    private JLabel lblProgressStatus;
    public boolean operationStatus;
    public static boolean isDone = false;

    public Progressbar(boolean status, JFrame owner){
        this.operationStatus = status;
        this.ownerWindow = owner;
        setupComponent(owner);
    }

    public void display(){
        jProgressDialog.setVisible(true);
    }

    public void dispose(){
        this.jProgressDialog.dispose();
    }

    public void setupComponent(JFrame owner){
        jProgressDialog = new JDialog(owner, Dialog.ModalityType.APPLICATION_MODAL);
        jProgressDialog.setTitle("Aguarde...");
        jProgressDialog.setResizable(false);
        jProgressDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jProgressDialog.setBounds(100,100,100,80);
        jProgressDialog.setPreferredSize(new Dimension(200,100));
        jProgressDialog.getContentPane().setLayout(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5,5,5,5));

        jProgressDialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(0,1,0,0));

        lblProgressStatus = new JLabel("Scaneando arquivo");
        contentPanel.add(lblProgressStatus);

        progressBar = new JProgressBar();
        progressBar.setString("0");
        progressBar.setForeground(SystemColor.textHighlight);
        progressBar.setIndeterminate(true);
        contentPanel.add(progressBar);
    }
}
