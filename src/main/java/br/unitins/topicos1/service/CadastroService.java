package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CadastroDTO;
import br.unitins.topicos1.dto.CadastroResponseDTO;
import jakarta.validation.Valid;

public interface CadastroService {
    public CadastroResponseDTO insert(@Valid CadastroDTO dto);
}
