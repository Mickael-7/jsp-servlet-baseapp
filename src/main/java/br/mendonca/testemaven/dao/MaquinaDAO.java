package br.mendonca.testemaven.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.mendonca.testemaven.model.entities.Maquina;

public class MaquinaDAO {

    // Método para registrar uma nova máquina
    public void register(Maquina maquina) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO maquinas (nome, peso_total, quebrada) values (?,?,?)");
        ps.setString(1, maquina.getNome());
        ps.setFloat(2, maquina.getPesoTotal());
        ps.setBoolean(3, maquina.isQuebrada());
        ps.execute();
        ps.close();
    }

    // Método para listar todas as máquinas
    public List<Maquina> listAllMachines() throws ClassNotFoundException, SQLException {
        List<Maquina> lista = new ArrayList<>();
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM maquinas");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Maquina maquina = new Maquina();
            maquina.setUuid(rs.getString("uuid"));
            maquina.setNome(rs.getString("nome"));
            maquina.setPesoTotal(rs.getFloat("peso_total"));
            maquina.setQuebrada(rs.getBoolean("quebrada"));
            lista.add(maquina);
        }

        rs.close();
        ps.close();

        return lista;
    }

    // Método para listar máquinas de forma paginada
    public List<Maquina> listMachinesPaginated(int pagina, int tamanhoPagina) throws ClassNotFoundException, SQLException {
        List<Maquina> lista = new ArrayList<>();
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String sql = "SELECT * FROM maquinas ORDER BY nome LIMIT ? OFFSET ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, tamanhoPagina); // Tamanho da página (LIMIT)
        ps.setInt(2, pagina * tamanhoPagina); // Deslocamento da página (OFFSET)

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Maquina maquina = new Maquina();
            maquina.setUuid(rs.getString("uuid"));
            maquina.setNome(rs.getString("nome"));
            maquina.setPesoTotal(rs.getFloat("peso_total"));
            maquina.setQuebrada(rs.getBoolean("quebrada"));
            lista.add(maquina);
        }

        rs.close();
        ps.close();

        return lista;
    }

    // Método para contar o total de máquinas
    public int getTotalMaquinas() throws ClassNotFoundException, SQLException {
        int total = 0;
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        // Contando o total de máquinas na tabela
        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM maquinas");
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            total = rs.getInt(1); // Obtém o número total de máquinas
        }

        rs.close();
        ps.close();

        return total;
    }

    // Método para pesquisar máquinas por nome
    public List<Maquina> search(String nome) throws ClassNotFoundException, SQLException {
        List<Maquina> lista = new ArrayList<>();
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM maquinas WHERE LOWER(nome) LIKE LOWER(?) || '%'");
        ps.setString(1, nome);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Maquina maquina = new Maquina();
            maquina.setUuid(rs.getString("uuid"));
            maquina.setNome(rs.getString("nome"));
            maquina.setPesoTotal(rs.getFloat("peso_total"));
            maquina.setQuebrada(rs.getBoolean("quebrada"));
            lista.add(maquina);
        }

        rs.close();
        ps.close();

        return lista;
    }
}
