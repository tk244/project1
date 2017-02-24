package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import constants.constant;
import model.tokenMODEL;

public class tokenDAO {

	private String Mailadress;
	public String getMailadress() {return Mailadress;}
	
	// トークン追加
	public int tokenInsert(Connection conn, String mailadress, String token) throws Exception 
	{
		int ret = 0;								// リターンコード
		
		try 
		{
			// SQL作成
			String sql = "INSERT INTO token ";
			sql = sql + "(mailadress  , token  ,  created_name  , updated_name) ";
			sql = sql + "VALUES ( '" + mailadress + "','" + token + "', 'system'  , 'system')  ";

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
	
	// トークンチェック
	public List<tokenMODEL> tokenCheck(String token) throws Exception 
	{
		Connection conn = null;
		
		List<tokenMODEL> tokenList = new ArrayList<tokenMODEL>();
		
		try 
		{
			// データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// SQL(有効期限指定)
			String sql = "SELECT * FROM token WHERE token = ? and deleted_flg = 0 ";
			sql = sql + "and NOW() <= ( created + INTERVAL 30 MINUTE)";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, token);

			// SQL実行
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				// メールアドレス取得
				tokenMODEL tokenmodel = new tokenMODEL(rs.getString("mailadress"),rs.getString("created"));
				tokenList.add(tokenmodel);
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
		return tokenList;
	}
}