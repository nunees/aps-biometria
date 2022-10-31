package dto;

public class UsuarioDto {
    private Long id;
    private String nome;
    private String sobrenome;

    private String nomeDeUsuario;

    private String caminhoArquivoDeDigital;
    private int nivelDeAcesso;
    private boolean isAdmin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

}
