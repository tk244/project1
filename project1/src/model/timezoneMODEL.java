package model;

public class timezoneMODEL {

	private String timezone_id;						// ���ԑ�ID
	private String timezone_name;					// ���ԑ�
	private String timezone_checked;
	
	
	public String getTimezone_id() {return timezone_id;}
	public String getTimezone_name() {return timezone_name;}
	public String getTimezone_checked() {return timezone_checked;}

	
	public timezoneMODEL(String timezone_id, String timezone_name, String timezone_checked){
		this.timezone_id = timezone_id;
		this.timezone_name = timezone_name;
		this.timezone_checked = timezone_checked;
	}
}
