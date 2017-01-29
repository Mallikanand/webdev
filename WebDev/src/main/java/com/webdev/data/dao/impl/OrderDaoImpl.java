package com.webdev.data.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webdev.data.dao.OrderDao;
import com.webdev.data.model.Order;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	public SessionFactory sessionFactory;

	@Override
	@Transactional(readOnly=true)
	public Order getOrder(int orderId) {
		
		Criteria criteria = sessionFactory.openSession().createCriteria(Order.class);
		Object order = criteria.add(Restrictions.eq("id", 1)).uniqueResult();
		
		return (Order)order;
	}

}
