package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import model.Author;
import model.Book;

public class AuthorDaoImpl extends AbstractCrudDao<Author> {

	public List<Book> getBooks(int id) {
		List<Book> books = new ArrayList<Book>();

		String sql = "select a.books from Author a where id=:id ";
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		books = em.createQuery(sql, Book.class).setParameter("id", id).getResultList();
		em.getTransaction().commit();
		em.close();
		return books;
	}

	@Override
	protected String getSqlForGetAll() {

		return "select a from Author a";
	}

	@Override
	Class<Author> getTClass() {

		return Author.class;
	}

}
