package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Usuario;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class StreamDTO {
    @NotNull
    private final String nome;
    @NotNull
    private final Usuario nomeUsuario;
    @NotNull
    private final Float custoStream;

    public StreamDTO(String nome, Usuario nomeUsuario, Float custoStream) {
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.custoStream = custoStream;
    }

    public String getNome() {
        return nome;
    }

    public Usuario getNomeUsuario() {
        return nomeUsuario;
    }

    public Float getCustoStream() {
        return custoStream;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreamDTO streamDTO = (StreamDTO) o;
        return Objects.equals(nome, streamDTO.nome) && Objects.equals(nomeUsuario, streamDTO.nomeUsuario) && Objects.equals(custoStream, streamDTO.custoStream);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, nomeUsuario, custoStream);
    }
}