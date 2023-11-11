package models;
import jakarta.persistence.*;

@Entity
public class ItemPedido {

    private Integer quantidade;
    private Double preco;

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_duo")
    private Duo duo;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Duo getDuo() {
        return duo;
    }

    public void setDuo(Duo faixa) {
        this.duo = faixa;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}