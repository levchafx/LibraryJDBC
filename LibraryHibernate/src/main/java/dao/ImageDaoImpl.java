package dao;

import model.Image;

public class ImageDaoImpl extends AbstractCrudDao<Image> {

	@Override
	protected String getSqlForGetAll() {

		return "select i from Image i";
	}

	@Override
	Class<Image> getTClass() {

		return Image.class;
	}

}
