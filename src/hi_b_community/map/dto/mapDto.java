package hi_b_community.map.dto;
//나의 위치 찍기 + 목격 위치 찍기 를 위한 DTO입니다 
public class mapDto {
	private int map_no;
	private Double latitude;
	private Double longtitude;
	private String land_number_address;
	private String road_name_address;
	private String map_writer;
	private String map_phone;
	private String map_content;
	
	public mapDto() {
		super();
	}

	public mapDto(int map_no, Double latitude, Double longtitude, String land_number_address, String road_name_address,
			String map_writer, String map_phone, String map_content) {
		super();
		this.map_no = map_no;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.land_number_address = land_number_address;
		this.road_name_address = road_name_address;
		this.map_writer = map_writer;
		this.map_phone = map_phone;
		this.map_content = map_content;
	}

	public int getMap_no() {
		return map_no;
	}

	public void setMap_no(int map_no) {
		this.map_no = map_no;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}

	public String getLand_number_address() {
		return land_number_address;
	}

	public void setLand_number_address(String land_number_address) {
		this.land_number_address = land_number_address;
	}

	public String getRoad_name_address() {
		return road_name_address;
	}

	public void setRoad_name_address(String road_name_address) {
		this.road_name_address = road_name_address;
	}

	public String getMap_writer() {
		return map_writer;
	}

	public void setMap_writer(String map_writer) {
		this.map_writer = map_writer;
	}

	public String getMap_phone() {
		return map_phone;
	}

	public void setMap_phone(String map_phone) {
		this.map_phone = map_phone;
	}

	public String getMap_content() {
		return map_content;
	}

	public void setMap_content(String map_content) {
		this.map_content = map_content;
	}

	
	
	
	
	
	
	

}
