package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by A-one on 10.04.2017.
 */
@Component
public abstract class MySqlBaseDao<E, ID extends Serializable> implements BaseDao<E, ID> {
    protected Class<? extends E> daoClass;

    @Autowired
    private SessionFactory sessionFactory;

    public MySqlBaseDao(Class<E> daoClass) {
        this.daoClass = daoClass;
    }

    @Override
    public void add(E e) {
        getCurrentSession().persist(e);
    }

    @Override
    public void addAll(Collection<E> collection) {
        Session session = getCurrentSession();
        for (E e : collection) {
            session.persist(e);
        }
    }

    @Override
    public E get(ID id) {
        return getCurrentSession().get(daoClass, id);
    }

    @Override
    public void update(E e) {
        getCurrentSession().update(e);
    }

    @Override
    public void delete(E e) {
        if (e != null) {
            getCurrentSession().delete(e);
        }
    }

    @Override
    public void delete(ID id) {
        E e = getCurrentSession().get(daoClass, id);
        if (e != null) {
            getCurrentSession().delete(e);
        }
    }

    @Override
    public List<E> getAll() {
        Session session = getCurrentSession();
/*        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<E> criteria = builder.createQuery((Type<E>) daoClass);
        Root<E> root = criteria.from((Type<E>) daoClass);
        criteria.select(root);*/
        return session.createCriteria(daoClass).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();//session.createQuery(criteria).list();
    }

    @Override
    public void deleteAll(Collection<E> collection) {
        Session session = getCurrentSession();
        for (E e : collection) {
            session.delete(e);
        }
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
