package servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import models.Product;
import models.Sale;
import DAO.*;

/**
 * Servlet implementation class SvHome
 */
@WebServlet(name = "SvHome", urlPatterns = {"/home"})
public class SvHome extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch the necessary data for the dashboard (products overview, sales overview, notifications, etc.)
		
		ProductDaoImp productDao = new ProductDaoImp();
		SaleDaoImp saleDao = new SaleDaoImp();
		
        List<Product> lowStockProducts = productDao.getLowStockProducts();
        List<Sale> recentSales = saleDao.getMostRecentSales();
        
        request.setAttribute("lowStockProducts", lowStockProducts);
        request.setAttribute("recentSales", recentSales);

        // Forward to the home.jsp page
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

}
