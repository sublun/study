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
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages="com.itheima.mapper", sqlSessionTemplateRef="sqlSessiontemplate1")
public class DataSource1Configuration {

	@Bean(name="datasource1")
	@ConfigurationProperties(prefix="spring.datasource.ds1")
	@Primary
	public DataSource getDataSource1() {
		DataSource dataSource = DataSourceBuilder.create().build();
		return dataSource;
	}
	
	@Bean(name="sqlSessionFactory1")
	@Primary
	public SqlSessionFactory getSqlSessionFactory1(@Qualifier("datasource1")DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		//sqlSessionFactoryBean.setMapperLocations();
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name="transactionManager1")
	@Primary
	public DataSourceTransactionManager getTransactionManager(@Qualifier("datasource1")DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean(name="sqlSessiontemplate1")
	@Primary
	public SqlSessionTemplate getSqlSessionTemplate(@Qualifier("sqlSessionFactory1")SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	/**
	 * 使用TransactionManager管理事务
	 */
	/*DefaultTransactionDefinition def = new DefaultTransactionDefinition();  
	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。  
	TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态  
	try {  
	    //逻辑代码，可以写上你的逻辑处理代码  
	    transactionManager.commit(status);  
	} catch (Exception e) {  
	    transactionManager.rollback(status);  
	}  */
	
	
	
}
