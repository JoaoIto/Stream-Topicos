package br.unitins.topicos1.model.Pagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)

public enum TipoDeChavePix {
    CPF(1, "CPF"),
    CNPJ(2, "CNPJ"),
    EMAIL(3, "email"),
    CELULAR(4, "celular"),
    ALEATORIO(5, "aleatorio");

    private final Integer id;
    private final String label;

    TipoDeChavePix(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoDeChavePix valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (TipoDeChavePix tipoDeChavePix : TipoDeChavePix.values()) {
            if (tipoDeChavePix.getId().equals(id))
                return tipoDeChavePix;
        }

        throw new IllegalArgumentException("Id inválida" + id);
    }
}
