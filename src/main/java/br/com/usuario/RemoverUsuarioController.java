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

@WebServlet({ "/RemoverUsuario", "/removerusuario" })
public class RemoverUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioRepository usuarioRepository = new UsuarioRepository();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario");
		if (request.getParameter("id") != null) {
			int idUsuario = Integer.parseInt(request.getParameter("id"));
			if (idUsuario == (Integer) request.getSession().getAttribute("idUsuario")) {
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Voce nao pode excluir o seu proprio usuario!');");
				out.println("</script>");
				response.setHeader("Refresh", "1;url=usuario");
			} else {
				try {
					usuarioRepository.deletarUsuario(idUsuario);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				dispatcher.forward(request, response);
			}
		}
	}

}
