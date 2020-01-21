package model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInstance {
	private int id;
	private int book_id;
	private int user_id;
	private Date dueDate;
}
