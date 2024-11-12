package br.mendonca.testemaven.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.mendonca.testemaven.dao.ProfessorDAO;
import br.mendonca.testemaven.model.entities.Professor;
import br.mendonca.testemaven.services.dto.ProfessorDTO;

public class ProfessorService {

    public void register(String name, Integer idade, boolean estaPresente) throws ClassNotFoundException, SQLException {
        ProfessorDAO dao = new ProfessorDAO();

        Professor professor = new Professor();
        professor.setName(name);
        professor.setIdade(idade);
        professor.setEstaPresente(estaPresente);

        dao.register(professor);
    }

    public List<ProfessorDTO> listAllProfessors() throws ClassNotFoundException, SQLException {
        List<ProfessorDTO> resp = new ArrayList<>();

        ProfessorDAO dao = new ProfessorDAO();
        List<Professor> lista = dao.listAllProfessors();

        for (Professor professor : lista) {
            resp.add(ProfessorDTO.professorMapper(professor));
        }

        return resp;
    }

    public ProfessorDTO searchProfessorByUuid(UUID uuid) throws ClassNotFoundException, SQLException {
        ProfessorDAO dao = new ProfessorDAO();
        Professor professor = dao.searchByUuid(uuid);

        return professor != null ? ProfessorDTO.professorMapper(professor) : null;
    }

    public List<ProfessorDTO> searchProfessorsByName(String name) throws ClassNotFoundException, SQLException {
        List<ProfessorDTO> resp = new ArrayList<>();

        ProfessorDAO dao = new ProfessorDAO();
        List<Professor> lista = dao.searchByName(name);

        for (Professor professor : lista) {
            resp.add(ProfessorDTO.professorMapper(professor));
        }

        return resp;
    }
}
