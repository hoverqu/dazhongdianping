package lps.entities;

/**
 * DazhongDishRecommend entity. @author MyEclipse Persistence Tools
 */

public class DazhongDishRecommend implements java.io.Serializable {

	// Fields

	private Long id;
	private Long caterid;
	private Integer recommendCount;
	private String dishTag;
	private Integer price;
	private Long dishid;
	private String dishname;
	private Integer createTime;
	private String picurl;

	// Constructors

	/** default constructor */
	public DazhongDishRecommend() {
	}

	/** full constructor */
	public DazhongDishRecommend(Long caterid, Integer recommendCount,
			String dishTag, Integer price, Long dishid, String dishname,
			Integer createTime, String picurl) {
		this.caterid = caterid;
		this.recommendCount = recommendCount;
		this.dishTag = dishTag;
		this.price = price;
		this.dishid = dishid;
		this.dishname = dishname;
		this.createTime = createTime;
		this.picurl = picurl;
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

	public Integer getRecommendCount() {
		return this.recommendCount;
	}

	public void setRecommendCount(Integer recommendCount) {
		this.recommendCount = recommendCount;
	}

	public String getDishTag() {
		return this.dishTag;
	}

	public void setDishTag(String dishTag) {
		this.dishTag = dishTag;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Long getDishid() {
		return this.dishid;
	}

	public void setDishid(Long dishid) {
		this.dishid = dishid;
	}

	public String getDishname() {
		return this.dishname;
	}

	public void setDishname(String dishname) {
		this.dishname = dishname;
	}

	public Integer getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public String getPicurl() {
		return this.picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

}