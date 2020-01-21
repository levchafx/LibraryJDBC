package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import db.DatabaseConnection;
import exception.DaoException;

public abstract class AbstractCrudDao<T> implements CrudDao<T> {
	final Logger log = Logger.getLogger(AbstractCrudDao.class);

	@Override
	public Connection getConnection() throws SQLException {
		Connection cn = DatabaseConnection.getDataSource().getConnection();
		return cn;
	}

	@Override
	public void closeConnection(Connection cn) throws SQLException {
		cn.close();
	}

	protected abstract T resultSetMapper(ResultSet rs) throws SQLException, DaoException;

	protected abstract void updateMapper(PreparedStatement ps, T value) throws SQLException;

	protected abstract void byIdMapper(PreparedStatement ps, int id) throws SQLException;

	protected abstract void createMapper(PreparedStatement ps, T t) throws SQLException;

	protected abstract String allQuery();

	protected abstract String byIdQuery();

	protected abstract String updateQuery();

	protected abstract String deleteQuery();

	protected abstract String createQuery();

	@Override
	public T getById(int id) throws DaoException {
		T t = null;
		ResultSet rs = null;
		try (Connection cn = getConnection(); PreparedStatement ps = cn.prepareStatement(byIdQuery())) {
			byIdMapper(ps, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				t = resultSetMapper(rs);
			}
		} catch (Exception e) {
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
		return t;
	}

	@Override
	public List<T> getAll() throws DaoException {
		List<T> tees = new ArrayList<T>();

		try (Connection cn = getConnection();
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery(allQuery())) {
			while (rs.next()) {
				tees.add(resultSetMapper(rs));
			}

		} catch (SQLException e) {

			log.error(e);
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}
		return tees;
	}

	@Override
	public T create(T value) throws DaoException {
		int id = 0;
		try (Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(createQuery(), Statement.RETURN_GENERATED_KEYS)) {
			createMapper(ps, value);
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}
		return getById(id);
	}

	@Override
	public T update(T value) throws DaoException {
		try (Connection cn = getConnection(); PreparedStatement ps = cn.prepareStatement(updateQuery())) {
			updateMapper(ps, value);

		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}

		return value;

	}

	@Override
	public void delete(int id) throws DaoException {
		try (Connection cn = getConnection(); PreparedStatement ps = cn.prepareStatement(deleteQuery())) {
			byIdMapper(ps, id);
			ps.executeUpdate();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}

	}

	@Override
	public int persist(T t) throws DaoException {
		ResultSet rs = null;
		int id = 0;
		try (Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(createQuery(), Statement.RETURN_GENERATED_KEYS);) {
			cn.setAutoCommit(false);
			createMapper(ps, t);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			cn.rollback();
		} catch (Exception e) {
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
		return id;
	}

}
