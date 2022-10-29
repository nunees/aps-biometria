package security;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintImageOptions;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;
import utils.Progressbar;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ValidateFingerprint {
    public static boolean isUserAllowed;
    private String userPath;

    public ValidateFingerprint(String username, JFrame owner) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showSaveDialog(null);

        userPath = fileChooser.getSelectedFile().toString();

        try{
            if (isFingerprintPresent(userPath, owner)) {
                System.out.print("Enter true loop");

                JOptionPane.showMessageDialog(null, "Digitais aceitas\nBem vindo " + username
                        + "!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                ValidateFingerprint.isUserAllowed = true;
            } else {
                System.out.print("enter false loop");
                ValidateFingerprint.isUserAllowed = false;

                JOptionPane.showMessageDialog(null, "Acesso não autorizado",
                        "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(),
                    "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o arquivo!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
