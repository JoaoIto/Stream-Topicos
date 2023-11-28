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

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Cadastro usuario;
    private LocalDateTime dataHora;

    private Double totalPedido;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "pedido")
    private List<ItemPedido> games;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public List<ItemPedido> getGames() {
        return games;
    }

    public void setGames(List<ItemPedido> games) {
        this.games = games;
    }

    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Double getTotalPedido() {
        return totalPedido;
    }

    public Cadastro getUsuario() {
        return usuario;
    }

    public void setUsuario(Cadastro usuario) {
        this.usuario = usuario;
    }
}