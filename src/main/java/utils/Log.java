package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    private static BufferedWriter log;

    public static void print(TipoLog opcao, String conteudo) {
        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .format(new Date());

        try {
            File fileLog = new File("./application.log");
            if(!fileLog.exists() && !fileLog.isDirectory()){
                boolean fileCreated = fileLog.createNewFile();
                if(fileCreated){
                    log = new BufferedWriter(new FileWriter(fileLog, true));
                }
            }

            log = new BufferedWriter(new FileWriter(fileLog, true));
            switch (opcao){
                case INFO:
                    System.out.println(date + " [INFO]: " + conteudo);
                    log.write(date + " [INFO]: " + conteudo );
                    log.newLine();
                    log.close();
                    break;
                case ALERTA:
                    System.out.println(date + " [ALERTA]: " + conteudo );
                    log.write(date + " [ALERTA]: " + conteudo );
                    log.newLine();
                    log.close();
                    break;
                case ERRO:
                    System.out.println(date + " [ERRO]: " + conteudo );
                    log.write(date + " [ERRO]: " + conteudo );
                    log.newLine();
                    log.close();
                    break;
                case APPLICATION:
                    System.out.println(date + " [APPLICATION]: " + conteudo );
                    log.write(date + " [APPLICATION]: " + conteudo );
                    log.newLine();
                    log.close();
                    break;
                default:
                    System.out.println(date + " [DEFAULT]: " + conteudo );
                    log.write(date + " [DEFAULT]: " + conteudo );
                    log.newLine();
                    log.close();
                    break;
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
            throw new RuntimeException(ioe);
        }

    }


}
