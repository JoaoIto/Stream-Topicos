package br.unitins.topicos1.model;

import java.util.List;

import br.unitins.topicos1.model.Usuario.Usuario;
import jakarta.persistence.*;

@Entity
public class Stream{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario nomeUsuario;

    @OneToMany(mappedBy = "stream", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Duo> duos;

    @Column
    private Float precoStream;

    public Float getPrecoStream() {
        return precoStream;
    }

    public void setPrecoStream(Float precoStream) {
        this.precoStream = precoStream;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(Usuario nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    /* 
    public List<Game> getListaGame() {
        return listaGame;
    }

    public void setListaGame(List<Game> listaGame) {
        this.listaGame = listaGame;
    }
    */

}