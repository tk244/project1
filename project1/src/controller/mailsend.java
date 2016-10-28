package controller;

import dao.tokenDAO;
import dao.userDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.constant;

/**
 * Servlet implementation class mailsend
 */
@WebServlet("/mailsend")
public class mailsend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mailsend() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String title = "aaa";
		String message = "bbb";
		
		//response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// ���[���̑��M
		try {
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

			//���M���b�Z�[�W���쐬
			MimeMessage mimeMessage = new MimeMessage(session);

			// To&From�A�h���X�̃Z�b�g
			InternetAddress toAddress = new InternetAddress("varchar244@gmail.com", "<���M�於>");
			mimeMessage.setRecipient(Message.RecipientType.TO,  toAddress);
			InternetAddress fromAddress = new InternetAddress("varchar244@gmail.com", "<���M����>");
			mimeMessage.setFrom(fromAddress);

			// �����Ɩ{���̃Z�b�g
			mimeMessage.setSubject("title", "ISO-2022-JP");
			mimeMessage.setText("message", "ISO-2022-JP");
			// ���[���̑��M
			Transport.send(mimeMessage);
			
			// ���MOK
			out.println("<html><body>");
			out.println("�����₢���킹���e��S���҂֑��M���܂����B");
			out.println("</body><html>");
		} catch (Exception e) {
			// ���M�G���[
			out.println("<html><body>");
			out.println("�����₢���킹���e�̑��M�Ɏ��s���܂����B");
			out.println("<br>�G���[���e�F" + e);
			out.println("</body><html>");		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mailadress = request.getParameter("mailadress");
		
		String message = "";
		
		Connection conn = null;
		
		// ���[���̑��M
		try {
			
		    //�g�[�N���쐬
			byte token[] = new byte[16];
		    StringBuffer tknbuf = new StringBuffer();
		    SecureRandom random = null;
		    
		    random = SecureRandom.getInstance("SHA1PRNG");
		    random.nextBytes(token);

		    for (int i = 0; i < token.length; i++) {
		    	tknbuf.append(String.format("%02x", token[i]));
		    }
			  
		    tokenDAO tokendao = new tokenDAO();
			
			

			String url = constant.url;
		    String user = constant.user;
		    String password = constant.password;
		     
 
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	conn = DriverManager.getConnection(url, user, password);

	        // �����R�~�b�g�E���[�h��ݒ�
	        conn.setAutoCommit(false);
	        
		    int ret = 0;
			ret = tokendao.tokenInsert(conn, mailadress, tknbuf.toString());
    	   	    
	        
			 //�{���쐬
			message = "http://localhost:8080/project1/newregist?tkn=" + tknbuf.toString();
			
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

			//���M���b�Z�[�W���쐬
			MimeMessage mimeMessage = new MimeMessage(session);

			// To&From�A�h���X�̃Z�b�g
			InternetAddress toAddress = new InternetAddress("varchar244@gmail.com", "<���M�於>");
			mimeMessage.setRecipient(Message.RecipientType.TO,  toAddress);
			InternetAddress fromAddress = new InternetAddress(mailadress, "<���M����>");
			mimeMessage.setFrom(fromAddress);
		 								
			// �����Ɩ{���̃Z�b�g
			mimeMessage.setSubject("title", "ISO-2022-JP");
			mimeMessage.setText(message, "ISO-2022-JP");
			// ���[���̑��M
			Transport.send(mimeMessage);
			
			// ���MOK
			RequestDispatcher dispatchar =
					request.getRequestDispatcher("/jsp/sentcompleted.jsp");
			dispatchar.forward(request, response);
			
	    	conn.commit();		    	 
		      
	    }catch (ClassNotFoundException e){

	    }catch (SQLException e){

	    }catch (Exception e){

      	}finally{
	        try{
				if (conn != null){
					conn.rollback();
					conn.close();
				}
	        }catch (SQLException e){
		          
	        }
      	}
	}
}
