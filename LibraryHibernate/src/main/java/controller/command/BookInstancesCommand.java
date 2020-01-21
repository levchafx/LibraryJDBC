package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import dao.BookInstanceDaoImpl;
import model.BookInstance;

public class BookInstancesCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		try {
			BookInstanceDaoImpl bookInstanceDaoImpl = new BookInstanceDaoImpl();
			List<BookInstance> books = bookInstanceDaoImpl.getPastDueBooks();
			request.setAttribute("books", books);
			forward("bookInstances");
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			request.setAttribute("exception message", "database failure");
			forward("error.jsp");
		}
	}

}
