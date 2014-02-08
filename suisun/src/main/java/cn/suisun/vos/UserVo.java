package cn.suisun.vos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class UserVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -785937117703487402L;
	private String uuid;
	private String account;
	private String password;
	private String confirm;
	private int power;
	private String industry;
	private String enterpriseName;
	private String enterpriseEnglish;
	private String shortName;
	private String shortEnglish;
	private String telephone;
	private String email;
	private String fax;
	private String address;
	private String website;
	private String linkman;
	private String englishLinkMan;
	private String phone;
	private String enterpriseInfo;


	public UserVo(String uuid, String account, String password, String confirm,
			int power, String industry, String enterpriseName,
			String enterpriseEnglish, String shortName, String shortEnglish,
			String telephone, String email, String fax, String address,
			String website, String linkman, String englishLinkMan,
			String phone, String enterpriseInfo) {
		super();
		this.uuid = uuid;
		this.account = account;
		this.password = password;
		this.confirm = confirm;
		this.power = power;
		this.industry = industry;
		this.enterpriseName = enterpriseName;
		this.enterpriseEnglish = enterpriseEnglish;
		this.shortName = shortName;
		this.shortEnglish = shortEnglish;
		this.telephone = telephone;
		this.email = email;
		this.fax = fax;
		this.address = address;
		this.website = website;
		this.linkman = linkman;
		this.englishLinkMan = englishLinkMan;
		this.phone = phone;
		this.enterpriseInfo = enterpriseInfo;
	}

	public UserVo() {
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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
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

	public String getEnterpriseInfo() {
		return enterpriseInfo;
	}

	public void setEnterpriseInfo(String enterpriseInfo) {
		this.enterpriseInfo = enterpriseInfo;
	}


	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "UserVo [uuid=" + uuid + ", account=" + account + ", password="
				+ password + ", confirm=" + confirm + ", power=" + power
				+ ", industry=" + industry + ", enterpriseName="
				+ enterpriseName + ", enterpriseEnglish=" + enterpriseEnglish
				+ ", shortName=" + shortName + ", shortEnglish=" + shortEnglish
				+ ", telephone=" + telephone + ", email=" + email + ", fax="
				+ fax + ", address=" + address + ", website=" + website
				+ ", linkman=" + linkman + ", englishLinkMan=" + englishLinkMan
				+ ", phone=" + phone + ", enterpriseInfo=" + enterpriseInfo
				+ "]";
	}



}
