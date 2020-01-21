package controller.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import dao.BookInstanceDaoImpl;
import model.BookInstance;

public class BookshelfCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		List<BookInstance> books = new ArrayList<>();
		BookInstanceDaoImpl bookInstanceDaoImpl = new BookInstanceDaoImpl();

		books = bookInstanceDaoImpl.getAllById(Integer.parseInt(request.getParameter("user_id")));
		if (!books.isEmpty()) {
			request.setAttribute("books", books);
			forward("bookshelf");
		}
		if (books.isEmpty()) {
			request.setAttribute("bookshelfempty", "your bookshelf is empty");
			forward("bookshelf");
		}
	}

}
