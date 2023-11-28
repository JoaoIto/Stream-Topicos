package service;

import dto.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import models.Cadastro;
import models.Duo;
import models.ItemPedido;
import models.Pedido;
import repository.CadastroRepository;
import repository.DuoRepository;
import repository.PedidoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    CadastroRepository cadastroRepository;

    @Inject
    PedidoRepository repository;

    @Inject
    DuoRepository duoRepository;

    @Inject
    HashService hashService;


    @Override
    @Transactional
    public PedidoResponseDTO insert(PedidoDTO dto, String login) {
        Pedido pedido = new Pedido();
        pedido.setDataHora(LocalDateTime.now());

        // calculo do total do pedido
        Double total = 0.0;
        for (ItemPedidoDTO itemDto : dto.games()) {
            total += (itemDto.preco() * itemDto.quantidade());
        }
        pedido.setTotalPedido(total);

        // adicionando os itens do pedido
        pedido.setGames(new ArrayList<ItemPedido>());
        for (ItemPedidoDTO itemDto : dto.games()) {
            ItemPedido item = new ItemPedido();
            item.setPreco(itemDto.preco());
            item.setQuantidade(itemDto.quantidade());
            item.setPedido(pedido);
            Duo duo = duoRepository.findById(itemDto.idPedido());
            item.setDuo(duo);

            pedido.getGames().add(item);
        }
        // buscando o cadastro pelo login
        Cadastro cadastro = cadastroRepository.findByLogin(login);
        pedido.setUsuario(cadastro);

        // salvando no banco
        repository.persist(pedido);

        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    public List<PedidoResponseDTO> findAll(){
        return repository.listAll().stream().map(PedidoResponseDTO::valueOf).toList();
    }
}
