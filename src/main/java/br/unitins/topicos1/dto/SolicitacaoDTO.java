package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

import br.unitins.topicos1.model.Pagamento.Pagamento;

public class SolicitacaoDTO {
    @NotBlank
    private Long idDuo;

    @NotBlank
    private Long idUsuario;

    @NotBlank
    private Boolean status;

    private Pagamento pagamento;


    public SolicitacaoDTO(Long idDuo, Boolean status) {
        this.idDuo = idDuo;
        this.status = status;
    }

    public Long getIdDuo() {
        return idDuo;
    }

    public void setIdDuo(Long idDuo) {
        this.idDuo = idDuo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitacaoDTO that = (SolicitacaoDTO) o;
        return Objects.equals(idDuo, that.idDuo) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDuo, status);
    }
}
