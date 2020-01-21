package dao;

import model.User;

public interface UserDao {
	public User verifyUser(String login, String password);

	public String verifyEmail(String email);
}
