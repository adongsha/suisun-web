package cn.suisun.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;


public class SpringBeanFactoryAware /*implements ApplicationContextAware*/ {

	private static ApplicationContext applicationContext;

	public static <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	public static <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}

	public void setApplicationContext(ApplicationContext arg)
			throws BeansException {
		applicationContext = arg;
	}

}
