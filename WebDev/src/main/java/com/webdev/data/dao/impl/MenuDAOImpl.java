package com.webdev.data.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webdev.data.dao.MenuDAO;
import com.webdev.data.model.MenuItem;

@Repository
public class MenuDAOImpl implements MenuDAO{

	/*@Autowired
	private DataSource dataSource;
	
	
	@Override
	public List<MenuItem> getMenu() {

		JdbcTemplate template = new JdbcTemplate(dataSource);
		List<MenuItem> items = template.query("select * from MenuItem", new RowMapper<MenuItem>(){

			@Override
			public MenuItem mapRow(ResultSet rs, int rowNum) throws SQLException {
				MenuItem m = new MenuItem(rs.getString(1), MenuType.valueOf(rs.getString(2)), FoodType.valueOf(rs.getString(3)), rs.getBigDecimal(4), rs.getString(4));
				return m;
			}
			
		});
		return items;
	}*/

	@Autowired
	public SessionFactory sessionFactory;

/*	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<MenuItem> getMenu() {
		Session session = sessionFactory.openSession();
		
		Query query = session
				.createSQLQuery("select id, item_name as itemName,menu_type as menuType,food_type as foodType,price,inactive FROM Menuitem")
				.addScalar("itemName",StandardBasicTypes.STRING)
				.addScalar("menuType")
				.addScalar("foodType")
				.addScalar("price")
				.addScalar("inactive");
		
		
		query.setResultTransformer(Transformers.aliasToBean(MenuItem.class));
		List<MenuItem> list = query.list();
		
		return list;
	}
*/	
	@Override
	@Transactional
	public List<MenuItem> getMenu(){
		Criteria criteria = sessionFactory.openSession().createCriteria(MenuItem.class);
		return criteria.list();
	}
}
