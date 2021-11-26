package br.com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static Connection conn;
	
	public static Connection conectar() {
		
		final String url = "jdbc:postgresql://localhost:5432/exerciciousuario";
		final String user = "postgres";
		final String password = "admin";

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Erro ao conectar com o banco de dados");
		}
		return conn;
	}
	
}
