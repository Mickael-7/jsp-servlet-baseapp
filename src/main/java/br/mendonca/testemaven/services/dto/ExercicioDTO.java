package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.Exercicio;

public class ExercicioDTO {

    private String uuid;
    private String nome;
    private int quantidadeSeries;
    private boolean disponivelNaAcademia;
    private boolean oculta; // Novo campo

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

    public boolean isOculta() {
        return oculta;
    }

    public void setOculta(boolean oculta) {
        this.oculta = oculta;
    }

    public static ExercicioDTO exercicioMapper(Exercicio exercicio) {
        ExercicioDTO dto = new ExercicioDTO();
        dto.setUuid(exercicio.getUuid());
        dto.setNome(exercicio.getNome());
        dto.setQuantidadeSeries(exercicio.getQuantidadeSeries());
        dto.setDisponivelNaAcademia(exercicio.isDisponivelNaAcademia());
        dto.setOculta(exercicio.isOculta()); // Mapeamento do novo campo
        return dto;
    }
}
