package cn.suisun.utils.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * junit测试获得bean
 *
 * @author wanghj
 * @data 2013-05-23
 *
 */
public class SpringBeanFactory {

	public static <T> T getBean(String name, Class<T> requiredType) {
		return ApplicationContextHolder.context.getBean(name, requiredType);
	}

	public static <T> T getBean(Class<T> requiredType) {
		System.out.println(ApplicationContextHolder.context);
		return ApplicationContextHolder.context.getBean(requiredType);
	}

	private static class ApplicationContextHolder {

		private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"classpath*:spring/applicationContext.xml"});

	}
}
