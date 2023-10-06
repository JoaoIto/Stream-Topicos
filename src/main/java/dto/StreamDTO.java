package dto;

import java.util.Objects;

public class StreamDTO {
    private String nome;
    private final String nomeUsuario;
    private Float custoStream;

    public StreamDTO(String nome, String nomeUsuario, Float custoStream) {
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.custoStream = custoStream;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeUsuario() {
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
