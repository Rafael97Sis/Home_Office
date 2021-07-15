package com.example.home_office.domain;

public class Endereco {

    private Long idMatriculaPessoa;
    private String rua;
    private String bairro;
    private Long numero;
    private String complemento;

    public Endereco(){
    }

    public Endereco(Long idMatriculaPessoa, String rua, String bairro, Long numero, String complemento) {
        this.idMatriculaPessoa = idMatriculaPessoa;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Long getIdMatriculaPessoa() {
        return idMatriculaPessoa;
    }

    public void setIdMatriculaPessoa(Long idMatriculaPessoa) {
        this.idMatriculaPessoa = idMatriculaPessoa;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "idMatriculaPessoa=" + idMatriculaPessoa +
                ", rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                '}';
    }

}

