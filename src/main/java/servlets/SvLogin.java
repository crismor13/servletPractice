package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.AuthService;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class SvLogin
 */
@WebServlet(name = "SvLogin", urlPatterns = {"/login"})
public class SvLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		
//	}
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post works!");
		AuthService authService;
		try {
			authService = new AuthService();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			boolean isUserInDb = authService.authenticate(username, password);
			if(isUserInDb) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
	            response.sendRedirect("home");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		doGet(request, response);
	}

}
