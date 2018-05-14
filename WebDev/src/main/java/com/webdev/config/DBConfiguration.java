package com.webdev.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DBConfiguration {

	@Bean
	@Profile(value="dev")
	public DataSource getDataSource() {
				
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(new DriverManagerConnectionFactory("jdbc:postgresql://localhost:5432", "postgres", "mkotra"), null);
		GenericObjectPool<PoolableConnection> objectPool = new GenericObjectPool<>(poolableConnectionFactory);
		poolableConnectionFactory .setPool(objectPool);
		
		return new PoolingDataSource<PoolableConnection>(objectPool);
	}

}
