package br.unitins.topicos1.model.Pagamento;

import java.time.LocalDate;

import br.unitins.topicos1.model.DefaultEntity;
import jakarta.persistence.*;

@Embeddable
public class Pagamento{
    @Column()
    private Float valor;

    @Column()
    private Boolean confirmacaoPagamento;

    private LocalDate dataConfirmacaoPagamento;

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

    public void setConfirmacaoPagamento(Boolean confirmacaoPagamento) {
        this.confirmacaoPagamento = confirmacaoPagamento;
    }

    public LocalDate getDataConfirmacaoPagamento() {
        return dataConfirmacaoPagamento;
    }

    public void setDataConfirmacaoPagamento(LocalDate dataConfirmacaoPagamento) {
        this.dataConfirmacaoPagamento = dataConfirmacaoPagamento;
    }

}