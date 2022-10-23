package security;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintImageOptions;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ValidateFingerprint {
    public static boolean isUserAllowed;
    public ValidateFingerprint(String username) throws IOException {
        if (isFingerprintPresent(username)) {
            JOptionPane.showMessageDialog(null, "Digitais aceitas\nBem vindo " + username + "!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            ValidateFingerprint.isUserAllowed = true;
        }else{
            ValidateFingerprint.isUserAllowed = false;
            JOptionPane.showMessageDialog(null, "Digitais não conferem",
                    "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
        }

    }

    public boolean isFingerprintPresent(String username) throws IOException {
        String fingerprintsPath = "src/main/java/security/fingerprints/";
        String userFingerprintPath = JOptionPane.showInputDialog("Caminho do arquivo: ");

        FingerprintTemplate probe = new FingerprintTemplate(
                new FingerprintImage(Files.readAllBytes(Paths.get(fingerprintsPath+"finger.png"))
                        ,new FingerprintImageOptions().dpi(500)));

        FingerprintTemplate candidate = new FingerprintTemplate(
                new FingerprintImage(Files.readAllBytes(Paths.get(userFingerprintPath))
                        ,new FingerprintImageOptions().dpi(500)));

        double score = new FingerprintMatcher(probe)
                .match(candidate);
        return score >= 80;
    }
}
