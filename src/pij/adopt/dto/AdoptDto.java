package pij.adopt.dto;

public class AdoptDto {
	private int adopt_no;
	private String adopt_writer;
	private String adopt_phone;
	private String adopt_title;
	private String adopt_sex;
	private String adopt_place;
	private String adopt_content;
	private String adopt_photo;
	private String adopt_email;
	private String adopt_kind;
	
	
	//기본 생성자
	public AdoptDto() {
		super();
		
	}


	//매개변수 생성자
	public AdoptDto(int adopt_no, String adopt_writer, String adopt_phone, String adopt_title, String adopt_sex,
			String adopt_place, String adopt_content, String adopt_photo, String adopt_email,String adopt_kind) {
		super();
		this.adopt_no = adopt_no;
		this.adopt_writer = adopt_writer;
		this.adopt_phone = adopt_phone;
		this.adopt_title = adopt_title;
		this.adopt_sex = adopt_sex;
		this.adopt_place = adopt_place;
		this.adopt_content = adopt_content;
		this.adopt_photo = adopt_photo;
		this.adopt_email = adopt_email;
		this.adopt_kind = adopt_kind;
	}

	//getter & setter
	public int getAdopt_no() {
		return adopt_no;
	}


	public void setAdopt_no(int adopt_no) {
		this.adopt_no = adopt_no;
	}


	public String getAdopt_writer() {
		return adopt_writer;
	}


	public void setAdopt_writer(String adopt_writer) {
		this.adopt_writer = adopt_writer;
	}


	public String getAdopt_phone() {
		return adopt_phone;
	}


	public void setAdopt_phone(String adopt_phone) {
		this.adopt_phone = adopt_phone;
	}


	public String getAdopt_title() {
		return adopt_title;
	}


	public void setAdopt_title(String adopt_title) {
		this.adopt_title = adopt_title;
	}


	public String getAdopt_sex() {
		return adopt_sex;
	}


	public void setAdopt_sex(String adopt_sex) {
		this.adopt_sex = adopt_sex;
	}


	public String getAdopt_place() {
		return adopt_place;
	}


	public void setAdopt_place(String adopt_place) {
		this.adopt_place = adopt_place;
	}


	public String getAdopt_content() {
		return adopt_content;
	}


	public void setAdopt_content(String adopt_content) {
		this.adopt_content = adopt_content;
	}


	public String getAdopt_photo() {
		return adopt_photo;
	}


	public void setAdopt_photo(String adopt_photo) {
		this.adopt_photo = adopt_photo;
	}


	public String getAdopt_email() {
		return adopt_email;
	}


	public void setAdopt_email(String adopt_email) {
		this.adopt_email = adopt_email;
	}


	public String getAdopt_kind() {
		return adopt_kind;
	}


	public void setAdopt_kind(String adopt_kind) {
		this.adopt_kind = adopt_kind;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
