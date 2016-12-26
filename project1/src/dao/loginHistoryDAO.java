package dao;

import java.sql.*;

import constants.constant;

public class loginHistoryDAO {

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
