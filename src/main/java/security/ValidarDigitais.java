package security;

import app.Application;
import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintImageOptions;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

import utils.Progressbar;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class ValidarDigitais {
    public boolean autenticado;

    public ValidarDigitais(String username, JFrame janelaReferencia) throws IOException {
        class MyThread implements Runnable{
            private boolean exit;
            final Thread t;

            final Progressbar progressbar;

            MyThread(String nomeThread){
                t = new Thread(this, nomeThread);
                System.out.println("[DEBUG]: New thread spawned!");
                exit = false;
                progressbar = new Progressbar(true, janelaReferencia);
                t.start();
            }

            public void run(){
                progressbar.display();
            }

            public void stop(){
                progressbar.statusDeOperacao = false;
                progressbar.display();
                exit = true;
            }
        }


        try{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.showSaveDialog(null);

            String userPath = fileChooser.getSelectedFile().toString();


            MyThread t1 = new MyThread("Progressbar Thread");
            try {
                if (isFingerprintPresent(userPath, username)) {
                    t1.stop();
                    JOptionPane.showMessageDialog(null, "Digitais aceitas\nBem vindo " + username
                            + "!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    autenticado = true;
                } else {
                    t1.stop();

                    autenticado = false;

                    JOptionPane.showMessageDialog(null, "Acesso não autorizado",
                            "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
                }
            }catch (IllegalArgumentException iae){
                t1.stop();
                JOptionPane.showMessageDialog(null, "Formato inválido!\nUtilize arquivos no formato .png ou .tiff", "Erro", JOptionPane.ERROR_MESSAGE);
            }catch (Exception e){
                t1.stop();
                e.printStackTrace();
            }



        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(),
                    "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isFingerprintPresent(String userPath, String systemPath) {
        try {
            //String fingerPrintsPath = "src/main/java/security/fingerprints/";
            String fingerPrintsPath = Application.usuarioDto().getCaminhoArquivoDeDigital();
            System.out.println(fingerPrintsPath);
            FingerprintTemplate probe = new FingerprintTemplate(
                    //new FingerprintImage(Files.readAllBytes(Paths.get(fingerPrintsPath + systemPath +".png"))
                    new FingerprintImage(Files.readAllBytes(Paths.get(fingerPrintsPath))
                            , new FingerprintImageOptions().dpi(500)));

            FingerprintTemplate candidate = new FingerprintTemplate(
                    new FingerprintImage(Files.readAllBytes(Paths.get(userPath))
                            , new FingerprintImageOptions().dpi(500)));


            double score = new FingerprintMatcher(probe).match(candidate);

            return score >= 80;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }
}
