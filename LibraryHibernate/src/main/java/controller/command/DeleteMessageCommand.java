package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;

import dao.MessageDaoImpl;

public class DeleteMessageCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		try {
			MessageDaoImpl messageDaoImpl = new MessageDaoImpl();
			messageDaoImpl.delete(Integer.parseInt(request.getParameter("id")));
			forward("userCabinet");
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			request.setAttribute("exception message", "database failure");
			forward("error.jsp");
		}
	}
}
