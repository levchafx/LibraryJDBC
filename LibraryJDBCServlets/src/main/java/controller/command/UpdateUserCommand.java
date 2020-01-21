package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;

import dao.UserDaoImpl;
import exception.DaoException;
import model.Authenticate;
import model.Role;
import model.User;

public class UpdateUserCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		try {
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			User u = new User();
			Authenticate a = new Authenticate();
			a.setId(Integer.parseInt(request.getParameter("user_authenticate_id")));
			a.setLogin(request.getParameter("user_login"));
			a.setPassword(request.getParameter("user_password"));
			a.setProfileEnabled(Boolean.valueOf(request.getParameter("user_profile_enable")));

			u.setId(Integer.parseInt(request.getParameter("user_id")));
			u.setName(request.getParameter("user_name"));
			u.setSurname(request.getParameter("user_surname"));
			u.setEmail(request.getParameter("user_email"));
			u.setAge(Integer.parseInt(request.getParameter("user_age")));
			u.setRole(Role.valueOf(request.getParameter("user_role")));
			u.setAuthenticate(a);
			userDaoImpl.update(u);
			forward("userCabinet");
		} catch (DaoException e) {
			log.error(e);
			e.printStackTrace();
			request.setAttribute("exception message", "database failure");
			forward("error.jsp");
		}
	}

}
