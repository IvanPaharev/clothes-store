package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.DressDao;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Dress;
import com.netcracker.store.persistence.entity.Manufacturer;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
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
@Component
public class MySqlDressDao extends MySqlBaseDao<Dress, Integer> implements DressDao {
    public MySqlDressDao() {
        super(Dress.class);
    }

    @Override
    public List<Dress> getDressesByType(String type) {
        Query query = entityManager.createQuery("from Dress where type.name = :type");
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    public List<Dress> getAllByCriteria(List<Category> categories,
                                        List<Manufacturer> manufacturers,
                                        double priceFrom,
                                        double priceTo,
                                        String type) {
        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Dress> criteriaQuery = builder.createQuery(Dress.class);
        Root<Dress> root = criteriaQuery.from(Dress.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.and(
                root.get("category").in(categories),
                root.get("manufacturer").in(manufacturers)),
                builder.ge(root.get("price"), priceFrom),
                builder.le(root.get("price"), priceTo),
                builder.equal(root.get("type").get("name"), type)
        );
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
