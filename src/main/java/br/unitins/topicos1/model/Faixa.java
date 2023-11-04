package br.unitins.topicos1.model;

import org.hibernate.annotations.Check;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Faixa extends DefaultEntity {

    private String nome;
    private String descricao;
    private Double preco;

    @Column(columnDefinition = "INT CHECK (estoque >= 0)")
    // @Check(constraints = "estoque >= 0")
    private Integer estoque;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public Integer getEstoque() {
        return estoque;
    }
    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    
    
}
