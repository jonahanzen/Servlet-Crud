package br.com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer idUsuario = null;
		String nomeUsuario = request.getParameter("usuario");
		String emailUsuario = request.getParameter("email");
		String senhaUsuario = request.getParameter("senha");

		// Verifica se os campos estao nulos e manda mensagem
		if (nomeUsuario.isBlank() || nomeUsuario == null || senhaUsuario.isBlank()
				|| senhaUsuario == null & emailUsuario == null) {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Favor inserir dados validos');");
				out.println("location='login.jsp';");
				out.println("</script>");
				out.close();
			} catch (Exception e) {
				e.getMessage();
			}
		}

		try {
			// LOGIN
			if (emailUsuario == null) {
				//Se nao achar o usuario no banco de dados
				if (!usuarioRepository.consultarUsuarioPorUsuarioOuEmail(nomeUsuario, emailUsuario)) {
					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Login incorreto ou usuario nao existe');");
					out.println("location='login.jsp';");
					out.println("</script>");
					out.close();
				}

			}
			
			// REGISTRO
			else {
				if (!usuarioRepository.consultarUsuarioPorUsuarioeEmail(nomeUsuario, emailUsuario)) {
					usuarioRepository.incluirUsuario(nomeUsuario, emailUsuario, senhaUsuario);
				} else {
					//Se achar o usuario no banco de dados
					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Este usuario ja existe!');");
					out.println("location='login.jsp';");
					out.println("</script>");
					out.close();
				}

			}
		} catch (Exception e) {
			e.getMessage();
		}

		try {
			idUsuario = usuarioRepository.consultarIdUsuario(nomeUsuario, senhaUsuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("senhaUsuario", senhaUsuario);
		request.getSession().setAttribute("emailUsuario", emailUsuario);
		request.getSession().setAttribute("idUsuario", idUsuario);
		request.getSession().setAttribute("usuario", nomeUsuario);
		response.sendRedirect("index.jsp");

	}
}
