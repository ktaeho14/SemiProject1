package hi_b_community.complain.dto;

import java.util.Date;

public class complainDto {
	private int complain_boardno;
	private int complain_groupno;
	private int complain_groupsq;
	private int complain_titletab;
	private String complain_title;
	private String complain_content;
	private String complain_name;
	private Date complain_regdate;
	private int complain_hit;
	private String complain_deleteyn;
	private String complain_myid;
	
	public complainDto() {
		super();
	}

	public complainDto(int complain_boardno, int complain_groupno, int complain_groupsq, int complain_titletab,
			String complain_title, String complain_content, String complain_name, Date complain_regdate,
			int complain_hit, String complain_deleteyn, String complain_myid) {
		super();
		this.complain_boardno = complain_boardno;
		this.complain_groupno = complain_groupno;
		this.complain_groupsq = complain_groupsq;
		this.complain_titletab = complain_titletab;
		this.complain_title = complain_title;
		this.complain_content = complain_content;
		this.complain_name = complain_name;
		this.complain_regdate = complain_regdate;
		this.complain_hit = complain_hit;
		this.complain_deleteyn = complain_deleteyn;
		this.complain_myid = complain_myid;
	}

	public int getComplain_boardno() {
		return complain_boardno;
	}

	public void setComplain_boardno(int complain_boardno) {
		this.complain_boardno = complain_boardno;
	}

	public int getComplain_groupno() {
		return complain_groupno;
	}

	public void setComplain_groupno(int complain_groupno) {
		this.complain_groupno = complain_groupno;
	}

	public int getComplain_groupsq() {
		return complain_groupsq;
	}

	public void setComplain_groupsq(int complain_groupsq) {
		this.complain_groupsq = complain_groupsq;
	}

	public int getComplain_titletab() {
		return complain_titletab;
	}

	public void setComplain_titletab(int complain_titletab) {
		this.complain_titletab = complain_titletab;
	}

	public String getComplain_title() {
		return complain_title;
	}

	public void setComplain_title(String complain_title) {
		this.complain_title = complain_title;
	}

	public String getComplain_content() {
		return complain_content;
	}

	public void setComplain_content(String complain_content) {
		this.complain_content = complain_content;
	}

	public String getComplain_name() {
		return complain_name;
	}

	public void setComplain_name(String complain_name) {
		this.complain_name = complain_name;
	}

	public Date getComplain_regdate() {
		return complain_regdate;
	}

	public void setComplain_regdate(Date complain_regdate) {
		this.complain_regdate = complain_regdate;
	}

	public int getComplain_hit() {
		return complain_hit;
	}

	public void setComplain_hit(int complain_hit) {
		this.complain_hit = complain_hit;
	}

	public String getComplain_deleteyn() {
		return complain_deleteyn;
	}

	public void setComplain_deleteyn(String complain_deleteyn) {
		this.complain_deleteyn = complain_deleteyn;
	}

	public String getComplain_myid() {
		return complain_myid;
	}

	public void setComplain_myid(String complain_myid) {
		this.complain_myid = complain_myid;
	}

	

	
	
	
	
}
