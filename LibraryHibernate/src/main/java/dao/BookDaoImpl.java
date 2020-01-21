package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import model.Author;
import model.Book;

public class BookDaoImpl extends AbstractCrudDao<Book> {
	AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();

	@Override
	protected String getSqlForGetAll() {

		return "select b from Book b";
	}

	@Override
	Class<Book> getTClass() {

		return Book.class;
	}

	public List<Author> getAuthors(int id) {
		List<Author> authors = new ArrayList<Author>();
		String sql = "select b.authors from Book b where b.id=:id";
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		authors = em.createQuery(sql).setParameter("id", id).getResultList();
		em.getTransaction().commit();
		em.close();
		return authors;
	}

	public List<Book> searchBook(String s) {

		List<Book> books = new ArrayList<>();
		String sql = "SELECT b from Book b join fetch b.authors a where b.title like '%" + s + "%' or a.name like '%"
				+ s + "%' or a.surname like '%" + s + "%'";
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		books = em.createQuery(sql, Book.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return books;
	}

}
