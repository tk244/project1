package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import constants.constant;
import model.loginHistoryMODEL;

public class loginHistoryDAO {

	// ��ƃe�[�u���S�擾
	public List<loginHistoryMODEL> GetData() throws Exception 
	{
		Connection conn = null;
		
		List<loginHistoryMODEL> loginHistoryList = new ArrayList<loginHistoryMODEL>();
		
		try 
		{
			// �f�[�^�x�[�X
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(�L�������w��)
			String sql = "SELECT * ";
			sql = sql + " FROM loginhistory ";
			sql = sql + " WHERE deleted_flg = '0' ";
			sql = sql + " ORDER BY login_date ";
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			// SQL���s
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String id = rs.getString("id");								// ID
				String userid = rs.getString("userid");						// ���[�UID
				String logindate = rs.getString("login_date");				//�@���O�C������
				
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
	
	// ���O�C������ǉ�
	public int loginHistoryInsert(String userid) throws Exception 
	{
		Connection conn = null;
		int ret = 0;								// ���^�[���R�[�h
		
		try 
		{
	    	// �f�[�^�x�[�X
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	conn = DriverManager.getConnection(constant.url, constant.user, constant.password);
	    	
			// �����R�~�b�g�E���[�h��ݒ�
			conn.setAutoCommit(false);
			
			// SQL�쐬
			String sql = "INSERT INTO loginhistory ";
			sql = sql + "(userid  , login_system  , created_name  , updated_name) ";
			sql = sql + "VALUES ( '" + userid + "', 'system', 'system'  , 'system')  ";

			// SQL���s
			Statement stmt = conn.createStatement();
			ret = stmt.executeUpdate(sql);

			stmt.close();
			
			//�R�~�b�g
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
