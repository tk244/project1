package dao;

import java.sql.*;
import constants.constant;

public class userDAO {

	public int loginCheck(String userid, String pass) 
	{
		Connection conn = null;

		String url = constant.url;
	    String user = constant.user;
	    String password = constant.password;
		    
	    try 
	    {   	
    	  
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	conn = DriverManager.getConnection(url, user, password);

	    	//Statement stmt = conn.createStatement();
	    	String sql = "SELECT * FROM user WHERE userid = ? and password = ? and deleted_flg = 0";

	    	PreparedStatement stmt = conn.prepareStatement(sql);
	    	stmt.setString(1, userid);
	    	stmt.setString(2, pass);

	    	ResultSet rs = stmt.executeQuery();


	    	int cnt = 0;
	    	while(rs.next()){
	    	cnt++;
	    	}

	    	rs.close();
	    	stmt.close();

	    	return cnt;
		      
	    }catch (ClassNotFoundException e){
	    	return -1;
	    }catch (SQLException e){
	    	return -1;
	    }catch (Exception e){
	    	return -1;
      	}finally{
        try{
          if (conn != null){
            conn.close();
          }
        }catch (SQLException e){
	          
        }
      }
	}
	
	public int userRegist(String userid, String pass, String username) 
	{
		Connection conn = null;

		String url = constant.url;
	    String user = constant.user;
	    String password = constant.password;
		    
	    try 
	    {   	
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	conn = DriverManager.getConnection(url, user, password);

	        // 自動コミット・モードを設定
	        conn.setAutoCommit(false);
	        
	    	String sql = "INSERT INTO jdbctestdb.user ";
	    	sql = sql + "(userid  , username  , password  ,  created_name  , updated_name) ";
	    	sql = sql + "VALUES ( '" + userid + "','" + username + "','" + pass + "' , 'system'  , 'system')  ";
	    	
	    	Statement stmt = conn.createStatement();
	    	
    	   	int num = stmt.executeUpdate(sql);
    	   	    	   	
	    	conn.commit();
	    	 
	    	stmt.close();

	    	return 0;
		      
	    }catch (ClassNotFoundException e){
	    	return -1;
	    }catch (SQLException e){
	    	return -1;
	    }catch (Exception e){
	    	return -1;
      	}finally{
        try{
			if (conn != null){
				conn.rollback();
				conn.close();
			}
        }catch (SQLException e){
	          
        }
      }
	}
}
