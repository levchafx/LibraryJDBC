package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import dao.BookDaoImpl;
import exception.DaoException;
import model.Book;

public class SearchCommand extends FrontCommand {
	BookDaoImpl bookDaoImpl = new BookDaoImpl();

	@Override
	public void process() throws ServletException, IOException {
		try {
			List<Book> books = bookDaoImpl.searchBook(request.getParameter("search"));
			if (books != null) {
				request.setAttribute("books", books);
				forward("repository");
			} else {
				forward("book-notfound");
			}
		} catch (DaoException e) {
			log.error(e);
			request.setAttribute("exception message", new DaoException("database failure"));
			forward("error.jsp");
		}
	}
}