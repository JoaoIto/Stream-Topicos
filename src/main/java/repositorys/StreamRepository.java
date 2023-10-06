package repositorys;

import dto.StreamResponseDTO;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import models.Stream;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class StreamRepository implements PanacheRepository<Stream> {


}

