package service;

import dto.CadastroResponseDTO;
import dto.LoginResponseDTO;
import dto.PedidoDTO;
import dto.PedidoResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.PedidoRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    PedidoRepository repository;

    @Inject
    HashService hashService;


    @Override
    public PedidoResponseDTO insert(PedidoDTO dto) {
        // Implemente a lógica para inserir um pedido e retorne o PedidoResponseDTO correspondente
        return null; // Substitua pelo código real
    }

    @Override
    public List<PedidoResponseDTO> findAll(){
        return repository.listAll().stream().map(PedidoResponseDTO::valueOf).toList();
    }
}
