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
import model.employmentMODEL;

public class employmentDAO {

	/**
	 * 雇用形態テーブル全取得
	 * @throws Exception 
	 */
	public List<employmentMODEL> GetData() throws Exception 
	{
		Connection conn = null;
		
		List<employmentMODEL> employmentList = new ArrayList<employmentMODEL>();
		
		try 
		{ 
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(有効期限指定)
			String sql = "SELECT * FROM employment_mst where deleted_flg = '0' ";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String employment_id = rs.getString("employment_id");				// 雇用形態ID
				String employment_name = rs.getString("employment_name");			// 雇用形態
				
				employmentMODEL employmentmodel = new employmentMODEL(employment_id, employment_name, "");
				employmentList.add(employmentmodel);
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
		return employmentList;
	}
	
	/**
	 * 雇用形態テーブル(KEY)取得
	 * @throws Exception 
	 */
	public List<employmentMODEL> GetKeyData(String id) throws Exception 
	{
		Connection conn = null;
		
		List<employmentMODEL> employmentList = new ArrayList<employmentMODEL>();
		
		try 
		{ 
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(有効期限指定)
			String sql = "SELECT employment_mst.* FROM employment ";
			sql = sql + " INNER JOIN employment_mst ON employment.employment_id = employment_mst.employment_id AND employment_mst.deleted_flg = '0'  ";
			sql = sql + " WHERE employment.deleted_flg = '0' AND employment.company_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);

			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String employment_id = rs.getString("employment_id");				// 雇用形態ID
				String employment_name = rs.getString("employment_name");			// 雇用形態

				employmentMODEL employmentmodel = new employmentMODEL(employment_id, employment_name, "");
				employmentList.add(employmentmodel);
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
				return null;
			}
		}
		return employmentList;
	}
	
	// 雇用形態テーブル&マスタ(KEY)取得
	public List<employmentMODEL> GetKeyDataMst(String id) throws Exception 
	{
		Connection conn = null;
		
		List<employmentMODEL> employmentList = new ArrayList<employmentMODEL>();
		
		try 
		{ 
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(有効期限指定)
			String sql = "SELECT  ";
			sql = sql + " (SELECT distinct employment.employment_id FROM ";
			sql = sql + " employment WHERE employment.employment_id = employment_mst.employment_id ";
			sql = sql + " AND employment.deleted_flg = '0' AND company_id = ?) as employment_checked ";
			sql = sql + " ,employment_mst.* ";
			sql = sql + " FROM employment_mst ";
			sql = sql + " WHERE employment_mst.deleted_flg = '0'  ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);

			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String employment_id = rs.getString("employment_id");				// 特徴ID
				String employment_name = rs.getString("employment_name");			// 特徴
				String employment_checked = rs.getString("employment_checked");			// 特徴

				employmentMODEL employmentmodel = new employmentMODEL(employment_id, employment_name, employment_checked);
				employmentList.add(employmentmodel);
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
		return employmentList;
	}
	
	/**
	 * 雇用形態テーブル追加
	 * @throws Exception 
	 */
	public int employmentInsert(Connection conn, String userid, int company_id, String employments[]) throws Exception 
	{

		int ret = 0;								// リターンコード

		try 
		{
			for (int i = 0; i < employments.length; i++ ) {
				// SQL作成
				String sql = "INSERT INTO employment ";
				sql = sql + "(company_id , employment_id ,created_name , updated_name) ";
				sql = sql + "VALUES ( '" + company_id + "','" + employments[i] + "','" + userid + "','" + userid + "' )";

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

	// 雇用形態テーブル削除
	public int employmentDelete(Connection conn, int company_id) throws Exception 
	{

		int ret = 0;								// リターンコード
		
		try 
		{
			String sql = "DELETE FROM employment ";
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
