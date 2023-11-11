package models;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;

@Entity
public class Duo {

    private Double horasGame;
    private Double preco;
    private String annotation;
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    public Double getHorasGame() {
        return horasGame;
    }

    public void setHorasGame(Double horasGame) {
        this.horasGame = horasGame;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}