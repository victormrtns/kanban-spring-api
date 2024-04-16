package br.com.kanban.kanban.dto;

import java.util.List;
import java.util.Optional;

import br.com.kanban.kanban.model.Coluna;

public class createQuadroDto {
    private Long usuario_id;
    private String nome;



    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
