package br.unitins.topicos1.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EstadoDTO {

    @NotBlank(message = "O campo nome não pode ser nulo.")
    private final String nome;
    @NotNull(message = "O campo sigla não pode ser nulo")
    @Size(min = 2, max = 2, message = "A sigla deve ter 2 digitos")
    private final String sigla;

    public EstadoDTO(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EstadoDTO other = (EstadoDTO) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (sigla == null) {
            if (other.sigla != null)
                return false;
        } else if (!sigla.equals(other.sigla))
            return false;
        return true;
    }
    

}
