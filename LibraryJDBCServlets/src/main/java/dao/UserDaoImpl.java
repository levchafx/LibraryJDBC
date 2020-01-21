package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DatabaseConnection;
import exception.DaoException;
import model.Authenticate;
import model.Role;
import model.User;

public class UserDaoImpl extends AbstractCrudDao<User> {
	private RoleDaoImpl roleDaoImpl = new RoleDaoImpl();
	private AuthenticateDaoImpl authenticateDaoImpl = new AuthenticateDaoImpl();

	private void updateRoles(int roleId, int userId, Connection cn) throws SQLException {
		PreparedStatement ps = cn.prepareStatement("Update users_roles set role_id =? where user_id=?");
		ps.setInt(1, roleId);
		ps.setInt(2, userId);
		ps.executeUpdate();
		cn.commit();

	}

	@Override
	public List<User> getAll() {
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try (Connection cn = DatabaseConnection.getDataSource().getConnection(); Statement ps = cn.createStatement()) {
			try {
				rs = ps.executeQuery(allQuery());
				while (rs.next()) {
					User u = new User();
					u.setId(rs.getInt("id"));
					u.setName(rs.getString("name"));
					u.setSurname(rs.getString("surname"));
					u.setAge(rs.getInt("age"));
					u.setEmail(rs.getString("email"));
					u.setRole(Role.valueOf(rs.getString("role")));
					u.setAuthenticate(new Authenticate(rs.getInt("id"), rs.getString("login"), rs.getString("password"),
							rs.getBoolean("profile_enable")));
					users.add(u);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				rs.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return users;

	}

	private void deleteRoles(int userId, Connection cn) throws SQLException {
		PreparedStatement ps = cn.prepareStatement("delete from users_roles where id =?");
		ps.setInt(1, userId);
		ps.executeUpdate();
	}

	private String getSqlQueryInsertRoles() {
		return "insert into users_roles (user_id,role_id) values(?,?)";
	}

	private void insertRoles(int userId, int roleId, PreparedStatement ps) throws SQLException {

		ps.setInt(1, userId);
		ps.setInt(2, roleId);
		ps.executeUpdate();

	}

	@Override
	protected String byIdQuery() {

		return "select * from users where id =(?)";
	}

	@Override
	protected String allQuery() {

		return "select users.id,name,surname,age,email,a.id,login,password,profile_enable,role from users join authenticate a on users.id = a.id join users_roles ur on users.id = ur.user_id  left join roles r on ur.role_id = r.id";
	}

	@Override
	protected String createQuery() {

		return "INSERT INTO USERS (name, surname,age, email) VALUES (?,?,?,?)";

	}

	@Override
	protected String deleteQuery() {

		return "delete from users where id=?";
	}

	@Override
	protected String updateQuery() {

		return "update users set name=?,surname=?,age=?,email=? where id=?;";
	}

	@Override
	public User update(User value) throws DaoException {
		Connection cn = null;
		int roleId = roleDaoImpl.getIdByRole(value.getRole());
		try {
			cn = getConnection();
			PreparedStatement ps = cn.prepareStatement(updateQuery());
			PreparedStatement ps1 = cn.prepareStatement(authenticateDaoImpl.updateQuery());
			cn.setAutoCommit(false);
			updateMapper(ps, value);
			ps.executeUpdate();
			authenticateDaoImpl.updateMapper(ps1, value.getAuthenticate());
			ps1.executeUpdate();
			updateRoles(roleId, value.getId(), cn);

		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				throw new DaoException(e.getMessage());
			}
		}
		return getById(value.getId());
	}

	@Override
	protected void byIdMapper(PreparedStatement ps, int id) throws SQLException {
		ps.setInt(1, id);

	}

	@Override
	protected User resultSetMapper(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setSurname(rs.getString("surname"));
		user.setEmail(rs.getString("email"));
		user.setAge(rs.getInt("age"));
		return user;
	}

	@Override
	protected void createMapper(PreparedStatement ps, User t) throws SQLException {
		ps.setString(1, t.getName());
		ps.setString(2, t.getSurname());
		ps.setInt(3, t.getAge());
		ps.setString(4, t.getEmail());

	}

	@Override
	public User create(User t) throws DaoException {
		Connection cn = null;
		int id = 0;
		int roleId = roleDaoImpl.getIdByRole(t.getRole());
		try {
			cn = getConnection();
			PreparedStatement ps = cn.prepareStatement(createQuery(), Statement.RETURN_GENERATED_KEYS);
			PreparedStatement ps1 = cn.prepareStatement(authenticateDaoImpl.createQuery());
			PreparedStatement ps2 = cn.prepareStatement(getSqlQueryInsertRoles());
			cn.setAutoCommit(false);
			createMapper(ps, t);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			authenticateDaoImpl.createMapper(ps1, t.getAuthenticate());
			ps1.executeUpdate();
			insertRoles(id, roleId, ps2);
			cn.commit();

		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		} finally {
			try {

				cn.close();
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				throw new DaoException(e.getMessage());
			}
		}

		return t;

	}

	@Override
	public void delete(int id) throws DaoException {
		Connection cn = null;

		try {
			cn = getConnection();
			PreparedStatement ps = cn.prepareStatement(deleteQuery());
			PreparedStatement ps1 = cn.prepareStatement(authenticateDaoImpl.deleteQuery());
			cn.setAutoCommit(false);
			byIdMapper(ps, id);
			ps.executeUpdate();
			byIdMapper(ps1, id);
			ps1.executeUpdate();
			deleteRoles(id, cn);
			cn.commit();

		} catch (Exception e) {

			try {
				cn.rollback();
			} catch (SQLException e1) {
				log.error(e1);
				e.printStackTrace();
				throw new DaoException(e1.getMessage());
			}

		} finally {
			try {
				cn.close();
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				throw new DaoException(e.getMessage());
			}
		}
	}

	@Override
	public User getById(int id) throws DaoException {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select users.id,name,surname,age,email,login,password,role from users join authenticate a on users.id = a.id join users_roles ur on users.id = ur.user_id  left join roles r on ur.role_id = r.id where users.id=?";
		User user = super.getById(id);
		user.setAuthenticate(authenticateDaoImpl.getById(id));
		try {
			cn = DatabaseConnection.getDataSource().getConnection();
			ps = cn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user.setRole(Role.valueOf(rs.getString("role")));

			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		} finally {
			try {
				rs.close();
				ps.close();
				cn.close();
			} catch (SQLException e) {
				log.error(e);
				e.printStackTrace();
				throw new DaoException(e.getMessage());
			}

		}
		return user;

	}

	public User verifyUser(String login, String password) throws NumberFormatException, DaoException {
		Authenticate auth = new Authenticate();
		User u = null;
		auth.setLogin(login);
		auth.setPassword(password);
		ResultSet rs = null;
		try (Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement("Select*from authenticate where login=?")) {
			ps.setString(1, login);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("login").equalsIgnoreCase(login) && rs.getString("password").equals(password)) {
					u = getById(Integer.parseInt(rs.getString("id")));
				}
			}
		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error(e);
				e.printStackTrace();
				throw new DaoException(e.getMessage());
			}
		}
		return u;

	}

	@Override
	protected void updateMapper(PreparedStatement ps, User value) throws SQLException {
		ps.setString(1, value.getName());
		ps.setString(2, value.getSurname());
		ps.setInt(3, value.getAge());
		ps.setString(4, value.getEmail());
		ps.setInt(5, value.getId());
	}

	public String verifyEmail(String email) throws DaoException {
		String s = "";
		String sql = "select email from users where email=?";
		ResultSet rs = null;
		try (Connection cn = getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				s = rs.getString("email");
			}
		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error(e);
				e.printStackTrace();
				throw new DaoException(e.getMessage());
			}
		}
		return s;
	}
}
