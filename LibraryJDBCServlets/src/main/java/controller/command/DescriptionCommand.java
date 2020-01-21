package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.commons.codec.binary.Base64;

import dao.ImageDaoImpl;
import exception.DaoException;

public class DescriptionCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		try {
			ImageDaoImpl imageDaoImpl = new ImageDaoImpl();
			request.setAttribute("description", request.getParameter("description"));
			try {
				byte[] image = imageDaoImpl.getById(Integer.parseInt(request.getParameter("image_id"))).getImage();

				String imageS = new String(Base64.encodeBase64(image), "UTF-8");
				request.setAttribute("image", imageS);
				forward("description");
			} catch (NullPointerException e) {
				log.error(e);
				e.printStackTrace();
				request.setAttribute("exception message", "database failure");
				forward("error");
			}

		} catch (DaoException e) {
			log.error(e);
			e.printStackTrace();
			request.setAttribute("exception message", "database failure");
			forward("error");
		}
	}
}
