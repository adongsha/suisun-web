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
@Table(name = "user")
@XmlRootElement
public class User {

	private String uuid;
	private String account;
	private String password;
	private String ip;
	private Date lastTime;
	private int power;   //0:超级管理员  1：管理员  2：用户
	private String industryId;
	private Date registDate;
	private String enterpriseName;
	private String enterpriseEnglish;
	private String shortName;
	private String shortEnglish;
	private String telephone;
	private String email;
	private String fax;
	private String address;
	private String enterpriseInfo;
	private String website;
	private String linkman;
	private String englishLinkMan;
	private String phone;
	private String logoUrl;
	private Float latitude;
	private Float longitude;

	public User() {
	}

	public User(String uuid, String account, String password, String ip,
			Date lastTime, int power, String industryId, Date registDate,
			String enterpriseName, String enterpriseEnglish, String shortName,
			String shortEnglish, String telephone, String email, String fax,
			String address, String enterpriseInfo, String website,
			String linkman, String englishLinkMan, String phone,
			String logoUrl, Float latitude, Float longitude) {
		super();
		this.uuid = uuid;
		this.account = account;
		this.password = password;
		this.ip = ip;
		this.lastTime = lastTime;
		this.power = power;
		this.industryId = industryId;
		this.registDate = registDate;
		this.enterpriseName = enterpriseName;
		this.enterpriseEnglish = enterpriseEnglish;
		this.shortName = shortName;
		this.shortEnglish = shortEnglish;
		this.telephone = telephone;
		this.email = email;
		this.fax = fax;
		this.address = address;
		this.enterpriseInfo = enterpriseInfo;
		this.website = website;
		this.linkman = linkman;
		this.englishLinkMan = englishLinkMan;
		this.phone = phone;
		this.logoUrl = logoUrl;
		this.latitude = latitude;
		this.longitude = longitude;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIndustryId() {
		return industryId;
	}

	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getEnterpriseEnglish() {
		return enterpriseEnglish;
	}

	public void setEnterpriseEnglish(String enterpriseEnglish) {
		this.enterpriseEnglish = enterpriseEnglish;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getShortEnglish() {
		return shortEnglish;
	}

	public void setShortEnglish(String shortEnglish) {
		this.shortEnglish = shortEnglish;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEnterpriseInfo() {
		return enterpriseInfo;
	}

	public void setEnterpriseInfo(String enterpriseInfo) {
		this.enterpriseInfo = enterpriseInfo;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getEnglishLinkMan() {
		return englishLinkMan;
	}

	public void setEnglishLinkMan(String englishLinkMan) {
		this.englishLinkMan = englishLinkMan;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "User [uuid=" + uuid + ", account=" + account + ", password="
				+ password + ", ip=" + ip + ", lastTime=" + lastTime
				+ ", power=" + power + ", industry=" + industryId
				+ ", registDate=" + registDate + ", enterpriseName="
				+ enterpriseName + ", enterpriseEnglish=" + enterpriseEnglish
				+ ", shortName=" + shortName + ", shortEnglish=" + shortEnglish
				+ ", telephone=" + telephone + ", email=" + email + ", fax="
				+ fax + ", address=" + address + ", enterpriseInfo="
				+ enterpriseInfo + ", website=" + website + ", linkman="
				+ linkman + ", englishLinkMan=" + englishLinkMan + ", phone="
				+ phone + ", logoUrl=" + logoUrl + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

}
