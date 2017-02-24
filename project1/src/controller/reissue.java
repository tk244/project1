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

import common.commonClass;
import constants.constant;
import dao.tokenDAO;
import dao.userDAO;
import model.tokenMODEL;
import model.userMODEL;

/**
 * Servlet implementation class reissue
 */
@WebServlet("/reissue")
public class reissue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(reissue.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reissue() {
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
				request.setAttribute("errorMessage","有効期限が無効です。");
				
				// エラー画面
				RequestDispatcher dispatchar =
						request.getRequestDispatcher("/jsp/error.jsp");
				dispatchar.forward(request, response);
				return;
			}
			
			if (ret != 0){
				// 異常
				return;
			}
			
			userMODEL usermodel = new userMODEL();		
			usermodel.SetUserid(tokenList.get(0).getMailadress());
			
			ret = usermodel.userExist();
			
			if (ret != 1)
			{
				request.setAttribute("errorMessage","登録されてません。");
				
				// エラー画面
				RequestDispatcher dispatchar =
						request.getRequestDispatcher("/jsp/error.jsp");
				dispatchar.forward(request, response);
				return;
			}
			
			request.setAttribute("mailadress", tokenList.get(0).getMailadress());
			
			// 再登録画面
			RequestDispatcher dispatchar =
					request.getRequestDispatcher("/jsp/reissue.jsp");
			dispatchar.forward(request, response);
		
		}catch (Exception e){
			logger.error(e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, constant.serverError);
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
		
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		String pass = request.getParameter("pass");
		String password = "";
		
	    try 
	    {   

			//　ユーザー再登録
			commonClass com = new commonClass();
			
			password = com.encryptStr(pass);
			
			userDAO userdao = new userDAO();			
			ret = userdao.userUpdate(userid, password);

			if (ret != 0)
			{
				// 再登録画面
				RequestDispatcher dispatchar =
						request.getRequestDispatcher("/jsp/reissue.jsp");
				dispatchar.forward(request, response);
			}
			else
			{
				HttpSession session = request.getSession(false);
				session.setAttribute("login", "OK");
				session.setAttribute("userid", userid);
				
				// メイン画面
				RequestDispatcher dispatchar =
						request.getRequestDispatcher("/top");
				dispatchar.forward(request, response);
			}
		}catch (Exception e){
			logger.error(e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, constant.serverError);
      	}	    
	    logger.debug("doPost End");
	}

}
