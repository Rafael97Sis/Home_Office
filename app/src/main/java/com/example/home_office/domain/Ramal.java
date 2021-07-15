package com.example.home_office.domain;

public class Ramal {

    private Long idMatriculaPessoa;
    private Long NumeroRamal;

    public Ramal(){

    }

    public Ramal(Long idMatriculaPessoa, Long numeroRamal) {
        this.idMatriculaPessoa = idMatriculaPessoa;
        NumeroRamal = numeroRamal;
    }

    public Ramal(Long ramal) {
    }

    public Long getIdMatriculaPessoa() {
        return idMatriculaPessoa;
    }

    public void setIdMatriculaPessoa(Long idMatriculaPessoa) {
        this.idMatriculaPessoa = idMatriculaPessoa;
    }

    public Long getNumeroRamal() {
        return NumeroRamal;
    }

    public void setNumeroRamal(Long numeroRamal) {
        NumeroRamal = numeroRamal;
    }

    @Override
    public String toString() {
        return "Ramal{" +
                "idMatriculaPessoa=" + idMatriculaPessoa +
                ", NumeroRamal=" + NumeroRamal +
                '}';
    }
}
