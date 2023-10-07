package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import models.Login;

import java.util.Objects;

public class CadastroDTO {
    @NotBlank
    private final String nome;
    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String nickname;
    @NotNull

    private final LoginDTO login;

    public CadastroDTO(String nome, String email, String nickname, LoginDTO login) {
        this.nome = nome;
        this.email = email;
        this.nickname = nickname;
        this.login = login;
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

    public LoginDTO getLogin() {
        return login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CadastroDTO that = (CadastroDTO) o;
        return Objects.equals(nome, that.nome) && Objects.equals(email, that.email) && Objects.equals(nickname, that.nickname) && Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email, nickname, login);
    }
}
