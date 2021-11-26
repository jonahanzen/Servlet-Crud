package br.com.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.usuario.UsuarioRepository;

@WebServlet({ "/login", "/registrar" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioRepository usuarioRepository = new UsuarioRepository();

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer idUsuario = null;
		String nomeUsuario = (String) request.getParameter("usuario");
		String senhaUsuario = (String) request.getParameter("senha");
		String emailUsuario = (String) request.getParameter("email");

		if (nomeUsuario != null && senhaUsuario != null) {
			// Se nao mandar email, consultar no banco
			if (emailUsuario == null) {
			idUsuario = usuarioRepository.consultarIdUsuario(nomeUsuario, senhaUsuario);
			} else {
				//Se mandar email, incluir no banco
				usuarioRepository.incluirUsuario(nomeUsuario, emailUsuario, senhaUsuario);
				idUsuario = usuarioRepository.consultarIdUsuario(nomeUsuario, senhaUsuario);
			}
			
			
			request.getSession().setAttribute("senhaUsuario", senhaUsuario);
			request.getSession().setAttribute("emailUsuario", emailUsuario);
			request.getSession().setAttribute("idUsuario", idUsuario);
			request.getSession().setAttribute("usuario", nomeUsuario);
		}
		response.sendRedirect(request.getContextPath() + "/index.jsp");

	}

}
