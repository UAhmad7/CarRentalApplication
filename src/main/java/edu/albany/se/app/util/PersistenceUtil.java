package edu.albany.se.app.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

public class PersistenceUtil
{
	@PersistenceUnit
	private static EntityManagerFactory entityManagerFactory = null;

	private static EntityManagerFactory getEntityManagerFactory()
	{
		if (entityManagerFactory == null)
		{
			entityManagerFactory = Persistence.createEntityManagerFactory("Application");
		}

		return entityManagerFactory;
	}

	public static EntityManager getEntityManager()
	{
		return getEntityManagerFactory().createEntityManager();
	}
}
