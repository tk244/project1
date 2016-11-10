package controller;


import java.io.IOException;
import java.security.SecureRandom;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		HttpSession session = request.getSession(false);
		
		session.setAttribute("login", "NEW");
		
		RequestDispatcher dispatchar =
				request.getRequestDispatcher("/jsp/registmail.jsp");
		dispatchar.forward(request, response);
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

