package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authenticate {
	public Authenticate(String login, String password, boolean profileEnabled) {
		this.login = login;
		this.password = password;
		this.profileEnabled = profileEnabled;
	}

	private int id;
	private String login;
	private String password;
	private boolean profileEnabled;
}
