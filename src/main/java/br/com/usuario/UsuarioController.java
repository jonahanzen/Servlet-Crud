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

		try {
			List<Usuario> todosUsuarios = usuarioRepository.todosUsuarios();
			request.setAttribute("listaUsuario", todosUsuarios);
			RequestDispatcher dispatcher = request.getRequestDispatcher("todosusuarios.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Verifica se nome, email e senha para inserir usuario nao sao nulos
		boolean parametros = (request.getParameter("nome") != null && request.getParameter("email") != null
				&& request.getParameter("senha") != null);
		if (parametros == true) {
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			usuarioRepository.incluirUsuario(nome, email, senha);

			System.out.println(request.getRequestURL().toString());
			response.sendRedirect(request.getRequestURI().toString());
		}

	}

}
