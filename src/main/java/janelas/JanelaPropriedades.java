package janelas;

import javax.swing.*;
import java.awt.*;

public class JanelaPropriedades extends JFrame {
    private JanelaPrincipal janelaPrincipal;

    public JanelaPropriedades(JanelaPrincipal janelaPrincipal){
        this.janelaPrincipal = janelaPrincipal;
        setComponents();
    }

    public void setComponents(){
        setTitle("Propriedades");
        setSize(new Dimension(300, 343));
        setLayout(null);
        setLocationRelativeTo(janelaPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }
}
