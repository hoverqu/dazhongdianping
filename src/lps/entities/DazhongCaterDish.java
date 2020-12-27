package lps.entities;

import java.sql.Timestamp;

/**
 * DazhongCaterDish entity. @author MyEclipse Persistence Tools
 */

public class DazhongCaterDish implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer caterid;
	private Timestamp createTime;
	private String dishname;
	private Float price;
	private String picurl;
	private String descr;
	private String unit;
	private Integer zanCount;
	private Float dissLevel;
	private String type;
	private Integer salesMonthsum;

	// Constructors

	/** default constructor */
	public DazhongCaterDish() {
	}

	/** full constructor */
	public DazhongCaterDish(Integer caterid, Timestamp createTime,
			String dishname, Float price, String picurl, String descr,
			String unit, Integer zanCount, Float dissLevel, String type,
			Integer salesMonthsum) {
		this.caterid = caterid;
		this.createTime = createTime;
		this.dishname = dishname;
		this.price = price;
		this.picurl = picurl;
		this.descr = descr;
		this.unit = unit;
		this.zanCount = zanCount;
		this.dissLevel = dissLevel;
		this.type = type;
		this.salesMonthsum = salesMonthsum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCaterid() {
		return this.caterid;
	}

	public void setCaterid(Integer caterid) {
		this.caterid = caterid;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getDishname() {
		return this.dishname;
	}

	public void setDishname(String dishname) {
		this.dishname = dishname;
	}

	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getPicurl() {
		return this.picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getZanCount() {
		return this.zanCount;
	}

	public void setZanCount(Integer zanCount) {
		this.zanCount = zanCount;
	}

	public Float getDissLevel() {
		return this.dissLevel;
	}

	public void setDissLevel(Float dissLevel) {
		this.dissLevel = dissLevel;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSalesMonthsum() {
		return this.salesMonthsum;
	}

	public void setSalesMonthsum(Integer salesMonthsum) {
		this.salesMonthsum = salesMonthsum;
	}

}