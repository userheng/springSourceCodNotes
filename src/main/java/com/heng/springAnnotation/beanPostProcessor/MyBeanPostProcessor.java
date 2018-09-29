package com.heng.springAnnotation.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor{

	public Object postProcessAfterInitialization(Object bean, String arg1) throws BeansException {
		System.out.println(bean.getClass().getName()+"： bean执行初始化方法之后执行.");
		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String arg1) throws BeansException {
		System.out.println(bean.getClass().getName()+"： bean执行初始化方法之前执行.");
		return bean;
	}

}
