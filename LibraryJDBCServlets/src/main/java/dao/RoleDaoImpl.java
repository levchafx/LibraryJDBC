package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DatabaseConnection;
import exception.DaoException;
import model.Role;

public class RoleDaoImpl extends AbstractCrudDao<Role> implements RoleDao {

	@Override
	protected String byIdQuery() {
		// TODO Auto-generated method stub
		return "Select* from roles where id=(?)";
	}

	@Override
	protected Role resultSetMapper(ResultSet rs) throws SQLException {
		Role role = Role.valueOf(rs.getString("role"));
		return role;

	}

	@Override
	protected String allQuery() {
		// TODO Auto-generated method stub
		return "Select*from roles";
	}

	@Override
	protected String createQuery() {

		return "insert into roles (role) values (?)";
	}

	@Override
	protected String deleteQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String updateQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getIdByRole(Role role) throws DaoException {
		int i = 0;
		String sql = "select id from roles where role ='" + role + "'";
		try {
			Connection cn = DatabaseConnection.getDataSource().getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				i = rs.getInt("id");
			}

		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}
		return i;
	}

	@Override
	protected void byIdMapper(PreparedStatement ps, int id) throws SQLException {
		ps.setInt(1, id);

	}

	@Override
	protected void createMapper(PreparedStatement ps, Role t) throws SQLException {
		ps.setString(1, String.valueOf(t));

	}

	@Override
	protected void updateMapper(PreparedStatement ps, Role value) {
		// TODO Auto-generated method stub

	}

}
