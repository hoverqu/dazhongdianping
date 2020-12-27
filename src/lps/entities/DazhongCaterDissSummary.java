package lps.entities;

/**
 * DazhongCaterDissSummary entity. @author MyEclipse Persistence Tools
 */

public class DazhongCaterDissSummary implements java.io.Serializable {

	// Fields

	private Long id;
	private Long caterFirmid;
	private Integer dissCount;
	private Integer goodDiss;
	private Integer modiDiss;
	private Integer lowDiss;
	private Integer createTime;

	// Constructors

	/** default constructor */
	public DazhongCaterDissSummary() {
	}

	/** full constructor */
	public DazhongCaterDissSummary(Long caterFirmid, Integer dissCount,
			Integer goodDiss, Integer modiDiss, Integer lowDiss,
			Integer createTime) {
		this.caterFirmid = caterFirmid;
		this.dissCount = dissCount;
		this.goodDiss = goodDiss;
		this.modiDiss = modiDiss;
		this.lowDiss = lowDiss;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCaterFirmid() {
		return this.caterFirmid;
	}

	public void setCaterFirmid(Long caterFirmid) {
		this.caterFirmid = caterFirmid;
	}

	public Integer getDissCount() {
		return this.dissCount;
	}

	public void setDissCount(Integer dissCount) {
		this.dissCount = dissCount;
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

	public Integer getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

}