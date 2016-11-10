package dao;

import java.sql.*;
import constants.constant;

public class userDAO {

	public int loginCheck(String userid, String pass) throws Exception 
	{
		Connection conn = null;
		int cnt = 0;
		
	    try 
	    {   	
	    	// データベース
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

	    	String sql = "SELECT * FROM user WHERE userid = ? and password = ? and deleted_flg = 0";

	    	PreparedStatement stmt = conn.prepareStatement(sql);
	    	stmt.setString(1, userid);
	    	stmt.setString(2, pass);

	    	// SQL実行
	    	ResultSet rs = stmt.executeQuery();

	    	while(rs.next()){
	    		cnt++;
	    		break;
	    	}

	    	rs.close();
	    	stmt.close();

	    	return cnt;
		      
	    }catch (ClassNotFoundException e){
	    	throw new Exception(getClass().getName() + " " + e.getMessage());
	    }catch (SQLException e){
	    	throw new Exception(getClass().getName() + " " + e.getMessage());
	    }catch (Exception e){
	    	throw new Exception(getClass().getName() + " " + e.getMessage());
      	}finally{
        try{
          if (conn != null){
            conn.close();
          }
        }catch (SQLException e){
        	throw new Exception(getClass().getName() + " " + e.getMessage());
        }
      }
	}
	
	public int userRegist(String userid, String pass, String username) throws Exception 
	{
		Connection conn = null;
		int ret = 0;
		
	    try 
	    {   	
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

	        // 自動コミット・モードを設定
	        conn.setAutoCommit(false);
	        
	    	String sql = "INSERT INTO jdbctestdb.user ";
	    	sql = sql + "(userid  , username  , password  ,  created_name  , updated_name) ";
	    	sql = sql + "VALUES ( '" + userid + "','" + username + "','" + pass + "' , 'system'  , 'system')  ";
	    	
	    	Statement stmt = conn.createStatement();
	    	
	    	ret = stmt.executeUpdate(sql);
	    	
	    	if (ret != 1){
	    		// 異常
	    		return -1;
	    	}
    	   	    	
	    	// コミット
	    	conn.commit();
	    	 
	    	stmt.close();

	    	return 0;
		      
	    }catch (ClassNotFoundException e){
	    	throw new Exception(getClass().getName() + " " + e.getMessage());
	    }catch (SQLException e){
	    	throw new Exception(getClass().getName() + " " + e.getMessage());
	    }catch (Exception e){
	    	throw new Exception(getClass().getName() + " " + e.getMessage());
      	}finally{
        try{
			if (conn != null){
				conn.rollback();
				conn.close();
			}
        }catch (SQLException e){
        	throw new Exception(getClass().getName() + " " + e.getMessage());
        }
      }
	}
	
	public int userExist(String userid) throws Exception 
	{
		Connection conn = null;
		int cnt = 0;
		
	    try 
	    {   	
	    	// データベース
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

	    	String sql = "SELECT * FROM user WHERE userid = ? and deleted_flg = 0";

	    	PreparedStatement stmt = conn.prepareStatement(sql);
	    	stmt.setString(1, userid);

	    	// SQL実行
	    	ResultSet rs = stmt.executeQuery();

	    	while(rs.next()){
	    		cnt++;
	    		break;
	    	}

	    	rs.close();
	    	stmt.close();

	    	return cnt;
		      
	    }catch (ClassNotFoundException e){
	    	throw new Exception(getClass().getName() + " " + e.getMessage());
	    }catch (SQLException e){
	    	throw new Exception(getClass().getName() + " " + e.getMessage());
	    }catch (Exception e){
	    	throw new Exception(getClass().getName() + " " + e.getMessage());
      	}finally{
        try{
          if (conn != null){
            conn.close();
          }
        }catch (SQLException e){
        	throw new Exception(getClass().getName() + " " + e.getMessage());
        }
      }
	}
	
}
