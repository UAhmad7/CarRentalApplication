package edu.albany.se.app.repository;

import edu.albany.se.app.model.Car;
import edu.albany.se.app.model.Reservation;
import edu.albany.se.app.model.User;
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

	public List<Reservation> getAll()
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		Query query = entityManager.createQuery("FROM Reservation");
		return query.getResultList();
	}

	public Reservation getByUserId(int userId)
	{
		EntityManager entityManager = PersistenceUtil.getEntityManager();

		Query query = entityManager.createQuery("FROM Reservation WHERE userId=:userId")
				.setParameter("userId", userId);
		List<Reservation> result = query.getResultList();

		return result.size() > 0 ? result.get(0) : null;
	}
}