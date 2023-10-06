package dto;

import java.util.Objects;

public class CadastroDTO {
    private final String nome;
    private final String email;
    private final String nickname;

    public CadastroDTO(String nome, String email, String nickname) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CadastroDTO that = (CadastroDTO) o;
        return Objects.equals(nome, that.nome) && Objects.equals(email, that.email) && Objects.equals(nickname, that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email, nickname);
    }
}
