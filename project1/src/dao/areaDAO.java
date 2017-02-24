package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constants.constant;
import model.areaMODEL;

public class areaDAO {
	
	// ��ƃe�[�u���S�擾
	public List<areaMODEL> GetData() throws Exception 
	{
		Connection conn = null;
		
		List<areaMODEL> areaList = new ArrayList<areaMODEL>();
		
		try 
		{
			// �f�[�^�x�[�X
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(�L�������w��)
			String sql = "SELECT * FROM area_mst where deleted_flg = '0' ";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// SQL���s
			ResultSet rs = stmt.executeQuery();

			areaMODEL areamodelInit = new areaMODEL(null, null, null);
			areaList.add(areamodelInit);
			
			while(rs.next()){
				String area_id = rs.getString("area_id");				// �s���{��ID
				String area_name = rs.getString("area_name");			// �s���{��
				
				areaMODEL areamodel = new areaMODEL(area_id, area_name, "");
				areaList.add(areamodel);
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
		return areaList;
	}
	
	// �s���{���e�[�u��&�}�X�^(KEY)�擾
	public List<areaMODEL> GetKeyDataMst(String id) throws Exception 
	{
		Connection conn = null;
		
		List<areaMODEL> areaList = new ArrayList<areaMODEL>();
		
		try 
		{
			// �f�[�^�x�[�X
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(�L�������w��)
			String sql = "SELECT  ";
			sql = sql + " (SELECT distinct company.area_id FROM ";
			sql = sql + " company WHERE company.area_id = area_mst.area_id ";
			sql = sql + " AND company.deleted_flg = '0' AND company_id = ?) as area_checked";
			sql = sql + " ,area_mst.* ";
			sql = sql + " FROM area_mst ";
			sql = sql + " WHERE area_mst.deleted_flg = '0'  ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);

			// SQL���s
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String area_id = rs.getString("area_id");				// ����ID
				String area_name = rs.getString("area_name");			// ����
				String area_checked = rs.getString("area_checked");			// ����

				areaMODEL areamodel = new areaMODEL(area_id, area_name, area_checked);
				areaList.add(areamodel);
			}

			rs.close();
			stmt.close();

		}catch (ClassNotFoundException e){
			throw new Exception(getClass().getName() + " " + e.getMessage());
		}catch (SQLException e){
			throw new Exception(getClass().getName() + " " + e.getMessage());
		}catch (Exception e){
			throw new Exception(getClass().getName() + " " +  e.getMessage());
		}finally{
			try{
				if (conn != null){
					conn.close();
				}
			}catch (SQLException e){
				throw new Exception(getClass().getName() + " " + e.getMessage());
			}
      	}
		return areaList;
	}
}
