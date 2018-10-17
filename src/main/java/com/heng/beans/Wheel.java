package com.heng.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

@Component
public class Wheel implements BeanNameAware, ApplicationContextAware, EmbeddedValueResolverAware {

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		System.out.println("EmbeddedValueResolverAware: "+resolver.resolveStringValue("current OS is ${os.name}, i am #{24 -1 }-year-old"));
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("ApplicationContextAware: "+applicationContext);
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("BeanNameAware: "+ name);
	}

}
