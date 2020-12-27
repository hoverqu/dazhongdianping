package lps.entities;

import java.sql.Timestamp;
import java.util.Date;

/**
 * ElmCaterFirm entity. @author MyEclipse Persistence Tools
 */

public class ElmCaterFirm implements java.io.Serializable {

	// Fields

	private Integer id;
	private String uuid;
	private String province;
	private String runTime;
	private String phone;
	private String tag;
	private String city;
	private String caterName;
	private Float pricerate;
	private String catericonurl;
	private String caterAddress;
	private Integer monthsalesum;
	private String placeLevel;
	private String dishLevel;
	private String serverLevel;
	private String manageLevel;
	private Date checkdate;
	private String licenceNo;
	private String manager;
	private String mainStatus;
	private String runItems;
	private Date validityDate;
	private String sendFrom;
	private Float sendFee;
	private Float sendStartmney;
	private Integer sendTime;
	private Integer meanSendtime;
	private String orderStarttime;
	private Float dissScore;
	private String firmCertificate;
	private String caterLongtitude;
	private String isEnsure;
	private String caterLatude;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public ElmCaterFirm() {
	}

	/** full constructor */
	public ElmCaterFirm(String uuid, String province, String runTime,
			String phone, String tag, String city, String caterName,
			Float pricerate, String catericonurl, String caterAddress,
			Integer monthsalesum, String placeLevel, String dishLevel,
			String serverLevel, String manageLevel, Date checkdate,
			String licenceNo, String manager, String mainStatus,
			String runItems, Date validityDate, String sendFrom, Float sendFee,
			Float sendStartmney, Integer sendTime, Integer meanSendtime,
			String orderStarttime, Float dissScore, String firmCertificate,
			String caterLongtitude, String isEnsure, String caterLatude,
			Timestamp createTime) {
		this.uuid = uuid;
		this.province = province;
		this.runTime = runTime;
		this.phone = phone;
		this.tag = tag;
		this.city = city;
		this.caterName = caterName;
		this.pricerate = pricerate;
		this.catericonurl = catericonurl;
		this.caterAddress = caterAddress;
		this.monthsalesum = monthsalesum;
		this.placeLevel = placeLevel;
		this.dishLevel = dishLevel;
		this.serverLevel = serverLevel;
		this.manageLevel = manageLevel;
		this.checkdate = checkdate;
		this.licenceNo = licenceNo;
		this.manager = manager;
		this.mainStatus = mainStatus;
		this.runItems = runItems;
		this.validityDate = validityDate;
		this.sendFrom = sendFrom;
		this.sendFee = sendFee;
		this.sendStartmney = sendStartmney;
		this.sendTime = sendTime;
		this.meanSendtime = meanSendtime;
		this.orderStarttime = orderStarttime;
		this.dissScore = dissScore;
		this.firmCertificate = firmCertificate;
		this.caterLongtitude = caterLongtitude;
		this.isEnsure = isEnsure;
		this.caterLatude = caterLatude;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRunTime() {
		return this.runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCaterName() {
		return this.caterName;
	}

	public void setCaterName(String caterName) {
		this.caterName = caterName;
	}

	public Float getPricerate() {
		return this.pricerate;
	}

	public void setPricerate(Float pricerate) {
		this.pricerate = pricerate;
	}

	public String getCatericonurl() {
		return this.catericonurl;
	}

	public void setCatericonurl(String catericonurl) {
		this.catericonurl = catericonurl;
	}

	public String getCaterAddress() {
		return this.caterAddress;
	}

	public void setCaterAddress(String caterAddress) {
		this.caterAddress = caterAddress;
	}

	public Integer getMonthsalesum() {
		return this.monthsalesum;
	}

	public void setMonthsalesum(Integer monthsalesum) {
		this.monthsalesum = monthsalesum;
	}

	public String getPlaceLevel() {
		return this.placeLevel;
	}

	public void setPlaceLevel(String placeLevel) {
		this.placeLevel = placeLevel;
	}

	public String getDishLevel() {
		return this.dishLevel;
	}

	public void setDishLevel(String dishLevel) {
		this.dishLevel = dishLevel;
	}

	public String getServerLevel() {
		return this.serverLevel;
	}

	public void setServerLevel(String serverLevel) {
		this.serverLevel = serverLevel;
	}

	public String getManageLevel() {
		return this.manageLevel;
	}

	public void setManageLevel(String manageLevel) {
		this.manageLevel = manageLevel;
	}

	public Date getCheckdate() {
		return this.checkdate;
	}

	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}

	public String getLicenceNo() {
		return this.licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getMainStatus() {
		return this.mainStatus;
	}

	public void setMainStatus(String mainStatus) {
		this.mainStatus = mainStatus;
	}

	public String getRunItems() {
		return this.runItems;
	}

	public void setRunItems(String runItems) {
		this.runItems = runItems;
	}

	public Date getValidityDate() {
		return this.validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

	public String getSendFrom() {
		return this.sendFrom;
	}

	public void setSendFrom(String sendFrom) {
		this.sendFrom = sendFrom;
	}

	public Float getSendFee() {
		return this.sendFee;
	}

	public void setSendFee(Float sendFee) {
		this.sendFee = sendFee;
	}

	public Float getSendStartmney() {
		return this.sendStartmney;
	}

	public void setSendStartmney(Float sendStartmney) {
		this.sendStartmney = sendStartmney;
	}

	public Integer getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Integer sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getMeanSendtime() {
		return this.meanSendtime;
	}

	public void setMeanSendtime(Integer meanSendtime) {
		this.meanSendtime = meanSendtime;
	}

	public String getOrderStarttime() {
		return this.orderStarttime;
	}

	public void setOrderStarttime(String orderStarttime) {
		this.orderStarttime = orderStarttime;
	}

	public Float getDissScore() {
		return this.dissScore;
	}

	public void setDissScore(Float dissScore) {
		this.dissScore = dissScore;
	}

	public String getFirmCertificate() {
		return this.firmCertificate;
	}

	public void setFirmCertificate(String firmCertificate) {
		this.firmCertificate = firmCertificate;
	}

	public String getCaterLongtitude() {
		return this.caterLongtitude;
	}

	public void setCaterLongtitude(String caterLongtitude) {
		this.caterLongtitude = caterLongtitude;
	}

	public String getIsEnsure() {
		return this.isEnsure;
	}

	public void setIsEnsure(String isEnsure) {
		this.isEnsure = isEnsure;
	}

	public String getCaterLatude() {
		return this.caterLatude;
	}

	public void setCaterLatude(String caterLatude) {
		this.caterLatude = caterLatude;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}