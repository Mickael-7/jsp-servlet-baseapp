package br.mendonca.testemaven.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.mendonca.testemaven.model.entities.Maquina;

public class MaquinaDAO {

    // Método para registrar uma nova máquina no banco de dados
    public void register(Maquina maquina) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO maquinas (nome, peso_total, quebrada) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionPostgres.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maquina.getNome());
            ps.setFloat(2, maquina.getPesoTotal());
            ps.setBoolean(3, maquina.isQuebrada());
            ps.executeUpdate();
        }
    }

    // Método para listar todas as máquinas
    public List<Maquina> listAllMachines() throws ClassNotFoundException, SQLException {
        List<Maquina> lista = new ArrayList<>();
        String sql = "SELECT * FROM maquinas";

        try (Connection conn = ConnectionPostgres.getConexao();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Maquina maquina = new Maquina();
                maquina.setUuid(rs.getString("uuid"));
                maquina.setNome(rs.getString("nome"));
                maquina.setPesoTotal(rs.getFloat("peso_total"));
                maquina.setQuebrada(rs.getBoolean("quebrada"));
                lista.add(maquina);
            }
        }
        return lista;
    }

    // Método para buscar máquinas por nome (com filtro)
    public List<Maquina> search(String nome) throws ClassNotFoundException, SQLException {
        List<Maquina> lista = new ArrayList<>();
        String sql = "SELECT * FROM maquinas WHERE LOWER(nome) LIKE LOWER(?) || '%'";

        try (Connection conn = ConnectionPostgres.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Maquina maquina = new Maquina();
                    maquina.setUuid(rs.getString("uuid"));
                    maquina.setNome(rs.getString("nome"));
                    maquina.setPesoTotal(rs.getFloat("peso_total"));
                    maquina.setQuebrada(rs.getBoolean("quebrada"));
                    lista.add(maquina);
                }
            }
        }
        return lista;
    }

    // Método para listar máquinas de forma paginada
    public List<Maquina> listMachinesPaginated(int pagina, int tamanhoPagina) throws ClassNotFoundException, SQLException {
        List<Maquina> lista = new ArrayList<>();
        String sql = "SELECT * FROM maquinas ORDER BY nome LIMIT ? OFFSET ?";

        try (Connection conn = ConnectionPostgres.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tamanhoPagina);
            ps.setInt(2, pagina * tamanhoPagina);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Maquina maquina = new Maquina();
                    maquina.setUuid(rs.getString("uuid"));
                    maquina.setNome(rs.getString("nome"));
                    maquina.setPesoTotal(rs.getFloat("peso_total"));
                    maquina.setQuebrada(rs.getBoolean("quebrada"));
                    lista.add(maquina);
                }
            }
        }
        return lista;
    }

    // Método para obter o total de máquinas cadastradas
    public int getTotalMaquinas() throws ClassNotFoundException, SQLException {
        String sql = "SELECT COUNT(*) AS total FROM maquinas";
        int total = 0;

        try (Connection conn = ConnectionPostgres.getConexao();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                total = rs.getInt("total");
            }
        }
        return total;
    }
}
