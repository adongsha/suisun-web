package cn.suisun.resource.test;

import org.junit.Before;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;

public class UserJerseyTest extends JerseyTest{

	WebResource r ;
	
	public UserJerseyTest(){
		super("com.efuture.zero.resource");   //注入要测试的路径的类所在的包
	}
	
	@Before
	public void init(){
		
		r = resource();
	}

}
