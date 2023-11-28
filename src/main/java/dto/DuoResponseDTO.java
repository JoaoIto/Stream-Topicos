package dto;

public record DuoResponseDTO(
        Long id,
        String annotation
) {
    // Construtores e métodos conforme necessário

    public static DuoResponseDTO fromDuo(DuoDTO duoDTO) {
        return new DuoResponseDTO(
                null, // Defina o ID conforme necessário ou remova se não estiver disponível no DTO
                duoDTO.annotation()
        );
    }
}
