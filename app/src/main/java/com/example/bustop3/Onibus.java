package com.example.bustop3;

public class Onibus {
    private int idbus;
    private String placa;
    private String modelo;
    private String empresa;

    public Onibus(String placa, String modelo, String empresa) {
        this.placa = placa;
        this.modelo = modelo;
        this.empresa = empresa;
    }

    public Onibus(){}

    public int getIdbus() {
        return idbus;
    }

    public void setIdbus(int idbus) {
        this.idbus = idbus;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
