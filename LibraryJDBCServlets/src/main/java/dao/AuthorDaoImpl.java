package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.DaoException;
import model.Author;
import model.Book;

public class AuthorDaoImpl extends AbstractCrudDao<Author> {
	// BookDaoImpl bookDaoImpl = new BookDaoImpl();

	@Override
	protected Author resultSetMapper(ResultSet rs) throws SQLException {
		Author a = new Author();
		a.setId(rs.getInt("id"));
		a.setName(rs.getString("name"));
		a.setSurname(rs.getString("surname"));

		return a;
	}

	@Override
	protected void updateMapper(PreparedStatement ps, Author value) throws SQLException {
		ps.setString(1, value.getName());
		ps.setString(2, value.getSurname());
		ps.setInt(3, value.getId());
		ps.executeUpdate();

	}

	@Override
	protected void byIdMapper(PreparedStatement ps, int id) throws SQLException {
		ps.setInt(1, id);
		ps.executeQuery();

	}

	@Override
	protected void createMapper(PreparedStatement ps, Author t) throws SQLException {
		ps.setString(1, t.getName());
		ps.setString(2, t.getSurname());
		ps.executeUpdate();

	}

	@Override
	protected String allQuery() {

		return "select*from author";
	}

	@Override
	protected String byIdQuery() {
		// TODO Auto-generated method stub
		return "select*from author where id=?";
	}

	@Override
	protected String updateQuery() {

		return "update author set name=?,set surname=? where id=?";
	}

	@Override
	protected String deleteQuery() {

		return "delete from author where id=?";
	}

	@Override
	protected String createQuery() {
		// TODO Auto-generated method stub
		return "insert into author (name,surname) values(?,?)";
	}

	public Author verifyAuthor(Author a) throws DaoException {
		ResultSet rs = null;
		String sql = "select*from author where name=? and surname=?";
		try (Connection cn = getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setString(1, a.getName());
			ps.setString(2, a.getSurname());
			rs = ps.executeQuery();
			if (rs.next()) {
				return getById(rs.getInt("id"));
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

		return create(a);

	}

	public List<Book> getBooks(int id) throws DaoException {
		List<Book> books = new ArrayList<Book>();
		ResultSet rs = null;
		String sql = "select b.id,title,description,image_id,quantity from book b join books_authors ba on b.id = ba.book_id where author_id=?";
		try (Connection cn = getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("id"));
				b.setTitle(rs.getString("title"));
				b.setDescription(rs.getString("description"));
				b.setImageId(rs.getInt("image_id"));
				b.setQuantity(rs.getInt("quantity"));
				books.add(b);
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
		return books;
	}

}
