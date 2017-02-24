package model;

import dao.tokenDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import constants.constant;

public class mailsendMODEL {

	// メール送信
	public int mailsend(String param, String mailadress, String tkn) throws Exception 
	{
		String message = "";						// メール本文
		Connection conn = null;						// データベース接続

		int ret = -1;								// リターンコード

		try {
			
			// ■データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// 自動コミット・モードを設定
			conn.setAutoCommit(false);
			
			// ■トークン登録
			tokenDAO tokendao = new tokenDAO();
			ret = tokendao.tokenInsert(conn, mailadress, tkn);

			if (ret != 1)
			{
				// 異常
				throw new Exception(getClass().getName() + " トークン登録異常" );
			}
			
			// 本文作成
			if (param.equals("new")){
				message = constant.localurl + "newregist?tkn=" + tkn;
			}else{
				message = constant.localurl + "reissue?tkn=" + tkn;
			}
			
			// メール関係プロパティの作成
			Properties property = new Properties();
			property.setProperty("mail.smtp.host", "smtp.gmail.com");
			property.setProperty("mail.smtp.port", "587");
			property.setProperty("mail.smtp.starttls.enable", "true");
			property.setProperty("mail.smtp.auth", "true");

			// メールセッションを確立(パスワード含む)
			Session session = Session.getDefaultInstance(property, new javax.mail.Authenticator() 
			{
				protected PasswordAuthentication getPasswordAuthentication() 
				{
				    return new PasswordAuthentication("varchar244@gmail.com","varchar123");
				}
			});

			// 送信メッセージを作成
			MimeMessage mimeMessage = new MimeMessage(session);

			// To&Fromアドレスのセット
			InternetAddress toAddress = new InternetAddress(mailadress, "<送信先名>");
			mimeMessage.setRecipient(Message.RecipientType.TO,  toAddress);
			InternetAddress fromAddress = new InternetAddress(mailadress, "<送信元名>");
			mimeMessage.setFrom(fromAddress);

			// 件名と本文のセット
			mimeMessage.setSubject("title", "ISO-2022-JP");
			mimeMessage.setText(message, "ISO-2022-JP");
			
			// メールの送信
			Transport.send(mimeMessage);

			//コミット
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
