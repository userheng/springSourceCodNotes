package com.heng.springAnnotation;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.heng.beans.Person;
import com.heng.config.MyConfig;

public class AppTest {

	@Test
	public void contextLoad() {
		//AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.heng");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
		
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		for(String name : beanDefinitionNames) {
			System.out.println("-->"+name);
		}
		
		System.out.println(context.getBean(Person.class));
		context.close();
	}
}
