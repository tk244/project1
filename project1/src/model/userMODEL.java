package model;

import dao.userDAO;
import common.commonClass;

public class userMODEL {
	
	private String Userid;
	private String Pass;
	private String Username;
	
	public void SetUserid(String userid) {this.Userid = userid;}
	public void SetPass(String pass) {this.Pass = pass;}
	public void SetUsername(String username) {this.Username = username;}
	
	public String getUserid() {return Userid;}
	public String getPass() {return Pass;}
	public String getUsername() {return Username;}
	
	public int loginCheck() 
	{
		int ret = 0;
		String password = "";
		
		commonClass com = new commonClass();
		
		password = com.encryptStr(Pass);
		
		userDAO userdao = new userDAO();
		
		ret = userdao.loginCheck(Userid, password);
		
		return ret;
	}
	
	
	public int userRegist() 
	{
		int ret = 0;
		String password = "";
		
		commonClass com = new commonClass();
		
		password = com.encryptStr(Pass);
		
		userDAO userdao = new userDAO();
		
		ret = userdao.userRegist(Userid, password, Username);
		
		return ret;
	}
}
