package lps.entities;

/**
 * DazhongCaterDishevaluate entity. @author MyEclipse Persistence Tools
 */

public class DazhongCaterDishevaluate implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer caterFirmid;
	private Integer dishid;
	private Integer saleSummonth;

	// Constructors

	/** default constructor */
	public DazhongCaterDishevaluate() {
	}

	/** full constructor */
	public DazhongCaterDishevaluate(Integer caterFirmid, Integer dishid,
			Integer saleSummonth) {
		this.caterFirmid = caterFirmid;
		this.dishid = dishid;
		this.saleSummonth = saleSummonth;
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

	public Integer getDishid() {
		return this.dishid;
	}

	public void setDishid(Integer dishid) {
		this.dishid = dishid;
	}

	public Integer getSaleSummonth() {
		return this.saleSummonth;
	}

	public void setSaleSummonth(Integer saleSummonth) {
		this.saleSummonth = saleSummonth;
	}

}