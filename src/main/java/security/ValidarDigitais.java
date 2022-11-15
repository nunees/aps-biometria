package security;

import app.Application;
import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintImageOptions;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

import utils.Log;
import utils.Progressbar;
import utils.TipoLog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
                Log.print(TipoLog.INFO, "Thread iniciada");
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
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Apenas arquivos .png", "png");
            fileChooser.addChoosableFileFilter(restrict);
            fileChooser.setAcceptAllFileFilterUsed(false);

            int status = fileChooser.showOpenDialog(null);
            String userPath = fileChooser.getSelectedFile().toString();


            MyThread t1 = new MyThread("Progressbar Thread");
            if(status == JFileChooser.APPROVE_OPTION){
                try {
                    if (isFingerprintPresent(userPath, username)) {
                        t1.stop();
                        JOptionPane.showMessageDialog(null, "Digitais aceitas\nBem vindo " + username
                                + "!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        Log.print(TipoLog.INFO, "Digitais verificadas");
                        autenticado = true;
                    } else {
                        t1.stop();

                        autenticado = false;

                        JOptionPane.showMessageDialog(null, "Acesso não autorizado",
                                "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
                        Log.print(TipoLog.ERRO, "Digitais nao conferem");
                    }
                }catch (IllegalArgumentException iae){
                    t1.stop();
                    JOptionPane.showMessageDialog(null, "Formato inválido!\nUtilize arquivos no formato .png ou .tiff", "Erro", JOptionPane.ERROR_MESSAGE);
                    Log.print(TipoLog.ERRO, "Formato de imagem invalido");
                }catch (Exception e){
                    Log.print(TipoLog.APPLICATION, e.toString());
                    t1.stop();
                }
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(),
                    "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
            Log.print(TipoLog.APPLICATION, "Erro inesperado de validacao");
        }
    }

    public boolean isFingerprintPresent(String userPath, String systemPath) {
        try {
            String fingerPrintsPath = Application.usuarioDto().getCaminhoArquivoDeDigital();
            FingerprintTemplate probe = new FingerprintTemplate(
                    new FingerprintImage(Files.readAllBytes(Paths.get(fingerPrintsPath))
                            , new FingerprintImageOptions().dpi(500)));

            FingerprintTemplate candidate = new FingerprintTemplate(
                    new FingerprintImage(Files.readAllBytes(Paths.get(userPath))
                            , new FingerprintImageOptions().dpi(500)));


            double score = new FingerprintMatcher(probe).match(candidate);
            Log.print(TipoLog.INFO, "Digital do usuario presente");
            return score >= 80;
        } catch (IOException e) {
            e.printStackTrace();
            Log.print(TipoLog.ERRO, e.getMessage());
            throw new RuntimeException(e);

        }
    }
}
