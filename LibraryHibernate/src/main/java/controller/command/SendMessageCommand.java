package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;

import dao.MessageDaoImpl;
import model.Message;

public class SendMessageCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		try {
			MessageDaoImpl messageDaoImpl = new MessageDaoImpl();
			Message message = new Message();
			message.setUserId(Integer.parseInt(request.getParameter("userId")));

			message.setMessage(request.getParameter("message"));
			messageDaoImpl.create(message);

			forward("userCabinet");

		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			request.setAttribute("exception message", "database failure");
			forward("error.jsp");
		}
	}
}
