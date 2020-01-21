package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;

import dao.UserDaoImpl;
import exception.DaoException;
import model.User;

public class LoginCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		try {
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			try {
				User u = userDaoImpl.verifyUser(login, password);

				if (u != null && u.getAuthenticate().isProfileEnabled()) {
					request.getSession().setAttribute("user_id", u.getId());
					request.getSession().setAttribute("name", u.getName());
					request.getSession().setAttribute("role", u.getRole());
					request.getSession().setAttribute("profile-enable", u.getAuthenticate().isProfileEnabled());
					forward("userCabinet");
				}
				if (!u.getAuthenticate().isProfileEnabled()) {
					request.getSession().setAttribute("user_id", u.getId());
					request.getSession().setAttribute("name", u.getName());
					request.getSession().setAttribute("role", u.getRole());
					request.getSession().setAttribute("profile-enable", u.getAuthenticate().isProfileEnabled());

					forward("blockedCabinet");
				}
			} catch (NullPointerException e) {
				request.setAttribute("notfound", "user not found");
				forward("login");
			}
		} catch (DaoException e) {
			log.error(e);
			e.printStackTrace();
			request.setAttribute("exception message", "database failure");
			forward("error.jsp");
		}
	}
}
