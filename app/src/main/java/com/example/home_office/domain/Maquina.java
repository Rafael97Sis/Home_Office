package com.example.home_office.domain;

public class Maquina {

    private Long idMatriculaPessoa;
    private Long NumeroPa;
    private String Modelo;

    public Maquina(){

    }

    public Maquina(Long idMatriculaPessoa, Long numeroPa, String modelo) {
        this.idMatriculaPessoa = idMatriculaPessoa;
        NumeroPa = numeroPa;
        Modelo = modelo;
    }

    public Long getIdMatriculaPessoa() {
        return idMatriculaPessoa;
    }

    public void setIdMatriculaPessoa(Long idMatriculaPessoa) {
        this.idMatriculaPessoa = idMatriculaPessoa;
    }

    public Long getNumeroPa() {
        return NumeroPa;
    }

    public void setNumeroPa(Long numeroPa) {
        NumeroPa = numeroPa;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    @Override
    public String toString() {
        return "Maquina{" +
                "idMatriculaPessoa=" + idMatriculaPessoa +
                ", NumeroPa=" + NumeroPa +
                ", Modelo='" + Modelo + '\'' +
                '}';
    }
}
