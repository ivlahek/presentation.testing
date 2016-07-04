package hr.ivlahek.presentation.testing;

import hr.ivlahek.presentation.testing.repository.AbstractRepository;
import hr.ivlahek.presentation.testing.repository.CityRepository;
import hr.ivlahek.presentation.testing.repository.CountryRepository;
import hr.ivlahek.presentation.testing.repository.UserRepository;
import hr.ivlahek.presentation.testing.util.EntityManagerUtil;
import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;

/**
 * Created by ivlahek on 4.7.2016..
 */
public class SystemTest {
    private EntityManagerUtil entityManagerUtil = new EntityManagerUtil("test-oracle");

    protected CountryRepository countryRepository;
    protected CityRepository cityRepository;
    protected UserRepository userRepository;

    protected EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        entityManager = entityManagerUtil.createEM();
        cityRepository = new CityRepository();
        cityRepository.setEntityManager(entityManager);
        countryRepository = new CountryRepository();
        countryRepository.setEntityManager(entityManager);
        userRepository = new UserRepository();
        userRepository.setEntityManager(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        delete("DELETE FROM END_USER", "DELETE FROM CITY", "DELETE FROM COUNTRY");

    }

    protected void delete(String... sqlStatements) {
        entityManager.getTransaction().begin();
        for (String sqlStatement : sqlStatements) {
            entityManager.createNativeQuery(sqlStatement).executeUpdate();
        }
        entityManager.getTransaction().commit();
    }

    protected <T> void delete(AbstractRepository<T> genericDao, T entity) {
        entityManager.getTransaction().begin();
        genericDao.remove(entity);
        entityManager.getTransaction().commit();
    }

    protected <T> void create(AbstractRepository<T> genericDao, T entity) {
        entityManager.getTransaction().begin();
        genericDao.persist(entity);
        entityManager.getTransaction().commit();
    }

    protected <T> void update(AbstractRepository<T> genericDao, T entity) {
        entityManager.getTransaction().begin();
        genericDao.persist(entity);
        entityManager.getTransaction().commit();
    }

}
