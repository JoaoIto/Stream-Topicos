package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import models.Stream;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StreamRepository implements PanacheRepository<Stream> {


}

