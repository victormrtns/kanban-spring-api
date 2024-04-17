package br.com.kanban.kanban.dto;

//record is a class that have the getters and setters included
public class LoginRequestDto {
    private String email;
    private String senha;

    public LoginRequestDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
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
}

