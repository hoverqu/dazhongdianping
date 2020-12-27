package lps.entities;

import java.util.Date;

/**
 * DazhongCaterDisscus entity. @author MyEclipse Persistence Tools
 */

public class DazhongCaterDisscus implements java.io.Serializable {

	// Fields

	private Long id;
	private Long caterid;
	private String disscussUrl;
	private String disscusor;
	private Integer disscusLevel;
	private String disscusContent;
	private Date disscusTime;
	private String discusType;
	private Integer envirment;
	private Integer percost;
	private Integer service;
	private String likedish;
	private Integer tasty;
	private Date returnDate;
	private String disscusReturn;
	private Integer createTime;
	private String uuid;
	private Float kouwei;
	private Float huanjing;
	private Float fuwu;
	private Float renjun;
	private Integer plid;

	// Constructors

	/** default constructor */
	public DazhongCaterDisscus() {
	}

	/** full constructor */
	public DazhongCaterDisscus(Long caterid, String disscussUrl,
			String disscusor, Integer disscusLevel, String disscusContent,
			Date disscusTime, String discusType, Integer envirment,
			Integer percost, Integer service, String likedish, Integer tasty,
			Date returnDate, String disscusReturn, Integer createTime,
			String uuid, Float kouwei, Float huanjing, Float fuwu,
			Float renjun, Integer plid) {
		this.caterid = caterid;
		this.disscussUrl = disscussUrl;
		this.disscusor = disscusor;
		this.disscusLevel = disscusLevel;
		this.disscusContent = disscusContent;
		this.disscusTime = disscusTime;
		this.discusType = discusType;
		this.envirment = envirment;
		this.percost = percost;
		this.service = service;
		this.likedish = likedish;
		this.tasty = tasty;
		this.returnDate = returnDate;
		this.disscusReturn = disscusReturn;
		this.createTime = createTime;
		this.uuid = uuid;
		this.kouwei = kouwei;
		this.huanjing = huanjing;
		this.fuwu = fuwu;
		this.renjun = renjun;
		this.plid = plid;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCaterid() {
		return this.caterid;
	}

	public void setCaterid(Long caterid) {
		this.caterid = caterid;
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

	public Integer getEnvirment() {
		return this.envirment;
	}

	public void setEnvirment(Integer envirment) {
		this.envirment = envirment;
	}

	public Integer getPercost() {
		return this.percost;
	}

	public void setPercost(Integer percost) {
		this.percost = percost;
	}

	public Integer getService() {
		return this.service;
	}

	public void setService(Integer service) {
		this.service = service;
	}

	public String getLikedish() {
		return this.likedish;
	}

	public void setLikedish(String likedish) {
		this.likedish = likedish;
	}

	public Integer getTasty() {
		return this.tasty;
	}

	public void setTasty(Integer tasty) {
		this.tasty = tasty;
	}

	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getDisscusReturn() {
		return this.disscusReturn;
	}

	public void setDisscusReturn(String disscusReturn) {
		this.disscusReturn = disscusReturn;
	}

	public Integer getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Float getKouwei() {
		return this.kouwei;
	}

	public void setKouwei(Float kouwei) {
		this.kouwei = kouwei;
	}

	public Float getHuanjing() {
		return this.huanjing;
	}

	public void setHuanjing(Float huanjing) {
		this.huanjing = huanjing;
	}

	public Float getFuwu() {
		return this.fuwu;
	}

	public void setFuwu(Float fuwu) {
		this.fuwu = fuwu;
	}

	public Float getRenjun() {
		return this.renjun;
	}

	public void setRenjun(Float renjun) {
		this.renjun = renjun;
	}

	public Integer getPlid() {
		return this.plid;
	}

	public void setPlid(Integer plid) {
		this.plid = plid;
	}

}