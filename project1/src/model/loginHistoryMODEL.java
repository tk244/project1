package model;

public class loginHistoryMODEL {

	private String id;						// ID
	private String userid;					// ���[�UID
	private String logindate;				// ���O�C������	
	
	public String getId() {return id;}
	public String getUserid() {return userid;}
	public String getLogindate() {return logindate;}	
	
	public loginHistoryMODEL(String id, String userid, String logindate){
		this.id = id;
		this.userid = userid;
		this.logindate = logindate;
	}
}
