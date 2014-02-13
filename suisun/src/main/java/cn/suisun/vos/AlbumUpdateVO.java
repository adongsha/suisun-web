package cn.suisun.vos;

import java.util.Date;

// 画册审批对象
public class AlbumUpdateVO {
	// 唯一标识
	private String uuid;
	// 用户名称
	private String userName;
	// 画册名称
	private String albumName;
	// 英文名称
	private String englisthName;
	// 画册创建时间
	private String createTime;
	// 画册更新内容
	private String updateContent;
	// 审批状态
	private int audit;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getEnglisthName() {
		return englisthName;
	}

	public void setEnglisthName(String englisthName) {
		this.englisthName = englisthName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateContent() {
		return updateContent;
	}

	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	public int getAudit() {
		return audit;
	}

	public void setAudit(int audit) {
		this.audit = audit;
	}

}
