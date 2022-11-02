package janelas;

import javax.swing.*;
import java.awt.*;

public class JanelaCadastro extends JFrame {
    private JanelaPrincipal janelaPrincipal;

    public JanelaCadastro(JanelaPrincipal janelaPrincipal){
        this.janelaPrincipal = janelaPrincipal;
        setComponents();
    }

    public void setComponents(){
        setTitle("Cadastro");
        setSize(new Dimension(300, 343));
        setLayout(null);
        setLocationRelativeTo(janelaPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }
}
