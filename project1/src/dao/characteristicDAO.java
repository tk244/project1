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
import model.characteristicMODEL;

public class characteristicDAO {

	/**
	 * 特徴テーブル全取得
	 * @throws Exception 
	 */
	public List<characteristicMODEL> GetData() throws Exception 
	{
		Connection conn = null;

		List<characteristicMODEL> characteristicList = new ArrayList<characteristicMODEL>();
		
		try 
		{   	
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(有効期限指定)
			String sql = "SELECT * FROM characteristic_mst where deleted_flg = '0' ";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String characteristic_id = rs.getString("characteristic_id");				// 特徴ID
				String characteristic_name = rs.getString("characteristic_name");			// 特徴
				
				characteristicMODEL characteristicmodel = new characteristicMODEL(characteristic_id, characteristic_name, "");
				characteristicList.add(characteristicmodel);
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
		return characteristicList;
	}
	
	/**
	 * 特徴テーブル(KEY)取得
	 * @throws Exception 
	 */
	public List<characteristicMODEL> GetKeyData(String id) throws Exception 
	{
		Connection conn = null;
		
		List<characteristicMODEL> characteristicList = new ArrayList<characteristicMODEL>();
		
		try 
		{ 
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(有効期限指定)
			String sql = "SELECT characteristic_mst.* FROM characteristic ";
			sql = sql + " INNER JOIN characteristic_mst ON characteristic.characteristic_id = characteristic_mst.characteristic_id AND characteristic_mst.deleted_flg = '0'  ";
			sql = sql + " WHERE characteristic.deleted_flg = '0' AND characteristic.company_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);

			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String characteristic_id = rs.getString("characteristic_id");				// 特徴ID
				String characteristic_name = rs.getString("characteristic_name");			// 特徴

				characteristicMODEL characteristicmodel = new characteristicMODEL(characteristic_id, characteristic_name, "");
				characteristicList.add(characteristicmodel);
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
		return characteristicList;
	}
	
	/**
	 * 特徴テーブル&マスタ(KEY)取得
	 * @throws Exception 
	 */
	public List<characteristicMODEL> GetKeyDataMst(String id) throws Exception 
	{
		Connection conn = null;
		
		List<characteristicMODEL> characteristicList = new ArrayList<characteristicMODEL>();
		
		try 
		{ 
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(有効期限指定)
			String sql = "SELECT  ";
			sql = sql + " (SELECT distinct characteristic.characteristic_id FROM ";
			sql = sql + " characteristic WHERE characteristic.characteristic_id = characteristic_mst.characteristic_id ";
			sql = sql + " AND characteristic.deleted_flg = '0' AND company_id = ?) as characteristic_checked ";
			sql = sql + " ,characteristic_mst.* ";
			sql = sql + " FROM characteristic_mst ";
			sql = sql + " WHERE characteristic_mst.deleted_flg = '0'  ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);

			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String characteristic_id = rs.getString("characteristic_id");				// 特徴ID
				String characteristic_name = rs.getString("characteristic_name");			// 特徴
				String characteristic_checked = rs.getString("characteristic_checked");			// 特徴

				characteristicMODEL characteristicmodel = new characteristicMODEL(characteristic_id, characteristic_name, characteristic_checked);
				characteristicList.add(characteristicmodel);
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
		return characteristicList;
	}
	
	/**
	 * 特徴テーブル追加
	 * @throws Exception 
	 */
	public int characteristicInsert(Connection conn, String userid, int company_id, String characteristics[]) throws Exception 
	{

		int ret = 0;								// リターンコード
		
		try 
		{   	
			for (int i = 0; i < characteristics.length; i++ ) {
				// SQL作成
				String sql = "INSERT INTO characteristic ";
				sql = sql + "(company_id , characteristic_id ,created_name , updated_name) ";
				sql = sql + "VALUES ( '" + company_id + "','" + characteristics[i] + "','" + userid + "','" + userid + "' )";
	   	
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
	

	
	// 特殊テーブル削除
	public int characteristicDelete(Connection conn, int company_id) throws Exception 
	{

		int ret = 0;								// リターンコード
		
		try 
		{   	
			String sql = "DELETE FROM  characteristic ";
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
