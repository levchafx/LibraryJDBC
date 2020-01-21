package filter;

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

@WebFilter(filterName = "MyFilter", urlPatterns = { "/userCabinet.jsp", "/addBook.jsp", "/blockedCabinet.jsp",
		"/bookshelf.jsp", "/messages.jsp", "/updateUsers.jsp", "/users.jsp" })

public class MyFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String loginURI = req.getContextPath() + "/login.jsp";
		
		if ((session != null) && (session.getAttribute("user_id") != null)) {
			chain.doFilter(req, resp);
		} else {
			resp.sendRedirect(loginURI);
		}
	}

}
