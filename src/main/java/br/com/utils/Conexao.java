package br.com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public static Connection conectar() {
		Connection conn = null;
		final String url = "jdbc:postgresql://localhost:5432/exerciciousuario";
		final String user = "postgres";
		final String password = "admin";

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Erro ao conectar com o banco de dados");
		}
		return conn;
	}

}
