package com.webdev.data.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.webdev.data.dao.MenuDAO;
import com.webdev.data.model.MenuItem;

@Repository
public class MenuDAOImpl implements MenuDAO{

	@Autowired
	private DataSource dataSource;
	
	
	@Override
	public List<MenuItem> getMenu() {

		JdbcTemplate template = new JdbcTemplate(dataSource);
		List<MenuItem> items = template.query("select * from MenuItem", new RowMapper<MenuItem>(){

			@Override
			public MenuItem mapRow(ResultSet rs, int rowNum) throws SQLException {
				MenuItem m = new MenuItem(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBigDecimal(4), rs.getString(4));
				return m;
			}
			
		});
		return items;
	}

}
