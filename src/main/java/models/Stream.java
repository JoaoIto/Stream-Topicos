package models;

import jakarta.persistence.*;

@Entity
public class Stream {
    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250)
    private String nome;

    @Column()
    private String nomeUsuario;

    @Column()
    private Float custoStream;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    
    public Float getCustoStream() {
        return custoStream;
    }

    public void setCustoStream(Float custoStream) {
        this.custoStream = custoStream;
    }
    
}
