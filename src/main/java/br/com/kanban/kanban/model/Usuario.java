package br.com.kanban.kanban.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.UniqueConstraint;

@Entity
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  

    private String nome;
    
    private String email;
    
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "usuarios-quadros", 
                uniqueConstraints = @UniqueConstraint (
                    columnNames = {"usuario_id","quadro_id"}, 
                    name = "unique_user_quadro"
                ), 
	            joinColumns = @JoinColumn(name = "usuario_id", 
                    referencedColumnName = "id", 
                    table = "usuario", 
                    unique = false
                ), 
	            inverseJoinColumns = @JoinColumn (
                    name = "quadro_id", 
                    referencedColumnName = "id", 
                    table = "quadro", 
                    unique = false
                    //updatable = false,
                )
            )
    private List<Quadro> quadros;
    
    public List<Quadro> getQuadros() {
        return quadros;
    }

    public void setQuadros(List<Quadro> quadros) {
        this.quadros = quadros;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
