package br.com.kanban.kanban.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Coluna implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_coluna;
    
    private String nome;

    @ManyToOne
    @JoinColumn(name = "quadro_id")
    private Quadro quadro;

    @OneToMany(mappedBy = "coluna", cascade = CascadeType.ALL)
    private List<Card> cards;

 
    
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Long getId_coluna() {
        return id_coluna;
    }

    public void setId_coluna(Long id_coluna) {
        this.id_coluna = id_coluna;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Quadro getQuadro() {
        return quadro;
    }

    public void setQuadro(Quadro quadro) {
        this.quadro = quadro;
    }

 

}
