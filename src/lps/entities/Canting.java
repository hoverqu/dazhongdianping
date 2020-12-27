package lps.entities;

/**
 * Canting entity. @author MyEclipse Persistence Tools
 */

public class Canting implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String fenshu;
	private String address;
	private String jingdu;
	private String weidu;
	private Integer time;
	private Integer qisongjiage;
	private float lufei;
	private Integer dafen;
	private Integer pinglunrenshu;
	private Integer foodid;
	private Integer webid;

	// Constructors

	/** default constructor */
	public Canting() {
	}

	/** full constructor */
	public Canting(String name, String fenshu, String address, String jingdu,
			String weidu, Integer time, Integer qisongjiage, Integer lufei,
			Integer dafen, Integer pinglunrenshu, Integer foodid, Integer webid) {
		this.name = name;
		this.fenshu = fenshu;
		this.address = address;
		this.jingdu = jingdu;
		this.weidu = weidu;
		this.time = time;
		this.qisongjiage = qisongjiage;
		this.lufei = lufei;
		this.dafen = dafen;
		this.pinglunrenshu = pinglunrenshu;
		this.foodid = foodid;
		this.webid = webid;
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

	public String getFenshu() {
		return this.fenshu;
	}

	public void setFenshu(String fenshu) {
		this.fenshu = fenshu;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJingdu() {
		return this.jingdu;
	}

	public void setJingdu(String jingdu) {
		this.jingdu = jingdu;
	}

	public String getWeidu() {
		return this.weidu;
	}

	public void setWeidu(String weidu) {
		this.weidu = weidu;
	}

	public Integer getTime() {
		return this.time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getQisongjiage() {
		return this.qisongjiage;
	}

	public void setQisongjiage(Integer qisongjiage) {
		this.qisongjiage = qisongjiage;
	}

	public float getLufei() {
		return this.lufei;
	}

	public void setLufei(float lufei) {
		this.lufei = lufei;
	}

	public Integer getDafen() {
		return this.dafen;
	}

	public void setDafen(Integer dafen) {
		this.dafen = dafen;
	}

	public Integer getPinglunrenshu() {
		return this.pinglunrenshu;
	}

	public void setPinglunrenshu(Integer pinglunrenshu) {
		this.pinglunrenshu = pinglunrenshu;
	}

	public Integer getFoodid() {
		return this.foodid;
	}

	public void setFoodid(Integer foodid) {
		this.foodid = foodid;
	}

	public Integer getWebid() {
		return this.webid;
	}

	public void setWebid(Integer webid) {
		this.webid = webid;
	}

}