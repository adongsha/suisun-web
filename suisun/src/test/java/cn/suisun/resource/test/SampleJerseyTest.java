/**
 * 版权所有 2013 efuture Company, Inc. 保留所有权
 */
package cn.suisun.resource.test;

import org.junit.Before;
import org.junit.Test;

import cn.suisun.service.SampleService;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;

/**
 * <p>
 * SampleService 测试类
 * 必须继承<code>JerseyTest{@link com.sun.jersey.test.framework.JerseyTest}</code>,只要测试开放接口API
 * </p>
 * 
 * <pre>
 *   <red>
 *       特别说明：该包下即service层不能测试调用的DAO里面的对象，这样会报空指针异常,
 *       原因：没有加载spring相关xml文件,所以只能单纯的测试对外开放的URI的路径是否正常使用
 *       如果你完成service层下面的方法的正常测试,请到phone{@link com.efuture.storage.phone.test.SamplePhoneTest}下面的test里进行测试
 *   </red>
 * </pre>
 * @author wanghj
 * @data   2013-05-27
 */
public class SampleJerseyTest extends JerseyTest{

	WebResource r ;
	SampleService sampleService;
	
	public SampleJerseyTest(){
		super("com.efuture.zero.resource");   //注入要测试的路径的类所在的包
	}
	
	@Before
	public void init(){
		
		r = resource();
		
	}
	
	/**
	 * 测试getSmaple()方面
	 * uri: sampleService/getSample
	 */
	@Test
	public void testGetSample(){
		 
		String responseMsg = r.path("sampleService/getSample/3")
				 .get(String.class);
		
		 System.out.println(responseMsg);
	}
	

}
