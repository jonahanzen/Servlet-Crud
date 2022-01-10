package br.com.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.login.LoginController;
import br.com.utils.Conexao;

/**
 * Classe responsavel por conter os metodos de CRUD no banco de dados de
 * {@link Usuario}
 */
public class UsuarioRepository {

	/**
	 * Metodo responsavel por incluir um usuario no banco de dados O metodo recebe
	 * um objeto usuario com as propriedades String usuario, String email e String
	 * senha
	 * 
	 * @param usuario com usuario, email e senha
	 * @throws SQLException
	 */
	public boolean incluirUsuario(Usuario usuario) throws SQLException {
		final String SQL = "INSERT INTO USUARIO " + "(usuario, email, senha) " + "VALUES (?,?,?);";

		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(SQL)) {

			try {
				stmt.setString(1, usuario.getUsuario());
				stmt.setString(2, usuario.getEmail());
				stmt.setString(3, usuario.getSenha());
				return stmt.execute();
			} catch (Exception e) {
				return false;
			}
		}
	}

	/**
	 * Metodo responsavel por incluir um usuario no banco de dados
	 * 
	 * @param usuario a ser incluido
	 * @param email   a ser incluido
	 * @param senha   a ser incluido
	 * @throws SQLException
	 */
	public void incluirUsuario(String usuario, String email, String senha) throws SQLException {

		final String SQL = "INSERT INTO USUARIO " + "(usuario, email, senha) " + "VALUES (?,?,?);";
		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(SQL)) {

			try {
				stmt.setString(1, usuario);
				stmt.setString(2, email);
				stmt.setString(3, senha);
				stmt.execute();

			} catch (Exception e) {
				System.out.println("Falha ao Incluir Usuario");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo responsavel por consultar o Id de um usuario especifico a partir de
	 * usuario e senha
	 * 
	 * @see {@link LoginController}
	 * 
	 * @param nomeUsuario  a ser consultado
	 * @param senhaUsuario a ser consultado
	 * @return Id do usuario da consulta
	 * @throws SQLException
	 */
	public Integer consultarIdUsuario(String nomeUsuario, String senhaUsuario) throws SQLException {
		final String SQL = "SELECT id FROM Usuario WHERE usuario = ? AND senha = ?;";

		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(SQL);) {
			stmt.setString(1, nomeUsuario);
			stmt.setString(2, senhaUsuario);

			try (ResultSet rs = stmt.executeQuery();) {
				if (rs.next()) {
					return rs.getInt("id");
				}
			} catch (Exception e) {
				System.out.println("Nao foi possivel encontrar o usuario");
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Metodo responsavel por consultar um usuario especifico a partir da Id
	 * 
	 * @param idUsuario a ser consultado
	 * @return Usuario da consulta
	 * @throws SQLException
	 */
	public Usuario consultarUnicoUsuario(int idUsuario) throws SQLException {
		final String SQL = "SELECT * FROM Usuario where id = ?;";

		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
			stmt.setInt(1, idUsuario);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Usuario usuario = new Usuario();
					usuario.setId(rs.getInt("id"));
					usuario.setUsuario(rs.getString("usuario"));
					usuario.setEmail(rs.getString("email"));
					usuario.setSenha(rs.getString("senha"));
					return usuario;
				}
			} catch (Exception e) {
				System.out.println("Nao foi possivel consultar usuario");
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * Metodo responsavel por consultar a existencia de um usuario pelo usuario ou email
	 * 
	 * @param usuario a ser consultado
	 * @param email a ser consultado
	 * @return true caso exista, false caso nao exista
	 * @throws SQLException
	 */
	public boolean consultarUsuarioPorUsuarioOuEmail(String usuario, String email) throws SQLException {
		final String SQL = "SELECT count(*) FROM Usuario where usuario = ? OR email = ? LIMIT 1;";

		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
			stmt.setString(1, usuario);
			stmt.setString(2, email);
			try (ResultSet rs = stmt.executeQuery()) {
				rs.next();
				return rs.getInt(1) == 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
	}

	/**
	 * Metodo responsavel por consultar todos os usuarios
	 * 
	 * @return List de usuarios da consulta
	 */
	public List<Usuario> todosUsuarios() throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		final String SQL = "SELECT * FROM Usuario ORDER BY id;";

		try (Connection conn = Conexao.conectar();
				PreparedStatement stmt = conn.prepareStatement(SQL);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setEmail(rs.getString("email"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			System.out.println("Falha ao consultar usuarios");
			e.printStackTrace();
		}
		return usuarios;
	}

	/**
	 * Metodo responsavel por alterar usuario no banco de dados
	 * 
	 * @param idUsuario a ser alterado
	 * @param novoNome  para alterar
	 * @param novoEmail para alterar
	 * @param novaSenha para alterar
	 * @throws SQLException
	 */
	public void alterarUsuario(int idUsuario, String novoNome, String novoEmail, String novaSenha) throws SQLException {
		final String SQL = "UPDATE Usuario " + "SET usuario=?, email=?, senha=? " + "WHERE id=?";

		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(SQL);) {
			stmt.setString(1, novoNome);
			stmt.setString(2, novoEmail);
			stmt.setString(3, novaSenha);
			stmt.setInt(4, idUsuario);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Nao foi possivel alterar o Usuario");
		}
	}

	/**
	 * Metodo responsavel por deletar um usuario no banco de dados
	 * 
	 * @param idUsuario a ser deletado
	 * @throws SQLException
	 */
	public void deletarUsuario(int idUsuario) throws SQLException {
		final String SQL = "DELETE FROM usuario where id = ?";
		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
			stmt.setInt(1, idUsuario);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Nao foi possivel remover o usuario");
		}
	}

}
