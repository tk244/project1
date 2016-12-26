package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import constants.constant;
import model.loginHistoryMODEL;

public class loginHistoryDAO {

	// 企業テーブル全取得
	public List<loginHistoryMODEL> GetData() throws Exception 
	{
		Connection conn = null;
		
		List<loginHistoryMODEL> loginHistoryList = new ArrayList<loginHistoryMODEL>();
		
		try 
		{
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(有効期限指定)
			String sql = "SELECT * ";
			sql = sql + " FROM loginhistory ";
			sql = sql + " WHERE deleted_flg = '0' ";
			sql = sql + " ORDER BY login_date ";
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String id = rs.getString("id");								// ID
				String userid = rs.getString("userid");						// ユーザID
				String logindate = rs.getString("login_date");				//　ログイン日時
				
				loginHistoryMODEL loginHistorymodel = new loginHistoryMODEL(id, userid, logindate);
				loginHistoryList.add(loginHistorymodel);
			}

			rs.close();
			stmt.close();
			  
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
		return loginHistoryList;
	}
	
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
