package cn.suisun.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "album")
@XmlRootElement
public class Album {

	private String uuid;
	private String userId;
	private String albumName;
	private String albumEnglish;
	private Boolean isAutoDownload;
	private String albumCover;
	private String albumPassword;
	private Date   createTime;
	
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getAlbumEnglish() {
		return albumEnglish;
	}
	public void setAlbumEnglish(String albumEnglish) {
		this.albumEnglish = albumEnglish;
	}
	public Boolean getIsAutoDownload() {
		return isAutoDownload;
	}
	public void setIsAutoDownload(Boolean isAutoDownload) {
		this.isAutoDownload = isAutoDownload;
	}
	public String getAlbumCover() {
		return albumCover;
	}
	public void setAlbumCover(String albumCover) {
		this.albumCover = albumCover;
	}
	public String getAlbumPassword() {
		return albumPassword;
	}
	public void setAlbumPassword(String albumPassword) {
		this.albumPassword = albumPassword;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
