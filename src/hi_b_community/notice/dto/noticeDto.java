package hi_b_community.notice.dto;

import java.util.Date;

public class noticeDto {
	private int notice_no;
	private String notice_title;
	private String notice_name;
	private String notice_content;
	private Date notice_regdate;
	private int notice_hit;
	private String notice_deleteyn;
	private String notice_myid;
	
	
	public noticeDto() {
		super();
	}


	public noticeDto(int notice_no, String notice_title, String notice_name, String notice_content, Date notice_regdate,
			int notice_hit, String notice_deleteyn, String notice_myid) {
		super();
		this.notice_no = notice_no;
		this.notice_title = notice_title;
		this.notice_name = notice_name;
		this.notice_content = notice_content;
		this.notice_regdate = notice_regdate;
		this.notice_hit = notice_hit;
		this.notice_deleteyn = notice_deleteyn;
		this.notice_myid = notice_myid;
	}


	public int getNotice_no() {
		return notice_no;
	}


	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}


	public String getNotice_title() {
		return notice_title;
	}


	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}


	public String getNotice_name() {
		return notice_name;
	}


	public void setNotice_name(String notice_name) {
		this.notice_name = notice_name;
	}


	public String getNotice_content() {
		return notice_content;
	}


	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}


	public Date getNotice_regdate() {
		return notice_regdate;
	}


	public void setNotice_regdate(Date notice_regdate) {
		this.notice_regdate = notice_regdate;
	}


	public int getNotice_hit() {
		return notice_hit;
	}


	public void setNotice_hit(int notice_hit) {
		this.notice_hit = notice_hit;
	}


	public String getNotice_deleteyn() {
		return notice_deleteyn;
	}


	public void setNotice_deleteyn(String notice_deleteyn) {
		this.notice_deleteyn = notice_deleteyn;
	}


	public String getNotice_myid() {
		return notice_myid;
	}


	public void setNotice_myid(String notice_myid) {
		this.notice_myid = notice_myid;
	}


	
	
	
	
	
	
	

}
