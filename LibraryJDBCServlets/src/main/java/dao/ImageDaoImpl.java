package dao;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Image;

public class ImageDaoImpl extends AbstractCrudDao<Image> {

	@Override
	protected Image resultSetMapper(ResultSet rs) throws SQLException {
		Image i = new Image();

		i.setId(rs.getInt("id"));
		i.setName(rs.getString("name"));
		i.setBookId(rs.getInt("book_id"));
		java.sql.Blob blob = (rs.getBlob("image"));
		byte[] array = blob.getBytes(1, (int) blob.length());

		i.setImage(array);
		return i;
	}

	@Override
	protected void updateMapper(PreparedStatement ps, Image value) throws SQLException {

		ps.setInt(1, value.getBookId());
		ps.setString(2, value.getName());
		Blob blob = new javax.sql.rowset.serial.SerialBlob(value.getImage());
		ps.setBlob(3, blob);

		ps.executeUpdate();
	}

	@Override
	protected void byIdMapper(PreparedStatement ps, int id) throws SQLException {
		ps.setInt(1, id);

	}

	@Override
	protected void createMapper(PreparedStatement ps, Image t) throws SQLException {
		ps.setInt(1, t.getBookId());
		ps.setString(2, t.getName());
		byte[] image = t.getImage();

		ps.setBlob(3, new javax.sql.rowset.serial.SerialBlob(image));
		ps.executeUpdate();
	}

	@Override
	protected String allQuery() {

		return "select*from image";
	}

	@Override
	protected String byIdQuery() {

		return "select*from image where book_id=?";
	}

	@Override
	protected String updateQuery() {

		return "update image set book_id=?,name=?,image=? where book_id=?";
	}

	@Override
	protected String deleteQuery() {

		return "delete from image where book_id =?";
	}

	@Override
	protected String createQuery() {

		return "insert into image (book_id,name,image) values(?,?,?)";
	}

}
