package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.Maquina;

public class MaquinaDTO {
    private String uuid;

    private String nome;
    private float pesoTotal;
    private boolean quebrada;


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

    public static MaquinaDTO maquinaMapper(Maquina maquina) {
        MaquinaDTO dto = new MaquinaDTO();
        dto.setNome(maquina.getNome());
        dto.setPesoTotal(maquina.getPesoTotal());
        dto.setQuebrada(maquina.isQuebrada());

        return dto;
    }
}
