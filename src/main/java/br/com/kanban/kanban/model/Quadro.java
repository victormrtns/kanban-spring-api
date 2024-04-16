package br.com.kanban.kanban.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Quadro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 


    private String nome;
    @OneToMany(mappedBy = "quadro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> card;


   

    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "usuarios-quadros", 
                uniqueConstraints = @UniqueConstraint (
                columnNames = {"usuario_id","quadro_id"}, 
                name = "unique_user_quadro"
                ), 
	            joinColumns = @JoinColumn(name = "quadro_id", 
                referencedColumnName = "id", 
                table = "quadro", 
                unique = false
                ), 
	            inverseJoinColumns = @JoinColumn (
                    name = "usuario_id", 
                    referencedColumnName = "id", 
                    table = "usuario", 
                    unique = false
                    
                )
            )    
	private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<Card> getCard() {
        return card;
    }

    public void setCard(List<Card> card) {
        this.card = card;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
   
}
