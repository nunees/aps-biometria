package janelas;

import db.DatabaseConnectionDriver;
import db.DatabaseQueryExecutor;
import dto.AgrotoxicoDto;
import dto.ProprietarioDto;
import utils.Log;
import utils.TipoLog;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class JanelaProprietarios extends JFrame {
    private JanelaPrincipal janelaPrincipal;
    private JPanel container;

    private List<String> proprietarios;

    private JLabel lblNome;
    private JLabel lblSobrenome;
    private JLabel lblCpf;
    private JLabel lblEndereco;
    private JLabel lblEstado;
    private JLabel lblMunicipio;
    private JLabel lblPais;

    public JanelaProprietarios(JanelaPrincipal janelaPrincipal){
        this.janelaPrincipal = janelaPrincipal;
        setComponents();
    }

    public void setComponents(){
        setTitle("Proprietarios");
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
        lbltitulo.setText("Listagem de proprietarios");
        lbltitulo.setForeground(new Color(0, 128, 0));
        lbltitulo.setFont(new Font("Dialog", Font.BOLD, 20));
        lbltitulo.setBounds(150,0,300,50);
        container.add(lbltitulo);

        Choice ch = new Choice();
        ch.setBounds(100,50, 300, 16);
        try{
            Connection connection = new DatabaseConnectionDriver().getConnection();
            DatabaseQueryExecutor executor = new DatabaseQueryExecutor(connection);
            proprietarios = executor.getAllProprietarios();
            if(proprietarios == null){
                JOptionPane.showMessageDialog(null,"Não foi encontrado nenhum proprietario na base.\n" +
                        "Verifique a conexão com o banco de dados!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }catch (SQLException e){
            Log.print(TipoLog.ERRO, e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        ch.add("");
        for(String proprietario : this.proprietarios){
            ch.add(proprietario);
        }

        ch.addItemListener(e -> {
            try {
                Connection connection = new DatabaseConnectionDriver().getConnection();
                DatabaseQueryExecutor executor = new DatabaseQueryExecutor(connection);
                String nome = e.getItem().toString();
                if(!Objects.equals(nome, "")){
                    ProprietarioDto proprietarioDto = executor.findProprietario(nome);
                    if(proprietarioDto != null){
                        lblNome.setText(proprietarioDto.getNome());
                        lblSobrenome.setText(proprietarioDto.getSobrenome());
                        lblCpf.setText(proprietarioDto.getCpf());
                        lblEndereco.setText(proprietarioDto.getEndereco());
                        lblEstado.setText(proprietarioDto.getEstado());
                        lblMunicipio.setText(proprietarioDto.getMunicipio());
                        lblPais.setText(proprietarioDto.getPais());
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
        lblNomeUsuario.setText("Nome:");
        lblNomeUsuario.setForeground(new Color(0, 128, 0));
        lblNomeUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNomeUsuario.setBounds(30,100,50,16);
        container.add(lblNomeUsuario);

        lblNome = new JLabel();
        lblNome.setText("");
        lblNome.setForeground(new Color(0, 128, 0));
        lblNome.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblNome.setBounds(200,100,100,16);
        container.add(lblNome);

        // Sobrenome
        JLabel lblSobrenomeUsuario = new JLabel();
        lblSobrenomeUsuario.setText("Sobrenome:");
        lblSobrenomeUsuario.setForeground(new Color(0, 128, 0));
        lblSobrenomeUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
        lblSobrenomeUsuario.setBounds(30,150,100,16);
        container.add(lblSobrenomeUsuario);

        lblSobrenome = new JLabel();
        lblSobrenome.setText("");
        lblSobrenome.setForeground(new Color(0, 128, 0));
        lblSobrenome.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblSobrenome.setBounds(200,150,100,16);
        container.add(lblSobrenome);

        // CPF
        JLabel lblCPFUsuario = new JLabel();
        lblCPFUsuario.setText("CPF:");
        lblCPFUsuario.setForeground(new Color(0, 128, 0));
        lblCPFUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
        lblCPFUsuario.setBounds(30,200,150,16);
        container.add(lblCPFUsuario);

        lblCpf = new JLabel();
        lblCpf.setText("");
        lblCpf.setForeground(new Color(0, 128, 0));
        lblCpf.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblCpf.setBounds(200,200,200,16);
        container.add(lblCpf);

        // Endereco
        JLabel lblEnderecoUsuario = new JLabel();
        lblEnderecoUsuario.setText("Endereco:");
        lblEnderecoUsuario.setForeground(new Color(0, 128, 0));
        lblEnderecoUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
        lblEnderecoUsuario.setBounds(30,250,150,16);
        container.add(lblEnderecoUsuario);

        lblEndereco = new JLabel();
        lblEndereco.setText("");
        lblEndereco.setForeground(new Color(0, 128, 0));
        lblEndereco.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblEndereco.setBounds(200,250,300,16);
        container.add(lblEndereco);

        // Estado
        JLabel lblEstadoUsuario = new JLabel();
        lblEstadoUsuario.setText("Estado:");
        lblEstadoUsuario.setForeground(new Color(0, 128, 0));
        lblEstadoUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
        lblEstadoUsuario.setBounds(30,300,150,16);
        container.add(lblEstadoUsuario);

        lblEstado = new JLabel();
        lblEstado.setText("");
        lblEstado.setForeground(new Color(0, 128, 0));
        lblEstado.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblEstado.setBounds(200,300,300,16);
        container.add(lblEstado);

        // Municipio
        JLabel lblMunicipioUsuario = new JLabel();
        lblMunicipioUsuario.setText("Municipio:");
        lblMunicipioUsuario.setForeground(new Color(0, 128, 0));
        lblMunicipioUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
        lblMunicipioUsuario.setBounds(30,350,150,16);
        container.add(lblMunicipioUsuario);

        lblMunicipio = new JLabel();
        lblMunicipio.setText("");
        lblMunicipio.setForeground(new Color(0, 128, 0));
        lblMunicipio.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblMunicipio.setBounds(200,350,150,16);
        container.add(lblMunicipio);

        // Pais
        JLabel lblPaisUsuario = new JLabel();
        lblPaisUsuario.setText("Pais:");
        lblPaisUsuario.setForeground(new Color(0, 128, 0));
        lblPaisUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
        lblPaisUsuario.setBounds(30,400,150,16);
        container.add(lblPaisUsuario);

        lblPais = new JLabel();
        lblPais.setText("");
        lblPais.setForeground(new Color(0, 128, 0));
        lblPais.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblPais.setBounds(200,400,150,16);
        container.add(lblPais);
    }
}
