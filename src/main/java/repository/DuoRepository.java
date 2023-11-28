package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import models.Duo;

@ApplicationScoped
public class DuoRepository implements PanacheRepository<Duo> {
}
