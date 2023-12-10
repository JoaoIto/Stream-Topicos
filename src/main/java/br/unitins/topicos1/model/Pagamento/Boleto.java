package br.unitins.topicos1.model.Pagamento;

import java.time.LocalDate;

import br.unitins.topicos1.model.Usuario;
import jakarta.persistence.Column;

public class Boleto extends Pagamento {
   
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDate dataGeracaoBoleto;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    


    public Boleto(Usuario usuario, LocalDate dataGeracaoBoleto, LocalDate dataVencimento) {


        this.usuario = usuario;
        this.dataGeracaoBoleto = dataGeracaoBoleto;
        this.dataVencimento = dataVencimento;
    }

    /* 
    public BoletoBancario (Double valor, Usuario usuario) {

        super(valor);

        this.usuario = usuario;
        this.dataGeracaoBoleto = LocalDate.now();
        this.dataVencimento = LocalDate.now().plusDays(10);
    }
    */

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataGeracaoBoleto() {
        return dataGeracaoBoleto;
    }

    public void setDataGeracaoBoleto(LocalDate dataGeracaoBoleto) {
        this.dataGeracaoBoleto = dataGeracaoBoleto;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    

}
