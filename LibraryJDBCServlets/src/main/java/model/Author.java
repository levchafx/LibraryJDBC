package model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
	private int id;
	private String name;
	private String surname;
	@ToString.Exclude
	private List<Book> books;

}
