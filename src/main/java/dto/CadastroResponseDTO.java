package dto;

public record CadastroResponseDTO(
        Long id,
        String nome,
        String email,
        String nickname
) { }
