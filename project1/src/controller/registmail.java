package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Servlet implementation class registmail
 */
@WebServlet("/registmail")
public class registmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String message = request.getParameter("message");
		
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
		
		// ���[���̑��M
		try {
			
		    //�g�[�N���쐬
			byte token[] = new byte[16];
		    StringBuffer tknbuf = new StringBuffer();
		    SecureRandom random = null;
		    
		    try {
		      random = SecureRandom.getInstance("SHA1PRNG");
		      random.nextBytes(token);
		 
		      for (int i = 0; i < token.length; i++) {
		    	  tknbuf.append(String.format("%02x", token[i]));
		      }
		 
		    } catch (NoSuchAlgorithmException e) {
		      e.printStackTrace();
		    }
			
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
			

		} catch (Exception e) {
			// ���M�G���[	
		}
	}

}