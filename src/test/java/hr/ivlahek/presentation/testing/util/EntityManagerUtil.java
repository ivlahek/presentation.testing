/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.ivlahek.presentation.testing.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 */
public class EntityManagerUtil {

    private static EntityManagerUtil instance;
    private static EntityManagerFactory emf;
    private final Logger logger = LoggerFactory.getLogger(EntityManagerUtil.class);


    private EntityManagerUtil() {
        try {
            emf = Persistence.createEntityManagerFactory("test");

            logger.info("DB Initialized: dialect = {}, url = {}, driver = {}, user = {}",
                    emf.getProperties().get("hibernate.dialect"),
                    emf.getProperties().get("javax.persistence.jdbc.url"),
                    emf.getProperties().get("javax.persistence.jdbc.driver"),
                    emf.getProperties().get("javax.persistence.jdbc.user"));

        } catch (RuntimeException e) {
            logger.warn("Failed initializing EntityManager: " + e.getMessage());
            throw e;
        }
    }

    public static synchronized EntityManagerUtil getInstance() {
        if (instance == null) {
            instance = new EntityManagerUtil();
        }
        return instance;
    }

    public EntityManager createEM() {
        return emf.createEntityManager();
    }

    public EntityManager startTransaction() {

        EntityManager em = createEM();
        em.getTransaction().begin();

        return em;
    }

    public void startTransaction(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
    }

    public void closeTransaction(EntityManager em) {

        EntityTransaction tx = em.getTransaction();
        try {

            if (tx.getRollbackOnly()) {
                tx.rollback();
            } else {
                tx.commit();
            }
        } finally {
            em.clear();
            em.close();
            logger.info("EM cleared and closed");
        }
    }

}
