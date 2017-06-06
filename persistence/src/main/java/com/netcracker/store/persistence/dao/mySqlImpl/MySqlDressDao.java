package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.DressDao;
import com.netcracker.store.persistence.dto.Criteria;
import com.netcracker.store.persistence.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by A-one on 10.04.2017.
 */
@Repository
public class MySqlDressDao extends MySqlBaseDao<Dress, Integer> implements DressDao {
    public MySqlDressDao() {
        super(Dress.class);
    }

    @Override
    public List<Dress> getDressesByType(String type) {
        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Dress> criteriaQuery = builder.createQuery(Dress.class);
        Root<Dress> root = criteriaQuery.from(Dress.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("type").get("name"), type));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Dress> getAllByCriteria(Criteria criteria) {
        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Dress> dressQuery = builder.createQuery(Dress.class);
        Root<Dress> dressRoot = dressQuery.from(Dress.class);
        dressQuery.select(dressRoot);
        dressQuery.where(builder.and(
                getPredicates(criteria, dressRoot, builder)
        ));
        if (criteria.getSort().isAsc()) {
            dressQuery.orderBy(builder.asc(dressRoot.get(criteria.getSort().getParameter())));
        } else {
            dressQuery.orderBy(builder.desc(dressRoot.get(criteria.getSort().getParameter())));
        }
        TypedQuery<Dress> dressTypedQuery = entityManager.createQuery(dressQuery);
        dressTypedQuery.setFirstResult((criteria.getPageNumber() - 1) * criteria.getPageSize());
        dressTypedQuery.setMaxResults(criteria.getPageSize());
        return dressTypedQuery.getResultList();
    }

    public long getQueryCount(Criteria criteria) {
        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<Dress> root = countQuery.from(Dress.class);
        countQuery.select(builder.count(root));
        countQuery.where(builder.and(getPredicates(criteria, root, builder)));
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Predicate[] getPredicates(Criteria criteria, Root<Dress> dressRoot, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (criteria.getCategories().size() != 0) {
            predicates.add(dressRoot.get("category").in(criteria.getCategories()));
        }
        if (criteria.getManufacturers().size() != 0) {
            predicates.add(dressRoot.get("manufacturer").in(criteria.getManufacturers()));
        }
        if (criteria.getType() != null) {
            predicates.add(builder.equal(dressRoot.get("type").get("name"), criteria.getType()));
        }
        if (criteria.getColor() != null) {
            predicates.add(builder.isMember(criteria.getColor(), dressRoot.get("colors")));
        }
        for (Size size : criteria.getSizes()) {
            predicates.add(builder.isMember(size, dressRoot.get("sizes")));
        }
        predicates.add(builder.ge(dressRoot.get("price"), criteria.getPriceFrom()));
        predicates.add(builder.le(dressRoot.get("price"), criteria.getPriceTo()));
        Predicate[] predicatesArray = new Predicate[predicates.size()];
        return predicates.toArray(predicatesArray);
    }
}
