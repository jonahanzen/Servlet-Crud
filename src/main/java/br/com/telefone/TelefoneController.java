package br.com.telefone;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Telefone> todosTelefones;
		RequestDispatcher dispatcher = request.getRequestDispatcher("todostelefones.jsp");
		try {
			todosTelefones = telefoneRepository.todosTelefones();
			request.setAttribute("listaTelefone", todosTelefones);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			String numero = request.getParameter("numero").trim();
			String tipo = request.getParameter("tipoTelefone").trim();
			try {
				PrintWriter out = response.getWriter();
				if (!telefoneRepository.consultarTelefonePorNumero(numero)) {
					Telefone telefone = new Telefone(ddd, numero, tipo, usuarioId);
					telefoneRepository.incluirTelefone(telefone);
				} else {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Telefone ja existe no banco de dados');");
					out.println("location='telefone';");
					out.println("</script>");
					out.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("novotelefone.jsp");
			dispatcher.forward(request, response);
		}

	}

}
