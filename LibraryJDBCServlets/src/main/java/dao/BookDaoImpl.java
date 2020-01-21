package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.DaoException;
import model.Author;
import model.Book;

public class BookDaoImpl extends AbstractCrudDao<Book> {
	AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();

	@Override
	protected Book resultSetMapper(ResultSet rs) throws SQLException, DaoException {
		Book book = new Book();
		book.setId(rs.getInt("id"));
		book.setTitle(rs.getString("title"));
		book.setDescription(rs.getString("description"));
		book.setImageId(rs.getInt("image_id"));
		book.setQuantity(rs.getInt("quantity"));
		book.setAuthors(getAuthors(book.getId()));

		return book;
	}

	@Override
	protected void updateMapper(PreparedStatement ps, Book value) throws SQLException {
		ps.setString(1, value.getTitle());
		ps.setString(2, value.getDescription());
		ps.setInt(3, value.getQuantity());
		ps.setInt(4, value.getImageId());
		ps.setInt(5, value.getId());
		ps.executeUpdate();
	}

	@Override
	protected void byIdMapper(PreparedStatement ps, int id) throws SQLException {
		ps.setInt(1, id);

	}

	@Override
	protected void createMapper(PreparedStatement ps, Book t) throws SQLException {
		ps.setString(1, t.getTitle());
		ps.setString(2, t.getDescription());
		ps.setInt(3, t.getQuantity());
		ps.setInt(4, t.getImageId());

		ps.executeUpdate();

	}

	@Override
	protected String allQuery() {

		return "select * from book";
	}

	@Override
	protected String byIdQuery() {

		return "select*from book where id=?";
	}

	@Override
	protected String updateQuery() {

		return "update book set title=?, description =?, quantity=?, image_id=? where id=?";
	}

	@Override
	protected String deleteQuery() {

		return "delete from book where id=?";
	}

	@Override
	protected String createQuery() {

		return "insert into book(title,description,quantity,image_id) values (?,?,?,?)";
	}

	@Override
	public Book create(Book value) throws DaoException {
		ResultSet rs = null;

		int id = 0;
		try (Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(createQuery(), Statement.RETURN_GENERATED_KEYS)) {
			createMapper(ps, value);
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e1) {
			log.error(e1);
			e1.printStackTrace();
			throw new DaoException(e1.getMessage());
		}
		for (Author a : value.getAuthors()) {

			try (Connection cn = getConnection();
					PreparedStatement ps1 = cn
							.prepareStatement("insert into books_authors (book_id,author_id) values (?,?)")) {
				ps1.setInt(1, id);
				ps1.setInt(2, authorDaoImpl.verifyAuthor(a).getId());
				ps1.executeUpdate();

			} catch (SQLException e) {
				log.error(e);
				e.printStackTrace();
				throw new DaoException(e.getMessage());
			}

		}
		return getById(id);
	}

	public List<Author> getAuthors(int id) throws DaoException {
		List<Author> authors = new ArrayList<Author>();
		ResultSet rs = null;
		try (Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(
						"select a.id,name,surname from author a join books_authors ab on a.id=ab.author_id where ab.book_id=?")) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Author a = new Author();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setSurname(rs.getString("surname"));
				authors.add(a);
			}
		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				throw new DaoException(e.getMessage());
			}
		}
		return authors;
	}

	@Override
	public Book update(Book value) throws DaoException {
		Book b = super.update(value);
		/*
		 * for (Author a : value.getAuthors()) {
		 * 
		 * b.getAuthors().add(authorDaoImpl.verifyAuthor(a)); }
		 */
		return b;

	}

	@Override
	public void delete(int id) throws DaoException {
		String sql = "delete from books_authors where book_id=?";
		super.delete(id);
		try (Connection cn = getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			byIdMapper(ps, id);
			ps.executeUpdate();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}

	}

	public List<Book> searchBook(String s) throws DaoException {
		ResultSet rs = null;
		List<Book> books = new ArrayList<>();
		String sql = "SELECT distinct b.id,title,description,image_id,quantity from book b join author a join books_authors on b.id = book_id and a.id=author_id where title like  ?  or surname like ?  or name like ?  ";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, "%" + s + "%");
			ps.setString(2, "%" + s + "%");
			ps.setString(3, "%" + s + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				books.add(resultSetMapper(rs));
			}
		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
				e.printStackTrace();
				throw new DaoException(e.getMessage());
			}
		}
		return books;
	}

}
