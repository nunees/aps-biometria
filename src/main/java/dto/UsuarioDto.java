package dto;

import java.util.Date;

public class UsuarioDto {
    private int id;
    private String nome;
    private String sobrenome;

    private String nomeDeUsuario;

    private String caminhoArquivoDeDigital;
    private int nivelDeAcesso;
    private boolean isAdmin;

    private Date created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getCaminhoArquivoDeDigital() {
        return caminhoArquivoDeDigital;
    }

    public void setCaminhoArquivoDeDigital(String caminhoArquivoDeDigital) {
        this.caminhoArquivoDeDigital = caminhoArquivoDeDigital;
    }

    public int getNivelDeAcesso() {
        return nivelDeAcesso;
    }

    public void setNivelDeAcesso(int nivelDeAcesso) {
        this.nivelDeAcesso = nivelDeAcesso;
    }

    public boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "UsuarioDto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", nomeDeUsuario='" + nomeDeUsuario + '\'' +
                ", caminhoArquivoDeDigital='" + caminhoArquivoDeDigital + '\'' +
                ", nivelDeAcesso=" + nivelDeAcesso +
                ", isAdmin=" + isAdmin +
                ", created_at= " + created_at +
                '}';
    }
}
