package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Product;
import models.Sale;

import java.io.IOException;
import java.util.List;

import DAO.ProductDaoImp;
import DAO.SaleDaoImp;

/**
 * Servlet implementation class SvInventory
 */
@WebServlet("/inventory")
public class SvInventory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvInventory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		ProductDaoImp productDao = new ProductDaoImp();
		
		
        List<Product> products = productDao.getAll();
       
        
        request.setAttribute("products", products);
        
		
		request.getRequestDispatcher("inventory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ProductDaoImp productDao = new ProductDaoImp();
		
		
		// Get the form type to determine which form was submitted
		String formType = request.getParameter("formType");

		if ("buttonForm".equals(formType)) {
		    // Handle the previous form (Edit/Delete buttons)
		    String editProductId = request.getParameter("edit");
		    String deleteProductId = request.getParameter("delete");

		    if (editProductId != null) {
		        // Handle edit action for product with ID = edit
		    } else if (deleteProductId != null) {
		        // Handle delete action for product with ID = delete
		    	int productId = Integer.parseInt(deleteProductId);
		    	productDao.delete(productId);
		    }
		} else if ("editForm".equals(formType)) {
		    // Handle the new edit form (Save Changes button)
		    String id = request.getParameter("id");
		    String name = request.getParameter("name");
		    String description = request.getParameter("description");
		    String price = request.getParameter("price");
		    String stock = request.getParameter("stock");

		    Product updatedProduct = new Product(Integer.parseInt(id), name, description, Double.parseDouble(price), Integer.parseInt(stock));
		    productDao.update(updatedProduct);
		} else if ("creationForm".equals(formType)) {
		    // Handle the new edit form (Save Changes button)
//		    String id = request.getParameter("id");
		    String name = request.getParameter("name");
		    String description = request.getParameter("description");
		    String price = request.getParameter("price");
		    String stock = request.getParameter("stock");

		    Product updatedProduct = new Product(name, description, Double.parseDouble(price), Integer.parseInt(stock));
		    productDao.save(updatedProduct);
		}

		
		response.sendRedirect("inventory");
	}

}
