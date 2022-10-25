package security;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintImageOptions;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

import frames.LoginFrame;
import frames.MainFrame;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ValidateFingerprint {
    public static boolean isUserAllowed;

    private JDialog progressbarDialog;

    public ValidateFingerprint(String username, JFrame owner) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showSaveDialog(null);

        String userPath = fileChooser.getSelectedFile().toString();

        showProgressBar(true, owner);
        try{
            if (isFingerprintPresent(userPath, owner)) {
                System.out.print("Enter true loop");
                showProgressBar(false, owner);
                JOptionPane.showMessageDialog(null, "Digitais aceitas\nBem vindo " + username + "!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                ValidateFingerprint.isUserAllowed = true;
            } else {
                System.out.print("enter false loop");
                showProgressBar(false, owner);
                ValidateFingerprint.isUserAllowed = false;
                JOptionPane.showMessageDialog(null, "Acesso não autorizado", "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
        }

    }

    public boolean isFingerprintPresent(String userPath, JFrame owner) {
        try {
            String fingerPrintsPath = "src/main/java/security/fingerprints/";
            FingerprintTemplate probe = new FingerprintTemplate(
                    new FingerprintImage(Files.readAllBytes(Paths.get(fingerPrintsPath + "finger.png"))
                            , new FingerprintImageOptions().dpi(500)));

            FingerprintTemplate candidate = new FingerprintTemplate(
                    new FingerprintImage(Files.readAllBytes(Paths.get(userPath))
                            , new FingerprintImageOptions().dpi(500)));


            double score = new FingerprintMatcher(probe).match(candidate);

            return score >= 80;
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        showProgressBar(false, owner);
        return false;
    }

    public void showProgressBar(boolean operation, JFrame owner) {
        if (progressbarDialog == null) {
            progressbarDialog = new JDialog(owner, Dialog.ModalityType.APPLICATION_MODAL);
            progressbarDialog.setTitle("Aguarde...");
            progressbarDialog.setResizable(false);
            progressbarDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            progressbarDialog.setBounds(100, 100, 100, 80);
            progressbarDialog.setPreferredSize(new Dimension(200, 100));
            progressbarDialog.getContentPane().setLayout(new BorderLayout());

            JPanel contentPanel = new JPanel();
            contentPanel.setBackground(Color.WHITE);
            contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

            progressbarDialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
            contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

            JLabel lblNewLabel = new JLabel("Lendo arquivo de digital");
            contentPanel.add(lblNewLabel);

            JProgressBar progressBar = new JProgressBar();
            progressBar.setString("0");
            progressBar.setForeground(SystemColor.textHighlight);
            progressBar.setIndeterminate(true);
            contentPanel.add(progressBar);

            progressbarDialog.setLocationRelativeTo(owner);
            progressbarDialog.pack();
        }
        if (operation) {
            System.out.print("Hit visibility");
            progressbarDialog.setVisible(true);
        } else {
            System.out.print("Hit Dispose");
            progressbarDialog.dispose();
        }
    }
}
