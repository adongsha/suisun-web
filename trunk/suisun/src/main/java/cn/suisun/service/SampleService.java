package cn.suisun.service;

import cn.suisun.vos.SampleVo;


/**
 * SampleApi 对外开放api
 * 
 * @author wanghj
 * @data 2013-05-23
 *
 */
public interface SampleService {

	/**
	 * <p>
	 *  通过<code>id</code>获得<code>sample</code>对象
	 * </p>
	 * @param id  sample id
	 * @return  sample对象
	 */
	SampleVo getSample(String id);
}
