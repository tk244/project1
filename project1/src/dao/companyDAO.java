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
import model.companyMODEL;

public class companyDAO {

	// 企業テーブル全取得
	public List<companyMODEL> GetData(String area_id, String characteristics[]) throws Exception 
	{
		Connection conn = null;
		
		List<companyMODEL> companyList = new ArrayList<companyMODEL>();
		
		try 
		{
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(有効期限指定)
			String sql = "SELECT company.*, business_mst.business_name, area_mst.area_name FROM company ";
			sql = sql + " LEFT JOIN business_mst ON business_mst.business_id = company.business_id AND business_mst.deleted_flg = '0' ";
			sql = sql + " LEFT JOIN area_mst ON area_mst.area_id = company.area_id AND area_mst.deleted_flg = '0' ";
			sql = sql + " LEFT JOIN characteristic ON characteristic.company_id = company.company_id AND characteristic.deleted_flg = '0' ";
			sql = sql + " WHERE company.deleted_flg = '0' ";
			
			if (area_id != null && !area_id.equals("")){
				sql = sql + " AND company.area_id = '" + area_id + "' ";
			}
			
			if (characteristics != null && characteristics.length != 0){
				sql = sql + " AND characteristic.characteristic_id in (";
				for (int i = 0; i < characteristics.length; i++ ) {
					sql = sql + "'" + characteristics[i] + "'";
					if (i != characteristics.length - 1)
					{
						sql = sql + ",";
					}
				}
				sql = sql + ")";
			}

			PreparedStatement stmt = conn.prepareStatement(sql);

			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String company_id = rs.getString("company_id");				// 会社ID
				String company_name = rs.getString("company_name");			// 会社名
				String area = rs.getString("area_name");					//　都道府県
				String business = rs.getString("business_name");			// 業種
				String salary = rs.getString("salary");						// 給与
				String remarks = rs.getString("remarks");					// 備考
				
				companyMODEL companymodel = new companyMODEL(company_id, company_name, area,
						business, salary, remarks);
				companyList.add(companymodel);
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
		return companyList;
	}
	
	// 企業テーブル(KEY)取得
	public List<companyMODEL> GetKeyData(String id) throws Exception 
	{
		Connection conn = null;
		
		List<companyMODEL> companyList = new ArrayList<companyMODEL>();
		
		try 
		{
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(有効期限指定)
			String sql = "SELECT company.*, business_mst.business_name, area_mst.area_name FROM company ";
			sql = sql + " LEFT JOIN business_mst ON business_mst.business_id = company.business_id AND business_mst.deleted_flg = '0' ";
			sql = sql + " LEFT JOIN area_mst ON area_mst.area_id = company.area_id AND area_mst.deleted_flg = '0' ";
			sql = sql + " WHERE company.deleted_flg = '0' AND company_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);

			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String company_id = rs.getString("company_id");				// 会社ID
				String company_name = rs.getString("company_name");			// 会社名
				String area = rs.getString("area_name");					//　都道府県
				String business = rs.getString("business_name");			// 業種
				String salary = rs.getString("salary");						// 給与
				String remarks = rs.getString("remarks");					// 備考
				
				companyMODEL companymodel = new companyMODEL(company_id, company_name, area,
						business, salary, remarks);
				companyList.add(companymodel);
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
		return companyList;
	}
	
	// 企業テーブル追加
	public int companyInsert(Connection conn, String userid, String company_name, String business_id, String area_id, String salary, String remarks) throws Exception 
	{	
		
		int ret = 0;								// リターンコード
		
		try 
		{
			// SQL作成
			String sql = "INSERT INTO company ";
			sql = sql + "(company_name , business_id , area_id , salary, remarks , created_name , updated_name) ";
			sql = sql + "VALUES ( '" + company_name + "','" + business_id + "','" + area_id + "','" + salary + "','" + remarks + "','" + userid + "','" + userid + "' )";

			// SQL実行
			Statement stmt = conn.createStatement();
			ret = stmt.executeUpdate(sql);

			stmt.close();

			// 企業ID取得
			sql = "select company_id from company order by company_id desc limit 1;";
			
			PreparedStatement stmt2 = conn.prepareStatement(sql);

			// SQL実行
			ResultSet rs = stmt2.executeQuery();

			while(rs.next()){
				ret = rs.getInt("company_id");				// 会社ID
			}
			stmt2.close();
			
			return ret;

		}catch (SQLException e){
			throw new Exception(getClass().getName() + " " + e.getMessage());
		}catch (Exception e){
			throw new Exception(getClass().getName() + " " + e.getMessage());
		}finally{
  
		}
	}
	
	// 企業テーブル更新
	public int companyUpdate(Connection conn, String userid, String company_id, String company_name, String business_id, String area_id, String salary, String remarks) throws Exception 
	{	
		
		int ret = 0;								// リターンコード

		try 
		{
			// SQL作成
			String sql = "UPDATE company SET ";
			sql = sql + " company_name = '" + company_name + "' , ";
			sql = sql + " business_id = '" + business_id + "' , ";
			sql = sql + " area_id = '" + area_id + "' , ";
			sql = sql + " salary = '" + salary + "' , ";
			sql = sql + " remarks = '" + remarks + "' , ";
			sql = sql + " updated_name = '" + userid + "' , ";
			sql = sql + " updated = NOW() ";
			sql = sql + " WHERE company_id = '" + company_id + "'";

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
	
	// 企業テーブル削除
	public int companyDelete(String deletechks[]) throws Exception 
	{
		Connection conn = null;
		int ret = 0;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);
	
			// 自動コミット・モードを設定
			conn.setAutoCommit(false);

			characteristicDAO characteristicdao = new characteristicDAO();
			timezoneDAO timezonedao = new timezoneDAO();
			periodDAO perioddao = new periodDAO();
			employmentDAO employmentdao = new employmentDAO();

			for (int i = 0; i < deletechks.length; i++)
			{
				String sql = "UPDATE company ";
				sql = sql + " SET deleted_flg = '1'";
				sql = sql + " WHERE company_id = '" + deletechks[i] + "'";
				
				Statement stmt = conn.createStatement();
				
				ret = stmt.executeUpdate(sql);
				
				if (ret != 1){
					// 異常
					throw new Exception("企業テーブル削除 異常");
				}
				stmt.close();
				
				int icompany_id = Integer.parseInt(deletechks[i]);
				
				// 特徴テーブル削除	
				ret = characteristicdao.characteristicDelete(conn, icompany_id);
				if (ret < 0)
				{
					throw new Exception("特徴テーブル削除 異常");
				}
				
				// 時間帯テーブル削除
				ret = timezonedao.timezoneDelete(conn, icompany_id);
				if (ret < 0)
				{
					throw new Exception("時間帯テーブル削除 異常");
				}
				
				// 期間テーブル削除
				ret = perioddao.periodDelete(conn, icompany_id);
				if (ret < 0)
				{
					throw new Exception("期間テーブル削除 異常");
				}
				
				// 雇用形態テーブル削除
				ret = employmentdao.employmentDelete(conn, icompany_id);
				if (ret < 0)
				{
					throw new Exception("雇用形態テーブル削除 異常");
				}
			 }
			 
			// コミット
			conn.commit();

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
}
