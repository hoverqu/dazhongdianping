package lps.entities;

/**
 * DazhongCaterPromotion entity. @author MyEclipse Persistence Tools
 */

public class DazhongCaterPromotion implements java.io.Serializable {

	// Fields

	private Long id;
	private Long caterid;
	private String promotionpicurl;
	private String promotionname;
	private Float nowPrice;
	private Float oldPrice;
	private Integer saleCount;
	private Integer createTime;
	private Integer star;
	private String commentsCount;
	private String validateDate;
	private Long promoUuid;
	private String tag;
	private Integer ok;

	// Constructors

	/** default constructor */
	public DazhongCaterPromotion() {
	}

	/** full constructor */
	public DazhongCaterPromotion(Long caterid, String promotionpicurl,
			String promotionname, Float nowPrice, Float oldPrice,
			Integer saleCount, Integer createTime, Integer star,
			String commentsCount, String validateDate, Long promoUuid,
			String tag, Integer ok) {
		this.caterid = caterid;
		this.promotionpicurl = promotionpicurl;
		this.promotionname = promotionname;
		this.nowPrice = nowPrice;
		this.oldPrice = oldPrice;
		this.saleCount = saleCount;
		this.createTime = createTime;
		this.star = star;
		this.commentsCount = commentsCount;
		this.validateDate = validateDate;
		this.promoUuid = promoUuid;
		this.tag = tag;
		this.ok = ok;
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

	public String getPromotionpicurl() {
		return this.promotionpicurl;
	}

	public void setPromotionpicurl(String promotionpicurl) {
		this.promotionpicurl = promotionpicurl;
	}

	public String getPromotionname() {
		return this.promotionname;
	}

	public void setPromotionname(String promotionname) {
		this.promotionname = promotionname;
	}

	public Float getNowPrice() {
		return this.nowPrice;
	}

	public void setNowPrice(Float nowPrice) {
		this.nowPrice = nowPrice;
	}

	public Float getOldPrice() {
		return this.oldPrice;
	}

	public void setOldPrice(Float oldPrice) {
		this.oldPrice = oldPrice;
	}

	public Integer getSaleCount() {
		return this.saleCount;
	}

	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}

	public Integer getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public Integer getStar() {
		return this.star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getCommentsCount() {
		return this.commentsCount;
	}

	public void setCommentsCount(String commentsCount) {
		this.commentsCount = commentsCount;
	}

	public String getValidateDate() {
		return this.validateDate;
	}

	public void setValidateDate(String validateDate) {
		this.validateDate = validateDate;
	}

	public Long getPromoUuid() {
		return this.promoUuid;
	}

	public void setPromoUuid(Long promoUuid) {
		this.promoUuid = promoUuid;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getOk() {
		return this.ok;
	}

	public void setOk(Integer ok) {
		this.ok = ok;
	}

}