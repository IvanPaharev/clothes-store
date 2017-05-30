package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.UserDao;
import com.netcracker.store.persistence.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


/**
 * Created by A-one on 10.04.2017.
 */
@Repository
public class MySqlUserDao extends MySqlBaseDao<User, Integer> implements UserDao {
    public MySqlUserDao() {
        super(User.class);
    }

    @Override
    public User getUserByEmail(String email) {
        CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("email"), email));
        List<User> users = entityManager.createQuery(criteriaQuery).getResultList();
        User user = null;
        if(!users.isEmpty()) {
            user = users.get(0);
        }
        return user;
    }
}
