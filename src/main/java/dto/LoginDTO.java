package dto;

import java.util.Objects;

public class LoginDTO {
    private final String nickname;
    private final String senha;
    public LoginDTO(String nickname, String senha) {
        this.nickname = nickname;
        this.senha = senha;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginDTO loginDTO = (LoginDTO) o;
        return Objects.equals(nickname, loginDTO.nickname) && Objects.equals(senha, loginDTO.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, senha);
    }
}
