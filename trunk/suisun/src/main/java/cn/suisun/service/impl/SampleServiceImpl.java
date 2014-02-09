/**
 * 版权所有 2013 efuture Company, Inc. 保留所有权
 */
package cn.suisun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.suisun.dao.SampleDao;
import cn.suisun.service.SampleService;
import cn.suisun.vos.SampleVo;


/**
 * SampleServiceImpl 对  SampleService 的实现
 * 
 * @author wanghj
 * @data 2013-05-23
 *
 */

@Service("SampleService")
public class SampleServiceImpl implements SampleService{

	@Resource
	SampleDao sampleDao;
	
	
	/**
	 * {@inheritDoc}
	 */
	public SampleVo getSample(String sampleId) {
		System.out.println("--->sampleDao:"+sampleDao);
		System.out.println("-------------------->"+sampleId);
		//System.out.println("sampleDao-->"+sampleDao.getSample(sampleId));

		return new SampleVo("1","wanghj");
	}




}


