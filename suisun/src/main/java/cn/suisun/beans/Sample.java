package cn.suisun.beans;

/**
 * <p>
 * 描述:Sample类
 * 对应数据库 sample表
 * </p>
 * 
 * @author wanghj
 * @data  2013-05-23
 */
public class Sample {

	private String id;      //sample id
	 
	private String name;    //名字
	

	public Sample() {
	}

	public Sample(String id, String name) {
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
		return "Sample [id=" + id + ", name=" + name + "]";
	}
	
	
}
