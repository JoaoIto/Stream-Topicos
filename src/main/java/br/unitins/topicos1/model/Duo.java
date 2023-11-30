package br.unitins.topicos1.model;

import jakarta.persistence.*;

import java.util.List;

import org.hibernate.annotations.Check;

@Entity
public class Duo extends DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_stream")
    private Stream stream;

    @Column(length = 20)
    private Double valorHoras;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(name = "duo_game", joinColumns = @JoinColumn(name = "id_duo"), inverseJoinColumns = @JoinColumn(name = "id_game"))
    private List<Game> listaGame;

    @Column(length = 100)
    private String descricao;

    private Boolean status;

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

    public Double getValorHoras() {
        return valorHoras;
    }

    public void setValorHoras(Double valorHoras) {
        this.valorHoras = valorHoras;
    }

    public List<Game> getListaGame() {
        return listaGame;
    }

    public void setListaGame(List<Game> listaGame) {
        this.listaGame = listaGame;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
