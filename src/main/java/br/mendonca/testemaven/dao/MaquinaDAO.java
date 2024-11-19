package br.mendonca.testemaven.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.mendonca.testemaven.model.entities.Maquina;

public class MaquinaDAO {

    // Método para registrar uma nova máquina
    public void register(Maquina maquina) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn
                .prepareStatement("INSERT INTO maquina (nome, peso_total, quebrada, oculta) values (?,?,?,?)");
        ps.setString(1, maquina.getNome());
        ps.setFloat(2, maquina.getPesoTotal());
        ps.setBoolean(3, maquina.isQuebrada());
        ps.setBoolean(4, maquina.isOculta());
        ps.execute();
        ps.close();
    }

    // Método para listar todas as máquinas
    public List<Maquina> listAllMachines() throws ClassNotFoundException, SQLException {
        List<Maquina> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM maquina");

        while (rs.next()) {
            Maquina maquina = new Maquina();
            maquina.setUuid(rs.getString("uuid"));
            maquina.setNome(rs.getString("nome"));
            maquina.setPesoTotal(rs.getFloat("peso_total"));
            maquina.setQuebrada(rs.getBoolean("quebrada"));
            maquina.setOculta(rs.getBoolean("oculta"));
            lista.add(maquina);
        }

        rs.close();
        st.close();

        return lista;
    }

    // Método para listar máquinas de forma paginada
    public List<Maquina> listMachinesPaginated(int pagina, int tamanhoPagina)
            throws ClassNotFoundException, SQLException {
        List<Maquina> lista = new ArrayList<>();
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String sql = "SELECT * FROM maquina WHERE oculta = true ORDER BY nome LIMIT ? OFFSET ?";
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
            maquina.setOculta(rs.getBoolean("oculta"));
            lista.add(maquina);
        }

        rs.close();
        ps.close();

        return lista;
    }

    public List<Maquina> listMaquinasExcluidosPaginados(int pagina, int tamanhoPagina)
            throws ClassNotFoundException, SQLException {
        List<Maquina> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String sql = "SELECT * FROM maquina WHERE oculta = false ORDER BY nome LIMIT ? OFFSET ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, tamanhoPagina);
        ps.setInt(2, pagina * tamanhoPagina);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Maquina maquina = new Maquina();
            maquina.setUuid(rs.getString("uuid"));
            maquina.setNome(rs.getString("nome"));
            maquina.setPesoTotal(rs.getFloat("peso_total"));
            maquina.setQuebrada(rs.getBoolean("quebrada"));
            maquina.setOculta(rs.getBoolean("oculta"));
            lista.add(maquina);
        }

        rs.close();
        ps.close();

        return lista;
    }

    public void desativarMaquina(String uuid) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("UPDATE maquina SET oculta = false WHERE uuid = ?");
        ps.setObject(1, UUID.fromString(uuid));
        ps.execute();
        ps.close();
    }

    // Método para contar o total de máquinas
    public int getTotalMaquinas() throws ClassNotFoundException, SQLException {
        int total = 0;
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        // Contando o total de máquinas na tabela
        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM maquina");
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

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM maquina WHERE LOWER(nome) LIKE LOWER(?) || '%'");
        ps.setString(1, nome);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Maquina maquina = new Maquina();
            maquina.setUuid(rs.getString("uuid"));
            maquina.setNome(rs.getString("nome"));
            maquina.setPesoTotal(rs.getFloat("peso_total"));
            maquina.setQuebrada(rs.getBoolean("quebrada"));
            maquina.setOculta(rs.getBoolean("oculta"));
            lista.add(maquina);
        }

        rs.close();
        ps.close();

        return lista;
    }
}
