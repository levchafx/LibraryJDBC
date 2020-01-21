package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exception.DaoException;
import model.Book;
import model.BookInstance;

public class BookInstanceDaoImpl extends AbstractCrudDao<BookInstance> {
	BookDaoImpl bookDaoImpl = new BookDaoImpl();

	@Override
	protected BookInstance resultSetMapper(ResultSet rs) throws SQLException {
		BookInstance bi = new BookInstance();
		bi.setId(rs.getInt("id"));
		bi.setUser_id(rs.getInt("user_id"));
		bi.setBook_id(rs.getInt("book_id"));
		bi.setDueDate(rs.getDate("due_date"));
		return bi;
	}

	@Override
	protected void updateMapper(PreparedStatement ps, BookInstance value) throws SQLException {
		ps.setInt(1, value.getUser_id());
		ps.setInt(2, value.getBook_id());
		ps.setDate(3, value.getDueDate());
		ps.executeUpdate();

	}

	@Override
	protected void byIdMapper(PreparedStatement ps, int id) throws SQLException {
		ps.setInt(1, id);

	}

	@Override
	protected void createMapper(PreparedStatement ps, BookInstance t) throws SQLException {
		ps.setInt(1, t.getUser_id());
		ps.setInt(2, t.getBook_id());
		ps.setDate(3, t.getDueDate());
		ps.executeUpdate();

	}

	@Override
	protected String allQuery() {

		return "select*from book_instance";
	}

	@Override
	protected String byIdQuery() {

		return "select * from book_instance where id=?";
	}

	@Override
	protected String updateQuery() {

		return "update bookInstance set user_id=?,set book_id=?,set due_date =?";
	}

	@Override
	protected String deleteQuery() {

		return "delete from book_instance where id=?";
	}

	@Override
	protected String createQuery() {

		return "insert into book_instance (user_id,book_id,due_date) values(?,?,?)";
	}

	@Override
	public BookInstance create(BookInstance value) throws DaoException {

		BookInstance bi = super.create(value);
		Book b = bookDaoImpl.getById(value.getBook_id());
		b.setQuantity(b.getQuantity() - 1);
		bookDaoImpl.update(b);
		return bi;
	}

	@Override
	public void delete(int id) throws DaoException {
		BookInstance bi = getById(id);

		Book b = bookDaoImpl.getById(bi.getBook_id());

		b.setQuantity(b.getQuantity() + 1);
		bookDaoImpl.update(b);
		super.delete(id);

	}

	public List<BookInstance> getAllById(int id) {
		List<BookInstance> books = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select*from book_instance where user_id=?";
		try (Connection cn = getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				BookInstance bi = resultSetMapper(rs);
				books.add(bi);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return books;
	}

	public List<BookInstance> getPastDueBooks() throws DaoException {
		String sql = "select *from book_instance where due_date<?";
		List<BookInstance> books = new ArrayList<>();
		ResultSet rs = null;
		try (Connection cn = getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setDate(1, Date.valueOf(LocalDate.now()));
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
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return books;
	}

}
