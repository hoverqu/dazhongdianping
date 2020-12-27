package lps.entities;

/**
 * DazhongCaterFirm entity. @author MyEclipse Persistence Tools
 */

public class DazhongCaterFirm implements java.io.Serializable {

	// Fields

	private Long id;
	private String catertype;
	private String province;
	private String runTime;
	private String phone;
	private String city;
	private String caterName;
	private String caterAddress;
	private String firmCertificate;
	private Integer createTime;
	private Integer dissCount;
	private Integer meanCost;
	private Float starLevel;
	private String caterFeature;
	private String caterLatude;
	private String caterLongtitude;
	private String uuid;
	private Integer goodDiss;
	private Integer modiDiss;
	private Integer lowDiss;
	private Integer ok;
	private Float kouwei;
	private Float fuwu;
	private Float huanjing;
	private String disscusReturn;
	private Integer shopGroupId;
	private Integer mainregionid;
	private String categoryname;
	private String categoryurlname;
	private Integer ok2;
	private Integer ok3;
	private String tag;
	private String dissScore;
	private Float coTasty;
	private Float coEnvironment;
	private String firmDocument;
	private String firmLecense;
	private String brandStory;
	private Integer ok4;
	private Integer ok5;
	private Integer ok6;

	// Constructors

	/** default constructor */
	public DazhongCaterFirm() {
	}

	/** full constructor */
	public DazhongCaterFirm(String catertype, String province, String runTime,
			String phone, String city, String caterName, String caterAddress,
			String firmCertificate, Integer createTime, Integer dissCount,
			Integer meanCost, Float starLevel, String caterFeature,
			String caterLatude, String caterLongtitude, String uuid,
			Integer goodDiss, Integer modiDiss, Integer lowDiss, Integer ok,
			Float kouwei, Float fuwu, Float huanjing, String disscusReturn,
			Integer shopGroupId, Integer mainregionid, String categoryname,
			String categoryurlname, Integer ok2, Integer ok3, String tag,
			String dissScore, Float coTasty, Float coEnvironment,
			String firmDocument, String firmLecense, String brandStory,
			Integer ok4, Integer ok5, Integer ok6) {
		this.catertype = catertype;
		this.province = province;
		this.runTime = runTime;
		this.phone = phone;
		this.city = city;
		this.caterName = caterName;
		this.caterAddress = caterAddress;
		this.firmCertificate = firmCertificate;
		this.createTime = createTime;
		this.dissCount = dissCount;
		this.meanCost = meanCost;
		this.starLevel = starLevel;
		this.caterFeature = caterFeature;
		this.caterLatude = caterLatude;
		this.caterLongtitude = caterLongtitude;
		this.uuid = uuid;
		this.goodDiss = goodDiss;
		this.modiDiss = modiDiss;
		this.lowDiss = lowDiss;
		this.ok = ok;
		this.kouwei = kouwei;
		this.fuwu = fuwu;
		this.huanjing = huanjing;
		this.disscusReturn = disscusReturn;
		this.shopGroupId = shopGroupId;
		this.mainregionid = mainregionid;
		this.categoryname = categoryname;
		this.categoryurlname = categoryurlname;
		this.ok2 = ok2;
		this.ok3 = ok3;
		this.tag = tag;
		this.dissScore = dissScore;
		this.coTasty = coTasty;
		this.coEnvironment = coEnvironment;
		this.firmDocument = firmDocument;
		this.firmLecense = firmLecense;
		this.brandStory = brandStory;
		this.ok4 = ok4;
		this.ok5 = ok5;
		this.ok6 = ok6;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCatertype() {
		return this.catertype;
	}

	public void setCatertype(String catertype) {
		this.catertype = catertype;
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

	public String getCaterAddress() {
		return this.caterAddress;
	}

	public void setCaterAddress(String caterAddress) {
		this.caterAddress = caterAddress;
	}

	public String getFirmCertificate() {
		return this.firmCertificate;
	}

	public void setFirmCertificate(String firmCertificate) {
		this.firmCertificate = firmCertificate;
	}

	public Integer getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public Integer getDissCount() {
		return this.dissCount;
	}

	public void setDissCount(Integer dissCount) {
		this.dissCount = dissCount;
	}

	public Integer getMeanCost() {
		return this.meanCost;
	}

	public void setMeanCost(Integer meanCost) {
		this.meanCost = meanCost;
	}

	public Float getStarLevel() {
		return this.starLevel;
	}

	public void setStarLevel(Float starLevel) {
		this.starLevel = starLevel;
	}

	public String getCaterFeature() {
		return this.caterFeature;
	}

	public void setCaterFeature(String caterFeature) {
		this.caterFeature = caterFeature;
	}

	public String getCaterLatude() {
		return this.caterLatude;
	}

	public void setCaterLatude(String caterLatude) {
		this.caterLatude = caterLatude;
	}

	public String getCaterLongtitude() {
		return this.caterLongtitude;
	}

	public void setCaterLongtitude(String caterLongtitude) {
		this.caterLongtitude = caterLongtitude;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getGoodDiss() {
		return this.goodDiss;
	}

	public void setGoodDiss(Integer goodDiss) {
		this.goodDiss = goodDiss;
	}

	public Integer getModiDiss() {
		return this.modiDiss;
	}

	public void setModiDiss(Integer modiDiss) {
		this.modiDiss = modiDiss;
	}

	public Integer getLowDiss() {
		return this.lowDiss;
	}

	public void setLowDiss(Integer lowDiss) {
		this.lowDiss = lowDiss;
	}

	public Integer getOk() {
		return this.ok;
	}

	public void setOk(Integer ok) {
		this.ok = ok;
	}

	public Float getKouwei() {
		return this.kouwei;
	}

	public void setKouwei(Float kouwei) {
		this.kouwei = kouwei;
	}

	public Float getFuwu() {
		return this.fuwu;
	}

	public void setFuwu(Float fuwu) {
		this.fuwu = fuwu;
	}

	public Float getHuanjing() {
		return this.huanjing;
	}

	public void setHuanjing(Float huanjing) {
		this.huanjing = huanjing;
	}

	public String getDisscusReturn() {
		return this.disscusReturn;
	}

	public void setDisscusReturn(String disscusReturn) {
		this.disscusReturn = disscusReturn;
	}

	public Integer getShopGroupId() {
		return this.shopGroupId;
	}

	public void setShopGroupId(Integer shopGroupId) {
		this.shopGroupId = shopGroupId;
	}

	public Integer getMainregionid() {
		return this.mainregionid;
	}

	public void setMainregionid(Integer mainregionid) {
		this.mainregionid = mainregionid;
	}

	public String getCategoryname() {
		return this.categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getCategoryurlname() {
		return this.categoryurlname;
	}

	public void setCategoryurlname(String categoryurlname) {
		this.categoryurlname = categoryurlname;
	}

	public Integer getOk2() {
		return this.ok2;
	}

	public void setOk2(Integer ok2) {
		this.ok2 = ok2;
	}

	public Integer getOk3() {
		return this.ok3;
	}

	public void setOk3(Integer ok3) {
		this.ok3 = ok3;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDissScore() {
		return this.dissScore;
	}

	public void setDissScore(String dissScore) {
		this.dissScore = dissScore;
	}

	public Float getCoTasty() {
		return this.coTasty;
	}

	public void setCoTasty(Float coTasty) {
		this.coTasty = coTasty;
	}

	public Float getCoEnvironment() {
		return this.coEnvironment;
	}

	public void setCoEnvironment(Float coEnvironment) {
		this.coEnvironment = coEnvironment;
	}

	public String getFirmDocument() {
		return this.firmDocument;
	}

	public void setFirmDocument(String firmDocument) {
		this.firmDocument = firmDocument;
	}

	public String getFirmLecense() {
		return this.firmLecense;
	}

	public void setFirmLecense(String firmLecense) {
		this.firmLecense = firmLecense;
	}

	public String getBrandStory() {
		return this.brandStory;
	}

	public void setBrandStory(String brandStory) {
		this.brandStory = brandStory;
	}

	public Integer getOk4() {
		return this.ok4;
	}

	public void setOk4(Integer ok4) {
		this.ok4 = ok4;
	}

	public Integer getOk5() {
		return this.ok5;
	}

	public void setOk5(Integer ok5) {
		this.ok5 = ok5;
	}

	public Integer getOk6() {
		return this.ok6;
	}

	public void setOk6(Integer ok6) {
		this.ok6 = ok6;
	}

}