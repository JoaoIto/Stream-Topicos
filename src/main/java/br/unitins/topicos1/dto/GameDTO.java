package br.unitins.topicos1.dto;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;

public class GameDTO {
    @NotNull
    private final String nome;
    @NotNull
    private final String categoria;


    public GameDTO( String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public int hashCode() {     
        return Objects.hash(nome, categoria);
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
        return true;
    }

}
