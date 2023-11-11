package models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Cadastro usuario;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "games")
    private List<ItemPedido> games;

    private Double totalPedido;

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Cadastro getUsuario() {
        return usuario;
    }

    public void setUsuario(Cadastro usuario) {
        this.usuario = usuario;
    }

    public List<ItemPedido> getItens() {
        return games;
    }

    public void setItens(List<ItemPedido> itens) {
        this.games = itens;
    }

    public Double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }

}