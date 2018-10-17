package com.heng.config.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.heng.aop.AspectObj;
import com.heng.aop.TargetObj;

@EnableAspectJAutoProxy//开启基于注解的AOP模式
//<aop:aspectj-autoproxy />
@Configuration
@ComponentScan(basePackages="com.heng.beans")
public class AopConfig {

	@Bean
	public TargetObj targetObj() {
		return new TargetObj();
	}
	
	@Bean
	public AspectObj aspectObj() {
		return new AspectObj();
	}
	
}
