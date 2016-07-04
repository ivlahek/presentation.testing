package hr.ivlahek.presentation.testing.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

/**
 * Created by ivlahek on 23.6.2016..
 */
public abstract class AbstractRepository<E> {
    @PersistenceContext(name = "test", unitName = "test", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public E findById(Object id) {
        return entityManager.find(returnEntityClass(), id);
    }

    public List<E> findAll() {
        String criteriaQuery = "from " + returnEntityClass().getName() + " ";
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    protected abstract Class<E> returnEntityClass();

    public void persist(E e) {
        this.entityManager.persist(e);
    }

    public void remove(E e) {
        this.entityManager.remove(e);
    }

    public E merge(E e) {
        return this.entityManager.merge(e);
    }




}
