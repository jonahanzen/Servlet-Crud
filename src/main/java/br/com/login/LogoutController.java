package br.com.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/Logout", "/logout" })
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public LogoutController() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/login");
		
	}

}
