package edu.albany.se.app.repository;

import edu.albany.se.app.model.Car;
import edu.albany.se.app.util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CarRepository
{
	public List<Car> getAll()
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		Query query = entityManager.createQuery("FROM Car");
		return query.getResultList();
	}

	public Car getById(int id)
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		Query query = entityManager.createQuery("FROM Car WHERE id=:id")
				.setParameter("id", id);
		List<Car> result = query.getResultList();

		return result.size() > 0 ? result.get(0) : null;
	}

	public void add(Car car)
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(car);
		entityManager.getTransaction().commit();
	}
}
