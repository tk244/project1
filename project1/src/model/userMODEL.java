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
	
	public int loginCheck() throws Exception 
	{
		int ret = 0;								//リターンコード
		String password = "";
		
		try {
			commonClass com = new commonClass();
			
			password = com.encryptStr(Pass);
			
			userDAO userdao = new userDAO();
			
			ret = userdao.loginCheck(Userid, password);
	    }catch (Exception e){
	    	throw new Exception(getClass().getName() + " " + e.getMessage());
	    }
		return ret;
	}
	
	
	public int userRegist() throws Exception 
	{
		int ret = 0;
		String password = "";
		
		try {
			commonClass com = new commonClass();
			
			password = com.encryptStr(Pass);
			
			userDAO userdao = new userDAO();
			
			ret = userdao.userRegist(Userid, password, Username);
	    }catch (Exception e){
	    	throw new Exception(getClass().getName() + " " + e.getMessage());
	    }
		return ret;
	}
	
	public int userExist() throws Exception
	{
		int ret = 0;								//リターンコード
		
		try {
			userDAO userdao = new userDAO();
			
			ret = userdao.userExist(Userid);
	    }catch (Exception e){
	    	throw new Exception(getClass().getName() + " " + e.getMessage());
	    }
		return ret;
	}
}
