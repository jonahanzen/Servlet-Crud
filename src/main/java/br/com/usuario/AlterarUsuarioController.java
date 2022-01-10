package br.com.usuario;

import java.io.IOException;
import java.io.PrintWriter;
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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer idUsuario = Integer.parseInt(request.getParameter("id"));
			Usuario usuario;
			RequestDispatcher dispatcher = request.getRequestDispatcher("alterarusuario.jsp");
			usuario = usuarioRepository.consultarUnicoUsuario(idUsuario);
			request.setAttribute("tempUsuario", usuario);
			dispatcher.forward(request, response);
		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer idUsuario = Integer.parseInt(request.getParameter("id"));
		String nomeUsuario = request.getParameter("usuario").trim();
		String emailUsuario = request.getParameter("email").trim();
		String senhaUsuario = request.getParameter("senha").trim();

		// Verifica se nome e senha nao sao vazios/nulos
		if (request.getParameter("usuario").isBlank() || request.getParameter("usuario") == null
				|| request.getParameter("senha").isBlank() || request.getParameter("senha") == null) {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Favor inserir dados corretos!');");
			out.println("</script>");
			response.setHeader("Refresh", "1;url=usuario");
		} else {
			try {
				usuarioRepository.alterarUsuario(idUsuario, nomeUsuario, emailUsuario, senhaUsuario);
				response.sendRedirect("usuario");
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
		}
	}

}
