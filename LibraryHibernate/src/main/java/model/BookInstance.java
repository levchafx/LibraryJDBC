package model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_instance")
public class BookInstance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int book_id;
	private int user_id;

	private Date dueDate;
}
