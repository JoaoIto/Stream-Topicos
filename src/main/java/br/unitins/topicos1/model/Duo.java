package br.unitins.topicos1.model;

import br.unitins.topicos1.model.Solicitacao.Solicitacao;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Duo {
    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer quantidadeHoras;

    @ManyToOne()
    @JoinColumn(name = "id_stream")
    private Stream stream;

    @OneToOne
    @JoinColumn(name = "id_solicitacao", unique = true)
    private Solicitacao solicitacao;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public void setQuantidadeHoras(Integer quantidadeHoras) {
        this.quantidadeHoras = quantidadeHoras;
    }

    public Float calcularCustoTotal () {
        if (quantidadeHoras != null && stream != null && stream.getPrecoStream() != null) {
            return quantidadeHoras * stream.getPrecoStream();
        }
        return null;
    }
    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
}