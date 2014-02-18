package cn.suisun.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "album_pic")
@XmlRootElement
public class AlbumPic {

	private String uuid;
	private String albumDirectoryId;
	private String picName;
	private String englishName;
	private String picUrl;
	private Integer indexPic;
	private String updateTime;
	/**
	 * 图片审核 0：刚提交图片 1：审核通过 -1:审核不通过
	 */
	private Integer audit;

	public AlbumPic() {
		// TODO Auto-generated constructor stub
	}

	public AlbumPic(String uuid, String albumDirectoryId, String picName,
			String englishName, String picUrl, Integer indexPic, Integer audit,
			String updateTime) {
		super();
		this.uuid = uuid;
		this.albumDirectoryId = albumDirectoryId;
		this.picName = picName;
		this.englishName = englishName;
		this.picUrl = picUrl;
		this.indexPic = indexPic;
		this.audit = audit;
		this.updateTime = updateTime;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAlbumDirectoryId() {
		return albumDirectoryId;
	}

	public void setAlbumDirectoryId(String albumDirectoryId) {
		this.albumDirectoryId = albumDirectoryId;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getIndexPic() {
		return indexPic;
	}

	public void setIndexPic(Integer indexPic) {
		this.indexPic = indexPic;
	}

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
