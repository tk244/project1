package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constants.constant;
import model.businessMODEL;

public class businessDAO {
	
	// �Ǝ�e�[�u���S�擾
	public List<businessMODEL> GetData() throws Exception 
	{
		Connection conn = null;
		
		List<businessMODEL> businessList = new ArrayList<businessMODEL>();
		
		try 
		{   	
			// �f�[�^�x�[�X
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(�L�������w��)
			String sql = "SELECT * FROM business_mst where deleted_flg = '0' ";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// SQL���s
			ResultSet rs = stmt.executeQuery();

			businessMODEL businessmodelInit = new businessMODEL(null, null, null);
			businessList.add(businessmodelInit);
			
			while(rs.next()){
				String business_id = rs.getString("business_id");				// �Ǝ�ID
				String business_name = rs.getString("business_name");			// �Ǝ햼
				
				businessMODEL businessmodel = new businessMODEL(business_id, business_name, "");
				businessList.add(businessmodel);
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
		return businessList;
	}
	
	// �s���{���e�[�u��&�}�X�^�擾
	public List<businessMODEL> GetKeyDataMst(String id) throws Exception 
	{
		Connection conn = null;
		
		List<businessMODEL> businessList = new ArrayList<businessMODEL>();
		
		try 
		{ 
			// �f�[�^�x�[�X
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(�L�������w��)
			String sql = "SELECT  ";
			sql = sql + " (SELECT distinct company.business_id FROM ";
			sql = sql + " company WHERE company.business_id = business_mst.business_id ";
			sql = sql + " AND company.deleted_flg = '0' AND company_id = ?) as business_checked ";
			sql = sql + " ,business_mst.* ";
			sql = sql + " FROM business_mst ";
			sql = sql + " WHERE business_mst.deleted_flg = '0'  ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);

			// SQL���s
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String business_id = rs.getString("business_id");				// ����ID
				String business_name = rs.getString("business_name");			// ����
				String business_checked = rs.getString("business_checked");			// ����

				businessMODEL businessmodel = new businessMODEL(business_id, business_name, business_checked);
				businessList.add(businessmodel);
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
		return businessList;
	}
}
