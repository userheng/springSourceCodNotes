package com.heng.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试:当容器无法调用类的无参数构造器会怎么样
 */
@Component
public class Car {

	/*
	 * 抛出：org.springframework.beans.factory.UnsatisfiedDependencyException
	 * 原始抛出点：容器刷新方法refresh()中的
	 * finishBeanFactoryInitialization()抛出异常
	 */
//	public Car(Object test) {
//		System.out.println("Car constructor: "+test.getClass().getName());
//	}
	
	//@Autowired 初始化该对象只能调用该有参构造器  可以不声明@Autowired
	public Car(Wheel test) {
		System.out.println("Car constructor: "+test.getClass().getName());
	}

}
