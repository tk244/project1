package model;

public class companyMODEL {

	private String company_id;				// ‰ïĞID
	private String company_name;			// ‰ïĞ–¼
	private String area;					// “s“¹•{Œ§
	private String business;				// ‹Æí
	private String salary;					// ‹‹—^
	private String remarks;					// ”õl
	
	
	public String getCompany_id() {return company_id;}
	public String getCompany_name() {return company_name;}
	public String getArea() {return area;}
	public String getBusiness() {return business;}
	public String getSalary() {return salary;}
	public String getRemarks() {return remarks;}
	
	
	public companyMODEL(String company_id, String company_name, String area,
			String business, String salary, String remarks){
		this.company_id = company_id;
		this.company_name = company_name;
		this.area = area;
		this.business = business;
		this.salary = salary;
		this.remarks = remarks;
	}
}
