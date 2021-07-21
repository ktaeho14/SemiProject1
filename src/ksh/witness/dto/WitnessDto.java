package ksh.witness.dto;

import java.util.Date;

public class WitnessDto {
	private int no;
	private String color;
	private String kind;
	private String place;
	private String specialmark;
	private String witness_pic;
	private String content;
	private Date witness_date;
	private String phone_no;
	private String city;
	private String writer;
	public WitnessDto() {
		super();
		
	}
	public WitnessDto(int no, String color, String kind, String place, String specialmark, String witness_pic,
			String content, Date witness_date,String phone_no,String city,String writer) {
		super();
		this.no = no;
		this.color = color;
		this.kind = kind;
		this.place = place;
		this.specialmark = specialmark;
		this.witness_pic = witness_pic;
		this.content = content;
		this.witness_date = witness_date;
		this.phone_no = phone_no;
		this.city = city;
		this.writer = writer;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getSpecialmark() {
		return specialmark;
	}
	public void setSpecialmark(String specialmark) {
		this.specialmark = specialmark;
	}
	public String getWitness_pic() {
		return witness_pic;
	}
	public void setWitness_pic(String witness_pic) {
		this.witness_pic = witness_pic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWitness_date() {
		return witness_date;
	}
	public void setWitness_date(Date witness_date) {
		this.witness_date = witness_date;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
	
	
	
	
}
