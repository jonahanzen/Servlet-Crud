package br.com.usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/usuario" })
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioRepository usuarioRepository = new UsuarioRepository();

	public UsuarioController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("todosusuarios.jsp");

		try {
			List<Usuario> todosUsuarios = usuarioRepository.todosUsuarios();
			request.setAttribute("listaUsuario", todosUsuarios);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = null;
		String email = null;
		String senha = null;
		
		if (request.getParameter("nome").isBlank() || request.getParameter("nome") != null ||
				request.getParameter("senha").isBlank() || request.getParameter("senha") != null ) {
			response.setHeader("Refresh", "1;url=index.jsp");
		} else {
			nome = request.getParameter("nome").trim();
			email = request.getParameter("email").trim();
			senha = request.getParameter("senha").trim();
			try {
				usuarioRepository.incluirUsuario(nome, email, senha);
				response.sendRedirect(request.getRequestURI().toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
	}
		
}
