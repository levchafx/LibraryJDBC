package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;

import dao.BookInstanceDaoImpl;
import exception.DaoException;

public class ReturnCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		try {
			BookInstanceDaoImpl bookInstanceDaoImpl = new BookInstanceDaoImpl();

			bookInstanceDaoImpl.delete(Integer.parseInt(request.getParameter("bookinstance_id")));
			forward("userCabinet");
		} catch (DaoException e) {
			log.error(e);
			e.printStackTrace();
			request.setAttribute("exception message", "database failure");
			forward("error.jsp");
		}
	}
}
