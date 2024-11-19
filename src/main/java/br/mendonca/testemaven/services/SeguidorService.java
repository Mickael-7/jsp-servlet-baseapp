package br.mendonca.testemaven.services;

import br.mendonca.testemaven.dao.SeguidorDAO;
import br.mendonca.testemaven.model.entities.Seguidor;
import br.mendonca.testemaven.services.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public class SeguidorService {

    private final SeguidorDAO seguidorDAO = new SeguidorDAO();

    public void addSeguidor(String seguidorUuid, String seguidoUuid) {
        try {
            Seguidor seguidor = new Seguidor(seguidorUuid, seguidoUuid);
            seguidorDAO.create(seguidor);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao adicionar seguidor: " + e.getMessage(), e);
        }
    }


    public List<Seguidor> listSeguidores(String seguidor) {
        try {
            return seguidorDAO.findBySeguidor(seguidor);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao listar seguidores: " + e.getMessage(), e);
        }
    }

    public void removeSeguidor(String uuid) {
        try {
            seguidorDAO.delete(uuid);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao remover seguidor: " + e.getMessage(), e);
        }
    }
}
