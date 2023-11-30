//package br.unitins.topicos1.service;
//
//import br.unitins.topicos1.dto.PedidoDTO;
//import br.unitins.topicos1.dto.PedidoResponseDTO;
//import br.unitins.topicos1.model.Duo;
//import br.unitins.topicos1.model.Pedido;
//import br.unitins.topicos1.model.Usuario;
//import br.unitins.topicos1.repository.DuoRepository;
//import br.unitins.topicos1.repository.PedidoRepository;
//import br.unitins.topicos1.repository.UsuarioRepository;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@ApplicationScoped
//public class PedidoServiceImpl implements PedidoService {
//
//    @Inject
//    DuoRepository duoRepository;
//
//    @Inject
//    UsuarioRepository usuarioRepository;
//
//    @Inject
//    PedidoRepository pedidoRepository;
//
//    @Override
//    @Transactional
//    public PedidoResponseDTO insert(PedidoDTO dto, String login) {
//        Pedido pedido = new Pedido();
//        pedido.setDataHora(LocalDateTime.now());
//
//
//
//        // salvando no banco
//        pedidoRepository.persist(pedido);
//
//        return PedidoResponseDTO.valueOf(pedido);
//    }
//
//    @Override
//    public PedidoResponseDTO findById(Long id) {
//        return PedidoResponseDTO.valueOf(pedidoRepository.findById(id));
//    }
//
//    @Override
//    public List<PedidoResponseDTO> findByAll() {
//        return pedidoRepository.listAll().stream()
//                .map(e -> PedidoResponseDTO.valueOf(e)).toList();
//    }
//
//    @Override
//    public List<PedidoResponseDTO> findByAll(String login) {
//        // Adapte essa parte conforme necessário, dependendo de como você deseja filtrar por usuário.
//        // Aqui estou apenas retornando todos os pedidos.
//        return pedidoRepository.listAll().stream()
//                .map(e -> PedidoResponseDTO.valueOf(e)).toList();
//    }
//}
