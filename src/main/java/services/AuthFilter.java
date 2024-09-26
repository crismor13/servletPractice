package services;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Apply the filter to all URLs or specific URL patterns (e.g., "/*" applies to all)
@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // You can initialize filter-specific settings here if needed
    	
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	
    	System.out.println("Auth filter works");
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Retrieve the session object (if it exists)
        HttpSession session = httpRequest.getSession(false);

        // Check if user is logged in (based on session attribute "user")
        boolean loggedIn = (session != null && session.getAttribute("username") != null);

        // Get the requested URL
        String loginURI = httpRequest.getContextPath() + "/login.jsp";

        boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isPublicResource = httpRequest.getRequestURI().endsWith("login.jsp") 
                                   || httpRequest.getRequestURI().endsWith("login")
                                   || httpRequest.getRequestURI().endsWith("register.jsp");

        // Redirect to login page if user is not logged in and trying to access restricted resources
        if (loggedIn || loginRequest || isPublicResource) {
            chain.doFilter(request, response);  // Proceed to the requested resource
        } else {
//            httpResponse.sendRedirect(loginURI);  // Redirect to login page
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code (optional)
    }


}

