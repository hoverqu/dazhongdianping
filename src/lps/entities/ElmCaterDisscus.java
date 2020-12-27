package lps.entities;

import java.sql.Timestamp;
import java.util.Date;

/**
 * ElmCaterDisscus entity. @author MyEclipse Persistence Tools
 */

public class ElmCaterDisscus implements java.io.Serializable {

	// Fields

	private Integer id;
	private String dissUuid;
	private String disscussUrl;
	private String disscusor;
	private Integer disscusLevel;
	private String disscusContent;
	private Date disscusTime;
	private String disscusReturn;
	private String disscusType;
	private String buyDish;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public ElmCaterDisscus() {
	}

	/** full constructor */
	public ElmCaterDisscus(String dissUuid, String disscussUrl,
			String disscusor, Integer disscusLevel, String disscusContent,
			Date disscusTime, String disscusReturn, String disscusType,
			String buyDish, Timestamp createTime) {
		this.dissUuid = dissUuid;
		this.disscussUrl = disscussUrl;
		this.disscusor = disscusor;
		this.disscusLevel = disscusLevel;
		this.disscusContent = disscusContent;
		this.disscusTime = disscusTime;
		this.disscusReturn = disscusReturn;
		this.disscusType = disscusType;
		this.buyDish = buyDish;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDissUuid() {
		return this.dissUuid;
	}

	public void setDissUuid(String dissUuid) {
		this.dissUuid = dissUuid;
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

	public String getDisscusReturn() {
		return this.disscusReturn;
	}

	public void setDisscusReturn(String disscusReturn) {
		this.disscusReturn = disscusReturn;
	}

	public String getDisscusType() {
		return this.disscusType;
	}

	public void setDisscusType(String disscusType) {
		this.disscusType = disscusType;
	}

	public String getBuyDish() {
		return this.buyDish;
	}

	public void setBuyDish(String buyDish) {
		this.buyDish = buyDish;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}