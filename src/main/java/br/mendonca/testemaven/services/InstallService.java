package br.mendonca.testemaven.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

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
				+ "    disponivel_na_academia BOOLEAN NOT NULL,"
				+ "	   oculta BOOLEAN NOT NULL)"
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


	public void populateProfessorTable() throws ClassNotFoundException, SQLException {
		String[] nomes = {"Carlos", "Ana", "João", "Maria", "Lucas", "Fernanda", "Paulo"};
		Random random = new Random();

		for (String nome : nomes) {
			int idade = random.nextInt(40) + 25; // Idade aleatória entre 25 e 64 anos
			boolean estaPresente = random.nextBoolean(); // Aleatório entre true ou false

			String sql = String.format("INSERT INTO professor (name, idade, estaPresente) VALUES ('%s', %d, %b)",
					nome, idade, estaPresente);

			statement(sql);
		}

	}

	public void populateExercicioTable() throws ClassNotFoundException, SQLException {
		String[] exercicios = {"Supino", "Agachamento", "Remada", "Rosca Direta", "Leg Press", "Desenvolvimento", "Elevação Lateral"};
		Random random = new Random();

		for (String nome : exercicios) {
			int quantidadeSeries = random.nextInt(5) + 1;
			boolean disponivelNaAcademia = random.nextBoolean();
			boolean oculta = true	;

			String sql = String.format("INSERT INTO exercicio (nome, quantidade_series, disponivel_na_academia, oculta) VALUES ('%s', %d, %b, %b)",
					nome, quantidadeSeries, disponivelNaAcademia,oculta);


			statement(sql);
		}
	}
	public void populateMaquinaTable() throws ClassNotFoundException, SQLException {
		String[] maquinas = {"Leg Press", "Supino Inclinado", "Cadeira Extensora", "Pulley Costas", "Hack Machine", "Pec Deck", "Bíceps Máquina"};
		Random random = new Random();

		for (String nome : maquinas) {
			float pesoTotal = 50 + random.nextFloat() * 150; // Peso total entre 50kg e 200kg
			boolean quebrada = random.nextBoolean(); // Aleatório entre true ou false

			String sql = String.format(
					"INSERT INTO maquinas (nome, peso_total, quebrada) VALUES ('%s', %.2f, %b)",
					nome, pesoTotal, quebrada
			);

			statement(sql);
		}
	}

}
