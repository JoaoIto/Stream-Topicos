package br.unitins.topicos1.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Duo{
    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer quantidadeHoras;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_stream")
    private Stream stream;

    @OneToOne
    @JoinColumn(name = "id_solicitacao")
    private Solicitacao solicitacao;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "duo_game", joinColumns = @JoinColumn(name = "id_duo"), inverseJoinColumns = @JoinColumn(name = "id_game"))
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

    public Integer getQuantidadeHoras() {
        return quantidadeHoras;
    }

    public Solicitacao getPedido() {
        return solicitacao;
    }

    public void setQuantidadeHoras(Integer quantidadeHoras) {
        this.quantidadeHoras = quantidadeHoras;
    }

    public Float calcularPrecoStream() {
        // Implemente o cálculo conforme necessário
        return quantidadeHoras * stream.getPrecoStream();
    }

    public void setPedido(Solicitacao pedido) {
        this.solicitacao = pedido;
    }

    public Float calcularCustoTotal() {
        if (quantidadeHoras != null && stream != null && stream.getPrecoStream() != null) {
            return quantidadeHoras * stream.getPrecoStream();
        }
        return null;
    }
}