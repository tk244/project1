package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
		
		// メールの送信
		try {
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

			//送信メッセージを作成
			MimeMessage mimeMessage = new MimeMessage(session);

			// To&Fromアドレスのセット
			InternetAddress toAddress = new InternetAddress("varchar244@gmail.com", "<送信先名>");
			mimeMessage.setRecipient(Message.RecipientType.TO,  toAddress);
			InternetAddress fromAddress = new InternetAddress("varchar244@gmail.com", "<送信元名>");
			mimeMessage.setFrom(fromAddress);

			// 件名と本文のセット
			mimeMessage.setSubject("title", "ISO-2022-JP");
			mimeMessage.setText("message", "ISO-2022-JP");
			// メールの送信
			Transport.send(mimeMessage);
			
			// 送信OK
			out.println("<html><body>");
			out.println("■お問い合わせ内容を担当者へ送信しました。");
			out.println("</body><html>");
		} catch (Exception e) {
			// 送信エラー
			out.println("<html><body>");
			out.println("■お問い合わせ内容の送信に失敗しました。");
			out.println("<br>エラー内容：" + e);
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
		
		// メールの送信
		try {
			
		    //トークン作成
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
			
		    //本文作成
			message = "http://localhost:8080/project1/newregist?tkn=" + tknbuf.toString();
			
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

			//送信メッセージを作成
			MimeMessage mimeMessage = new MimeMessage(session);

			// To&Fromアドレスのセット
			InternetAddress toAddress = new InternetAddress("varchar244@gmail.com", "<送信先名>");
			mimeMessage.setRecipient(Message.RecipientType.TO,  toAddress);
			InternetAddress fromAddress = new InternetAddress(mailadress, "<送信元名>");
			mimeMessage.setFrom(fromAddress);
		 								
			// 件名と本文のセット
			mimeMessage.setSubject("title", "ISO-2022-JP");
			mimeMessage.setText(message, "ISO-2022-JP");
			// メールの送信
			Transport.send(mimeMessage);
			
			// 送信OK
			RequestDispatcher dispatchar =
					request.getRequestDispatcher("/jsp/sentcompleted.jsp");
			dispatchar.forward(request, response);
			

		} catch (Exception e) {
			// 送信エラー	
		}
	}

}
