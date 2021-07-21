package hi_b_community.question.dto;

import java.util.Date;

public class questionDto {
	private int question_boardno;
	private int question_groupno;
	private int question_groupsq;
	private int question_titletab;
	private String question_title;
	private String question_content;
	private String question_name;
	private Date question_regdate;
	private int question_hit;
	private String question_deleteyn;
	private String question_myid;
	
	
	public questionDto() {
		super();
	}


	public questionDto(int question_boardno, int question_groupno, int question_groupsq, int question_titletab,
			String question_title, String question_content, String question_name, Date question_regdate,
			int question_hit, String question_deleteyn, String question_myid) {
		super();
		this.question_boardno = question_boardno;
		this.question_groupno = question_groupno;
		this.question_groupsq = question_groupsq;
		this.question_titletab = question_titletab;
		this.question_title = question_title;
		this.question_content = question_content;
		this.question_name = question_name;
		this.question_regdate = question_regdate;
		this.question_hit = question_hit;
		this.question_deleteyn = question_deleteyn;
		this.question_myid = question_myid;
	}


	public int getQuestion_boardno() {
		return question_boardno;
	}


	public void setQuestion_boardno(int question_boardno) {
		this.question_boardno = question_boardno;
	}


	public int getQuestion_groupno() {
		return question_groupno;
	}


	public void setQuestion_groupno(int question_groupno) {
		this.question_groupno = question_groupno;
	}


	public int getQuestion_groupsq() {
		return question_groupsq;
	}


	public void setQuestion_groupsq(int question_groupsq) {
		this.question_groupsq = question_groupsq;
	}


	public int getQuestion_titletab() {
		return question_titletab;
	}


	public void setQuestion_titletab(int question_titletab) {
		this.question_titletab = question_titletab;
	}


	public String getQuestion_title() {
		return question_title;
	}


	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}


	public String getQuestion_content() {
		return question_content;
	}


	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}


	public String getQuestion_name() {
		return question_name;
	}


	public void setQuestion_name(String question_name) {
		this.question_name = question_name;
	}


	public Date getQuestion_regdate() {
		return question_regdate;
	}


	public void setQuestion_regdate(Date question_regdate) {
		this.question_regdate = question_regdate;
	}


	public int getQuestion_hit() {
		return question_hit;
	}


	public void setQuestion_hit(int question_hit) {
		this.question_hit = question_hit;
	}


	public String getQuestion_deleteyn() {
		return question_deleteyn;
	}


	public void setQuestion_deleteyn(String question_deleteyn) {
		this.question_deleteyn = question_deleteyn;
	}


	public String getQuestion_myid() {
		return question_myid;
	}


	public void setQuestion_myid(String question_myid) {
		this.question_myid = question_myid;
	}


	
	
	
 
	
}
