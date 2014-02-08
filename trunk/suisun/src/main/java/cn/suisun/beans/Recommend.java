package cn.suisun.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

/**
 * 推荐表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "recommend")
@XmlRootElement
public class Recommend {

	private String uuid;
	private String albumId;
	private int order;

	public Recommend() {
	}

	public Recommend(String uuid, String albumId, int order) {
		super();
		this.uuid = uuid;
		this.albumId = albumId;
		this.order = order;
	}

	
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "uuid", unique = true, nullable = false, length = 32)
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Recommend [uuid=" + uuid + ", albumId=" + albumId + ", order="
				+ order + "]";
	}

	
}
