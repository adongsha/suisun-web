package cn.suisun.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Properties配置文件帮助类
 * 
 * @author wanghj
 * 
 */
public class PropertiesUtils {

	/**
	 * 根据文件名获得properties配置
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static Properties getProperties(String fileName) {

		Properties properties = new Properties();

		try {
			properties.load(PropertiesUtils.class.getClassLoader()
					.getResourceAsStream(fileName));
			
			//本地测试
			//InputStream in = ClassLoader.getSystemResourceAsStream(fileName);
			//properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties;
	}

	/**
	 * 根据配置文件名和key获得value
	 * 
	 * @param fileName
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String getProperty(String fileName, String key) {
		try {
			String value = getProperties(fileName).getProperty(key);
			return switchPropertyValue(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String switchPropertyValue(String value) throws Exception {

		if (value == null) {
			return value;
		}

		return new String(value.getBytes("ISO8859-1"), "UTF-8");
	}
}
