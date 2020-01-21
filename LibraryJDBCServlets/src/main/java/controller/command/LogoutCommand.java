package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;

public class LogoutCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		request.getSession().invalidate();
		forward("login");

	}

}
