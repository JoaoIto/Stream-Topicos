package br.unitins.topicos1.model.Pagamento;

import java.time.LocalDate;

import br.unitins.topicos1.model.Usuario;
import jakarta.persistence.Column;

public class Pix extends Pagamento{

    @Column(nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String chave;

    private LocalDate dataExpiracaoTokenPix;

    public Pix(Usuario usuario, String chave, LocalDate dataExpiracaoTokenPix) {
        this.usuario = usuario;
        this.chave = chave;
        this.dataExpiracaoTokenPix = LocalDate.now().plusDays(1);
    }

    /*
    public Pix(Double valor, String nome, String cpf) {

        super(valor);

        this.nome = nome;
        this.cpf = cpf;
        this.dataExpiracaoTokenPix = LocalDate.now().plusDays(1);
    }
     */

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public LocalDate getDataExpiracaoTokenPix() {
        return dataExpiracaoTokenPix;
    }

    public void setDataExpiracaoTokenPix(LocalDate dataExpiracaoTokenPix) {
        this.dataExpiracaoTokenPix = dataExpiracaoTokenPix;
    }
    

}
