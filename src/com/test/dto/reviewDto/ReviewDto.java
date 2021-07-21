package com.test.dto.reviewDto;

public class ReviewDto {
/*
 * RV_NO    NUMBER  CONSTRAINT PK_RV_NO PRIMARY KEY,
	NUM      NUMBER  NOT NULL,
	TITLE    VARCHAR(2000) NOT NULL,
	RV_PIC   VARCHAR(2000) NOT NULL,
	ID       VARCHAR(500)  NOT NULL,
	LOSTDATE  DATE          NOT NULL,
	PROVINCE  VARCHAR(200)  NOT NULL,--도/특별시/광역시
	RESERV    VARCHAR(2000) NOT NULL,--나머지 주소
	LOSTPLACE VARCHAR(2000) NOT NULL,
	SPECIES   VARCHAR(100)  NOT NULL,
	CATE      VARCHAR(2000) NOT NULL,
	CONTENT   LONG			NOT NULL,
 * */
	private int 	rvNo;
	private int 	num;
	private String title;
	private String rvPic;
	private String id;
	private String lostDate;
	private String province;
	private String reserv;
	private String lostPlace;
	private String species;
	private String cate;
	private String content;
	private String regDate;
	
	public ReviewDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewDto(int rvNo, int num, String title, String rvPic, String id, String lostDate, String province,
			String reserv, String lostPlace, String species, String cate, String content, String regDate) {
		super();
		this.rvNo = rvNo;
		this.num = num;
		this.title = title;
		this.rvPic = rvPic;
		this.id = id;
		this.lostDate = lostDate;
		this.province = province;
		this.reserv = reserv;
		this.lostPlace = lostPlace;
		this.species = species;
		this.cate = cate;
		this.content = content;
		this.regDate = regDate;
	}

	public int getRvNo() {
		return rvNo;
	}

	public void setRvNo(int rvNo) {
		this.rvNo = rvNo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRvPic() {
		return rvPic;
	}

	public void setRvPic(String rvPic) {
		this.rvPic = rvPic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLostDate() {
		return lostDate;
	}

	public void setLostDate(String lostDate) {
		this.lostDate = lostDate;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getReserv() {
		return reserv;
	}

	public void setReserv(String reserv) {
		this.reserv = reserv;
	}

	public String getLostPlace() {
		return lostPlace;
	}

	public void setLostPlace(String lostPlace) {
		this.lostPlace = lostPlace;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "ReviewDto [rvNo=" + rvNo + ", num=" + num + ", title=" + title + ", rvPic=" + rvPic + ", id=" + id
				+ ", lostDate=" + lostDate + ", province=" + province + ", reserv=" + reserv + ", lostPlace="
				+ lostPlace + ", species=" + species + ", cate=" + cate + ", content=" + content +",regDate="+ regDate+"]";
	}
	
	
	
	
}
