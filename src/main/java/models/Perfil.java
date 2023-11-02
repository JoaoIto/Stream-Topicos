package models;

import java.util.Objects;

public class Perfil {
    USER(1, 'user'),
    STREAMER(2, 'admin');

    private final Integer id;
    private final String label;

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfil perfil = (Perfil) o;
        return Objects.equals(id, perfil.id) && Objects.equals(label, perfil.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }
}
