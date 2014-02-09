package cn.suisun.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "album_directory")
@XmlRootElement
public class AlbumDirectory {

	private String uuid;
	private String albumId;
	private String directoryName;
	private String directoryEnglish;
	private Integer picNum;
	private Integer indexDirectory;
	
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
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
	public String getDirectoryName() {
		return directoryName;
	}
	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}
	public String getDirectoryEnglish() {
		return directoryEnglish;
	}
	public void setDirectoryEnglish(String directoryEnglish) {
		this.directoryEnglish = directoryEnglish;
	}
	public Integer getPicNum() {
		return picNum;
	}
	public void setPicNum(Integer picNum) {
		this.picNum = picNum;
	}
	public Integer getIndexDirectory() {
		return indexDirectory;
	}
	public void setIndexDirectory(Integer indexDirectory) {
		this.indexDirectory = indexDirectory;
	}
	
	
}
