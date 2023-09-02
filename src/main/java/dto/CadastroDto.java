package dto;

public class CadastroDto {
    private final String nome;
    private final String email;
    private final String nickname;

    public CadastroDto(String nome, String email, String nickname) {
        this.nome = nome;
        this.email = email;
        this.nickname = nickname;
    }

    public String getNome() {
        return nome;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }
}
