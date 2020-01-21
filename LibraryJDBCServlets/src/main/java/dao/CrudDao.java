package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import exception.DaoException;

public interface CrudDao<T> {
	Connection getConnection() throws SQLException;

	void closeConnection(Connection cn) throws SQLException;

	int persist(T t) throws DaoException;

	T getById(int id) throws DaoException;

	List<T> getAll() throws DaoException;

	T create(T value) throws DaoException;

	T update(T value) throws DaoException;

	void delete(int id) throws DaoException;

}
