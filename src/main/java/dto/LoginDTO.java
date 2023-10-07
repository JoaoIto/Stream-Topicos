package dto;

import jakarta.validation.constraints.NotBlank;
import models.Cadastro;

import java.util.Objects;

public class LoginDTO {
    @NotBlank
    private String senha;

    public LoginDTO() {
        // Construtor vazio
    }
    public LoginDTO(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginDTO loginDTO = (LoginDTO) o;
        return Objects.equals(senha, loginDTO.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senha);
    }
}
