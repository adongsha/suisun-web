/**
 * 版权所有 2013 efuture Company, Inc. 保留所有权利。
 */
package cn.suisun.vos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述：
 * 
 * @author wanghj
 * @data   2013-05-28
 *
 */
@XmlRootElement
public class SampleVo {

	private String id;      //sample id
	 
	private String name;    //名称
	
	

	public SampleVo() {
	}

	public SampleVo(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SampleVo [id=" + id + ", name=" + name + "]";
	}
	
	
}
