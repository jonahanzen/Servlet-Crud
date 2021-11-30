package br.com.usuario;

import java.util.List;

import br.com.telefone.Telefone;

public class Usuario {

	private Integer id;
	private String usuario;
	private String email;
	private String senha;
	private List<Telefone> telefones;
	
	public Usuario() {
	}
	
	public Usuario(String usuario, String email, String senha) {
		super();
		this.usuario = usuario;
		this.email = email;
		this.senha = senha;
	}






	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", email=" + email + ", senha=" + senha + ", telefones="
				+ telefones + "]";
	}

}
