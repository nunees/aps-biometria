package security;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintImageOptions;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ValidateFingerprint {
    public ValidateFingerprint() throws IOException {
        if (isFingerprintPresent()) {
            JOptionPane.showMessageDialog(null, "Digitais aceitas", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Digitais não conferem",
                    "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
        }

    }

    public boolean isFingerprintPresent() throws IOException {

        FingerprintTemplate probe = new FingerprintTemplate(
                new FingerprintImage(Files.readAllBytes(Paths.get("src/main/java/security/fingerprints/finger.png"))
                        , new FingerprintImageOptions().dpi(500)));

        FingerprintTemplate candidate = new FingerprintTemplate(
                new FingerprintImage(Files.readAllBytes(Paths.get("src/main/java/security/fingerprints/finger.png"))
                        ,new FingerprintImageOptions().dpi(500)));

        double score = new FingerprintMatcher(probe)
                .match(candidate);
        return score >= 80;
    }
}
