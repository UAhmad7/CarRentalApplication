package edu.albany.se.app.repository;

import edu.albany.se.app.model.Car;
import edu.albany.se.app.model.Country;
import edu.albany.se.app.model.Location;
import edu.albany.se.app.model.User;
import edu.albany.se.app.util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserRepository
{
	public int userExists(User user)
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		Query query = entityManager.createQuery("FROM User WHERE email=:email AND password=:password")
				.setParameter("email", user.getEmail())
				.setParameter("password", user.getPassword());
		List<User> users = query.getResultList();

		if (users.size() == 1)
		{
			return users.get(0).getId();
		}

		return 0;
	}
	public void delete(User user)
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
		entityManager.getTransaction().commit();
	}

	public boolean create(User user)
	{
		boolean success = false;
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("FROM User WHERE email=:email")
				.setParameter("email", user.getEmail());
		int userCount = query.getResultList().size();

		if (userCount == 0)
		{
			query = entityManager.createQuery("FROM Country WHERE name=:name")
					.setParameter("name", user.getCountry().getName());
			List<Country> countries = query.getResultList();

			if (countries.size() > 0)
			{
				user.setCountryId(countries.get(0).getId());
			}
			else
			{
				entityManager.persist(user.getCountry());
				user.setCountryId(user.getCountry().getId());
			}

			entityManager.merge(user);
			success = true;
		}

		entityManager.getTransaction().commit();

		return success;
	}

	public void update(User user)
	{
		User u=getById(user.getId());
		int c=u.getCountryId();
		user.setCountryId(c);
		EntityManager entityManager = PersistenceUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}

	public List<User> getAll()
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		Query query = entityManager.createQuery("FROM User");
		return query.getResultList();
	}

	public User getById(int id)
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		Query query = entityManager.createQuery("FROM User WHERE id=:id")
				.setParameter("id", id);
		List<User> result = query.getResultList();

		return result.size() > 0 ? result.get(0) : null;
	}

	public User getByEmail(String email)
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		Query query = entityManager.createQuery("FROM User WHERE email=:email")
				.setParameter("email", email);
		List<User> result = query.getResultList();

		return result.size() > 0 ? result.get(0) : null;
	}
}
