package br.com.kanban.kanban.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Card {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_card;  

    private String nome;
    private String descricao;
    private String status;
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
