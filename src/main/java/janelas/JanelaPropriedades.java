package janelas;

import db.DatabaseConnectionDriver;
import db.DatabaseQueryExecutor;
import dto.PropriedadeDto;
import dto.ProprietarioDto;
import utils.Log;
import utils.TipoLog;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class JanelaPropriedades extends JFrame {
    private JanelaPrincipal janelaPrincipal;

    private JPanel container;

    private List<String> propriedades;

    private JLabel lblNomeFantasia;
    private JLabel lblCnpj;
    private JLabel lblEndereco;
    private JLabel lblEstado;
    private JLabel lblPais;
    private JLabel lblTelefone;

    public JanelaPropriedades(JanelaPrincipal janelaPrincipal){
        this.janelaPrincipal = janelaPrincipal;
        setComponents();
    }

    public void setComponents(){
        setTitle("Propriedades");
        setSize(new Dimension(500,700));
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
        lbltitulo.setText("Listagem de propriedades");
        lbltitulo.setForeground(new Color(0, 128, 0));
        lbltitulo.setFont(new Font("Dialog", Font.BOLD, 20));
        lbltitulo.setBounds(150,0,300,50);
        container.add(lbltitulo);

        Choice ch = new Choice();
        ch.setBounds(100,50, 300, 16);
        try{
            Connection connection = new DatabaseConnectionDriver().getConnection();
            DatabaseQueryExecutor executor = new DatabaseQueryExecutor(connection);
            propriedades = executor.getAllPropriedades();
            if(propriedades == null){
                JOptionPane.showMessageDialog(null,"Não foi encontrado nenhuma propriedade na base.\n" +
                        "Verifique a conexão com o banco de dados!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }catch (SQLException e){
            Log.print(TipoLog.ERRO, e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        ch.add("");
        for(String propriedade : this.propriedades){
            ch.add(propriedade);
        }

        ch.addItemListener(e -> {
            try {
                Connection connection = new DatabaseConnectionDriver().getConnection();
                DatabaseQueryExecutor executor = new DatabaseQueryExecutor(connection);
                String nome = e.getItem().toString();
                if(!Objects.equals(nome, "")){
                    PropriedadeDto propriedadeDto = executor.findPropriedade(nome);
                    if(propriedadeDto != null){
                        lblNomeFantasia.setText(propriedadeDto.getNomeFantasia());
                        lblCnpj.setText(propriedadeDto.getCnpj());
                        lblEndereco.setText(propriedadeDto.getEndereco());
                        lblEstado.setText(propriedadeDto.getEstado());
                        lblTelefone.setText(propriedadeDto.getTelefone());
                        lblPais.setText(propriedadeDto.getPais());
                    }
                }
            } catch (SQLException ex) {
                Log.print(TipoLog.ERRO, ex.getMessage());
                throw new RuntimeException(ex);
            }

        });

        container.add(ch);

        // Nome
        JLabel lblNomeUsuario = new JLabel();
        lblNomeUsuario.setText("Nome Fantasia:");
        lblNomeUsuario.setForeground(new Color(0, 128, 0));
        lblNomeUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNomeUsuario.setBounds(30,100,150,16);
        container.add(lblNomeUsuario);

        lblNomeFantasia = new JLabel();
        lblNomeFantasia.setText("");
        lblNomeFantasia.setForeground(new Color(0, 128, 0));
        lblNomeFantasia.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblNomeFantasia.setBounds(200,100,100,16);
        container.add(lblNomeFantasia);

        // CNPJ
        JLabel lblCnpjUsuario = new JLabel();
        lblCnpjUsuario.setText("CNPJ:");
        lblCnpjUsuario.setForeground(new Color(0, 128, 0));
        lblCnpjUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
        lblCnpjUsuario.setBounds(30,150,100,16);
        container.add(lblCnpjUsuario);

        lblCnpj = new JLabel();
        lblCnpj.setText("");
        lblCnpj.setForeground(new Color(0, 128, 0));
        lblCnpj.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblCnpj.setBounds(200,150,300,16);
        container.add(lblCnpj);

        // Endereco
        JLabel lblEnderecoUsuario = new JLabel();
        lblEnderecoUsuario.setText("Endereço:");
        lblEnderecoUsuario.setForeground(new Color(0, 128, 0));
        lblEnderecoUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
        lblEnderecoUsuario.setBounds(30,200,150,16);
        container.add(lblEnderecoUsuario);

        lblEndereco = new JLabel();
        lblEndereco.setText("");
        lblEndereco.setForeground(new Color(0, 128, 0));
        lblEndereco.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblEndereco.setBounds(200,200,150,16);
        container.add(lblEndereco);

        // Estado
        JLabel lblEstadoUsuario = new JLabel();
        lblEstadoUsuario.setText("Estado:");
        lblEstadoUsuario.setForeground(new Color(0, 128, 0));
        lblEstadoUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
        lblEstadoUsuario.setBounds(30,250,150,16);
        container.add(lblEstadoUsuario);

        lblEstado = new JLabel();
        lblEstado.setText("");
        lblEstado.setForeground(new Color(0, 128, 0));
        lblEstado.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblEstado.setBounds(200,250,150,16);
        container.add(lblEstado);

        // Pais
        JLabel lblPaisUsuario = new JLabel();
        lblPaisUsuario.setText("Pais:");
        lblPaisUsuario.setForeground(new Color(0, 128, 0));
        lblPaisUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
        lblPaisUsuario.setBounds(30,300,150,16);
        container.add(lblPaisUsuario);

        lblPais = new JLabel();
        lblPais.setText("");
        lblPais.setForeground(new Color(0, 128, 0));
        lblPais.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblPais.setBounds(200,300,150,16);
        container.add(lblPais);

        // Telefone
        JLabel lblTelefoneUsuario = new JLabel();
        lblTelefoneUsuario.setText("Municipio:");
        lblTelefoneUsuario.setForeground(new Color(0, 128, 0));
        lblTelefoneUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
        lblTelefoneUsuario.setBounds(30,350,150,16);
        container.add(lblTelefoneUsuario);

        lblTelefone = new JLabel();
        lblTelefone.setText("");
        lblTelefone.setForeground(new Color(0, 128, 0));
        lblTelefone.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblTelefone.setBounds(200,350,150,16);
        container.add(lblTelefone);
    }
}
