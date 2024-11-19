package br.mendonca.testemaven.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.mendonca.testemaven.dao.ExercicioDAO;
import br.mendonca.testemaven.model.entities.Exercicio;
import br.mendonca.testemaven.services.dto.ExercicioDTO;

public class ExercicioService {

    public void register(String nome, int quantidadeSeries, boolean disponivel, boolean oculta) throws SQLException, ClassNotFoundException {
        ExercicioDAO dao = new ExercicioDAO();
        Exercicio exercicio = new Exercicio();
        exercicio.setNome(nome);
        exercicio.setQuantidadeSeries(quantidadeSeries);
        exercicio.setDisponivelNaAcademia(disponivel);
        exercicio.setOculta(oculta); // Novo campo

        dao.register(exercicio);
    }

    public List<ExercicioDTO> listAllExercicios() throws ClassNotFoundException, SQLException {
        List<ExercicioDTO> resp = new ArrayList<>();

        ExercicioDAO dao = new ExercicioDAO();
        List<Exercicio> lista = dao.listAllExercicios();

        for (Exercicio exercicio : lista) {
            resp.add(ExercicioDTO.exercicioMapper(exercicio));
        }

        return resp;
    }

    public List<ExercicioDTO> listExerciciosPaginados(int pagina, int tamanhoPagina) throws ClassNotFoundException, SQLException {
        List<ExercicioDTO> resp = new ArrayList<>();
        ExercicioDAO dao = new ExercicioDAO();
        List<Exercicio> lista = dao.listExerciciosPaginados(pagina, tamanhoPagina);

        for (Exercicio exercicio : lista) {
            resp.add(ExercicioDTO.exercicioMapper(exercicio));
        }
        return resp;
    }

    public List<ExercicioDTO> listExerciciosExcluidosPaginados(int pagina, int tamanhoPagina) throws ClassNotFoundException, SQLException {
        List<ExercicioDTO> resp = new ArrayList<>();
        ExercicioDAO dao = new ExercicioDAO();
        List<Exercicio> lista = dao.listExerciciosExcluidosPaginados(pagina, tamanhoPagina);

        for (Exercicio exercicio : lista) {
            resp.add(ExercicioDTO.exercicioMapper(exercicio));
        }
        return resp;
    }

    public void desativarExercicio(String uuid) throws ClassNotFoundException, SQLException {
        ExercicioDAO dao = new ExercicioDAO();
        dao.desativarExercicio(uuid);
    }
}
