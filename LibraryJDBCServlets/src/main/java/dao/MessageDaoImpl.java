package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Message;

public class MessageDaoImpl extends AbstractCrudDao<Message> {

	@Override
	protected Message resultSetMapper(ResultSet rs) throws SQLException {
		Message m = new Message();
		m.setId(rs.getInt("id"));
		m.setUserId(rs.getInt("user_id"));
		m.setMessage(rs.getString("message"));
		return m;
	}

	@Override
	protected void updateMapper(PreparedStatement ps, Message value) throws SQLException {
		ps.setInt(1, value.getUserId());
		ps.setString(2, value.getMessage());
		ps.setInt(3, value.getId());
		ps.executeUpdate();
	}

	@Override
	protected void byIdMapper(PreparedStatement ps, int id) throws SQLException {
		ps.setInt(1, id);

	}

	@Override
	protected void createMapper(PreparedStatement ps, Message t) throws SQLException {
		ps.setInt(1, t.getUserId());
		ps.setString(2, t.getMessage());
		ps.executeUpdate();

	}

	@Override
	protected String allQuery() {

		return "select*from message";
	}

	@Override
	protected String byIdQuery() {

		return "select*from message where id =?";
	}

	@Override
	protected String updateQuery() {

		return "update message set user_id=?,message =? where id=?";
	}

	@Override
	protected String deleteQuery() {

		return "delete from message where id=?";
	}

	@Override
	protected String createQuery() {

		return "insert into message (user_id,message) values(?,?)";
	}

}
