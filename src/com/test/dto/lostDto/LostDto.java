package com.test.dto.lostDto;


public class LostDto {
	private int num;
	private String title;
	private String writer;
	private String tel;
	private String   lostDate;
	private String province;
	private String reserv;
	private String lostPlace;
	private String lostPic;
	private String detail;
	private String species;
	private String cate;
	private String etc;
	private int    watch;
	
	public LostDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public LostDto(int num, String title, String writer, String tel, String lostDate, String province, String reserv,
			String lostPlace, String lostPic, String detail, String species, String cate, String etc, int watch) {
		super();
		this.num = num;
		this.title = title;
		this.writer = writer;
		this.tel = tel;
		this.lostDate = lostDate;
		this.province = province;
		this.reserv = reserv;
		this.lostPlace = lostPlace;
		this.lostPic = lostPic;
		this.detail = detail;
		this.species = species;
		this.cate = cate;
		this.etc = etc;
		this.watch= watch;
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



	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public String getLostPic() {
		return lostPic;
	}

	public void setLostPic(String lostPic) {
		this.lostPic = lostPic;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}


	public int getWatch() {
		return watch;
	}


	public void setWatch(int watch) {
		this.watch = watch;
	}


	@Override
	public String toString() {
		return "LostDto [num=" + num + ", title=" + title + ", writer=" + writer + ", tel=" + tel + ", lostDate="
				+ lostDate + ", province=" + province + ", reserv=" + reserv + ", lostPlace=" + lostPlace + ", lostPic="
				+ lostPic + ", detail=" + detail + ", species=" + species + ", cate=" + cate + ", etc=" + etc
				+ ", watch=" + watch + "]";
	}

	
}
