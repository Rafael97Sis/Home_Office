package com.example.home_office.domain;

public class Pessoa {

    private Long matricula;
    private String nome;

    public Pessoa(){

    }

    public Pessoa(Long matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "matricula=" + matricula +
                ", nome='" + nome + '\'' +
                '}';
    }
}
