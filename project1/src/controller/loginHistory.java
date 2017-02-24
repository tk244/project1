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

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import constants.constant;
import dao.loginHistoryDAO;
import model.loginHistoryMODEL;

/**
 * Servlet implementation class loginHistory
 */
@WebServlet("/loginHistory")
public class loginHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(loginHistory.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		DOMConfigurator.configure(context.getRealPath(constant.log4j));

		logger.debug("doGet Start");

		try {
			
			//　ログイン履歴取得	
			loginHistoryDAO loginHistorydao = new loginHistoryDAO();
			List<loginHistoryMODEL> loginHistorylist = loginHistorydao.GetData();
			request.setAttribute("loginHistoryList", loginHistorylist);
			
			// トップ画面
			RequestDispatcher dispatchar =
					request.getRequestDispatcher("/jsp/loginHistory.jsp");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
