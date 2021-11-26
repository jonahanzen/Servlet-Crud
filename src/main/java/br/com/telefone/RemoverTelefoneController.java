package br.com.telefone;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/RemoverTelefone", "/removertelefone" })
public class RemoverTelefoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TelefoneRepository telefoneRepository = new TelefoneRepository();

	public RemoverTelefoneController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!request.getParameter("idTelefone").isBlank()) {
			try {
				telefoneRepository.deletarTelefone(Integer.parseInt(request.getParameter("idTelefone")));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("telefone");
		}
	}

}
