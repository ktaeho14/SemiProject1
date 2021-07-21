package com.test.dto.userDto;

public class UserDto {
	/*
	 * MYNO NUMBER CONSTRAINT PK_ANI_USER_MYNO PRIMARY KEY,
	MYID VARCHAR2(500) NOT NULL,
	MYPW VARCHAR2(500) NOT NULL,
	MYNAME VARCHAR2(1000) NOT NULL,
	MYADDR VARCHAR2(4000) NOT NULL,
	MYPHONE VARCHAR2(20) NOT NULL,
	MYEMAIL VARCHAR2(100) NOT NULL,
	MYROLE VARCHAR2(50) NOT NULL,
	CONSTRAINT UQ_ANI_USER_MYID UNIQUE(MYID),
	CONSTRAINT UQ_ANI_USER_PHONE UNIQUE(MYPHONE),
	CONSTRAINT UQ_ANI_USER_EMAIL UNIQUE(MYEMAIL)
	 * 
	 * */
	private int myNo;
	private String myId;
	private String myPw;
	private String myName;
	private String myAddr;
	private String myPhone;
	private String myEmail;
	private String myRole;
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(int myNo, String myId, String myPw, String myName, String myAddr, String myPhone, String myEmail,
			String myRole) {
		super();
		this.myNo = myNo;
		this.myId = myId;
		this.myPw = myPw;
		this.myName = myName;
		this.myAddr = myAddr;
		this.myPhone = myPhone;
		this.myEmail = myEmail;
		this.myRole = myRole;
	}

	public int getMyNo() {
		return myNo;
	}

	public void setMyNo(int myNo) {
		this.myNo = myNo;
	}

	public String getMyId() {
		return myId;
	}

	public void setMyId(String myId) {
		this.myId = myId;
	}

	public String getMyPw() {
		return myPw;
	}

	public void setMyPw(String myPw) {
		this.myPw = myPw;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public String getMyAddr() {
		return myAddr;
	}

	public void setMyAddr(String myAddr) {
		this.myAddr = myAddr;
	}

	public String getMyPhone() {
		return myPhone;
	}

	public void setMyPhone(String myPhone) {
		this.myPhone = myPhone;
	}

	public String getMyEmail() {
		return myEmail;
	}

	public void setMyEmail(String myEmail) {
		this.myEmail = myEmail;
	}

	public String getMyRole() {
		return myRole;
	}

	public void setMyRole(String myRole) {
		this.myRole = myRole;
	}

	@Override
	public String toString() {
		return "UserDto [myNo=" + myNo + ", myId=" + myId + ", myPw=" + myPw + ", myName=" + myName + ", myAddr="
				+ myAddr + ", myPhone=" + myPhone + ", myEmail=" + myEmail + ", myRole=" + myRole + "]";
	}
	
	
	
	
}
