package br.mendonca.testemaven.model.entities;

public class Maquina {
    private String uuid;
    private String nome;
    private float pesoTotal;
    private boolean quebrada;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(float pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public boolean isQuebrada() {
        return quebrada;
    }

    public void setQuebrada(boolean quebrada) {
        this.quebrada = quebrada;
    }
}
