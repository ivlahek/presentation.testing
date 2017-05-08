package hr.ivlahek.presentation.testing.util;

import hr.ivlahek.presentation.testing.entity.City;
import hr.ivlahek.presentation.testing.repository.AbstractRepository;
import hr.ivlahek.presentation.testing.repository.CountryRepository;
import hr.ivlahek.presentation.testing.service.UserService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.File;

/**
 * Created by ivlahek on 23.4.2017..
 */
@RunWith(Arquillian.class)
public class ArquillianBaseClass {

  @Inject
  protected EntityManager entityManager;
  @Inject
  protected CountryRepository countryRepository;

  @Deployment
  public static Archive<?> createDeployment() {
    File[] files = Maven.resolver()
        .loadPomFromFile("pom.xml")
        .importDependencies(ScopeType.COMPILE)
        .resolve()
        .withTransitivity()
        .asFile();

    WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test2.war")
        .addAsLibraries(files)
        .addPackages(true, City.class.getPackage())
        .addPackages(true, AbstractRepository.class.getPackage())
        .addClass(EntityManagerProducer.class)
        .addClass(UserService.class)
        .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    System.out.println(webArchive.toString(true));
    return webArchive;
  }

  @Before
  public void openTransaction() {
    entityManager.joinTransaction();
    entityManager.getTransaction().begin();
    entityManager.clear();
  }

  @After
  public void tearDown() {
    entityManager.joinTransaction();
    entityManager.getTransaction().rollback();
  }
}
