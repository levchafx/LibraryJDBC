package model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int id;
	private String name;
	private String surname;
	private Authenticate authenticate;
	private int age;
	private String email;
	private Role role;
	@ToString.Exclude
	private List<Book> bookshelf;

}
