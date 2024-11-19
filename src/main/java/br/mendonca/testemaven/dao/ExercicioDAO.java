package br.mendonca.testemaven.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.mendonca.testemaven.model.entities.Exercicio;

public class ExercicioDAO {

    public void register(Exercicio exercicio) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO exercicio (nome, quantidade_series, disponivel_na_academia, oculta) VALUES (?, ?, ?, ?)"
        );
        ps.setString(1, exercicio.getNome());
        ps.setInt(2, exercicio.getQuantidadeSeries());
        ps.setBoolean(3, exercicio.isDisponivelNaAcademia());
        ps.setBoolean(4, exercicio.isOculta()); // Novo campo adicionado
        ps.execute();
        ps.close();
    }

    public List<Exercicio> listAllExercicios() throws ClassNotFoundException, SQLException {
        List<Exercicio> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM exercicio");

        while (rs.next()) {
            Exercicio exercicio = new Exercicio();
            exercicio.setUuid(rs.getString("uuid"));
            exercicio.setNome(rs.getString("nome"));
            exercicio.setQuantidadeSeries(rs.getInt("quantidade_series"));
            exercicio.setDisponivelNaAcademia(rs.getBoolean("disponivel_na_academia"));
            exercicio.setOculta(rs.getBoolean("oculta")); // Novo campo mapeado
            lista.add(exercicio);
        }

        rs.close();
        st.close();

        return lista;
    }

    public List<Exercicio> listExerciciosPaginados(int pagina, int tamanhoPagina) throws ClassNotFoundException, SQLException {
        List<Exercicio> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String sql = "SELECT * FROM exercicio WHERE oculta = true ORDER BY nome LIMIT ? OFFSET ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, tamanhoPagina);
        ps.setInt(2, pagina * tamanhoPagina);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Exercicio exercicio = new Exercicio();
            exercicio.setUuid(rs.getString("uuid"));
            exercicio.setNome(rs.getString("nome"));
            exercicio.setQuantidadeSeries(rs.getInt("quantidade_series"));
            exercicio.setDisponivelNaAcademia(rs.getBoolean("disponivel_na_academia"));
            exercicio.setOculta(rs.getBoolean("oculta")); // Novo campo mapeado
            lista.add(exercicio);
        }

        rs.close();
        ps.close();

        return lista;
    }

    public List<Exercicio> listExerciciosExcluidosPaginados(int pagina, int tamanhoPagina) throws ClassNotFoundException, SQLException {
        List<Exercicio> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String sql = "SELECT * FROM exercicio WHERE oculta = false ORDER BY nome LIMIT ? OFFSET ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, tamanhoPagina);
        ps.setInt(2, pagina * tamanhoPagina);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Exercicio exercicio = new Exercicio();
            exercicio.setUuid(rs.getString("uuid"));
            exercicio.setNome(rs.getString("nome"));
            exercicio.setQuantidadeSeries(rs.getInt("quantidade_series"));
            exercicio.setDisponivelNaAcademia(rs.getBoolean("disponivel_na_academia"));
            exercicio.setOculta(rs.getBoolean("oculta"));
            lista.add(exercicio);
        }

        rs.close();
        ps.close();

        return lista;
    }

    public Exercicio searchByName(String nome) throws ClassNotFoundException, SQLException {
        Exercicio exercicio = null;

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM exercicio WHERE nome = ?");
        ps.setString(1, nome);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            exercicio = new Exercicio();
            exercicio.setUuid(rs.getString("uuid"));
            exercicio.setNome(rs.getString("nome"));
            exercicio.setQuantidadeSeries(rs.getInt("quantidade_series"));
            exercicio.setDisponivelNaAcademia(rs.getBoolean("disponivel_na_academia"));
            exercicio.setOculta(rs.getBoolean("oculta"));
        }

        rs.close();
        ps.close();

        return exercicio;
    }

    public List<Exercicio> searchByAvailability(boolean disponivel) throws ClassNotFoundException, SQLException {
        List<Exercicio> lista = new ArrayList<>();

        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM exercicio WHERE disponivel_na_academia = ?");
        ps.setBoolean(1, disponivel);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Exercicio exercicio = new Exercicio();
            exercicio.setUuid(rs.getString("uuid"));
            exercicio.setNome(rs.getString("nome"));
            exercicio.setQuantidadeSeries(rs.getInt("quantidade_series"));
            exercicio.setDisponivelNaAcademia(rs.getBoolean("disponivel_na_academia"));
            exercicio.setOculta(rs.getBoolean("oculta"));
            lista.add(exercicio);
        }

        rs.close();
        ps.close();

        return lista;
    }

    public void desativarExercicio(String uuid) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        PreparedStatement ps = conn.prepareStatement("UPDATE exercicio SET oculta = false WHERE uuid = ?");
        ps.setObject(1, UUID.fromString(uuid));
        ps.execute();
        ps.close();
    }

}
