package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Authenticate;

public class AuthenticateDaoImpl extends AbstractCrudDao<Authenticate> implements AuthenticateDao {

	@Override
	protected String byIdQuery() {

		return "select * from authenticate where id=(?)";
	}

	@Override
	protected String allQuery() {
		// TODO Auto-generated method stub
		return "select*from authenticate";
	}

	@Override
	protected String createQuery() {
		return "insert into authenticate (login,password,profile_enable) values(?,?,?)";
	}

	@Override
	protected String deleteQuery() {

		return "delete from authenticate where id =?";
	}

	@Override
	protected String updateQuery() {

		return "update authenticate set login=?,password=?,profile_enable=? where id=?;";
	}

	@Override
	protected void byIdMapper(PreparedStatement ps, int id) throws SQLException {
		ps.setInt(1, id);

	}

	@Override
	protected void createMapper(PreparedStatement ps, Authenticate t) throws SQLException {

		ps.setString(1, t.getLogin());
		ps.setString(2, t.getPassword());
		ps.setBoolean(3, true);

	}

	@Override
	protected Authenticate resultSetMapper(ResultSet rs) throws SQLException {
		Authenticate auth = new Authenticate(rs.getInt("id"), rs.getString("login"), rs.getString("password"),
				Boolean.valueOf(rs.getBoolean("profile_enable")));
		return auth;
	}

	@Override
	protected void updateMapper(PreparedStatement ps, Authenticate value) throws SQLException {
		ps.setString(1, value.getLogin());
		ps.setString(2, value.getPassword());
		ps.setBoolean(3, value.isProfileEnabled());
		ps.setInt(4, value.getId());

	}

}
