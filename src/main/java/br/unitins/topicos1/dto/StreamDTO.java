package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class StreamDTO {
    @NotNull
    private String nome;
    @NotNull
    private Long idUsuario;
    @NotNull
    private Float custoStream;


    public StreamDTO(String nome, Long idUsuario, Float custoStream) {
        this.nome = nome;
        this.idUsuario = idUsuario;
        this.custoStream = custoStream;
    }

    public StreamDTO() {

    }

    public String getNome() {
        return nome;
    }

    public Long getIdUsuario() {
        return idUsuario;
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
        return Objects.equals(nome, streamDTO.nome) && Objects.equals(idUsuario, streamDTO.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, idUsuario);
    }
}