package br.unitins.topicos1.model;

import java.util.List;

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

    /*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(name = "stream_game", joinColumns = @JoinColumn(name = "id_stream"), inverseJoinColumns = @JoinColumn(name = "id_game"))
    private List<Game> listaGame;
    */

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