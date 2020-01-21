package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import controller.command.FrontCommand;
import controller.command.UnknownCommand;

@WebServlet(name = "FrontControllerServlet")

public class FrontControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final Logger log = Logger.getLogger(FrontControllerServlet.class);

	private void getCommandInitProcess(HttpServletRequest request, HttpServletResponse response) {
		FrontCommand command = getCommand(request);
		command.init(getServletContext(), request, response);
		try {
			command.process();
		} catch (ServletException | IOException e) {

		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		getCommandInitProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		getCommandInitProcess(request, response);
	}

	private FrontCommand getCommand(HttpServletRequest request) {
		try {
			Class type = Class.forName(String.format("controller.command.%sCommand", request.getParameter("Command")));
			return (FrontCommand) type.asSubclass(FrontCommand.class).newInstance();
		} catch (Exception e) {
			return new UnknownCommand();
		}
	}
}