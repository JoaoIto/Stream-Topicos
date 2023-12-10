package br.unitins.topicos1.model.Pagamento;

import java.time.LocalDate;

import br.unitins.topicos1.model.Usuario;
import jakarta.persistence.Column;

public class Pix extends Pagamento{

    @Column(nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String chave;

    private LocalDate dataExpiracaoChavePix;

    public Pix(Usuario usuario, String chave, LocalDate dataExpiracaoChavePix) {
        this.usuario = usuario;
        this.chave = chave;
        this.dataExpiracaoChavePix = LocalDate.now().plusDays(1);
    }


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

    public LocalDate getDataExpiracaoChavePix() {
        return dataExpiracaoChavePix;
    }

    public void setDataExpiracaoChavePix(LocalDate dataExpiracaoChavePix) {
        this.dataExpiracaoChavePix = dataExpiracaoChavePix;
    }
    

}
