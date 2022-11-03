package db;

import dto.UsuarioDto;

import java.io.IOException;
import java.sql.*;

public class JDBCExecutor {
    protected final Connection connection;
    protected final SQLiteJDBCDriverConnection sqLiteJDBCDriverConnection;

    private static final String GET_ONE_USER = "SELECT id, nome, sobrenome, username, caminhoArquivoDeDigital, nivelAcesso, " +
            "isAdmin FROM usuarios WHERE username = ?";

    private static final String CREATE_USER = "INSERT INTO usuarios (nome, sobrenome, username, caminhoArquivoDeDigital," +
            " nivelAcesso, isAdmin) VALUES (?,?,?,?,?,?)";

    private static final String DELETE_USER = "DELETE FROM usuarios WHERE username = ?";

    public JDBCExecutor(Connection connection) throws SQLException{
        this.connection = connection;
        sqLiteJDBCDriverConnection = new SQLiteJDBCDriverConnection();
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

    public void delete(String username){
        try(PreparedStatement statement = this.connection.prepareStatement(DELETE_USER)){
            statement.setString(1,username);
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
