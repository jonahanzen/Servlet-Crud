package br.com.telefone;

public class Telefone {

	private Integer id;
	private Integer ddd;
	private String numero;
	private String tipo;
	private Integer usuarioId;

	public Telefone() {
	}

	public Telefone(Integer id, Integer ddd, String numero, String tipo, Integer usuarioId) {
		super();
		this.id = id;
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
		this.usuarioId = usuarioId;
	}

	public Telefone(Integer ddd, String numero, String tipo, Integer usuarioId) {
		super();
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
		this.usuarioId = usuarioId;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
