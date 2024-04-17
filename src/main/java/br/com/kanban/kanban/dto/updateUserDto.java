package br.com.kanban.kanban.dto;

import java.util.List;

public class updateUserDto {
    private String nome;
    
    private String email;
    
    private String senha;

    private List<Long> quadros;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Long> getQuadros() {
        return quadros;
    }

    public void setQuadros(List<Long> quadros) {
        this.quadros = quadros;
    }

    
}
