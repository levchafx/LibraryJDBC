package dao;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Authenticate;
import model.Author;
import model.Book;
import model.BookInstance;
import model.Image;
import model.Message;
import model.Role;
import model.User;

public class DaoRunner {

	public static void main(String[] args) {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		BookDaoImpl bookDaoImpl = new BookDaoImpl();
		BookInstanceDaoImpl bookInstanceImpl = new BookInstanceDaoImpl();
		MessageDaoImpl messageDaoImpl = new MessageDaoImpl();
		ImageDaoImpl imageDaoImpl = new ImageDaoImpl();
		User user = new User();
		Authenticate auth = new Authenticate();
		user.setName("viktoria");
		user.setSurname("l");
		user.setAge(35);
		user.setEmail("viktoria@mail.ru");
		user.setRole(Role.ADMIN);
		auth.setLogin("viktoria");
		auth.setPassword("1234");
		auth.setProfileEnabled(true);
		user.setAuthenticate(auth);
		// userDaoImpl.create(user);
		// userDaoImpl.delete(2);
		// System.out.println(userDaoImpl.getAll());
		User u = userDaoImpl.findById(1);
		u.setName("vika");
		u.setSurname("levchenko");
		u.getAuthenticate().setPassword("12345");
		u.getAuthenticate().setProfileEnabled(true);
		u.setRole(Role.USER);
		u.setEmail("vika@mail.ru"); //
		// userDaoImpl.update(u);
		// System.out.println(userDaoImpl.verifyUser("vika", "12345"));
		Book book = new Book();
		book.setTitle("head first java");

		book.setDescription("my java favourite");
		book.setQuantity(5);

		List<Author> authors = new ArrayList<Author>();
		Author a = new Author();
		a.setName("kathy");
		a.setSurname("sierra");
		Author a1 = new Author();
		a1.setName("michael");
		a1.setSurname("beits");
		authors.add(a);
		authors.add(a1);
		book.setAuthors(authors);
		// bookDaoImpl.create(book);

		for (Book b : bookDaoImpl.getAll()) {
			System.out.println(b);
			System.out.println(bookDaoImpl.getAuthors(b.getId()));
		}
		// System.out.println(bookDaoImpl.searchBook("mich"));

		LocalDate localDate = LocalDate.now();
		BookInstance bi = new BookInstance();
		bi.setUser_id(1);
		bi.setBook_id(1);
		bi.setDueDate(Date.valueOf(localDate.plusWeeks(1))); //
		// bookInstanceImpl.create(bi);
		System.out.println(bookInstanceImpl.getAll());
		Message message = new Message();
		message.setUserId(1);
		message.setMessage("please unsuspend my account"); //
		// messageDaoImpl.create(message);
		System.out.println(messageDaoImpl.getAll());
		message = messageDaoImpl.findById(1);
		message.setMessage("please let me read the book for another week or so");
		message.setUserId(2);
		// messageDaoImpl.update(message);

		System.out.println(messageDaoImpl.getAll());

		Image i = new Image();
		// i.setBookId(10);
		i.setName("bookImage");
		byte[] bytes = null;
		try {
			bytes = org.apache.commons.io.FileUtils
					.readFileToByteArray(new File("/Users/sergeilevchenko/Downloads/DogHeart.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		i.setImage(bytes);
		// imageDaoImpl.create(i);
		// System.out.println(imageDaoImpl.findById(1));
		System.out.println(userDaoImpl.getAll());

	}
}
