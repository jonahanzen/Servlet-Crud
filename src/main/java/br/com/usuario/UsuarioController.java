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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("todosusuarios.jsp");

		try {
			List<Usuario> todosUsuarios = usuarioRepository.todosUsuarios();
			request.setAttribute("listaUsuario", todosUsuarios);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

			String nomeUsuario = request.getParameter("usuario");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			Usuario usuario = new Usuario(nomeUsuario, email, senha);

			// Verifica todos os campos e tenta incluir o usuario caso nao exista no banco
			// de dados
			if (nomeUsuario != null && senha != null && nomeUsuario.trim().length() > 0 && senha.trim().length() > 0) {
				System.out.println(usuarioRepository.consultarUsuarioPorUsuarioOuEmail(nomeUsuario, email));
				if (!usuarioRepository.consultarUsuarioPorUsuarioOuEmail(nomeUsuario, email)) {
					usuarioRepository.incluirUsuario(usuario);
					dispatcher.forward(request, response);
				}
			} else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Usuario ja existe ou os dados estao invalidos, favor tente novamente');");
				out.println("</script>");
				response.setHeader("Refresh", "1;url=index.jsp");

			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
