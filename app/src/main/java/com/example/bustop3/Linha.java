package com.example.bustop3;

public class Linha {
    private int idlinha;
    private String hora_saida;
    private String hora_chegada;
    private int box;

    public Linha(String hora_saida, String hora_chegada, int box) {
        this.hora_saida = hora_saida;
        this.hora_chegada = hora_chegada;
        this.box = box;
    }

    public Linha(){}

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

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }
}
