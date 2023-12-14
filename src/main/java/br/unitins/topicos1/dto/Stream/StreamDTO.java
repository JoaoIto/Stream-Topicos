package br.unitins.topicos1.dto.Stream;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class StreamDTO {
    @NotNull
    private String nome;
    @NotNull
    private Float custoStream;


    public StreamDTO(String nome, Long idUsuario, Float custoStream) {
        this.nome = nome;
        this.custoStream = custoStream;
    }

    public StreamDTO() {

    }

    public String getNome() {
        return nome;
    }

    public Float getCustoStream() {
        return custoStream;
    }

    public void setCustoStream(Float custoStream) {
        this.custoStream = custoStream;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreamDTO streamDTO = (StreamDTO) o;
        return Objects.equals(nome, streamDTO.nome) && Objects.equals(custoStream, streamDTO.custoStream);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, custoStream);
    }
}