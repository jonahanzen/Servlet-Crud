package br.com.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.login.LoginController;
import br.com.telefone.TelefoneRepository;
import br.com.utils.Conexao;

/**
 * Classe responsavel por conter os metodos de CRUD no banco de dados de
 * {@link Usuario}
 */
public class UsuarioRepository {

	private Connection conn;

	public UsuarioRepository() {
		if (conn == null) {
			conn = Conexao.conectar();
		}
	}

	/**
	 * Metodo responsavel por incluir um usuario no banco de dados O metodo recebe
	 * um objeto usuario com as propriedades String usuario, String email e String
	 * senha
	 * 
	 * @param usuario com usuario, email e senha
	 */
	public void incluirUsuario(Usuario usuario) {
		final String sql = "INSERT INTO USUARIO " + "(usuario, email, senha) " + "VALUES (?,?,?);";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getUsuario());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.execute();

		} catch (Exception e) {
			System.out.println("Falha ao Incluir Usuario");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo responsavel por incluir um usuario no banco de dados
	 * 
	 * @param usuario a ser incluido
	 * @param email   a ser incluido
	 * @param senha   a ser incluido
	 */
	public void incluirUsuario(String usuario, String email, String senha) {
		String sql = null;
		sql = "INSERT INTO USUARIO " + "(usuario, email, senha) " + "VALUES (?,?,?);";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario);
			stmt.setString(2, email);
			stmt.setString(3, senha);
			stmt.execute();

		} catch (Exception e) {
			System.out.println("Falha ao Incluir Usuario");
			e.printStackTrace();
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
	 */
	public Integer consultarIdUsuario(String nomeUsuario, String senhaUsuario) {
		String sql = null;
		sql = "SELECT id FROM Usuario WHERE usuario = ? AND senha = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nomeUsuario);
			stmt.setString(2, senhaUsuario);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("Nao foi possivel encontrar o usuario");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Metodo responsavel por consultar um usuario especifico a partir da Id
	 * 
	 * @param idUsuario a ser consultado
	 * @return Usuario da consulta
	 */
	public Usuario consultarUnicoUsuario(int idUsuario) {
		final String sql = "SELECT * FROM Usuario where id = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idUsuario);
			ResultSet rs = stmt.executeQuery();
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

	/**
	 * Metodo responsavel por consultar todos os usuarios
	 * 
	 * @return List de usuarios da consulta
	 */
	public List<Usuario> todosUsuarios() throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		final String sql = "SELECT * FROM Usuario ORDER BY id;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
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
	 */
	public void alterarUsuario(int idUsuario, String novoNome, String novoEmail, String novaSenha) {
		String sql = "UPDATE Usuario " + "SET usuario=?, email=?, senha=? " + "WHERE id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, novoNome);
			stmt.setString(2, novoEmail);
			stmt.setString(3, novaSenha);
			stmt.setInt(4, idUsuario);
			stmt.execute();
		} catch (Exception e) {
			System.out.println("Nao foi possivel alterar o Usuario");
		}
	}

	/**
	 * Metodo responsavel por deletar um usuario no banco de dados
	 * 
	 * @param idUsuario a ser deletado
	 */
	public void deletarUsuario(int idUsuario) {
		TelefoneRepository telefoneRepository = new TelefoneRepository();
		telefoneRepository.deletarTelefoneUsuario(idUsuario);
		String sql = "DELETE FROM usuario where id = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idUsuario);
			stmt.execute();
		} catch (Exception e) {
			System.out.println("Nao foi possivel remover o usuario");
		}
	}

}
