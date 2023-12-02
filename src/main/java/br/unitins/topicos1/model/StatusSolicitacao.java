package br.unitins.topicos1.model;

public enum StatusSolicitacao {
    AGUARDANDO(1, "Aguardando"),
    ACEITA(2, "Aceita"),
    RECUSADA(3, "Recusada");

    private final Integer id;
    private final String label;

    StatusSolicitacao(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Perfil valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Perfil perfil : Perfil.values()) {
            if (perfil.getId().equals(id))
                return perfil;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
}
