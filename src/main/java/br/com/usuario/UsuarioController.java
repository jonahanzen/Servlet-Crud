package br.com.usuario;

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
		PrintWriter out = response.getWriter();
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		if (nome != null && senha != null) {
			if (nome.trim().length() > 0 && senha.trim().length() > 0) {
				try {
					RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
					usuarioRepository.incluirUsuario(nome, email, senha);
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Favor inserir dados validos!');");
				out.println("</script>");
				response.setHeader("Refresh", "1;url=index.jsp");
			}
		} 
	}

}
