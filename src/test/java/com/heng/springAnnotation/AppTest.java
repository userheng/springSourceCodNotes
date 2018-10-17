package com.heng.springAnnotation;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
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
	
	/**
	 * 测试无参构造器调用
	 */
	@Test
	public void testNoParamConstructor() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.heng.beans");
		context.close();
	}
	
	/*
	 * org.springframework.context.annotation.AutowiredAnnotationBeanPostProcessor
	 * org.springframework.context.annotation.RequiredAnnotationBeanPostProcessor
	 * org.springframework.context.annotation.CommonAnnotationBeanPostProcessor
	 */
	@Test
	public void showbeanPostProcessors() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
		Map<String, BeanPostProcessor> beans = context.getBeansOfType(BeanPostProcessor.class);
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		System.out.println("showbeanPostProcessors->start "+ beanFactory.getClass().getName());
		if(beanFactory instanceof DefaultListableBeanFactory) {
			beanFactory = (DefaultListableBeanFactory)beanFactory;
			System.out.println(((DefaultListableBeanFactory) beanFactory).getBeanPostProcessors());
			/*
			 * [org.springframework.context.support.ApplicationContextAwareProcessor@7205765b
			 				 * , org.springframework.context.annotation.ConfigurationClassPostProcessor$ImportAwareBeanPostProcessor@47987356
			 * , org.springframework.context.support.PostProcessorRegistrationDelegate$BeanPostProcessorChecker@22ef9844
			 * , com.heng.springAnnotation.beanPostProcessor.MyBeanPostProcessor@6283d8b8
							 * , org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@3b6ddd1d
							 * , org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@3f6b0be5
							 * , org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor@611889f4
			 * , org.springframework.context.support.ApplicationListenerDetector@26a7b76d]
			 */
		}
		System.out.println("showbeanPostProcessors->end "+ beanFactory.getClass().getName());
		for(Entry<String, BeanPostProcessor> beanInfo : beans.entrySet()) {
			System.out.println(beanInfo);
		}
		context.close();
	}
	
	/*
	 * org.springframework.context.annotation.ConfigurationClassPostProcessor
	 */
	@Test
	public void showBeanFactoryProcessors() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
		Map<String, BeanFactoryPostProcessor> beans = context.getBeansOfType(BeanFactoryPostProcessor.class);
		for(Entry<String, BeanFactoryPostProcessor> beanInfo : beans.entrySet()) {
			System.out.println(beanInfo);
		}
		context.close();
	}
	
	
	/*
	 *  Car constructor: com.heng.beans.Wheel
		org.springframework.context.annotation.internalConfigurationAnnotationProcessor=org.springframework.context.annotation.ConfigurationClassPostProcessor@4efbca5a
		org.springframework.context.annotation.internalAutowiredAnnotationProcessor=org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@1b7cc17c
		org.springframework.context.annotation.internalRequiredAnnotationProcessor=org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor@59662a0b
		org.springframework.context.annotation.internalCommonAnnotationProcessor=org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@77fbd92c
		org.springframework.context.event.internalEventListenerProcessor=org.springframework.context.event.EventListenerMethodProcessor@67c27493
		org.springframework.context.event.internalEventListenerFactory=org.springframework.context.event.DefaultEventListenerFactory@1a482e36
		car=com.heng.beans.Car@72967906
		wheel=com.heng.beans.Wheel@5b8dfcc1
		environment=StandardEnvironment {activeProfiles=[], defaultProfiles=[default], propertySources=[MapPropertySource {name='systemProperties'}, SystemEnvironmentPropertySource {name='systemEnvironment'}]}
		systemProperties={java.runtime.name=Java(TM) SE Runtime Environment, sun.boot.library.path=D:\JDK\jdk1.8\jre\bin, java.vm.version=25.162-b12, java.vm.vendor=Oracle Corporation, java.vendor.url=http://java.oracle.com/, path.separator=;, java.vm.name=Java HotSpot(TM) 64-Bit Server VM, file.encoding.pkg=sun.io, user.country=CN, user.script=, sun.java.launcher=SUN_STANDARD, sun.os.patch.level=, java.vm.specification.name=Java Virtual Machine Specification, user.dir=D:\GitRespositories\springSourceCodeNotes, java.runtime.version=1.8.0_162-b12, java.awt.graphicsenv=sun.awt.Win32GraphicsEnvironment, java.endorsed.dirs=D:\JDK\jdk1.8\jre\lib\endorsed, os.arch=amd64, java.io.tmpdir=C:\Users\HENGTH~1\AppData\Local\Temp\, line.separator=
		, java.vm.specification.vendor=Oracle Corporation, user.variant=, os.name=Windows 10, sun.jnu.encoding=GBK, java.library.path=D:\JDK\jdk1.8\bin;C:\Windows\Sun\Java\bin;C:\Windows\system32;C:\Windows;D:/JDK/jdk1.8/bin/../jre/bin/server;D:/JDK/jdk1.8/bin/../jre/bin;D:/JDK/jdk1.8/bin/../jre/lib/amd64;D:\oracle\product\11.2.0\dbhome_2\bin;C:\Program Files (x86)\Intel\iCLS Client\;C:\Program Files\Intel\iCLS Client\;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\Intel\Intel(R) Management Engine Components\DAL;C:\Program Files\Intel\Intel(R) Management Engine Components\DAL;C:\Program Files (x86)\Intel\Intel(R) Management Engine Components\IPT;C:\Program Files\Intel\Intel(R) Management Engine Components\IPT;C:\Program Files\Intel\WiFi\bin\;C:\Program Files\Common Files\Intel\WirelessCommon\;D:\Program Files (x86)\TortoiseSVN\bin;D:\JDK\jdk1.8\bin;D:\Program Files (x86)\apache-maven-3.5.2\bin;D:\Program Files (x86)\apache-ant-1.10.2\bin;D:\JDK\jdk1.6\jre\bin;C:\Users\hength20953\Desktop;;., java.specification.name=Java Platform API Specification, java.class.version=52.0, sun.management.compiler=HotSpot 64-Bit Tiered Compilers, os.version=10.0, user.home=C:\Users\hength20953, user.timezone=GMT+08:00, java.awt.printerjob=sun.awt.windows.WPrinterJob, file.encoding=UTF-8, java.specification.version=1.8, java.class.path=D:\JDK\jdk1.8\jre\lib\resources.jar;D:\JDK\jdk1.8\jre\lib\rt.jar;D:\JDK\jdk1.8\jre\lib\jsse.jar;D:\JDK\jdk1.8\jre\lib\jce.jar;D:\JDK\jdk1.8\jre\lib\charsets.jar;D:\JDK\jdk1.8\jre\lib\jfr.jar;D:\JDK\jdk1.8\jre\lib\ext\access-bridge-64.jar;D:\JDK\jdk1.8\jre\lib\ext\cldrdata.jar;D:\JDK\jdk1.8\jre\lib\ext\dnsns.jar;D:\JDK\jdk1.8\jre\lib\ext\jaccess.jar;D:\JDK\jdk1.8\jre\lib\ext\jfxrt.jar;D:\JDK\jdk1.8\jre\lib\ext\localedata.jar;D:\JDK\jdk1.8\jre\lib\ext\nashorn.jar;D:\JDK\jdk1.8\jre\lib\ext\sunec.jar;D:\JDK\jdk1.8\jre\lib\ext\sunjce_provider.jar;D:\JDK\jdk1.8\jre\lib\ext\sunmscapi.jar;D:\JDK\jdk1.8\jre\lib\ext\sunpkcs11.jar;D:\JDK\jdk1.8\jre\lib\ext\zipfs.jar;D:\GitRespositories\springSourceCodeNotes\target\test-classes;D:\GitRespositories\springSourceCodeNotes\target\classes;C:\Users\hength20953\.p2\pool\plugins\org.junit.jupiter.api_5.0.0.v20170910-2246.jar;C:\Users\hength20953\.p2\pool\plugins\org.junit.jupiter.engine_5.0.0.v20170910-2246.jar;C:\Users\hength20953\.p2\pool\plugins\org.junit.jupiter.migrationsupport_5.0.0.v20170910-2246.jar;C:\Users\hength20953\.p2\pool\plugins\org.junit.jupiter.params_5.0.0.v20170910-2246.jar;C:\Users\hength20953\.p2\pool\plugins\org.junit.platform.commons_1.0.0.v20170910-2246.jar;C:\Users\hength20953\.p2\pool\plugins\org.junit.platform.engine_1.0.0.v20170910-2246.jar;C:\Users\hength20953\.p2\pool\plugins\org.junit.platform.launcher_1.0.0.v20170910-2246.jar;C:\Users\hength20953\.p2\pool\plugins\org.junit.platform.runner_1.0.0.v20170910-2246.jar;C:\Users\hength20953\.p2\pool\plugins\org.junit.platform.suite.api_1.0.0.v20170910-2246.jar;C:\Users\hength20953\.p2\pool\plugins\org.junit.vintage.engine_4.12.0.v20170910-2246.jar;C:\Users\hength20953\.p2\pool\plugins\org.opentest4j_1.0.0.v20170910-2246.jar;C:\Users\hength20953\.p2\pool\plugins\org.apiguardian_1.0.0.v20170910-2246.jar;C:\Users\hength20953\.p2\pool\plugins\org.junit_4.12.0.v201504281640\junit.jar;C:\Users\hength20953\.p2\pool\plugins\org.hamcrest.core_1.3.0.v201303031735.jar;D:\mavenRepository\org\springframework\spring-context\4.3.14.RELEASE\spring-context-4.3.14.RELEASE.jar;D:\mavenRepository\org\springframework\spring-aop\4.3.14.RELEASE\spring-aop-4.3.14.RELEASE.jar;D:\mavenRepository\org\springframework\spring-beans\4.3.14.RELEASE\spring-beans-4.3.14.RELEASE.jar;D:\mavenRepository\org\springframework\spring-core\4.3.14.RELEASE\spring-core-4.3.14.RELEASE.jar;D:\mavenRepository\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;D:\mavenRepository\org\springframework\spring-expression\4.3.14.RELEASE\spring-expression-4.3.14.RELEASE.jar;D:\mavenRepository\javax\annotation\javax.annotation-api\1.3.2\javax.annotation-api-1.3.2.jar;D:\mavenRepository\junit\junit\3.8.1\junit-3.8.1.jar;D:\Program Files (x86)\eclipse_jee-oxygen\eclipse\configuration\org.eclipse.osgi\414\0\.cp;D:\Program Files (x86)\eclipse_jee-oxygen\eclipse\configuration\org.eclipse.osgi\412\0\.cp, user.name=hength20953, java.vm.specification.version=1.8, sun.java.command=org.eclipse.jdt.internal.junit.runner.RemoteTestRunner -version 3 -port 52133 -testLoaderClass org.eclipse.jdt.internal.junit5.runner.JUnit5TestLoader -loaderpluginname org.eclipse.jdt.junit5.runtime -test com.heng.springAnnotation.AppTest:showAppContextObjs, java.home=D:\JDK\jdk1.8\jre, sun.arch.data.model=64, user.language=zh, java.specification.vendor=Oracle Corporation, awt.toolkit=sun.awt.windows.WToolkit, java.vm.info=mixed mode, java.version=1.8.0_162, java.ext.dirs=D:\JDK\jdk1.8\jre\lib\ext;C:\Windows\Sun\Java\lib\ext, sun.boot.class.path=D:\JDK\jdk1.8\jre\lib\resources.jar;D:\JDK\jdk1.8\jre\lib\rt.jar;D:\JDK\jdk1.8\jre\lib\sunrsasign.jar;D:\JDK\jdk1.8\jre\lib\jsse.jar;D:\JDK\jdk1.8\jre\lib\jce.jar;D:\JDK\jdk1.8\jre\lib\charsets.jar;D:\JDK\jdk1.8\jre\lib\jfr.jar;D:\JDK\jdk1.8\jre\classes, java.vendor=Oracle Corporation, file.separator=\, java.vendor.url.bug=http://bugreport.sun.com/bugreport/, sun.io.unicode.encoding=UnicodeLittle, sun.cpu.endian=little, sun.desktop=windows, sun.cpu.isalist=amd64}
		systemEnvironment={USERDOMAIN_ROAMINGPROFILE=HS, PROCESSOR_LEVEL=6, SESSIONNAME=Console, ALLUSERSPROFILE=C:\ProgramData, PROCESSOR_ARCHITECTURE=AMD64, TNS_ADMIN=D:\oracle\product\11.2.0\dbhome_2\NETWORK\ADMIN, PSModulePath=C:\Program Files\WindowsPowerShell\Modules;C:\Windows\system32\WindowsPowerShell\v1.0\Modules, SystemDrive=C:, JETTY_HOME=D:\Program Files (x86)\jetty-distribution-7.4.2.v20110526, USERNAME=hength20953, USERDNSDOMAIN=HS.HANDSOME.COM.CN, ProgramFiles(x86)=C:\Program Files (x86), FPS_BROWSER_USER_PROFILE_STRING=Default, PATHEXT=.COM;.EXE;.BAT;.CMD;.VBS;.VBE;.JS;.JSE;.WSF;.WSH;.MSC, test=testpath, ProgramData=C:\ProgramData, ProgramW6432=C:\Program Files, HOMEPATH=\Users\hength20953, PROCESSOR_IDENTIFIER=Intel64 Family 6 Model 142 Stepping 9, GenuineIntel, M2_HOME=D:\Program Files (x86)\apache-maven-3.5.2, ProgramFiles=C:\Program Files, PUBLIC=C:\Users\Public, windir=C:\Windows, =::=::\, LOCALAPPDATA=C:\Users\hength20953\AppData\Local, USERDOMAIN=HS, FPS_BROWSER_APP_PROFILE_STRING=Internet Explorer, LOGONSERVER=\\HSDC02, JAVA_HOME=D:\JDK\jdk1.8, LDMS_LOCAL_DIR=C:\Program Files (x86)\LANDesk\LDClient\Data, ANT_HOME=D:\Program Files (x86)\apache-ant-1.10.2, HADOOP_USER_NAME=hadoop, APPDATA=C:\Users\hength20953\AppData\Roaming, ORACLE_HOME=D:\oracle\product\11.2.0\dbhome_2, CommonProgramFiles=C:\Program Files\Common Files, Path=D:/JDK/jdk1.8/bin/../jre/bin/server;D:/JDK/jdk1.8/bin/../jre/bin;D:/JDK/jdk1.8/bin/../jre/lib/amd64;D:\oracle\product\11.2.0\dbhome_2\bin;C:\Program Files (x86)\Intel\iCLS Client\;C:\Program Files\Intel\iCLS Client\;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\Intel\Intel(R) Management Engine Components\DAL;C:\Program Files\Intel\Intel(R) Management Engine Components\DAL;C:\Program Files (x86)\Intel\Intel(R) Management Engine Components\IPT;C:\Program Files\Intel\Intel(R) Management Engine Components\IPT;C:\Program Files\Intel\WiFi\bin\;C:\Program Files\Common Files\Intel\WirelessCommon\;D:\Program Files (x86)\TortoiseSVN\bin;D:\JDK\jdk1.8\bin;D:\Program Files (x86)\apache-maven-3.5.2\bin;D:\Program Files (x86)\apache-ant-1.10.2\bin;D:\JDK\jdk1.6\jre\bin;C:\Users\hength20953\Desktop;, OS=Windows_NT, classpath=.;D:\JDK\jdk1.8/lib/dt.jar;D:\JDK\jdk1.8/lib/tools.jar, COMPUTERNAME=USERHENG, PROCESSOR_REVISION=8e09, CommonProgramW6432=C:\Program Files\Common Files, NLS_LANG=SIMPLIFIED CHINESE_CHINA.ZHS16GBK, ComSpec=C:\Windows\system32\cmd.exe, =D:=D:\, SystemRoot=C:\Windows, TEMP=C:\Users\HENGTH~1\AppData\Local\Temp, HOMEDRIVE=C:, USERPROFILE=C:\Users\hength20953, TMP=C:\Users\HENGTH~1\AppData\Local\Temp, CommonProgramFiles(x86)=C:\Program Files (x86)\Common Files, NUMBER_OF_PROCESSORS=4}
		org.springframework.context.annotation.ConfigurationClassPostProcessor.importRegistry=[]
		messageSource=org.springframework.context.support.DelegatingMessageSource@2f9f7dcf
		applicationEventMulticaster=org.springframework.context.event.SimpleApplicationEventMulticaster@747ddf94
		lifecycleProcessor=org.springframework.context.support.DefaultLifecycleProcessor@35e2d654
	 */
	@Test
	public void showAppContextObjs() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.heng.beans");
		Map<String, Object> beans = context.getBeansOfType(Object.class);
		for(Entry<String, Object> beanInfo : beans.entrySet()) {
			System.out.println(beanInfo);
		}
		context.close();
	}
	
	@Test
	public void activeProfile() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("test","dev","pro");
		context.scan("com.heng.beans");
		context.refresh();
		Map<String, Object> beans = context.getBeansOfType(Object.class);
		for(Entry<String, Object> beanInfo : beans.entrySet()) {
			System.out.println(beanInfo);
		}
		context.close();
	}
	
}
