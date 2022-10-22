package classes;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintImageOptions;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ValidateFingerprint {
    public ValidateFingerprint() {
        String userFingerprintPath = "";
        if (isFingerprint(userFingerprintPath)) {
            JOptionPane.showMessageDialog(null, "Digitais aceitas", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
        JOptionPane.showMessageDialog(null, "Digitais não encontradas no sistema",
                "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
    }

    public boolean isFingerprint(String userFingerprintPath) {
        try {
            FingerprintTemplate probe = new FingerprintTemplate(
                    new FingerprintImage(
                            Files.readAllBytes(Paths.get(userFingerprintPath)),
                            new FingerprintImageOptions().dpi(500)));


            FingerprintTemplate candidate = new FingerprintTemplate(
                    new FingerprintImage(
                            Files.readAllBytes(Paths.get("candidate.tif")),
                            new FingerprintImageOptions()
                                    .dpi(500)));

            double score = new FingerprintMatcher(probe).match(candidate);
            boolean matches = score >= 40;
            return score >= 40;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro interno", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }
}
