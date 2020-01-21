package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;

import dao.UserDaoImpl;
import exception.DaoException;
import model.Authenticate;
import model.Role;
import model.User;

public class RegistrationCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		try {
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			User u = new User();
			Authenticate a = new Authenticate();
			u.setName(request.getParameter("name"));
			u.setSurname(request.getParameter("surname"));
			u.setAge(Integer.parseInt(request.getParameter("age")));
			u.setEmail(request.getParameter("email"));
			if (null != request.getParameter("role")) {
				u.setRole(Role.valueOf(request.getParameter("role")));
			}
			if (null == request.getParameter("role")) {
				u.setRole(Role.USER);
			}
			a.setLogin(request.getParameter("login"));
			a.setPassword(request.getParameter("password"));
			a.setProfileEnabled(true);
			u.setAuthenticate(a);
			if (null == userDaoImpl.verifyUser(request.getParameter("login"), request.getParameter("password"))
					&& "" == userDaoImpl.verifyEmail(request.getParameter("email"))
					&& request.getParameter("password").equals(request.getParameter("confirmpassword"))) {
				userDaoImpl.create(u);
				if (null == request.getSession().getAttribute("user_id")) {
					forward("login");
				}
				forward("userCabinet");
			} else {
				if (!u.getAuthenticate().getPassword().equals(request.getParameter("confirmpassword"))) {
					request.setAttribute("passworddoesntmatch", "password doesn't match");
					forward("registration");
				}
				if (!userDaoImpl.verifyEmail(u.getEmail()).isEmpty()) {
					request.setAttribute("emailexists", "provide another email");
					forward("registration");
				}
				if (null != userDaoImpl.verifyUser(u.getAuthenticate().getLogin(), u.getAuthenticate().getPassword())) {
					request.setAttribute("userexists", "provide antoher login");
					forward("registration");
				}
			}
		} catch (DaoException e) {
			log.error(e);
			e.printStackTrace();
			request.setAttribute("exception message", "database failure");
			forward("error");
		}
	}
}
