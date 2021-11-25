package br.com.telefone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.utils.Conexao;

/**
 * Classe responsavel por conter os metodos de CRUD no banco de dados de {@link Telefone} 
 */
public class TelefoneRepository {

	private Connection conn;

	public TelefoneRepository() {
		if (conn == null) {
			conn = Conexao.conectar();
		}
	}

	/**
	 * Metodo responsavel por incluir um telefone no banco de dados
	 * O metodo recebe um objeto telefone com as propriedades
	 * Integer Ddd, String Numero, String tipoTelefone e Integer usuarioId
	 * 
	 * @param telefone com ddd, numero, tipo e usuario_id
	 */
	public void incluirTelefone(Telefone telefone) {
		final String sql = "INSERT INTO Telefone " + "(ddd, numero, tipo, usuario_id) " + "VALUES (?, ?, ?, ?);";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, telefone.getDdd());
			stmt.setString(2, telefone.getNumero());
			stmt.setString(3, telefone.getTipo());
			stmt.setInt(4, telefone.getUsuarioId());
			stmt.execute();
		} catch (Exception e) {
			System.out.println("Nao foi possivel incluir o telefone");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo responsavel por incluir um telefone no banco de dados
	 * 
	 * @param ddd Telefone a ser incluido
	 * @param numero Telefone a ser incluido
	 * @param tipoTelefone Telefone a ser incluido
	 * @param usuarioId Telefone a ser incluido
	 */ 
	public void incluirTelefone(int ddd, String numero, String tipoTelefone, int usuarioId) {
		String sql = null;
		sql = "INSERT INTO TELEFONE " + "(ddd, numero, tipo, usuario_id) " + "VALUES (?, ?, ?, ?);";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ddd);
			stmt.setString(2, numero);
			stmt.setString(3, tipoTelefone);
			stmt.setInt(4, usuarioId);
			stmt.execute();

		} catch (Exception e) {
			System.out.println("Falha ao Incluir Telefone");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo responsavel por consultar um unico usuario no banco de dados
	 * 
	 * 
	 * @param idTelefone a ser consultado
	 * @return Telefone da consulta ou nulo caso nao ache 
	 */
	public Telefone consultarUnicoTelefone(int idTelefone) {
		final String sql = "SELECT * FROM Telefone where id = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idTelefone);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Telefone telefone = new Telefone();
				telefone.setId(rs.getInt("id"));
				telefone.setDdd(rs.getInt("ddd"));
				telefone.setNumero(rs.getString("numero"));
				telefone.setTipo(rs.getString("tipo"));
				return telefone;	
			}
		} catch (Exception e) {
			System.out.println("Nao foi possivel consultar Telefone");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo responsavel por consultar todos os telefones no banco de dados
	 * 
	 * 
	 * @return List de todos os telefones da consulta
	 */
	public List<Telefone> todosTelefones() {
		List<Telefone> telefones = new ArrayList<Telefone>();
		final String sql = "SELECT * FROM telefone ORDER BY id;";

		try {
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Telefone telefone = new Telefone();
				telefone.setId(rs.getInt("id"));
				telefone.setDdd(rs.getInt("ddd"));
				telefone.setNumero(rs.getString("numero"));
				telefone.setTipo(rs.getString("tipo"));
				telefone.setUsuarioId(rs.getInt("usuario_id"));
				telefones.add(telefone);
			}
		} catch (SQLException e) {
			System.out.println("Falha ao consultar telefones");
			e.printStackTrace();
		}
		return telefones;
	}

	
	/**
	 * Metodo responsavel por alterar um telefone no banco de dados
	 * O metodo recebe o id do telefone atual e os parametros que serao alterados
	 * 
	 * @param idTelefone a ser alterado
	 * @param novoDdd para alterar o telefone
	 * @param novoNumero para alterar o telefone
	 * @param novoTipo para alterar o telefone
	 */
	public void alterarTelefone(int idTelefone, int novoDdd, String novoNumero, String novoTipo) {
		final String sql = "UPDATE Telefone " + "SET ddd = ?, numero = ?, tipo = ? " + "WHERE id = ?;";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, novoDdd);
			stmt.setString(2, novoNumero);
			stmt.setString(3, novoTipo);
			stmt.setInt(4, idTelefone);
			stmt.execute();
		} catch (Exception e) {
			System.out.println("Nao foi possivel alterar o telefone");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo responsavel por deletar um telefone no banco de dados
	 * 
	 * @param idTelefone a ser deletado
	 */
	public void deletarTelefone(int idTelefone) {
		final String sql = "DELETE FROM telefone where id = ?;";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idTelefone);
			stmt.execute();
		} catch (Exception e) {
			System.out.println("Nao foi possivvel remover o telefone");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo responsavel por deletar um telefone a partir do id do usuario
	 * 
	 * @param usuarioId a partir do qual vai ser deletado o telefone
	 */
	public void deletarTelefoneUsuario(int usuarioId) {
		final String sql = "DELETE FROM telefone where usuario_id = ?;";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, usuarioId);
			stmt.execute();
		} catch (Exception e) {
			System.out.println("Nao foi possivel remover o telefone");
			e.printStackTrace();
		}
	}

}
