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

	// ���[�����M
	public int mailsend(String param, String mailadress, String tkn) throws Exception 
	{
		String message = "";						// ���[���{��
		Connection conn = null;						// �f�[�^�x�[�X�ڑ�

		int ret = -1;								// ���^�[���R�[�h

		try {
			
			// ���f�[�^�x�[�X
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// �����R�~�b�g�E���[�h��ݒ�
			conn.setAutoCommit(false);
			
			// ���g�[�N���o�^
			tokenDAO tokendao = new tokenDAO();
			ret = tokendao.tokenInsert(conn, mailadress, tkn);

			if (ret != 1)
			{
				// �ُ�
				throw new Exception(getClass().getName() + " �g�[�N���o�^�ُ�" );
			}
			
			// �{���쐬
			if (param.equals("new")){
				message = constant.localurl + "newregist?tkn=" + tkn;
			}else{
				message = constant.localurl + "reissue?tkn=" + tkn;
			}
			
			// ���[���֌W�v���p�e�B�̍쐬
			Properties property = new Properties();
			property.setProperty("mail.smtp.host", "smtp.gmail.com");
			property.setProperty("mail.smtp.port", "587");
			property.setProperty("mail.smtp.starttls.enable", "true");
			property.setProperty("mail.smtp.auth", "true");

			// ���[���Z�b�V�������m��(�p�X���[�h�܂�)
			Session session = Session.getDefaultInstance(property, new javax.mail.Authenticator() 
			{
				protected PasswordAuthentication getPasswordAuthentication() 
				{
				    return new PasswordAuthentication("varchar244@gmail.com","varchar123");
				}
			});

			// ���M���b�Z�[�W���쐬
			MimeMessage mimeMessage = new MimeMessage(session);

			// To&From�A�h���X�̃Z�b�g
			InternetAddress toAddress = new InternetAddress(mailadress, "<���M�於>");
			mimeMessage.setRecipient(Message.RecipientType.TO,  toAddress);
			InternetAddress fromAddress = new InternetAddress(mailadress, "<���M����>");
			mimeMessage.setFrom(fromAddress);

			// �����Ɩ{���̃Z�b�g
			mimeMessage.setSubject("title", "ISO-2022-JP");
			mimeMessage.setText(message, "ISO-2022-JP");
			
			// ���[���̑��M
			Transport.send(mimeMessage);

			//�R�~�b�g
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
