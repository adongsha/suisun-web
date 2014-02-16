package cn.suisun.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

/**
 * 客户统计表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "customer_statistics")
@XmlRootElement
public class CustomerStat {

	private String uuid;
	private String phoneType;
	private String area;
	private String albumLook;  //1:表示点击查看  2：表示下载
	private String albumType;
	private String albumId;
	private Date downloadTime;
	private Integer openNum;
	private Date lastTimeLook;
	private String userId;
	private double longitude;
	private double latitude;

	public CustomerStat() {
	}

	public CustomerStat(String uuid, String phoneType, String area,
			String albumLook, String albumType, Date downloadTime,
			Integer openNum, Date lastTimeLook, String userId) {
		super();
		this.uuid = uuid;
		this.phoneType = phoneType;
		this.area = area;
		this.albumLook = albumLook;
		this.albumType = albumType;
		this.downloadTime = downloadTime;
		this.openNum = openNum;
		this.lastTimeLook = lastTimeLook;
		this.userId = userId;
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

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAlbumLook() {
		return albumLook;
	}

	public void setAlbumLook(String albumLook) {
		this.albumLook = albumLook;
	}

	public String getAlbumType() {
		return albumType;
	}

	public void setAlbumType(String albumType) {
		this.albumType = albumType;
	}

	public Date getDownloadTime() {
		return downloadTime;
	}

	public void setDownloadTime(Date downloadTime) {
		this.downloadTime = downloadTime;
	}

	public Integer getOpenNum() {
		return openNum;
	}

	public void setOpenNum(Integer openNum) {
		this.openNum = openNum;
	}

	public Date getLastTimeLook() {
		return lastTimeLook;
	}

	public void setLastTimeLook(Date lastTimeLook) {
		this.lastTimeLook = lastTimeLook;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "CustomerStat [uuid=" + uuid + ", phoneType=" + phoneType
				+ ", area=" + area + ", albumLook=" + albumLook
				+ ", albumType=" + albumType + ", albumId=" + albumId
				+ ", downloadTime=" + downloadTime + ", openNum=" + openNum
				+ ", lastTimeLook=" + lastTimeLook + ", userId=" + userId
				+ ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}




}
