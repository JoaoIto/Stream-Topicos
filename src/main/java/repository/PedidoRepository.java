package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import models.Pedido;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

}
