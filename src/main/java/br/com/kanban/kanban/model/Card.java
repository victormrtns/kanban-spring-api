package br.com.kanban.kanban.model;

import jakarta.persistence.*;

@Entity
public class Card {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_card;  

    private String nome;
    private String descricao;
    private String status;

    @ManyToOne
    @JoinColumn(name ="quadro_id")
    private Quadro quadro;
    public Long getId_card() {
        return id_card;
    }
    public void setId_card(Long id_card) {
        this.id_card = id_card;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Quadro getQuadro() {
        return quadro;
    }

    public void setQuadro(Quadro quadro) {
        this.quadro = quadro;
    }
}
