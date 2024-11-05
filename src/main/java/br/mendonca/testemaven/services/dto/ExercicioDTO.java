package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.Exercicio;

public class ExercicioDTO {

    private String nome;
    private int quantidadeSeries;
    private boolean disponivelNaAcademia;

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

    public static ExercicioDTO exercicioMapper(Exercicio exercicio) {
        ExercicioDTO dto = new ExercicioDTO();
        dto.setNome(exercicio.getNome());
        dto.setQuantidadeSeries(exercicio.getQuantidadeSeries());
        dto.setDisponivelNaAcademia(exercicio.isDisponivelNaAcademia());

        return dto;
    }
}
