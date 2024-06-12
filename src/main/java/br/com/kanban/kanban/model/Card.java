package br.com.kanban.kanban.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_card;  

    private String nome;
    private String descricao;
    private String status;

    @ManyToOne
    @JoinColumn(name = "quadro_id")
    @JsonManagedReference
    private Quadro quadro; 

   
    public Quadro getQuadro() {
        return quadro;
    }
    public void setQuadro(Quadro quadro) {
        this.quadro = quadro;
    }
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
}