package com.webdev.data.dao.impl;

import java.util.Set;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webdev.data.dao.OrderDao;
import com.webdev.data.model.Order;
import com.webdev.data.model.User;

@Repository
public class OrderDaoImpl implements OrderDao {

/*	@Autowired
	public SessionFactory sessionFactory;
*/        
        @PersistenceContext
        public EntityManager entityManager;

	@Override
	@Transactional(value = "jpaTransactionManager", readOnly=true)
	public Order getOrder(int orderId) {
            return entityManager.find(Order.class, orderId);     
	}
        
        @Override
        @Transactional(value="jpaTransactionManager",readOnly = false)
        public void save(Order order){
            entityManager.persist(order);
            
        }

        @Override
        public Set<Order> getOrdersByUser(String userId) {
            
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> cq = criteriaBuilder.createQuery(User.class);
            Root<User> userCQ = cq.from(User.class);
            userCQ.fetch("orders",JoinType.INNER);
            cq.select(userCQ);
            cq.where(criteriaBuilder.equal(userCQ.get("userId"), userId));
            
            TypedQuery<User> query = entityManager.createQuery(cq);
            query.setHint("javax.persistence.loadGraph", createUserOrderGraph());
            
            User user = query.getSingleResult();
            return user.getOrders();
        }
        
        private EntityGraph<User> createUserOrderGraph(){
            
            EntityGraph<User> userGraph = entityManager.createEntityGraph(User.class);
            Subgraph<Object> ordersSubGraph = userGraph.addSubgraph("orders");
            ordersSubGraph.addAttributeNodes("items");
            
           return userGraph;

        }

}
