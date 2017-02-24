package model;

public class areaMODEL {

	private String area_id;				// “s“¹•{Œ§ID
	private String area_name;			// “s“¹•{Œ§
	private String area_checked;
	
	
	public String getArea_id() {return area_id;}
	public String getArea_name() {return area_name;}
	public String getArea_checked() {return area_checked;}

	
	public areaMODEL(String area_id, String area_name, String area_checked){
		this.area_id = area_id;
		this.area_name = area_name;
		this.area_checked = area_checked;
	}
}
