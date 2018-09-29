package com.heng.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

//@ConfigurationProperties SpringBoot应用中才引入的
public class Person implements InitializingBean, DisposableBean {

	@Value("baseinfo")
	private String baseinfo;
	@Value("${person.username}")
	private String username;
	@Value("${person.password}")
	private String password;
	@Value("#{23 + 1}")
	private int age;

	public Person() {
		System.out.println("constructor person init...");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean afterPropertiesSet()...");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean destroy()...");
	}

	public void beanInit() {
		System.out.println("@Bean beanInit()...");
	}

	public void beanDestroy() {
		System.out.println("@Bean beanDestroy()...");
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("JSR @PostConstruct postConstruct()...");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("JSR @PreDestroy preDestroy()...");
	}

	@Override
	public String toString() {
		return "Person [baseinfo=" + baseinfo + ", username=" + username + ", password=" + password + ", age=" + age
				+ "]";
	}

}
