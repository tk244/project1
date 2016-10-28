package dao;

import java.sql.*;
import constants.constant;

public class tokenDAO {

	public int tokenInsert(Connection conn, String mailadress, String token) 
	{		    
	    try 
	    {   	
        
	    	String sql = "INSERT INTO token ";
	    	sql = sql + "(mailadress  , token  ,  created_name  , updated_name) ";
	    	sql = sql + "VALUES ( '" + mailadress + "','" + token + "', 'system'  , 'system')  ";
	    	
	    	Statement stmt = conn.createStatement();
	    	
    	   	int num = stmt.executeUpdate(sql);
    	   	      	
	    	 
	    	stmt.close();

	    	return num;
		      
	    }catch (SQLException e){
	    	return -1;
	    }catch (Exception e){
	    	return -1;
      	}finally{
  
        }
	}
}