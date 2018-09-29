## Spring系列源码阅读

### BeanPostProcessor的生命周期

```java
public interface BeanPostProcessor {
    //在bean执行初始化之前执行
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;
    //在bean执行初始化之后执行
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}

//BeanPostProcessor在应用上下文刷新refresh()中的finishBeanFactoryInitialization(beanFactory);阶段执行

protected Object doCreateBean(final String beanName, final RootBeanDefinition mbd, final Object[] args)
    throws BeanCreationException {
    ...
        try {
			populateBean(beanName, mbd, instanceWrapper);//Bean对象属性注入
			if (exposedObject != null) {
				exposedObject = initializeBean(beanName, exposedObject, mbd);
                 /*
                 if (mbd == null || !mbd.isSynthetic()) {
					wrappedBean = 
					//调用所有的BeanPostProcessor的postProcessBeforeInitialization方法
					//若返回bean为null则跳出循环
					applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
				}

			 	try {
			 		//Bean的初始化调用
					invokeInitMethods(beanName, wrappedBean, mbd);
				}catch (Throwable ex) {
					throw new BeanCreationException(
					(mbd != null ? mbd.getResourceDescription() : null),
					beanName, "Invocation of init method failed", ex);
				}
				if (mbd == null || !mbd.isSynthetic()) {
					wrappedBean = 
					//调用所有的BeanPostProcessor的postProcessAfterInitialization方法
					//若返回bean为null则跳出循环
					applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
				}
                 */
                
			}
		}
    ...
}

```

**Bean**的生命周期

1. 对象创建

2. 对象初始化

   - `@Bean(initMethod="xxx")`
   - JSR `@PostConstruct`
   - Spring `org.springframework.beans.factory.DisposableBean`

3. 对象销毁
   - `@Bean(initMethod="xxx")`
   - JSR `@PreDestroy`
   - Spring `org.springframework.beans.factory.InitializingBean`

**执行顺序优先级**: `JSR` >  `org.springframework.beans.factory.xxx` > `@Bean` 

- `JSR`  @PostConstruct由CommonAnnotationBeanPostProcessor.postProcessBeforeInitialization()来解析调用 【InitDestroyAnnotationBeanPostProcessor】

  ```java
  protected void invokeInitMethods(String beanName, final Object bean, RootBeanDefinition mbd)
  			throws Throwable {
  
  		boolean isInitializingBean = (bean instanceof InitializingBean);
  		if (isInitializingBean && (mbd == null || !mbd.isExternallyManagedInitMethod("afterPropertiesSet"))) {
  			if (logger.isDebugEnabled()) {
  				logger.debug("Invoking afterPropertiesSet() on bean with name '" + beanName + "'");
  			}
  			if (System.getSecurityManager() != null) {
  				try {
  					AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
  						@Override
  						public Object run() throws Exception {
  							((InitializingBean) bean).afterPropertiesSet();
  							return null;
  						}
  					}, getAccessControlContext());
  				}
  				catch (PrivilegedActionException pae) {
  					throw pae.getException();
  				}
  			}
  			else {
                  //org.springframework.beans.factory.InitializingBean.afterPropertiesSet()
                  //得到调用
  				((InitializingBean) bean).afterPropertiesSet();
  			}
  		}
  
  		if (mbd != null) {
  			String initMethodName = mbd.getInitMethodName();
  			if (initMethodName != null && !(isInitializingBean && "afterPropertiesSet".equals(initMethodName)) &&
  					!mbd.isExternallyManagedInitMethod(initMethodName)) {
                  //@Bean标识的initMethod得到处理调用
  				invokeCustomInitMethod(beanName, bean, mbd);
  			}
  		}
  	}
  ```

  

**AbstractApplicationContext.refresh():** 刷新spring应用上下文

```java
@Override
public void refresh() throws BeansException, IllegalStateException {
	synchronized (this.startupShutdownMonitor) {
		// Prepare this context for refreshing.
		prepareRefresh();

		// Tell the subclass to refresh the internal bean factory.
		ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

		// Prepare the bean factory for use in this context.
		prepareBeanFactory(beanFactory);

		try {
			// Allows post-processing of the bean factory in context subclasses.
			postProcessBeanFactory(beanFactory);

			// Invoke factory processors registered as beans in the context.
			invokeBeanFactoryPostProcessors(beanFactory);

			// Register bean processors that intercept bean creation.
			registerBeanPostProcessors(beanFactory);

			// Initialize message source for this context.
			initMessageSource();

			// Initialize event multicaster for this context.
			initApplicationEventMulticaster();

			// Initialize other special beans in specific context subclasses.
			onRefresh();

			// Check for listener beans and register them.
			registerListeners();

             //实例化所有剩下的non-lazy-init的单实例
             //先getBean,没有则createBean
			finishBeanFactoryInitialization(beanFactory);

			// Last step: publish corresponding event.
			finishRefresh();
		}

		catch (BeansException ex) {
			if (logger.isWarnEnabled()) {
				logger.warn("Exception encountered during context initialization - " +
						"cancelling refresh attempt: " + ex);
			}

			// Destroy already created singletons to avoid dangling resources.
			destroyBeans();

			// Reset 'active' flag.
			cancelRefresh(ex);

			// Propagate exception to caller.
			throw ex;
		}

		finally {
			// Reset common introspection caches in Spring's core, since we
			// might not ever need metadata for singleton beans anymore...
			resetCommonCaches();
		}
	}
}
```

### Spring注解

**@Value**

赋值方式

1. `@Value(基本数值)`
2. `@Value(#{20 -2}`  SPEL表达式
3. `@Value(${xxx})`   获取配置文件或系统环境中的值