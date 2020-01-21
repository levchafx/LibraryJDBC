package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;

import dao.UserDaoImpl;
import model.User;

public class UpdateUserFormCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		try {
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			User u = userDaoImpl.findById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("user", u);
			forward("updateUsers");
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			request.setAttribute("exception message", "database failure");
			forward("error.jsp");
		}
	}
}
