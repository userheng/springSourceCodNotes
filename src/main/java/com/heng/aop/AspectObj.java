package com.heng.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect//告诉Spring该类是切面类
public class AspectObj {

	@Pointcut("execution(public int com.heng.aop.TargetObj.*(..))")
	public void pointCut() {
		
	}
	
	@Before("pointCut()")
	public void before(JoinPoint jp) {
		System.out.println("Method: "+jp.getSignature().getName()+" @Before()");
	}
	
	@After("pointCut()")
	public void after(JoinPoint jp) {
		System.out.println("Method: "+jp.getSignature().getName()+" @After()");
	}
	
	@AfterReturning(value="pointCut()", returning="result")
	public void afterRun(JoinPoint jp, Object result) {
		System.out.println("Method: "+jp.getSignature().getName()+" @AfterReturning() " + result);
	}
	
	@AfterThrowing(value="pointCut()",throwing="e")
	public void afterRun(JoinPoint jp, Exception e) {
		System.out.println("Method: "+jp.getSignature().getName()+" @AfterThrowing() "+e);
	}
	
	
}
