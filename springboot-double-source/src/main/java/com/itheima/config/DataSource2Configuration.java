package com.itheima.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Configuration
@MapperScan(basePackages="com.itheima.mapper2", sqlSessionTemplateRef="sqlSessiontemplate2")
public class DataSource2Configuration {

	@Bean(name="datasource2")
	@ConfigurationProperties(prefix="spring.datasource.ds2")
	public DataSource getDataSource2() {
		DataSource dataSource = DataSourceBuilder.create().build();
		return dataSource;
	}
	
	@Bean(name="sqlSessionFactory2")
	public SqlSessionFactory getSqlSessionFactory2(@Qualifier("datasource2")DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		//sqlSessionFactoryBean.setMapperLocations();
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name="transactionManager2")
	public DataSourceTransactionManager getTransactionManager(@Qualifier("datasource2")DataSource dataSource) {
		DataSourceTransactionManager transactionManager =  new DataSourceTransactionManager(dataSource);
		return transactionManager;
	}
	
	@Bean(name="sqlSessiontemplate2")
	public SqlSessionTemplate getSqlSessionTemplate(@Qualifier("sqlSessionFactory2")SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	
	
}
