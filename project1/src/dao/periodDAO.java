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
	 * ���ԃe�[�u���S�擾
	 * @throws Exception 
	 */
	public List<periodMODEL> GetData() throws Exception 
	{
		Connection conn = null;
		
		List<periodMODEL> periodList = new ArrayList<periodMODEL>();
		
		try 
		{
			// �f�[�^�x�[�X
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(�L�������w��)
			String sql = "SELECT * FROM period_mst where deleted_flg = '0' ";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// SQL���s
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String period_id = rs.getString("period_id");				// ����ID
				String period_name = rs.getString("period_name");			// ����
				
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
	 * ���ԃe�[�u��(KEY)�擾
	 * @throws Exception 
	 */
	public List<periodMODEL> GetKeyData(String id) throws Exception 
	{
		Connection conn = null;
		
		List<periodMODEL> periodList = new ArrayList<periodMODEL>();
		
		try 
		{
			// �f�[�^�x�[�X
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(�L�������w��)
			String sql = "SELECT period_mst.* FROM period ";
			sql = sql + " INNER JOIN period_mst ON period.period_id = period_mst.period_id AND period_mst.deleted_flg = '0'  ";
			sql = sql + " WHERE period.deleted_flg = '0' AND period.company_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			
			// SQL���s
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String period_id = rs.getString("period_id");				// ����ID
				String period_name = rs.getString("period_name");			// ����

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
	
	// ���ԃe�[�u��&�}�X�^(KEY)�擾
	public List<periodMODEL> GetKeyDataMst(String id) throws Exception 
	{
		Connection conn = null;
		
		List<periodMODEL> periodList = new ArrayList<periodMODEL>();
		
		try 
		{ 
			// �f�[�^�x�[�X
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(�L�������w��)
			String sql = "SELECT  ";
			sql = sql + " (SELECT distinct period.period_id FROM ";
			sql = sql + " period WHERE period.period_id = period_mst.period_id ";
			sql = sql + " AND period.deleted_flg = '0' AND company_id = ?) as period_checked ";
			sql = sql + " ,period_mst.* ";
			sql = sql + " FROM period_mst ";
			sql = sql + " WHERE period_mst.deleted_flg = '0'  ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);

			// SQL���s
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String period_id = rs.getString("period_id");				// ����ID
				String period_name = rs.getString("period_name");			// ����
				String period_checked = rs.getString("period_checked");			// ����

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
	 * ���ԃe�[�u���ǉ�
	 * @throws Exception 
	 */
	public int periodInsert(Connection conn, String userid, int company_id, String periods[]) throws Exception 
	{	
		
		int ret = 0;								// ���^�[���R�[�h
		
		try 
		{
			for (int i = 0; i < periods.length; i++ ) {
				// SQL�쐬
				String sql = "INSERT INTO period ";
				sql = sql + "(company_id , period_id ,created_name , updated_name) ";
				sql = sql + "VALUES ( '" + company_id + "','" + periods[i] + "','" + userid + "','" + userid + "' )";

				// SQL���s
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
	
	// ���ԃe�[�u���폜
	public int periodDelete(Connection conn, int company_id) throws Exception 
	{

		int ret = 0;								// ���^�[���R�[�h
		
		try 
		{
			String sql = "DELETE FROM period ";
			sql = sql + " WHERE company_id = '" + company_id + "' ";

			// SQL���s
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
