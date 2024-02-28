package br.unitins.topicos1.services.Cadastro;

import br.unitins.topicos1.dto.Cadastro.CadastroDTO;
import br.unitins.topicos1.dto.Cadastro.CadastroResponseDTO;
import jakarta.validation.Valid;

public interface CadastroService {
    public CadastroResponseDTO insert(@Valid CadastroDTO dto);
}
