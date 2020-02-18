package com.company.recycling.core.util;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

	@Autowired
	private Environment environment;

	
//	@Bean(name = "dataSource")
//	public DataSource dataSource() {
//		DataSource dataSource = null;
//		JndiTemplate jndi = new JndiTemplate();
//		try {
//			//System.out.println(environment.getProperty("jndi.dataSource.webCliente"));
//			dataSource = (DataSource) jndi.lookup("java:/PostgresDS");
//			System.out.println(dataSource.getConnection());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return dataSource;
//	}
	
	
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		DatosConexion datosConexion = new DatosConexion();
		dataSource.setDriverClassName(datosConexion.getDriverclassname());
		dataSource.setUrl(datosConexion.getUrl());
		dataSource.setUsername(datosConexion.getUsername());
		dataSource.setPassword(datosConexion.getPassword());
		return dataSource;
	}
	
//    @Bean
//    public DataSourceTransactionManager getTransactionManager(){
//    	DataSourceTransactionManager transactionManager=new DataSourceTransactionManager(dataSource());
//    	return transactionManager;
//    }
}