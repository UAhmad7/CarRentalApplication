package edu.albany.se.app.repository;

import edu.albany.se.app.model.Country;
import edu.albany.se.app.util.PersistenceUtil;

import javax.persistence.EntityManager;

public class CountryRepository
{

    public void update(Country country)
    {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(country);
        entityManager.getTransaction().commit();
    }

}
