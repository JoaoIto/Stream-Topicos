package br.unitins.topicos1.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import br.unitins.topicos1.model.Pagamento.Pagamento;

@Entity
public class Solicitacao {
    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_duo")
    private Duo duo;

    @Column(length = 10)
    private Float valorTotal;

    @Column
    private StatusSolicitacao status;

    @OneToOne
    @JoinColumn(name = "id_pagamento", unique = true)
    private Pagamento pagamento;

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Duo getDuo() {
        return duo;
    }

    public void setDuo(Duo duo) {
        this.duo = duo;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }

        public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}
