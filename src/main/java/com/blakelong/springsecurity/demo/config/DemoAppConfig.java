package com.blakelong.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@PropertySource("classpath:persistence-mysql.properties")
@ComponentScan(basePackages="com.blakelong.springsecurity.demo")
public class DemoAppConfig implements WebMvcConfigurer {

	// set up variable that will be injected with properties by use of @PropertySource
	@Autowired
	private Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	@Bean 
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	// define a bean for security datasource - significantly less with xml
	@Bean
	public DataSource securityDataSouce() {
			
		// create connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		// set the jdbc driver class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		
		// logging to make sure we have the correct information from properties file
		logger.info("===>> jdbc.url: " + env.getProperty("jdbc.url"));
		logger.info("===>> jdbc.user: " + env.getProperty("jdbc.user"));
		
		// set database connection props
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// set connection pool props
		securityDataSource.setInitialPoolSize(getIntPropety("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntPropety("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntPropety("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntPropety("connection.pool.maxIdleTime"));
		
		return securityDataSource;	
	}
	
	// helper to read environment property and convert to int
	private int getIntPropety(String prop) {
		return Integer.parseInt(env.getProperty(prop));
	}
	
	
	// config to serve content from css directory and need to implement WebMvcConfigurer
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	}
}
