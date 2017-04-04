package com.webdev.data.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webdev.data.dao.OrderDao;
import com.webdev.data.model.Order;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	public SessionFactory sessionFactory;
        
        @PersistenceContext
        public EntityManager entityManager;

	@Override
	@Transactional(value = "jpaTransactionManager", readOnly=true)
	public Order getOrder(int orderId) {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
            CriteriaQuery<Order> criteria = criteriaBuilder.createQuery(Order.class);
            final Root<Order> root = criteria.from(Order.class);
            criteria.select(root);
            criteria.where(criteriaBuilder.equal(root.get("id"), orderId));
            
            return entityManager.createQuery(criteria).getSingleResult();
            
	}
        
        @Override
        @Transactional(value="jpaTransactionManager",readOnly = false)
        public void save(Order order){
            entityManager.persist(order);
            
        }

}
