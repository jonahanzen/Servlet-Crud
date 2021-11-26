package br.com.usuario;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/RemoverUsuario", "/removerusuario" })
public class RemoverUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioRepository usuarioRepository = new UsuarioRepository();

	public RemoverUsuarioController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			int idUsuario = Integer.parseInt(request.getParameter("id"));
			usuarioRepository.deletarUsuario(idUsuario);
			RequestDispatcher dispatcher = request.getRequestDispatcher("usuario");
			dispatcher.forward(request, response);
		}
	}

}
