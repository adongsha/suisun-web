package cn.suisun.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "industry")
@XmlRootElement
public class Industry {

	private String uuid;
	private String industryName;
	private String note;

	
	public Industry() {
	}

	public Industry(String uuid, String industryName, String note) {
		super();
		this.uuid = uuid;
		this.industryName = industryName;
		this.note = note;
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

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Industry [uuid=" + uuid + ", industryName=" + industryName
				+ ", note=" + note + "]";
	}

}
