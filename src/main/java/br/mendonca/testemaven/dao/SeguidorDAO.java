package br.mendonca.testemaven.dao;

import br.mendonca.testemaven.model.entities.Seguidor;
import br.mendonca.testemaven.services.dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SeguidorDAO {

    public void create(Seguidor seguidor) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO seguidores (seguidor_uuid, seguido_uuid) VALUES (?, ?)";

        if (seguidor.getSeguidorUuid() == null || seguidor.getSeguidoUuid() == null) {
            throw new IllegalArgumentException("UUIDs não podem ser nulos.");
        }

        try (Connection conn = ConnectionPostgres.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, seguidor.getSeguidorUuid());
            ps.setString(2, seguidor.getSeguidoUuid());
            ps.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir seguidor na tabela 'seguidores': " + e.getMessage(), e);
        }
    }

    public List<Seguidor> findAll() throws ClassNotFoundException, SQLException {
        List<Seguidor> seguidores = new ArrayList<>();
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String sql = "SELECT * FROM seguidores";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Seguidor seguidor = new Seguidor(
                        rs.getString("seguidor_uuid"),
                        rs.getString("seguido_uuid")
                );
                seguidores.add(seguidor);
            }
        }

        return seguidores;
    }


    public List<Seguidor> findBySeguidor(String seguidorUuid) throws ClassNotFoundException, SQLException {
        List<Seguidor> seguidores = new ArrayList<>();
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String sql = "SELECT * FROM seguidores WHERE seguidor_uuid = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, seguidorUuid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Seguidor seguidor = new Seguidor(
                            rs.getString("seguidor_uuid"),
                            rs.getString("seguido_uuid")
                    );
                    seguidores.add(seguidor);
                }
            }
        }

        return seguidores;
    }

    public void delete(String uuid) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String sql = "DELETE FROM seguidores WHERE uuid = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, UUID.fromString(uuid));
            ps.execute();
        }
    }

    public void deleteBySeguidor(String seguidorUuid) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String sql = "DELETE FROM seguidores WHERE seguidor_uuid = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, seguidorUuid);
            ps.execute();
        }
    }
}
