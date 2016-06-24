package hr.ivlahek.presentation.testing.repository;

import hr.ivlahek.presentation.testing.repository.CountryRepository;
import hr.ivlahek.presentation.testing.util.EntityManagerUtil;
import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;

/**
 * Created by ivlahek on 24.6.2016..
 */
public abstract class IntegrationTest {

    protected CountryRepository countryRepository;
    protected CityRepository cityRepository;
    protected UserRepository userRepository;
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        EntityManagerUtil entityManagerUtil = EntityManagerUtil.getInstance();
        entityManager = entityManagerUtil.createEM();
        entityManager.joinTransaction();
        entityManager.getTransaction().begin();

        countryRepository = new CountryRepository();
        countryRepository.setEntityManager(entityManager);

        cityRepository = new CityRepository();
        cityRepository.setEntityManager(entityManager);

        userRepository = new UserRepository();
        userRepository.setEntityManager(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.joinTransaction();
        entityManager.getTransaction().rollback();
    }
}
