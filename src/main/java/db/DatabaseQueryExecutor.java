package db;

import dto.AgrotoxicoDto;
import dto.PropriedadeDto;
import dto.ProprietarioDto;
import dto.UsuarioDto;
import utils.Log;
import utils.TipoLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryExecutor {
    protected final Connection connection;
    protected final DatabaseConnectionDriver databaseConnectionDriver;

    // Controle usuarios
    private static final String GET_ONE_USER = "SELECT id, nome, sobrenome, username, caminhoArquivoDeDigital, nivelAcesso, " +
            "isAdmin, created_at FROM usuarios WHERE username = ?";

    private static final String CREATE_USER = "INSERT INTO usuarios (nome, sobrenome, username, caminhoArquivoDeDigital," +
            " nivelAcesso, isAdmin) VALUES (?,?,?,?,?,?)";

    private static final String GET_ALL_USERNAMES = "SELECT username FROM usuarios";


    //Proprietarios
    private static final String GET_ALL_PROPRIETARIOS = "SELECT nome FROM proprietarios";

    private static final String GET_ONE_PROPRIETARIO = "SELECT id, nome, sobrenome, cpf, endereco, estado, municipio, pais FROM proprietarios WHERE nome = ?";

    //Agrotoxico
    private static final String GET_ALL_AGROTOXICOS = "SELECT empresaResponsavel FROM agrotoxicos";
    private static final String GET_ONE_AGROTOXICO = "SELECT id,empresaResponsavel,pais, situacao from agrotoxicos WHERE empresaResponsavel = ?";

    //Propriedades
    private static final String GET_ALL_PROPRIEDADES = "SELECT nomeFantasia FROM propriedades";
    private static final String GET_ONE_PROPRIEDADE = "SELECT id, nomeFantasia, cnpj, endereco, estado, pais, telefone FROM propriedades WHERE nomeFantasia = ?";

    public DatabaseQueryExecutor(Connection connection) throws SQLException{
        this.connection = connection;
        databaseConnectionDriver = new DatabaseConnectionDriver();
    }

    public UsuarioDto findUsuario(String username){
        UsuarioDto usuario = new UsuarioDto();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE_USER)){
            statement.setString(1,username);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setNomeDeUsuario(rs.getString("username"));
                usuario.setCaminhoArquivoDeDigital(rs.getString("caminhoArquivoDeDigital"));
                usuario.setNivelDeAcesso(rs.getInt("nivelAcesso"));
                usuario.setAdmin(rs.getBoolean("isAdmin"));
                usuario.setCreated_at(rs.getDate("created_at"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return usuario;
    }

    public UsuarioDto create(UsuarioDto usuarioDto){
        try(PreparedStatement statement = this.connection.prepareStatement(CREATE_USER)){
            statement.setString(1,usuarioDto.getNome());
            statement.setString(2, usuarioDto.getSobrenome());
            statement.setString(3, usuarioDto.getNomeDeUsuario());
            statement.setString(4, usuarioDto.getCaminhoArquivoDeDigital());
            statement.setLong(5, usuarioDto.getNivelDeAcesso());
            statement.setBoolean(6, usuarioDto.getAdmin());
            statement.execute();
            return this.findUsuario(usuarioDto.getNomeDeUsuario());
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<String> getAllUsernames(){
        List<String> usernames = new ArrayList<String>();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ALL_USERNAMES)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                usernames.add(rs.getString("username"));
            }
        }catch (SQLException e){
            Log.print(TipoLog.ERRO, e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return usernames;
    }

    public List<String> getAllAgrotoxicos(){
        List<String> agrotoxicos = new ArrayList<>();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ALL_AGROTOXICOS)){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                agrotoxicos.add(rs.getString("empresaResponsavel"));
            }
        }catch (SQLException e){
            Log.print(TipoLog.ERRO, e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return agrotoxicos;
     }

    public AgrotoxicoDto findAgrotoxico(String empresa){
        AgrotoxicoDto agrotoxicoDto = new AgrotoxicoDto();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE_AGROTOXICO)){
            statement.setString(1,empresa);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                agrotoxicoDto.setId(rs.getInt("id"));
                agrotoxicoDto.setNomeEmpresa(rs.getString("empresaResponsavel"));
                agrotoxicoDto.setPais(rs.getString("pais"));
                agrotoxicoDto.setSituacao(rs.getString("situacao"));
                //agrotoxicoDto.setCreated_at(rs.getDate("created_at"));
            }
        }catch (SQLException e){
            Log.print(TipoLog.ERRO, e.getMessage());
            e.printStackTrace();
        }
        return agrotoxicoDto;
    }

    public List<String> getAllProprietarios(){
        List<String> proprietarios = new ArrayList<>();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ALL_PROPRIETARIOS)){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                proprietarios.add(rs.getString("nome"));
            }
        }catch (SQLException e){
            Log.print(TipoLog.ERRO, e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return proprietarios;
    }

    public ProprietarioDto findProprietario(String proprietario){
        ProprietarioDto proprietarioDto = new ProprietarioDto();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE_PROPRIETARIO)){
            statement.setString(1,proprietario);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                proprietarioDto.setId(rs.getInt("id"));
                proprietarioDto.setNome(rs.getString("nome"));
                proprietarioDto.setSobrenome(rs.getString("sobrenome"));
                proprietarioDto.setCpf(rs.getString("cpf"));
                proprietarioDto.setEndereco(rs.getString("endereco"));
                proprietarioDto.setEstado(rs.getString("estado"));
                proprietarioDto.setMunicipio(rs.getString("municipio"));
                proprietarioDto.setPais(rs.getString("pais"));
            }
        }catch (SQLException e){
            Log.print(TipoLog.ERRO, e.getMessage());
            e.printStackTrace();
        }
        return proprietarioDto;
    }

    public List<String> getAllPropriedades(){
        List<String> propriedades = new ArrayList<>();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ALL_PROPRIEDADES)){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                propriedades.add(rs.getString("nomeFantasia"));
            }
        }catch (SQLException e){
            Log.print(TipoLog.ERRO, e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return propriedades;
    }

    public PropriedadeDto findPropriedade(String propriedade){
        PropriedadeDto propriedadeDto = new PropriedadeDto();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE_PROPRIEDADE)){
            statement.setString(1,propriedade);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                propriedadeDto.setId(rs.getInt("id"));
                propriedadeDto.setNomeFantasia(rs.getString("nomeFantasia"));
                propriedadeDto.setCnpj(rs.getString("cnpj"));
                propriedadeDto.setEndereco(rs.getString("endereco"));
                propriedadeDto.setEstado(rs.getString("estado"));
                propriedadeDto.setPais(rs.getString("pais"));
                propriedadeDto.setTelefone(rs.getString("telefone"));
            }
        }catch (SQLException e){
            Log.print(TipoLog.ERRO, e.getMessage());
            e.printStackTrace();
        }
        return propriedadeDto;
    }

}
