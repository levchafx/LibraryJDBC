package dao;

import model.Message;

public class MessageDaoImpl extends AbstractCrudDao<Message> {

	@Override
	protected String getSqlForGetAll() {

		return "select m from Message m";
	}

	@Override
	Class<Message> getTClass() {

		return Message.class;
	}

}
