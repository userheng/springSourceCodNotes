package com.heng.springAnnotation.aop;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.heng.aop.TargetObj;
import com.heng.config.aop.AopConfig;

public class AopTest {

	@Test
	public void aopTest() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
		TargetObj bean = context.getBean(TargetObj.class);
		bean.div(1, 2);
		System.out.println("=====================");
		bean.div(1, 0);
		context.close();
	}
	
	@Test
	public void aopTest2() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.heng.config.aop");
		TargetObj bean = context.getBean(TargetObj.class);
		bean.div(1, 2);
		System.out.println("=====================");
		bean.div(1, 0);
		context.close();
	}
}
