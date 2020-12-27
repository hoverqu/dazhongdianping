package lps.dianpupinglun;

import java.util.Date;

/**
 * DazhongCaterDisscus entity. @author MyEclipse Persistence Tools
 */

public class DazhongCaterDisscus implements java.io.Serializable {

	// Fields

	private Integer id;
	private String disscussUrl;
	private String disscusor;
	private Integer disscusLevel;
	private String disscusContent;
	private Date disscusTime;
	private String discusType;
	private String disscusReturn;
	private String uuid;

	// Constructors

	/** default constructor */
	public DazhongCaterDisscus() {
	}

	/** full constructor */
	public DazhongCaterDisscus(String disscussUrl, String disscusor,
			Integer disscusLevel, String disscusContent, Date disscusTime,
			String discusType, String disscusReturn, String uuid) {
		this.disscussUrl = disscussUrl;
		this.disscusor = disscusor;
		this.disscusLevel = disscusLevel;
		this.disscusContent = disscusContent;
		this.disscusTime = disscusTime;
		this.discusType = discusType;
		this.disscusReturn = disscusReturn;
		this.uuid = uuid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDisscussUrl() {
		return this.disscussUrl;
	}

	public void setDisscussUrl(String disscussUrl) {
		this.disscussUrl = disscussUrl;
	}

	public String getDisscusor() {
		return this.disscusor;
	}

	public void setDisscusor(String disscusor) {
		this.disscusor = disscusor;
	}

	public Integer getDisscusLevel() {
		return this.disscusLevel;
	}

	public void setDisscusLevel(Integer disscusLevel) {
		this.disscusLevel = disscusLevel;
	}

	public String getDisscusContent() {
		return this.disscusContent;
	}

	public void setDisscusContent(String disscusContent) {
		this.disscusContent = disscusContent;
	}

	public Date getDisscusTime() {
		return this.disscusTime;
	}

	public void setDisscusTime(Date disscusTime) {
		this.disscusTime = disscusTime;
	}

	public String getDiscusType() {
		return this.discusType;
	}

	public void setDiscusType(String discusType) {
		this.discusType = discusType;
	}

	public String getDisscusReturn() {
		return this.disscusReturn;
	}

	public void setDisscusReturn(String disscusReturn) {
		this.disscusReturn = disscusReturn;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}