package br.mendonca.testemaven.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.mendonca.testemaven.dao.ConnectionPostgres;

public class InstallService {

	private void statement(String sql) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		Statement st = conn.createStatement();
		st.executeUpdate(sql);
		st.close();
	}

	public void testConnection() throws ClassNotFoundException, SQLException {
		ConnectionPostgres.getConexao();
	}

	public void deleteUserTable() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE IF EXISTS users");
	}

	public void deleteExercicioTable() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE IF EXISTS exercicio");
	}



	public void createUserTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE users ("
				+ "    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
				+ "    name VARCHAR(255) NOT NULL,"
				+ "    email VARCHAR(255) NOT NULL,"
				+ "    password VARCHAR(255) NOT NULL)"
		);


	}
	public void deleteProfessorTable() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE IF EXISTS professor");
	}


	public void createProfessorTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE professor ("
				+ "    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
				+ "    name VARCHAR(255) NOT NULL,"
				+ "    idade INTEGER NOT NULL,"
				+ "    estaPresente BOOLEAN NOT NULL"
				+ ")");
	}
	public void createExercicioTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE exercicio ("
				+ "    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
				+ "    nome VARCHAR(255) NOT NULL,"
				+ "    quantidade_series INT NOT NULL,"
				+ "    disponivel_na_academia BOOLEAN NOT NULL)"
		);
	}

	public void createMaquinaTable() throws ClassNotFoundException, SQLException {
		statement("CREATE TABLE maquinas ("
				+ "    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
				+ "    nome VARCHAR(100) NOT NULL,"
				+ "    peso_total FLOAT NOT NULL,"
				+ "    quebrada BOOLEAN NOT NULL)"
		);
	}

	public void deleteMaquinaTable() throws ClassNotFoundException, SQLException {
		statement("DROP TABLE IF EXISTS maquinas");

	}
}
