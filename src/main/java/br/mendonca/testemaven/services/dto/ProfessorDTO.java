package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.Professor;

public class ProfessorDTO {

    private String uuid;
    private String name;
    private Integer idade;
    private boolean estaPresente;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public boolean isEstaPresente() {
        return estaPresente;
    }

    public void setEstaPresente(boolean estaPresente) {
        this.estaPresente = estaPresente;
    }

    public static ProfessorDTO professorMapper(Professor professor) {
        ProfessorDTO dto = new ProfessorDTO();
        dto.setUuid(professor.getUuid());
        dto.setName(professor.getName());
        dto.setIdade(professor.getIdade());
        dto.setEstaPresente(professor.isEstaPresente());

        return dto;
    }
}
