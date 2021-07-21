package hi_b_community.map.dto;
//보호소 안내(map_guide.jsp) 를 위한 DTO입니다 
public class mapshelterDto {
	private String shelter_name;
	private double shelter_latitude;
	private double shelter_longtitude;
	
	
	public mapshelterDto() {
		super();
	}


	public mapshelterDto(String shelter_name, double shelter_latitude, double shelter_longtitude) {
		super();
		this.shelter_name = shelter_name;
		this.shelter_latitude = shelter_latitude;
		this.shelter_longtitude = shelter_longtitude;
	}


	public String getShelter_name() {
		return shelter_name;
	}


	public void setShelter_name(String shelter_name) {
		this.shelter_name = shelter_name;
	}


	public double getShelter_latitude() {
		return shelter_latitude;
	}


	public void setShelter_latitude(double shelter_latitude) {
		this.shelter_latitude = shelter_latitude;
	}


	public double getShelter_longtitude() {
		return shelter_longtitude;
	}


	public void setShelter_longtitude(double shelter_longtitude) {
		this.shelter_longtitude = shelter_longtitude;
	}
	
	
	
}
