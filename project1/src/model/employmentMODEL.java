package model;

public class employmentMODEL {

	private String employment_id;					// 雇用形態ID
	private String employment_name;					// 雇用形態
	private String employment_checked;
	
	public String getEmployment_id() {return employment_id;}
	public String getEmployment_name() {return employment_name;}
	public String getEmployment_checked() {return employment_checked;}

	
	public employmentMODEL(String employment_id, String employment_name, String employment_checked){
		this.employment_id = employment_id;
		this.employment_name = employment_name;
		this.employment_checked = employment_checked;
	}
}
