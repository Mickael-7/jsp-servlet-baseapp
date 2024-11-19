package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.Maquina;

public class MaquinaDTO {
    private String uuid;

    private String nome;
    private float pesoTotal;
    private boolean quebrada;
    private boolean oculta;

    public String getNome() {
        return nome;
    }

    public String getUuid() {
        return uuid;
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

    public boolean isOculta() {
        return oculta;
    }

    public void setOculta(boolean oculta) {
        this.oculta = oculta;
    }

    public static MaquinaDTO maquinaMapper(Maquina maquina) {
        MaquinaDTO dto = new MaquinaDTO();
        dto.setNome(maquina.getNome());
        dto.setPesoTotal(maquina.getPesoTotal());
        dto.setQuebrada(maquina.isQuebrada());
        dto.setOculta(maquina.isOculta());
        return dto;
    }
}
