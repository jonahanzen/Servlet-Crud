package br.com.telefone;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/AlterarTelefone", "/alterartelefone" })
public class AlterarTelefoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TelefoneRepository telefoneRepository = new TelefoneRepository();

	public AlterarTelefoneController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer idTelefone = Integer.parseInt(request.getParameter("id"));
		Telefone telefone = telefoneRepository.consultarUnicoTelefone(idTelefone);
		request.setAttribute("tempTelefone", telefone);
		RequestDispatcher dispatcher = request.getRequestDispatcher("alterartelefone.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int telefoneId = Integer.parseInt(request.getParameter("telefoneId"));
		int ddd = Integer.parseInt(request.getParameter("ddd"));
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipoTelefone");
		telefoneRepository.alterarTelefone(telefoneId, ddd, numero, tipo);
		response.sendRedirect("telefone");
	}

}
