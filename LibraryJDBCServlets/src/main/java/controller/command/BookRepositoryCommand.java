package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import dao.BookDaoImpl;
import exception.DaoException;
import model.Book;

public class BookRepositoryCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		try {
			BookDaoImpl bookDaoImpl = new BookDaoImpl();
			List<Book> books = bookDaoImpl.getAll();
			request.setAttribute("books", books);
		} catch (DaoException e) {
			log.error(e);
			e.printStackTrace();
			request.setAttribute("exception message", "database failure");
			forward("error.jsp");
		}
		forward("repository");

	}

}
