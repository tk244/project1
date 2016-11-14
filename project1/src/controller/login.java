package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import constants.constant;

import model.userMODEL;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(login.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		ServletContext context = getServletContext();
//		
//		//WEB-INF以下のinit.propertiesの物理パスを取得する。
//		// 「/」はコンテキストルートを意味する
//		String path = context.getRealPath("/WEB-INF/init.properties");
//		
//		InputStream in = new FileInputStream(path);
//		Properties prop = new Properties();
//		prop.load(in);
//		in.close();
//		
//		String aaa ="";
//		
//		aaa = prop.getProperty("hoge");
//		aaa = prop.getProperty("foo");
		
		RequestDispatcher dispatchar =
				request.getRequestDispatcher("/jsp/login.jsp");
		dispatchar.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int ret = 0;								// リターンコード
		
		ServletContext context = getServletContext();
		DOMConfigurator.configure(context.getRealPath(constant.log4j));

		logger.debug("doPost Start");
		
		try {
		
			HttpSession session = request.getSession(false);
			
			// リクエストパラメータの取得
			request.setCharacterEncoding("UTF-8");
			String userid = request.getParameter("userid");
			String pass = request.getParameter("pass");
			
			request.setAttribute("userid", userid);
			request.setAttribute("pass", pass);
			
			userMODEL usermodel = new userMODEL();
			usermodel.SetUserid(userid);
			usermodel.SetPass(pass);
			
			// ログインチェック
			ret = usermodel.loginCheck();
			
			if (ret != 1)
			{
				session.setAttribute("login", "NG");
				
				request.setAttribute("errorMessage","ユーザIDまたはパスワードが違います");
				
				// ログイン画面
				RequestDispatcher dispatchar =
						request.getRequestDispatcher("/jsp/login.jsp");
				dispatchar.forward(request, response);
			}else{
				
				session.setAttribute("login", "OK");
				session.setAttribute("userid", userid);

//				RequestDispatcher dispatch = request.getRequestDispatcher("/top");
//				dispatch.forward(request, response);
				
				response.sendRedirect(context.getContextPath() + "/top");

			}	

		}catch (Exception e){
			logger.error(e.getMessage());
		}
		
		logger.debug("doPost End");
	}
}
