package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
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

import dao.companyDAO;	

import dao.areaDAO;										// 地域
import model.areaMODEL;

import dao.businessDAO;									// 業種
import model.businessMODEL;

import dao.characteristicDAO;							// 特徴
import model.characteristicMODEL;

import dao.timezoneDAO;									// 時間帯
import model.timezoneMODEL;

import dao.periodDAO;									// 期間
import model.periodMODEL;

import dao.employmentDAO;								// 雇用形態
import model.employmentMODEL;

/**
 * Servlet implementation class companyreist
 * 企業追加
 */
@WebServlet("/companyreist")
public class companyreist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(companyreist.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public companyreist() {
		super();
		// TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		ServletContext context = getServletContext();
		DOMConfigurator.configure(context.getRealPath(constant.log4j));

		logger.debug("doGet Start");

		try {
			
			//　地域マスタ取得	
			areaDAO areadao = new areaDAO();
			List<areaMODEL> arealist = areadao.GetData();
			arealist.removeAll(Collections.singleton(null)); 
			request.setAttribute("areaList", arealist);
			
			//　業種マスタ取得	
			businessDAO businessdao = new businessDAO();
			List<businessMODEL> businesslist = businessdao.GetData();
			request.setAttribute("businessList", businesslist);
			
			// 特徴マスタ取得
			characteristicDAO characteristicdao = new characteristicDAO();
			List<characteristicMODEL> characteristiclist = characteristicdao.GetData();
			request.setAttribute("characteristicList", characteristiclist);
			
			// 時間帯マスタ取得
			timezoneDAO timezonedao = new timezoneDAO();
			List<timezoneMODEL> timezonelist = timezonedao.GetData();
			request.setAttribute("timezoneList", timezonelist);
			
			// 期間マスタ取得
			periodDAO perioddao = new periodDAO();
			List<periodMODEL> periodlist = perioddao.GetData();
			request.setAttribute("periodList", periodlist);
			
			// 雇用形態マスタ取得
			employmentDAO employmentdao = new employmentDAO();
			List<employmentMODEL> employmentlist = employmentdao.GetData();
			request.setAttribute("employmentList", employmentlist);
					
			// 会員登録画面
			RequestDispatcher dispatchar =
					request.getRequestDispatcher("/jsp/companyreist.jsp");
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

		int ret = 0;
		int company_id = 0;		
		Connection conn = null;						//データベース接続

		ServletContext context = getServletContext();
		DOMConfigurator.configure(context.getRealPath(constant.log4j));

		logger.debug("doPost Start");
		
		try {
			
			HttpSession session = ((HttpServletRequest)request).getSession();
			request.setCharacterEncoding("UTF-8");
			String userid = (String)session.getAttribute("userid");
			
			// ■データベース
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// 自動コミット・モードを設定
			conn.setAutoCommit(false);
			
			String company_name = request.getParameter("company_name");
			String business_id = request.getParameter("business");
			String area_id = request.getParameter("area");
			String salary = request.getParameter("salary");
			String remarks = request.getParameter("remarks");			
			
			// 企業テーブル
			companyDAO companydao = new companyDAO();
			company_id = companydao.companyInsert(conn, userid, company_name, business_id, area_id, salary, remarks);
			
			if (company_id < 1)
			{
				throw new Exception("企業テーブル追加 異常");
			}
			
			// 特徴テーブル
			characteristicDAO characteristicdao = new characteristicDAO();
			String characteristics[] = request.getParameterValues("characteristics");
			ret = characteristicdao.characteristicInsert(conn, userid, company_id, characteristics);
			
			if (ret < 0)
			{
				throw new Exception();
			}
			
			// 時間帯テーブル
			timezoneDAO timezonedao = new timezoneDAO();
			String timezones[] = request.getParameterValues("timezones");
			ret = timezonedao.timezoneInsert(conn, userid, company_id, timezones);
			
			if (ret < 0)
			{
				throw new Exception();
			}
			
			// 期間テーブル
			periodDAO perioddao = new periodDAO();
			String periods[] = request.getParameterValues("periods");
			ret = perioddao.periodInsert(conn, userid, company_id, periods);
			
			if (ret < 0)
			{
				throw new Exception();
			}
			
			// 雇用形態テーブル
			employmentDAO employmentdao = new employmentDAO();
			String employments[] = request.getParameterValues("employments");
			ret = employmentdao.employmentInsert(conn, userid, company_id, employments);
			
			if (ret < 0)
			{
				throw new Exception();
			}
			
			//コミット
			conn.commit();	

			RequestDispatcher dispatch = request.getRequestDispatcher("/top");
			dispatch.forward(request, response);
			
		}catch (ClassNotFoundException e){
			logger.error(e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, constant.serverError);
		}catch (SQLException e){
			logger.error(e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, constant.serverError);
		}catch (Exception e){
			logger.error(e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, constant.serverError);
		}finally{
			try{
				if (conn != null){
					conn.rollback();
					conn.close();
				}
			}catch (SQLException e){
				logger.error(e.getMessage());
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, constant.serverError);
			}
		}
		
		logger.debug("doPost End");
	}
}
