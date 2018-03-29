package com.webdev.data.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webdev.data.dao.UserDao;
import com.webdev.data.model.User;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    public EntityManager entityManager;
	
	@Override
	@Transactional(value = "jpaTransactionManager", readOnly=true)
	public User getUser(String userId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = criteriaBuilder.createQuery(User.class);
        Root<User> userCQ = cq.from(User.class);
        cq.select(userCQ);
        cq.where(criteriaBuilder.equal(userCQ.get("userId"), userId));

        TypedQuery<User> query = entityManager.createQuery(cq);
        return query.getSingleResult();
	}

	@Override
    @Transactional(value="jpaTransactionManager",readOnly = false)
	public void saveUser(User user) {
		entityManager.persist(user);

	}

	@Override
	@Transactional(value = "jpaTransactionManager", readOnly=false)
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(value = "jpaTransactionManager", readOnly=false)
	public void editUser(User user) {
		// TODO Auto-generated method stub

	}

}
