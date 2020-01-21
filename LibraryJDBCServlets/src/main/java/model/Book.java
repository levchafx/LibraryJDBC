package model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	private int id;
	private int imageId;
	private String title;
	private String description;
	private List<Author> authors;
	private int quantity;

}
