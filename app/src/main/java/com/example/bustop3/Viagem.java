package com.example.bustop3;

public class Viagem {

    private int idviagem;
    private String cidade_partida;
    private String cidade_chegada;
    private String hora_saida;
    private String hora_chegada;

    public Viagem(String cidade_partida, String cidade_chegada, String hora_saida, String hora_chegada) {
        this.cidade_partida = cidade_partida;
        this.cidade_chegada = cidade_chegada;
        this.hora_saida = hora_saida;
        this.hora_chegada = hora_chegada;
    }

    public int getIdviagem() {
        return idviagem;
    }

    public void setIdviagem(int idviagem) {
        this.idviagem = idviagem;
    }

    public String getCidade_partida() {
        return cidade_partida;
    }

    public void setCidade_partida(String cidade_partida) {
        this.cidade_partida = cidade_partida;
    }

    public String getCidade_chegada() {
        return cidade_chegada;
    }

    public void setCidade_chegada(String cidade_chegada) {
        this.cidade_chegada = cidade_chegada;
    }

    public String getHora_saida() {
        return hora_saida;
    }

    public void setHora_saida(String hora_saida) {
        this.hora_saida = hora_saida;
    }

    public String getHora_chegada() {
        return hora_chegada;
    }

    public void setHora_chegada(String hora_chegada) {
        this.hora_chegada = hora_chegada;
    }
}
