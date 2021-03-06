package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;

import dao.UserDaoImpl;
import exception.DaoException;

public class DeleteUserCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		try {
			UserDaoImpl userDaoImpl = new UserDaoImpl();

			userDaoImpl.delete(Integer.parseInt(request.getParameter("user_id")));
			forward("userCabinet");

		} catch (DaoException e) {
			log.error(e);
			e.printStackTrace();
			request.setAttribute("exception message", "database failure");
			forward("error.jsp");
		}
	}

}
