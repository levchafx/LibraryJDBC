package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import dao.BookDaoImpl;
import model.Book;

public class BookRepositoryCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		try {
			BookDaoImpl bookDaoImpl = new BookDaoImpl();
			List<Book> books = bookDaoImpl.getAll();
			request.setAttribute("books", books);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			request.setAttribute("exception message", "database failure");
			forward("error");
		}
		forward("repository");

	}

}
