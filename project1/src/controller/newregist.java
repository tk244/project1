package controller;

import java.io.IOException;
import java.util.List;

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
import dao.tokenDAO;
import model.userMODEL;
import model.tokenMODEL;

/**
 * Servlet implementation class newregist
 */
@WebServlet("/newregist")
public class newregist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(newregist.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newregist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int ret = 0;								// リターンコード
		
		// トークン取得
		request.setCharacterEncoding("UTF-8");
		String tkn = request.getParameter("tkn");
		
		ServletContext context = getServletContext();
		DOMConfigurator.configure(context.getRealPath(constant.log4j));

		logger.debug("doGet Start");
		
		try {
			
			//　トークン判定		
			tokenDAO tokendao = new tokenDAO();
			
			List<tokenMODEL> tokenList = tokendao.tokenCheck(tkn);
			
			if ((tokenList == null) || (tokenList.size() != 1)){
				
				return;
			}
			
			if (ret != 0){
				// 異常
				return;
			}
					
			request.setAttribute("mailadress", tokenList.get(0).getMailadress());
			
			// 新規登録画面
			RequestDispatcher dispatchar =
					request.getRequestDispatcher("/jsp/newregist.jsp");
			dispatchar.forward(request, response);
		
		}catch (Exception e){
			logger.error(e.getMessage());
		}
		
		logger.debug("doGet End");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int ret = 0;								// リターンコード
		
		ServletContext context = getServletContext();
		DOMConfigurator.configure(context.getRealPath(constant.log4j));

		logger.debug("doPost Start");
		
	    try 
	    {   
			// リクエストパラメータの取得
			request.setCharacterEncoding("UTF-8");
			String userid = request.getParameter("userid");
			String pass = request.getParameter("pass");
			String username = request.getParameter("username");
			
			
			//　新規ユーザー登録
			userMODEL usermodel = new userMODEL();
			
			usermodel.SetUserid(userid);
			usermodel.SetPass(pass);
			usermodel.SetUsername(username);
		
			ret = usermodel.userRegist();
			
	//		request.setAttribute("errorMessage",
	//                "入力に誤りがあります");
	//		
	//		request.setAttribute("errorPass",
	//                "パスワードに誤りがあります");
			
			if (ret != 0)
			{
				// 新規登録画面
				RequestDispatcher dispatchar =
						request.getRequestDispatcher("/jsp/newregist.jsp");
				dispatchar.forward(request, response);
			}
			else
			{
				HttpSession session = request.getSession(false);
				session.setAttribute("login", "OK");
				session.setAttribute("userid", userid);
				
				// メイン画面
				RequestDispatcher dispatchar =
						request.getRequestDispatcher("/jsp/top.jsp");
				dispatchar.forward(request, response);
			}
		}catch (Exception e){
			logger.error(e.getMessage());		
      	}	    
	    logger.debug("doPost End");
	}
}
