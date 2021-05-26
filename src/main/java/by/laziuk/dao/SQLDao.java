package by.laziuk.dao;

import by.laziuk.cars.impl.Identifiable;
import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class SQLDao<T extends Identifiable> extends Dao<T> {

    EntityManagerFactory factory;
    EntityManager manager;

    public SQLDao(Class<T> type, String name) {
        super(type);
        factory = Persistence.createEntityManagerFactory(name);
        manager = factory.createEntityManager();
    }

    @Override
    public List<T> read() {
        CriteriaQuery<T> crit = manager.getCriteriaBuilder().createQuery(type);
        crit.select(crit.from(type));
        return manager.createQuery(crit).getResultList();
    }

    @Override
    public void write(List<T> list) {
        manager.getTransaction().begin();
        for(T elem : list) manager.persist(elem);
        manager.getTransaction().commit();
    }

    @Override
    public void update(T obj) {
        manager.getTransaction().begin();
        manager.merge(obj);
        manager.getTransaction().commit();
    }

    @Override
    public void delete(String id) {
        T obj = manager.getReference(type, id);
        manager.getTransaction().begin();
        manager.remove(obj);
        manager.getTransaction().commit();
    }

    @Override
    public void create(T obj) {
        manager.getTransaction().begin();
        if(obj.getId().length() == 0) obj.setId("");
        manager.persist(obj);
        manager.getTransaction().commit();
    }

    @Override
    public T readOne(String id) {
        if(id == null) {
            List<T> res = read();
            return res.size() > 0 ? res.get(0) : null;
        }

        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<T> crit = cb.createQuery(type);
        Root<T> q = crit.from(type);
        crit.select(q);
        crit.where(cb.like(q.get("id"), id));
        return manager.createQuery(crit).getSingleResult();
    }
}
