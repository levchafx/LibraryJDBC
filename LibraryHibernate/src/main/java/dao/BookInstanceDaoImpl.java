package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import model.Book;
import model.BookInstance;

public class BookInstanceDaoImpl extends AbstractCrudDao<BookInstance> {
	BookDaoImpl bookDaoImpl = new BookDaoImpl();

	@Override
	public BookInstance create(BookInstance value) {

		BookInstance bi = super.create(value);
		Book b = bookDaoImpl.findById(value.getBook_id());
		b.setQuantity(b.getQuantity() - 1);
		bookDaoImpl.update(b);
		return bi;
	}

	@Override
	public void delete(int id) {
		BookInstance bi = findById(id);

		Book b = bookDaoImpl.findById(bi.getBook_id());

		b.setQuantity(b.getQuantity() + 1);
		bookDaoImpl.update(b);
		super.delete(id);

	}

	public List<BookInstance> getPastDueBooks() {
		String sql = "select bi from Bookinstance where bi.duedate<:dueDate";
		List<BookInstance> books = new ArrayList<>();
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		books = em.createQuery(sql, BookInstance.class).setParameter("dueDate", LocalDate.now()).getResultList();
		em.getTransaction().commit();
		em.close();
		return books;
	}

	@Override
	protected String getSqlForGetAll() {

		return "select bi from BookInstance bi";
	}

	@Override
	Class<BookInstance> getTClass() {

		return BookInstance.class;
	}

	public List<BookInstance> getAllById(int id) {
		String sql = "select bi from BookInstance bi where user_id=:id";
		List<BookInstance> books = new ArrayList<>();
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		books = em.createQuery(sql, BookInstance.class).setParameter("id", id).getResultList();
		em.getTransaction().commit();
		em.close();
		return books;
	}

}
