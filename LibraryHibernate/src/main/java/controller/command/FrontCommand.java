package controller.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public abstract class FrontCommand {
	final Logger log = Logger.getLogger(FrontCommand.class);
	protected ServletContext context;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public void init(ServletContext servletContext, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {
		this.context = servletContext;
		this.request = servletRequest;
		this.response = servletResponse;
	}

	public abstract void process() throws ServletException, IOException;

	protected void forward(String target) throws ServletException, IOException {
		target = String.format("/WEB-INF/jsp/%s.jsp", target);
		RequestDispatcher dispatcher = context.getRequestDispatcher(target);
		dispatcher.forward(request, response);
	}
}
