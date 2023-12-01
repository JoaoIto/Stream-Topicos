package br.unitins.topicos1.model;

import jakarta.persistence.*;

import java.util.List;

import org.hibernate.annotations.Check;

@Entity
public class Duo extends DefaultEntity {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250)
    private Stream stream;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(name = "stream_game", joinColumns = @JoinColumn(name = "id_stream"), inverseJoinColumns = @JoinColumn(name = "id_game"))
    private List<Game> listaGame;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

    public List<Game> getListaGame() {
        return listaGame;
    }

    public void setListaGame(List<Game> listaGame) {
        this.listaGame = listaGame;
    }
    
}