package lps.entities;

import java.sql.Timestamp;

/**
 * DazhongCaterThinkSummary entity. @author MyEclipse Persistence Tools
 */

public class DazhongCaterThinkSummary implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer caterid;
	private String thinkConent;
	private Integer thinkSum;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public DazhongCaterThinkSummary() {
	}

	/** full constructor */
	public DazhongCaterThinkSummary(Integer caterid, String thinkConent,
			Integer thinkSum, Timestamp createTime) {
		this.caterid = caterid;
		this.thinkConent = thinkConent;
		this.thinkSum = thinkSum;
		this.createTime = createTime;
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

	public String getThinkConent() {
		return this.thinkConent;
	}

	public void setThinkConent(String thinkConent) {
		this.thinkConent = thinkConent;
	}

	public Integer getThinkSum() {
		return this.thinkSum;
	}

	public void setThinkSum(Integer thinkSum) {
		this.thinkSum = thinkSum;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}