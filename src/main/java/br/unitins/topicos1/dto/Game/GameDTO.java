package br.unitins.topicos1.dto.Game;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;

public class GameDTO {
    @NotNull
    private final String nome;
    @NotNull
    private final String categoria;

    private final String nomeImagem;


    public GameDTO( String nome, String categoria, String nomeImagem) {
        this.nome = nome;
        this.categoria = categoria;
        this.nomeImagem = nomeImagem;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    @Override
    public int hashCode() {     
        return Objects.hash(nome, categoria, nomeImagem);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())return false;
        GameDTO other = (GameDTO) obj;
        
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (categoria == null) {
            if (other.categoria != null)
                return false;
        } else if (!categoria.equals(other.categoria))
            return false;
        if (nomeImagem == null) {
            if (other.nomeImagem != null)
                return false;
        } else if (!nomeImagem.equals(other.nomeImagem))
            return false;
        return true;
    }

}
