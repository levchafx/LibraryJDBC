package dao;

import javax.persistence.EntityManager;

import model.User;

public class UserDaoImpl extends AbstractCrudDao<User> implements UserDao {
	@Override
	public User verifyUser(String login, String password) {
		String sql = "select u from User u where u.authenticate.login=:login and u.authenticate.password=:password";
		User u = null;
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		try {
			u = (User) em.createQuery(sql).setParameter("login", login).setParameter("password", password)
					.getSingleResult();

		} catch (Exception e) {
			return u;
		} finally {

			em.close();
		}
		return u;

	}

	@Override
	public String verifyEmail(String email) {
		String s = "";
		String sql = "select u.email from User u where u.email=:email";
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		try {
			s = (String) em.createQuery(sql).setParameter("email", email).getSingleResult();
		} catch (Exception e) {
			return s;
		}
		em.getTransaction().commit();
		em.close();
		return s;
	}

	@Override
	protected String getSqlForGetAll() {

		return "select u from User u";
	}

	@Override
	Class<User> getTClass() {

		return User.class;
	}
}
