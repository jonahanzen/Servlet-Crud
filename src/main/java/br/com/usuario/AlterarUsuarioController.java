package br.com.usuario;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/AlterarUsuario", "/alterarusuario" })
public class AlterarUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioRepository usuarioRepository = new UsuarioRepository();

	public AlterarUsuarioController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer idUsuario = Integer.parseInt(request.getParameter("id"));
		Usuario usuario;
		RequestDispatcher dispatcher = request.getRequestDispatcher("alterarusuario.jsp");
		try {
			usuario = usuarioRepository.consultarUnicoUsuario(idUsuario);
			request.setAttribute("tempUsuario", usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		Integer idUsuario   = Integer.parseInt(request.getParameter("id"));
		String nomeUsuario  = request.getParameter("usuario");
		String emailUsuario = request.getParameter("email");
		String senhaUsuario = request.getParameter("senha");
		try {
			usuarioRepository.alterarUsuario(idUsuario, nomeUsuario, emailUsuario, senhaUsuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("usuario");
	}

}
