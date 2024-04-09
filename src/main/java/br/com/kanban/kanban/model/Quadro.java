package br.com.kanban.kanban.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Quadro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer colunas;

    @OneToMany(mappedBy = "quadro",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Card> cards;

//    @OneToOne(mappedBy = "quadro")
//    private Quadro projeto;

    public Integer getColunas() {
        return colunas;
    }

    public void setColunas(Integer colunas) {
        this.colunas = colunas;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

//    public Quadro getProjeto() {
//        return projeto;
//    }
//
//    public void setProjeto(Quadro projeto) {
//        this.projeto = projeto;
//    }
}
