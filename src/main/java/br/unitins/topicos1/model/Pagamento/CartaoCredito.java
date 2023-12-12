package br.unitins.topicos1.model.Pagamento;

import jakarta.persistence.Column;

public class CartaoCredito extends Pagamento{

    @Column(nullable = false)
    private String numeroCartao;

    @Column(nullable = false)
    private String nomeImpressoCartao;

    @Column(nullable = false)
    private String cpfTitular;

    private BandeiraCartao bandeiraCartao;

    public CartaoCredito(String numeroCartao, String nomeImpressoCartao, 
    String cpfTitular, BandeiraCartao bandeiraCartao) {
        this.numeroCartao = numeroCartao;
        this.nomeImpressoCartao = nomeImpressoCartao;
        this.cpfTitular = cpfTitular;
        this.bandeiraCartao = bandeiraCartao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeImpressoCartao() {
        return nomeImpressoCartao;
    }

    public void setNomeImpressoCartao(String nomeImpressoCartao) {
        this.nomeImpressoCartao = nomeImpressoCartao;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public void setCpfTitular(String cpfTitular) {
        this.cpfTitular = cpfTitular;
    }

    public BandeiraCartao getBandeiraCartao() {
        return bandeiraCartao;
    }

    public void setBandeiraCartao(BandeiraCartao bandeiraCartao) {
        this.bandeiraCartao = bandeiraCartao;
    }


    
}
