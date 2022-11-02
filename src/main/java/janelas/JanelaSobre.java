package janelas;

import app.Application;

import javax.swing.*;
import java.awt.*;

public class JanelaSobre extends JFrame {

    private JanelaPrincipal janelaPrincipal;
    public JanelaSobre(Window janela){
        this.janelaPrincipal = (JanelaPrincipal) janela;
        setComponents();
    }

    public void setComponents(){
        setTitle("Sobre");
        setSize(new Dimension(300, 343));
        setLayout(null);
        setLocationRelativeTo(janelaPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

    }
}
