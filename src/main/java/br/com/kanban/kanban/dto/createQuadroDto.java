package br.com.kanban.kanban.dto;

import java.util.List;

import br.com.kanban.kanban.model.Coluna;

public class createQuadroDto {
    private List<Coluna> coluna;
    

    public List<Coluna> getColuna() {
        return coluna;
    }

    public void setColuna(List<Coluna> coluna) {
        this.coluna = coluna;
    }
    
}
