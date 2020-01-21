package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import dao.UserDaoImpl;
import model.User;

public class UsersCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {

		UserDaoImpl userDaoImpl = new UserDaoImpl();
		List<User> users = userDaoImpl.getAll();
		request.setAttribute("users", users);
		forward("users");

	}
}
