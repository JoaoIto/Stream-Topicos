package service;

import dto.CadastroDto;
import dto.CadastroResponseDTO;

public interface CadastroService {
    public CadastroResponseDTO insert(CadastroDto dto);

    public CadastroResponseDTO update(CadastroDto dto, Long id);

    public void delete(Long id);
}
