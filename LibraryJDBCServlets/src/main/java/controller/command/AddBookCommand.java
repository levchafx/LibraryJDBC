package controller.command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import dao.BookDaoImpl;
import dao.ImageDaoImpl;
import exception.DaoException;
import model.Author;
import model.Book;
import model.Image;

public class AddBookCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {

		BookDaoImpl bookDaoImpl = new BookDaoImpl();
		ImageDaoImpl imageDaoImpl = new ImageDaoImpl();
		List<Author> list = new ArrayList<>();
		try {
			Book b = new Book();
			Image i = new Image();
			b.setAuthors(list);
			b.setTitle(request.getParameter("title"));
			b.setDescription(request.getParameter("description"));
			b.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			System.out.println(request.getParameter("authors"));
			if (request.getParameter("authors").contains(",")) {
				String[] authors = request.getParameter("authors").split(",");
				for (int j = 0; j < authors.length; j++) {
					String[] author = authors[j].split(" ");
					Author a = new Author();
					a.setName(author[0]);
					a.setSurname(author[1]);
					b.getAuthors().add(a);
				}
			} else {
				String[] authors = request.getParameter("authors").split(" ");
				Author a = new Author();
				a.setName(authors[0]);
				a.setSurname(authors[1]);
				b.getAuthors().add(a);
			}
			Book book;

			book = bookDaoImpl.create(b);

			if (!request.getParameter("image").isEmpty()) {
				i.setBookId(book.getId());
				i.setName(request.getParameter("image_name"));
				File uploads = new File(request.getServletContext().getInitParameter("upload.location"));
				File f = new File(uploads, request.getParameter("image"));
				i.setImage(Files.readAllBytes(f.toPath()));
				imageDaoImpl.create(i);
			}
			forward("userCabinet");
		} catch (DaoException e) {
			log.error(e);
			request.setAttribute("exception message", new DaoException("database failure"));
			forward("error");
		}

	}

}
