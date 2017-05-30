package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.DressDao;
import com.netcracker.store.persistence.dto.Criteria;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Dress;
import com.netcracker.store.persistence.entity.Manufacturer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
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
    public List<Dress> getAllByCriteria(Criteria criteria, String type) {
        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Dress> criteriaQuery = builder.createQuery(Dress.class);
        Root<Dress> root = criteriaQuery.from(Dress.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.and(
                root.get("category").in(criteria.getCategories()),
                root.get("manufacturer").in(criteria.getManufacturers())),
                builder.ge(root.get("price"), criteria.getPriceFrom()),
                builder.le(root.get("price"), criteria.getPriceTo()),
                builder.equal(root.get("type").get("name"), type)
        );
        if (criteria.getSort().isAsc()) {
            criteriaQuery.orderBy(builder.asc(root.get(criteria.getSort().getParameter())));
        } else {
            criteriaQuery.orderBy(builder.desc(root.get(criteria.getSort().getParameter())));
        }
        TypedQuery<Dress> dressTypedQuery = entityManager.createQuery(criteriaQuery);
        dressTypedQuery.setFirstResult((criteria.getPageNumber() - 1) * criteria.getPageSize());
        dressTypedQuery.setMaxResults(criteria.getPageSize());
        return dressTypedQuery.getResultList();
    }

    public long getQueryCount(Criteria criteria, String type) {
        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<Dress> root = countQuery.from(Dress.class);
        countQuery.select(builder.count(root));
        countQuery.where(builder.and(
                root.get("category").in(criteria.getCategories()),
                root.get("manufacturer").in(criteria.getManufacturers())),
                builder.ge(root.get("price"), criteria.getPriceFrom()),
                builder.le(root.get("price"), criteria.getPriceTo()),
                builder.equal(root.get("type").get("name"), type)
        );
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
