package model;

public class businessMODEL {

	private String business_id;				// ‹ÆŽíID
	private String business_name;			// ‹ÆŽí–¼
	private String business_checked;
	
	public String getBusiness_id() {return business_id;}
	public String getBusiness_name() {return business_name;}
	public String getBusiness_checked() {return business_checked;}

	
	public businessMODEL(String business_id, String business_name, String business_checked){
		this.business_id = business_id;
		this.business_name = business_name;
		this.business_checked = business_checked;
	}
}
