package controller.command;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;

import dao.BookInstanceDaoImpl;
import exception.DaoException;
import model.BookInstance;

public class GetBookCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		try {
			if (request.getSession().getAttribute("profile-enable").equals(true)) {
				BookInstanceDaoImpl bookInstanceDaoImpl = new BookInstanceDaoImpl();
				BookInstance bi = new BookInstance();
				LocalDate localDate = LocalDate.now();
				bi.setBook_id(Integer.parseInt(request.getParameter("book_id")));
				bi.setUser_id(Integer.parseInt(request.getParameter("user_id")));
				bi.setDueDate(Date.valueOf(localDate.plusWeeks(Integer.parseInt(request.getParameter("weeks")))));
				bookInstanceDaoImpl.create(bi);
				forward("userCabinet");
			} else {
				forward("blockedCabinet");
			}
		} catch (DaoException e) {
			log.error(e);
			e.printStackTrace();
			request.setAttribute("exception message", "database failure");
			forward("error.jsp");
		}
	}
}
