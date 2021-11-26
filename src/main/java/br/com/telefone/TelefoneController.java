package br.com.telefone;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/telefone")
public class TelefoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TelefoneRepository telefoneRepository = new TelefoneRepository();

	public TelefoneController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Telefone> todosTelefones = telefoneRepository.todosTelefones();
		request.setAttribute("listaTelefone", todosTelefones);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todostelefones.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Verifica se id do usuario, ddd, numero, tipo de telefone para inserir
		// Telefone nao sao nulos
		boolean parametros = (request.getParameter("idUsuario") != null && request.getParameter("ddd") != null
				&& request.getParameter("numero") != null && request.getParameter("tipoTelefone") != null);

		// Caso não sejam nulos, inclui no banco de dados
		if (parametros == true) {
			int usuarioId = Integer.parseInt(request.getParameter("idUsuario"));
			int ddd = Integer.parseInt(request.getParameter("ddd"));
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipoTelefone");
			telefoneRepository.incluirTelefone(ddd, numero, tipo, usuarioId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("novotelefone.jsp");
			dispatcher.forward(request, response);
		}

	}

}
