package edu.albany.se.app.repository;

import edu.albany.se.app.model.Location;
import edu.albany.se.app.util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class LocationRepository
{
	public List<Location> getAll()
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		Query query = entityManager.createQuery("FROM Location");
		return query.getResultList();
	}

	public void update(Location location)
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(location);
		entityManager.getTransaction().commit();
	}


	public Location getById(int id)
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		Query query = entityManager.createQuery("FROM Location WHERE id=:id")
				.setParameter("id", id);
		List<Location> result = query.getResultList();

		return result.size() > 0 ? result.get(0) : null;
	}
	public void delete(Location location)
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.contains(location) ? location : entityManager.merge(location));
		entityManager.getTransaction().commit();
	}

}
