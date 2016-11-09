package controller;


import java.io.IOException;
import java.io.PrintWriter;

import java.security.SecureRandom;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import constants.constant;
import model.mailsendMODEL;
import model.userMODEL;;


/**
 * Servlet implementation class mailsend
 */
@WebServlet("/mailsend")
public class mailsend extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(mailsend.class);
       
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

		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		ServletContext context = getServletContext();
		DOMConfigurator.configure(context.getRealPath(constant.log4j));

		logger.debug("doGet Start");
		
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
		}catch (Exception e){
			logger.error(e.getMessage());
		}
		
		logger.debug("doGet End");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int ret = -1;								//���^�[���R�[�h		
		request.setCharacterEncoding("UTF-8");
		
		// ���[���A�h���X�擾
		String mailadress = request.getParameter("mailadress");
		
		ServletContext context = getServletContext();
		DOMConfigurator.configure(context.getRealPath(constant.log4j));

		logger.debug("doPost Start");
		
		try {
			
			userMODEL usermodel = new userMODEL();		
			usermodel.SetUserid(mailadress);
			
			ret = usermodel.userExist();
			
			if (ret > 0)
			{
        		request.setAttribute("errorMessage","���ɓo�^�ς݂ł��B");
				
				//���O�C�����
				RequestDispatcher dispatchar =
						request.getRequestDispatcher("/jsp/registmail.jsp");
				dispatchar.forward(request, response);
				return;
			}
			
		    // ���g�[�N���쐬
			byte token[] = new byte[16];
		    StringBuffer tknbuf = new StringBuffer();
		    SecureRandom random = null;
		    
		    random = SecureRandom.getInstance("SHA1PRNG");
		    random.nextBytes(token);

		    for (int i = 0; i < token.length; i++) {
		    	tknbuf.append(String.format("%02x", token[i]));
		    }
			  
		    // �����[�����M���g�[�N���o�^
		    mailsendMODEL mailsendmodel = new mailsendMODEL();		    
		    ret = mailsendmodel.mailsend(mailadress, tknbuf.toString());
		    
		    if (ret == 0){
				request.setAttribute("Message","/newregist?tkn=" + tknbuf.toString());
				
				// ���MOK
				RequestDispatcher dispatchar =
						request.getRequestDispatcher("/jsp/sentcompleted.jsp");
				dispatchar.forward(request, response);
	        }
	        else{
//        		request.setAttribute("errorMessage","���[�����M�Ɏ��s���܂����B");
//				
//				//���O�C�����
//				RequestDispatcher dispatchar =
//						request.getRequestDispatcher("/jsp/registmail.jsp");
//				dispatchar.forward(request, response);
	        	
	        	request.setAttribute("Message","/newregist?tkn=" + tknbuf.toString());
				
				// ���MOK
				RequestDispatcher dispatchar =
						request.getRequestDispatcher("/jsp/sentcompleted.jsp");
				dispatchar.forward(request, response);
	        }
	    	 		      
		}catch (Exception e){
			logger.error(e.getMessage());
		}
		
		logger.debug("doPost End");
	}
}

