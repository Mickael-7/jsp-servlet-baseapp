package br.mendonca.testemaven.model.entities;

public class Exercicio {

    private String uuid;
    private String nome;
    private int quantidadeSeries;
    private boolean disponivelNaAcademia;

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

    public int getQuantidadeSeries() {
        return quantidadeSeries;
    }

    public void setQuantidadeSeries(int quantidadeSeries) {
        this.quantidadeSeries = quantidadeSeries;
    }

    public boolean isDisponivelNaAcademia() {
        return disponivelNaAcademia;
    }

    public void setDisponivelNaAcademia(boolean disponivelNaAcademia) {
        this.disponivelNaAcademia = disponivelNaAcademia;
    }
}
