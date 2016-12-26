package dao;

import java.sql.*;

import constants.constant;

public class loginHistoryDAO {

	// ログイン履歴追加
	public int loginHistoryInsert(String userid) throws Exception 
	{
		Connection conn = null;
		int ret = 0;								// リターンコード
		
		try 
		{
	    	// データベース
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	conn = DriverManager.getConnection(constant.url, constant.user, constant.password);
	    	
			// 自動コミット・モードを設定
			conn.setAutoCommit(false);
			
			// SQL作成
			String sql = "INSERT INTO loginhistory ";
			sql = sql + "(userid  , login_system  , created_name  , updated_name) ";
			sql = sql + "VALUES ( '" + userid + "', 'system', 'system'  , 'system')  ";

			// SQL実行
			Statement stmt = conn.createStatement();
			ret = stmt.executeUpdate(sql);

			stmt.close();
			
			//コミット
			conn.commit();

			return ret;

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
}
