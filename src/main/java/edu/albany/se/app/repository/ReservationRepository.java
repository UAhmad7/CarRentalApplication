package edu.albany.se.app.repository;

import edu.albany.se.app.model.Reservation;
import edu.albany.se.app.util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ReservationRepository
{
	public void addReservation(Reservation reservation)
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(reservation);
		entityManager.getTransaction().commit();
	}

	public Reservation getById(int id)
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		Query query = entityManager.createQuery("FROM Reservation WHERE id=:id")
				.setParameter("id", id);
		List<Reservation> result = query.getResultList();

		return result.size() > 0 ? result.get(0) : null;
	}
	public void delete(Reservation reservation)
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.contains(reservation) ? reservation : entityManager.merge(reservation));
		entityManager.getTransaction().commit();
	}

	public List<Reservation> getAll()
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		Query query = entityManager.createQuery("FROM Reservation");
		return query.getResultList();
	}

	public List<Reservation> getByUserId(int userId)
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		Query query = entityManager.createQuery("FROM Reservation WHERE userId=:userId")
				.setParameter("userId", userId);
		List<Reservation> result = query.getResultList();

		return result;
//		return result.size() > 0 ? result.get(0) : null;
	}
}
