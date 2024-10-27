package br.com.fiap.contatos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_contato")

public class Contato {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TBL_CONTATO_SEQ")
    @SequenceGenerator(
            name = "TBL_CONTATO_SEQ",
            sequenceName = "TBL_CONTATO_SEQ",
            allocationSize = 1
    )


    private Long id;

    private String nome;

    private String email;



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
