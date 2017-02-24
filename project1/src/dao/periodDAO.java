package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import constants.constant;
import model.periodMODEL;

public class periodDAO {

	/**
	 * 期間テーブル全取得
	 * @throws Exception 
	 */
	public List<periodMODEL> GetData() throws Exception 
	{
		Connection conn = null;
		
		List<periodMODEL> periodList = new ArrayList<periodMODEL>();
		
		try 
		{
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(有効期限指定)
			String sql = "SELECT * FROM period_mst where deleted_flg = '0' ";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String period_id = rs.getString("period_id");				// 期間ID
				String period_name = rs.getString("period_name");			// 期間
				
				periodMODEL periodmodel = new periodMODEL(period_id, period_name, "");
				periodList.add(periodmodel);
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
		return periodList;
	}
	
	/**
	 * 期間テーブル(KEY)取得
	 * @throws Exception 
	 */
	public List<periodMODEL> GetKeyData(String id) throws Exception 
	{
		Connection conn = null;
		
		List<periodMODEL> periodList = new ArrayList<periodMODEL>();
		
		try 
		{
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(有効期限指定)
			String sql = "SELECT period_mst.* FROM period ";
			sql = sql + " INNER JOIN period_mst ON period.period_id = period_mst.period_id AND period_mst.deleted_flg = '0'  ";
			sql = sql + " WHERE period.deleted_flg = '0' AND period.company_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			
			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String period_id = rs.getString("period_id");				// 期間ID
				String period_name = rs.getString("period_name");			// 期間

				periodMODEL periodmodel = new periodMODEL(period_id, period_name, "");
				periodList.add(periodmodel);
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
		return periodList;
	}
	
	// 期間テーブル&マスタ(KEY)取得
	public List<periodMODEL> GetKeyDataMst(String id) throws Exception 
	{
		Connection conn = null;
		
		List<periodMODEL> periodList = new ArrayList<periodMODEL>();
		
		try 
		{ 
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(有効期限指定)
			String sql = "SELECT  ";
			sql = sql + " (SELECT distinct period.period_id FROM ";
			sql = sql + " period WHERE period.period_id = period_mst.period_id ";
			sql = sql + " AND period.deleted_flg = '0' AND company_id = ?) as period_checked ";
			sql = sql + " ,period_mst.* ";
			sql = sql + " FROM period_mst ";
			sql = sql + " WHERE period_mst.deleted_flg = '0'  ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);

			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String period_id = rs.getString("period_id");				// 特徴ID
				String period_name = rs.getString("period_name");			// 特徴
				String period_checked = rs.getString("period_checked");			// 特徴

				periodMODEL periodmodel = new periodMODEL(period_id, period_name, period_checked);
				periodList.add(periodmodel);
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
		return periodList;
	}
	
	/**
	 * 期間テーブル追加
	 * @throws Exception 
	 */
	public int periodInsert(Connection conn, String userid, int company_id, String periods[]) throws Exception 
	{	
		
		int ret = 0;								// リターンコード
		
		try 
		{
			for (int i = 0; i < periods.length; i++ ) {
				// SQL作成
				String sql = "INSERT INTO period ";
				sql = sql + "(company_id , period_id ,created_name , updated_name) ";
				sql = sql + "VALUES ( '" + company_id + "','" + periods[i] + "','" + userid + "','" + userid + "' )";

				// SQL実行
				Statement stmt = conn.createStatement();
				ret = stmt.executeUpdate(sql);

				stmt.close();
			}

			return ret;

		}catch (SQLException e){
			throw new Exception(getClass().getName() + " " + e.getMessage());
		}catch (Exception e){
			throw new Exception(getClass().getName() + " " + e.getMessage());
		}finally{

		}
	}
	
	// 期間テーブル削除
	public int periodDelete(Connection conn, int company_id) throws Exception 
	{

		int ret = 0;								// リターンコード
		
		try 
		{
			String sql = "DELETE FROM period ";
			sql = sql + " WHERE company_id = '" + company_id + "' ";

			// SQL実行
			Statement stmt = conn.createStatement();
			ret = stmt.executeUpdate(sql);

			stmt.close();

			return ret;

		}catch (SQLException e){
			throw new Exception(getClass().getName() + " " + e.getMessage());
		}catch (Exception e){
			throw new Exception(getClass().getName() + " " + e.getMessage());
		}finally{

		}
	}
}
