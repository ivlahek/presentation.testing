package hr.ivlahek.presentation.testing.util;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by ivlahek on 20.11.13..
 */
@ApplicationScoped
public class EntityManagerProducer {
  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EntityManagerProducer.class);
  private EntityManagerFactory emf;

  public EntityManagerFactory getEntityManagerFactory() {
    return emf;
  }

  @PostConstruct
  public void initialize() {
    log.debug(">> initialize() EntityManagerFactory is initializing ...");
    emf = Persistence.createEntityManagerFactory("test");

    log.info("DB Initialized: dialect = {}, url = {}, driver = {}, user = {}",
        emf.getProperties().get("hibernate.dialect"),
        emf.getProperties().get("javax.persistence.jdbc.url"),
        emf.getProperties().get("javax.persistence.jdbc.driver"),
        emf.getProperties().get("javax.persistence.jdbc.user"));

    log.debug("<< initialize() EntityManagerFactory is initialized.");
  }


  @PreDestroy
  public void destroy() {
    if (emf != null) {
      log.debug("Closing EntityManagerFactory ...");
      emf.close();
    }
  }

  @Default
  @Produces
  @RequestScoped
  public EntityManager getEntityManager() {
    final EntityManager em = emf.createEntityManager();
    log.debug("created new EntityManager");
    return em;
  }


  public void close(@Disposes EntityManager em) {
    log.debug("closing EntityManager, isOpen = {}, isActive = {}", em.isOpen(), em.getTransaction() != null ? em.getTransaction().isActive() : null);

    if (em.isOpen()) {
      try {
        if (em.getTransaction().isActive()) {
          em.getTransaction().rollback();
        }
      } catch (Exception e1) {
        log.error("Rollback transaction exception : ", e1);
      }

      try {
        em.clear();
        em.close();
      } catch (Exception e3) {
        log.info("Closing exception : ", e3);
      }
    }
  }


  @Override
  public String toString() {
    return String.format("EntityManagerFactory isOpen = {}", emf != null ? emf.isOpen() : null);
  }
}
