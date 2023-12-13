package br.unitins.topicos1.model.Pagamento;

import java.time.LocalDate;

import br.unitins.topicos1.model.Solicitacao.Solicitacao;
import jakarta.persistence.*;

@Entity
public class Pagamento{
    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Float valor;

    @Column()
    private Boolean confirmacaoPagamento;

    @Column
    private TipoPagamento tipoPagamento;

    private LocalDate dataConfirmacaoPagamento;

    @OneToOne
    @JoinColumn(name = "id_solicitacao")
    private Solicitacao solicitacao;

    public Pagamento(Float valorTotal) {

        this.valor = valorTotal;
        this.confirmacaoPagamento = true;
        this.dataConfirmacaoPagamento = LocalDate.now();
    }

    public Pagamento() {
        this.confirmacaoPagamento = false;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Boolean getConfirmacaoPagamento() {
        return confirmacaoPagamento;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public void setConfirmacaoPagamento(Boolean confirmacaoPagamento) {
        this.confirmacaoPagamento = confirmacaoPagamento;
    }

    public LocalDate getDataConfirmacaoPagamento() {
        return dataConfirmacaoPagamento;
    }

    public void setDataConfirmacaoPagamento(LocalDate dataConfirmacaoPagamento) {
        this.dataConfirmacaoPagamento = dataConfirmacaoPagamento;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
}