package br.com.kanban.kanban.dto;

public class cardDto {
    private String nome;
    private String descricao;
    private String status;
    private String type;
    private Long quadroId;

    public Long getQuadroId() {
        return quadroId;
    }

    public void setQuadroId(Long quadroId) {
        this.quadroId = quadroId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
