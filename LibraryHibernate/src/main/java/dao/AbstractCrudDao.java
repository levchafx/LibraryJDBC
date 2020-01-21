package dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

public abstract class AbstractCrudDao<T> implements CrudDao<T> {
	final Logger log = Logger.getLogger(AbstractCrudDao.class);

	protected abstract String getSqlForGetAll();

	abstract Class<T> getTClass();

	public T create(T t) {

		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		return t;
	}

	public List<T> getAll() {
		List<T> tees;
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		tees = em.createQuery(getSqlForGetAll(), getTClass()).getResultList();
		em.getTransaction().commit();
		em.close();
		return tees;
	}

	public T update(T t) {
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();

		em.merge(t);

		em.getTransaction().commit();
		em.close();
		return t;
	}

	public void delete(int id) {
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		T t = em.find(getTClass(), id);
		em.remove(t);

		em.getTransaction().commit();
		em.close();
	}

	public T findById(int id) {
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		T t = em.find(getTClass(), id);
		em.getTransaction().commit();
		em.close();
		return t;
	}

}
