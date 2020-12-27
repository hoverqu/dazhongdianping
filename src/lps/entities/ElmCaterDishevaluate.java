package lps.entities;

import java.sql.Timestamp;

/**
 * ElmCaterDishevaluate entity. @author MyEclipse Persistence Tools
 */

public class ElmCaterDishevaluate implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer caterFirmid;
	private String dishid;
	private Integer saleSummonth;
	private Integer dissStarlevel;
	private Timestamp dissDatetime;
	private Timestamp createTime;
	private Timestamp modifyDate;

	// Constructors

	/** default constructor */
	public ElmCaterDishevaluate() {
	}

	/** full constructor */
	public ElmCaterDishevaluate(Integer caterFirmid, String dishid,
			Integer saleSummonth, Integer dissStarlevel,
			Timestamp dissDatetime, Timestamp createTime, Timestamp modifyDate) {
		this.caterFirmid = caterFirmid;
		this.dishid = dishid;
		this.saleSummonth = saleSummonth;
		this.dissStarlevel = dissStarlevel;
		this.dissDatetime = dissDatetime;
		this.createTime = createTime;
		this.modifyDate = modifyDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCaterFirmid() {
		return this.caterFirmid;
	}

	public void setCaterFirmid(Integer caterFirmid) {
		this.caterFirmid = caterFirmid;
	}

	public String getDishid() {
		return this.dishid;
	}

	public void setDishid(String dishid) {
		this.dishid = dishid;
	}

	public Integer getSaleSummonth() {
		return this.saleSummonth;
	}

	public void setSaleSummonth(Integer saleSummonth) {
		this.saleSummonth = saleSummonth;
	}

	public Integer getDissStarlevel() {
		return this.dissStarlevel;
	}

	public void setDissStarlevel(Integer dissStarlevel) {
		this.dissStarlevel = dissStarlevel;
	}

	public Timestamp getDissDatetime() {
		return this.dissDatetime;
	}

	public void setDissDatetime(Timestamp dissDatetime) {
		this.dissDatetime = dissDatetime;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

}