package model;

public class companyMODEL {

	private String company_id;				// ���ID
	private String company_name;			// ��Ж�
	private String area;					// �s���{��
	private String business;				// �Ǝ�
	private String salary;					// ���^
	private String remarks;					// ���l
	
	
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
