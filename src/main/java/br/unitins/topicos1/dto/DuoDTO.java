package br.unitins.topicos1.dto;
import java.util.List;
import java.util.Objects;

import br.unitins.topicos1.model.Game;
import jakarta.validation.constraints.NotBlank;


public class DuoDTO {
    @NotBlank
    private final Long idStream;

    @NotBlank
    private final Long idUsuario;
    @NotBlank
    private List<Long> idGames;

    @NotBlank
    private Integer quantidadeHoras;

    
    public DuoDTO(@NotBlank Long idStream, @NotBlank Long idUsuario, @NotBlank List<Long> idGames) {
        this.idStream = idStream;
        this.idGames = idGames;
        this.idUsuario = idUsuario;
    }

    public Long getIdStream() {
        return idStream;
    }

    public Integer getQuantidadeHoras() {
        return quantidadeHoras;
    }

    public void setQuantidadeHoras(int quantidadeHoras) {
        this.quantidadeHoras = quantidadeHoras;
    }

    public List<Long> getIdGames() {
        return idGames;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setQuantidadeHoras(Integer quantidadeHoras) {
        this.quantidadeHoras = quantidadeHoras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DuoDTO duoDTO = (DuoDTO) o;
        return Objects.equals(idStream, duoDTO.idStream) && Objects.equals(idGames, duoDTO.idGames) && Objects.equals(quantidadeHoras, duoDTO.quantidadeHoras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStream, idGames, quantidadeHoras);
    }
}
