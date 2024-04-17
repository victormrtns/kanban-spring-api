package br.com.kanban.kanban.dto;

import java.util.List;

public class updateQuadroDto {
    private List<Long> usuarios_id;
    private String nome;

    public List<Long> getUsuarios_id() {
        return usuarios_id;
    }

    public void setUsuarios_id(List<Long> usuarios_id) {
        this.usuarios_id = usuarios_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
