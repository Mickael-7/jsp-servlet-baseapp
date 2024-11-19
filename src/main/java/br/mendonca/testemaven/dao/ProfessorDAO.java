package br.mendonca.testemaven.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.mendonca.testemaven.model.entities.Professor;

public class ProfessorDAO {

    public void register(Professor professor) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO professor (name, idade, estaPresente, estaAtivo) VALUES (?, ?, ?, ?)");
        ps.setString(1, professor.getName());
        ps.setInt(2, professor.getIdade());
        ps.setBoolean(3, professor.isEstaPresente());
        ps.setBoolean(4, professor.isEstaAtivo());
        ps.execute();
        ps.close();
    }

    // Método para listar todos os professores
    public List<Professor> listAllProfessors() throws ClassNotFoundException, SQLException {
        List<Professor> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM professor");

        while (rs.next()) {
            Professor professor = new Professor();
            professor.setName(rs.getString("name"));
            professor.setIdade(rs.getInt("idade"));
            professor.setEstaPresente(rs.getBoolean("estaPresente"));
            professor.setEstaAtivo(rs.getBoolean("estaAtivo"));
            lista.add(professor);
        }

        rs.close();
        st.close();

        return lista;
    }

    // Método para buscar um professor por ID
    public Professor searchById(String id) throws ClassNotFoundException, SQLException {
        Professor professor = null;

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM professor WHERE uuid = ?");
        ps.setObject(1, UUID.fromString(id));

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            professor = new Professor();
            professor.setName(rs.getString("name"));
            professor.setIdade(rs.getInt("idade"));
            professor.setEstaPresente(rs.getBoolean("estaPresente"));
            professor.setEstaAtivo(rs.getBoolean("estaAtivo"));
        }

        rs.close();
        ps.close();

        return professor;
    }

    // Método para buscar professores por nome
    public List<Professor> searchByName(String name) throws ClassNotFoundException, SQLException {
        List<Professor> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM professor WHERE LOWER(name) LIKE LOWER(?) || '%'");
        ps.setString(1, name);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Professor professor = new Professor();
            professor.setName(rs.getString("name"));
            professor.setIdade(rs.getInt("idade"));
            professor.setEstaPresente(rs.getBoolean("estaPresente"));
            professor.setEstaAtivo(rs.getBoolean("estaAtivo"));
            lista.add(professor);
        }

        rs.close();
        ps.close();

        return lista;
    }

    // Método para listar professores de forma paginada
    public List<Professor> listProfessoresPaginados(int pagina, int tamanhoPagina) throws ClassNotFoundException, SQLException {
        List<Professor> lista = new ArrayList<>();
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String sql = "SELECT * FROM professor WHERE estaAtivo = true ORDER BY name LIMIT ? OFFSET ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, tamanhoPagina);
        ps.setInt(2, pagina * tamanhoPagina);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Professor professor = new Professor();
            professor.setUuid(rs.getString("uuid"));
            professor.setName(rs.getString("name"));
            professor.setIdade(rs.getInt("idade"));
            professor.setEstaPresente(rs.getBoolean("estaPresente"));
            professor.setEstaAtivo(rs.getBoolean("estaAtivo"));
            lista.add(professor);
        }

        rs.close();
        ps.close();

        return lista;
    }

    public void desativarProfessor(String id) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("UPDATE professor SET estaAtivo = false WHERE uuid = ?");
        ps.setObject(1, UUID.fromString(id));
        ps.execute();
        ps.close();
    }

    public List<Professor> listAllProfessorDesativadoPaginado(int pageNumber, int pageSize) throws SQLException, ClassNotFoundException {
        ArrayList<Professor> lista = new ArrayList<>();
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        int offset = (pageNumber - 1) * pageSize;

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM professor WHERE estaAtivo = false LIMIT ? OFFSET ?");
        ps.setInt(1, pageSize);
        ps.setInt(2, offset);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Professor professor = new Professor();
            professor.setUuid(rs.getString("uuid"));
            professor.setName(rs.getString("name"));
            professor.setIdade(rs.getInt("idade"));
            professor.setEstaPresente(rs.getBoolean("estaPresente"));
            professor.setEstaAtivo(rs.getBoolean("estaAtivo"));
            lista.add(professor);
        }

        rs.close();
        ps.close();

        return lista;
    }

}
