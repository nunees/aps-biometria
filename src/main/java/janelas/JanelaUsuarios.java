package janelas;

import javax.swing.*;
import java.awt.*;

public class JanelaUsuarios extends JFrame{
    private JanelaPrincipal janelaPrincipal;

    private JLabel nome;
    private JLabel sobrenome;
    private JLabel nomeDeUsuario;
    private JLabel caminhoDigital;
    private JLabel nivelAcesso;
    private JLabel isAdmin;

    public JanelaUsuarios(JanelaPrincipal janela){
        this.janelaPrincipal = janela;
        setComponents();
    }

    public void setComponents(){
        setTitle("Usuarios");
        setSize(new Dimension(300, 343));
        setLayout(null);
        setLocationRelativeTo(janelaPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel container = new JPanel();

        nome = new JLabel();
        nome.setText("Nome: ");
        container.add(nome);

    }
}
