package br.com.kanban.kanban.model;

import jakarta.persistence.*;

@Entity
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer nome;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "quadro_id")
//    private Quadro quadro;
    public Integer getNome() {
        return nome;
    }

    public void setNome(Integer nome) {
        this.nome = nome;
    }

//    public Quadro getQuadro() {
//        return quadro;
//    }
//
//    public void setQuadro(Quadro quadro) {
//        this.quadro = quadro;
//    }
}

