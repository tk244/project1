package model;

public class tokenMODEL {
	
	private String mailadress;
	private String created;
	
	public String getMailadress() {return mailadress;}
	public String getCreated() {return created;}
	
	public void setMailadress(String mailadress) {
		this.mailadress = mailadress;
	}

		
	public tokenMODEL(String mailadress, String created){
		this.mailadress = mailadress;
		this.created = created;
	}
}
