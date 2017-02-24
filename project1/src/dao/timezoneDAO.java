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
import model.timezoneMODEL;

public class timezoneDAO {

	/**
	 * 時間帯テーブル全取得
	 * @throws Exception 
	 */
	public List<timezoneMODEL> GetData() throws Exception 
	{
		Connection conn = null;
		
		List<timezoneMODEL> timezoneList = new ArrayList<timezoneMODEL>();
		
		try 
		{
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(有効期限指定)
			String sql = "SELECT * FROM timezone_mst where deleted_flg = '0' ";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String timezone_id = rs.getString("timezone_id");				// 時間帯ID
				String timezone_name = rs.getString("timezone_name");			// 時間帯
				
				timezoneMODEL timezonemodel = new timezoneMODEL(timezone_id, timezone_name, "");
				timezoneList.add(timezonemodel);
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
		return timezoneList;
	}
	
	/**
	 * 時間帯テーブル(KEY)取得
	 * @throws Exception 
	 */
	public List<timezoneMODEL> GetKeyData(String id) throws Exception 
	{
		Connection conn = null;
		
		List<timezoneMODEL> timezoneList = new ArrayList<timezoneMODEL>();
		
		try 
		{
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(会社ID指定)
			String sql = "SELECT timezone_mst.* FROM timezone ";
			sql = sql + " INNER JOIN timezone_mst ON timezone.timezone_id = timezone_mst.timezone_id AND timezone_mst.deleted_flg = '0'  ";
			sql = sql + " WHERE timezone.deleted_flg = '0' AND timezone.company_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);

			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String timezone_id = rs.getString("timezone_id");				// 時間帯ID
				String timezone_name = rs.getString("timezone_name");			// 時間帯

				timezoneMODEL timezonemodel = new timezoneMODEL(timezone_id, timezone_name, "");
				timezoneList.add(timezonemodel);
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
		return timezoneList;
	}
	
	// 時間帯テーブル&マスタ(KEY)取得
	public List<timezoneMODEL> GetKeyDataMst(String id) throws Exception 
	{
		Connection conn = null;
		
		List<timezoneMODEL> timezoneList = new ArrayList<timezoneMODEL>();
		
		try 
		{
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(有効期限指定)
			String sql = "SELECT  ";
			sql = sql + " (SELECT distinct timezone.timezone_id FROM ";
			sql = sql + " timezone WHERE timezone.timezone_id = timezone_mst.timezone_id ";
			sql = sql + " AND timezone.deleted_flg = '0' AND company_id = ?) as timezone_checked ";
			sql = sql + " ,timezone_mst.* ";
			sql = sql + " FROM timezone_mst ";
			sql = sql + " WHERE timezone_mst.deleted_flg = '0'  ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);

			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String timezone_id = rs.getString("timezone_id");				// 特徴ID
				String timezone_name = rs.getString("timezone_name");			// 特徴
				String timezone_checked = rs.getString("timezone_checked");			// 特徴

				timezoneMODEL timezonemodel = new timezoneMODEL(timezone_id, timezone_name, timezone_checked);
				timezoneList.add(timezonemodel);
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
		return timezoneList;
	}
	/**
	 * 時間帯テーブル追加
	 * @throws Exception 
	 */
	public int timezoneInsert(Connection conn, String userid, int company_id, String timezones[]) throws Exception 
	{	
		
		int ret = 0;								// リターンコード
		
		try 
		{
			for (int i = 0; i < timezones.length; i++ ) {
				// SQL作成
				String sql = "INSERT INTO timezone ";
				sql = sql + "(company_id , timezone_id ,created_name , updated_name) ";
				sql = sql + "VALUES ( '" + company_id + "','" + timezones[i] + "','" + userid + "','" + userid + "' )";

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
	
	// 時間帯テーブル削除
	public int timezoneDelete(Connection conn, int company_id) throws Exception 
	{

		int ret = 0;								// リターンコード
		
		try 
		{   	
			String sql = "DELETE FROM  timezone ";
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
