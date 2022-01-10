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

		try {
			if (nomeUsuario.isBlank() || nomeUsuario == null || senhaUsuario.isBlank() || senhaUsuario == null
					|| !usuarioRepository.consultarUsuarioPorUsuarioOuEmail(null, emailUsuario)) {
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Favor inserir dados validos');");
				out.println("location='login.jsp';");
				out.println("</script>");
				out.close();
			} else {
				// Se nao mandar email, consultar no banco
				if (emailUsuario == null) {
					try {
						idUsuario = usuarioRepository.consultarIdUsuario(nomeUsuario, senhaUsuario);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					// Se mandar email, incluir no banco
					try {
						//TODO Mandar mensagem caso o usuario insira login incorreto
						if (usuarioRepository.consultarUsuarioPorUsuarioOuEmail(nomeUsuario, emailUsuario)) {
							PrintWriter out = response.getWriter();
							out.println("<script type=\"text/javascript\">");
							out.println("alert('Este usuario ja existe!');");
							out.println("location='login.jsp';");
							out.println("</script>");
							out.close();
						} else {
							try {
								usuarioRepository.incluirUsuario(nomeUsuario, emailUsuario, senhaUsuario);
								idUsuario = usuarioRepository.consultarIdUsuario(nomeUsuario, senhaUsuario);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
				request.getSession().setAttribute("senhaUsuario", senhaUsuario);
				request.getSession().setAttribute("emailUsuario", emailUsuario);
				request.getSession().setAttribute("idUsuario", idUsuario);
				request.getSession().setAttribute("usuario", nomeUsuario);
				response.sendRedirect("index.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}