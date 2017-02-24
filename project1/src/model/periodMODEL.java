package model;

public class periodMODEL {

	private String period_id;						// ŠúŠÔID
	private String period_name;						// ŠúŠÔ
	private String period_checked;	
	
	public String getPeriod_id() {return period_id;}
	public String getPeriod_name() {return period_name;}
	public String getPeriod_checked() {return period_checked;}

	
	public periodMODEL(String period_id, String period_name, String period_checked){
		this.period_id = period_id;
		this.period_name = period_name;
		this.period_checked = period_checked;
	}
}
