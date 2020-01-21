package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import dao.MessageDaoImpl;
import model.Message;

public class MessagesCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		try {
			MessageDaoImpl messageDaoImpl = new MessageDaoImpl();
			List<Message> messages = messageDaoImpl.getAll();
			request.setAttribute("messages", messages);
			forward("messages");
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			request.setAttribute("exception message", "database failure");
			forward("error.jsp");
		}
	}
}
