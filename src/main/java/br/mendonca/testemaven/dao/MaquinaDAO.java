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

    public List<Maquina> listAllMachines() throws ClassNotFoundException, SQLException {
        ArrayList<Maquina> lista = new ArrayList<Maquina>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM maquinas");

        while (rs.next()) {
            Maquina maquina = new Maquina();
            maquina.setUuid(rs.getString("uuid"));
            maquina.setNome(rs.getString("nome"));
            maquina.setPesoTotal(rs.getFloat("peso_total"));
            maquina.setQuebrada(rs.getBoolean("quebrada"));

            lista.add(maquina);
        }

        rs.close();

        return lista;
    }

    public List<Maquina> search(String nome) throws ClassNotFoundException, SQLException {
        ArrayList<Maquina> lista = new ArrayList<Maquina>();

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

        return lista;
    }
}
