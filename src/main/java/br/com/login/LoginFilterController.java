package br.com.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilterController implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String loginURI = request.getContextPath() + "/login";

		boolean estaLogado = session != null && session.getAttribute("usuario") != null
				&& session.getAttribute("idUsuario") != null;
		boolean loginRequest = request.getRequestURI().equals(loginURI);

		if (estaLogado || loginRequest) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(loginURI);
		}

	}

}