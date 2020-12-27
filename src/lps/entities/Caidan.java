package lps.entities;

/**
 * Caidan entity. @author MyEclipse Persistence Tools
 */

public class Caidan implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String tuijian;
	private Integer price;
	private Integer yueshou;
	private Integer canguanid;

	// Constructors

	/** default constructor */
	public Caidan() {
	}

	/** full constructor */
	public Caidan(String name, String tuijian, Integer price, Integer yueshou,
			Integer canguanid) {
		this.name = name;
		this.tuijian = tuijian;
		this.price = price;
		this.yueshou = yueshou;
		this.canguanid = canguanid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTuijian() {
		return this.tuijian;
	}

	public void setTuijian(String tuijian) {
		this.tuijian = tuijian;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getYueshou() {
		return this.yueshou;
	}

	public void setYueshou(Integer yueshou) {
		this.yueshou = yueshou;
	}

	public Integer getCanguanid() {
		return this.canguanid;
	}

	public void setCanguanid(Integer canguanid) {
		this.canguanid = canguanid;
	}

}