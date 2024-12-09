package com.example.bustop3;

public class Usuario {
    private int idusr;
    private String login;
    private String senha;
    private String cidade;

    public Usuario(int idusr, String login, String senha, String cidade) {
        this.idusr = idusr;
        this.login = login;
        this.senha = senha;
        this.cidade = cidade;
    }

    public Usuario(String login, String senha, String cidade) {
        this.login = login;
        this.senha = senha;
        this.cidade = cidade;
    }

    public int getIdusr() {
        return idusr;
    }

    public void setIdusr(int idusr) {
        this.idusr = idusr;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
