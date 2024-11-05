package br.mendonca.testemaven.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.mendonca.testemaven.model.entities.Professor;

public class ProfessorDAO {

    public void register(Professor professor) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO professor (uuid, name, idade, estaPresente) VALUES (?, ?, ?, ?)");
        ps.setString(1, professor.getUuid());
        ps.setString(2, professor.getName());
        ps.setInt(3, professor.getIdade());
        ps.setBoolean(4, professor.isEstaPresente());
        ps.execute();
        ps.close();
    }

    public List<Professor> listAllProfessors() throws ClassNotFoundException, SQLException {
        List<Professor> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM professor");

        while (rs.next()) {
            Professor professor = new Professor();
            professor.setUuid(rs.getString("uuid"));
            professor.setName(rs.getString("name"));
            professor.setIdade(rs.getInt("idade"));
            professor.setEstaPresente(rs.getBoolean("estaPresente"));
            lista.add(professor);
        }

        rs.close();
        st.close();

        return lista;
    }

    public Professor searchByUuid(String uuid) throws ClassNotFoundException, SQLException {
        Professor professor = null;

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM professor WHERE uuid = ?");
        ps.setString(1, uuid);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            professor = new Professor();
            professor.setUuid(rs.getString("uuid"));
            professor.setName(rs.getString("name"));
            professor.setIdade(rs.getInt("idade"));
            professor.setEstaPresente(rs.getBoolean("estaPresente"));
        }

        rs.close();
        ps.close();

        return professor;
    }

    public List<Professor> searchByName(String name) throws ClassNotFoundException, SQLException {
        List<Professor> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM professor WHERE LOWER(name) LIKE LOWER(?) || '%'");
        ps.setString(1, name);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Professor professor = new Professor();
            professor.setUuid(rs.getString("uuid"));
            professor.setName(rs.getString("name"));
            professor.setIdade(rs.getInt("idade"));
            professor.setEstaPresente(rs.getBoolean("estaPresente"));
            lista.add(professor);
        }

        rs.close();
        ps.close();

        return lista;
    }
}
