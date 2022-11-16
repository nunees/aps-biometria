package janelas;

import db.DatabaseConnectionDriver;
import db.DatabaseQueryExecutor;
import dto.AgrotoxicoDto;
import utils.Log;
import utils.TipoLog;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class JanelaAgrotoxicos extends JFrame{
    private JanelaPrincipal janelaPrincipal;

    private List<String> empresas;

    private JPanel container;

    private JLabel lblNomeEmpresa;

    private JLabel lblPais;

    private JLabel lblSituacao;

    public JanelaAgrotoxicos(JanelaPrincipal janelaPrincipal){
        this.janelaPrincipal = janelaPrincipal;
        setComponents();
    }

    public void setComponents(){
        setTitle("Agrotoxicos");
        setSize(new Dimension(500,500));
        setLayout(null);
        setLocationRelativeTo(janelaPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        container = new JPanel();
        container.setLayout(null);
        container.setBackground(new Color(255, 255, 255));
        container.setBounds(0, 0, 500, 700);
        getContentPane().add(container);

        JLabel lbltitulo = new JLabel();
        lbltitulo.setText("Listagem de agrotóxicos");
        lbltitulo.setForeground(new Color(0, 128, 0));
        lbltitulo.setFont(new Font("Dialog", Font.BOLD, 20));
        lbltitulo.setBounds(150,0,300,50);
        container.add(lbltitulo);

        Choice ch = new Choice();
        ch.setBounds(100,50, 300, 16);
        try{
            Connection connection = new DatabaseConnectionDriver().getConnection();
            DatabaseQueryExecutor executor = new DatabaseQueryExecutor(connection);
            empresas = executor.getAllAgrotoxicos();
            if(empresas == null){
                JOptionPane.showMessageDialog(null,"Não foi encontrado nenhuma empresa na base.\n" +
                        "Verifique a conexão com o banco de dados!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        ch.add("");
        for(String empresa : this.empresas){
            ch.add(empresa);
        }

        ch.addItemListener(e -> {
            try {
                Connection connection = new DatabaseConnectionDriver().getConnection();
                DatabaseQueryExecutor executor = new DatabaseQueryExecutor(connection);
                String empresa = e.getItem().toString();
                if(!Objects.equals(empresa, "")){
                    AgrotoxicoDto agrotoxicoDto = executor.findAgrotoxico(empresa);
                    if(agrotoxicoDto != null){
                        lblNomeEmpresa.setText(agrotoxicoDto.getNomeEmpresa());
                        lblPais.setText(agrotoxicoDto.getPais());
                        lblSituacao.setText(agrotoxicoDto.getSituacao());
                    }
                }
            } catch (SQLException ex) {
                Log.print(TipoLog.ERRO, ex.getMessage());
                throw new RuntimeException(ex);
            }

        });



        container.add(ch);

        // Nome
        JLabel lblNome = new JLabel();
        lblNome.setText("Nome:");
        lblNome.setForeground(new Color(0, 128, 0));
        lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNome.setBounds(30,100,50,16);
        container.add(lblNome);

        lblNomeEmpresa = new JLabel();
        lblNomeEmpresa.setText("");
        lblNomeEmpresa.setForeground(new Color(0, 128, 0));
        lblNomeEmpresa.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblNomeEmpresa.setBounds(200,100,100,16);
        container.add(lblNomeEmpresa);

        // Local
        JLabel lblSobrenome = new JLabel();
        lblSobrenome.setText("Importado de:");
        lblSobrenome.setForeground(new Color(0, 128, 0));
        lblSobrenome.setFont(new Font("Dialog", Font.BOLD, 14));
        lblSobrenome.setBounds(30,150,100,16);
        container.add(lblSobrenome);

        lblPais = new JLabel();
        lblPais.setText("");
        lblPais.setForeground(new Color(0, 128, 0));
        lblPais.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblPais.setBounds(200,150,100,16);
        container.add(lblPais);

        // Situacao
        JLabel lblUsername = new JLabel();
        lblUsername.setText("Situação:");
        lblUsername.setForeground(new Color(0, 128, 0));
        lblUsername.setFont(new Font("Dialog", Font.BOLD, 14));
        lblUsername.setBounds(30,200,150,16);
        container.add(lblUsername);

        lblSituacao = new JLabel();
        lblSituacao.setText("");
        lblSituacao.setForeground(new Color(0, 128, 0));
        lblSituacao.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblSituacao.setBounds(200,200,150,16);
        container.add(lblSituacao);

    }
}
