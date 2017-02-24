package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
import dao.areaDAO;
import dao.businessDAO;
import dao.characteristicDAO;
import dao.companyDAO;
import dao.employmentDAO;
import dao.periodDAO;
import dao.timezoneDAO;
import model.areaMODEL;
import model.businessMODEL;
import model.characteristicMODEL;
import model.companyMODEL;
import model.employmentMODEL;
import model.periodMODEL;
import model.timezoneMODEL;

/**
 * Servlet implementation class companyedit
 * ��ƍX�V
 */
@WebServlet("/companyedit")
public class companyedit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(companyedit.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public companyedit() {
		super();
		// TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		DOMConfigurator.configure(context.getRealPath(constant.log4j));

		logger.debug("doPost Start");
		
		try {
			
			String company_id = request.getParameter("company_id");
			
			// ��ƃe�[�u���擾
			companyDAO companydao = new companyDAO();
			List<companyMODEL> companyList = companydao.GetKeyData(company_id);
			request.setAttribute("companyLists", companyList);
			
			
			//�@�n��}�X�^�擾
			areaDAO areadao = new areaDAO();
			List<areaMODEL> arealist = areadao.GetKeyDataMst(company_id);
			request.setAttribute("areaList", arealist);
			
			//�@�Ǝ�}�X�^�擾	
			businessDAO businessdao = new businessDAO();
			List<businessMODEL> businesslist = businessdao.GetKeyDataMst(company_id);
			request.setAttribute("businessList", businesslist);
			
			// �����}�X�^�擾	
			characteristicDAO characteristicdao = new characteristicDAO();
			List<characteristicMODEL> characteristiclist = characteristicdao.GetKeyDataMst(company_id);		
			request.setAttribute("characteristicList", characteristiclist);
			
			// ���ԑу}�X�^�擾
			timezoneDAO timezonedao = new timezoneDAO();
			List<timezoneMODEL> timezonelist = timezonedao.GetKeyDataMst(company_id);
			request.setAttribute("timezoneList", timezonelist);
			
			// ���ԃ}�X�^�擾
			periodDAO perioddao = new periodDAO();
			List<periodMODEL> periodlist = perioddao.GetKeyDataMst(company_id);	
			request.setAttribute("periodList", periodlist);
			
			// �ٗp�`�ԃ}�X�^�擾
			employmentDAO employmentdao = new employmentDAO();		
			List<employmentMODEL> employmentlist = employmentdao.GetKeyDataMst(company_id);	
			request.setAttribute("employmentList", employmentlist);

			// �g�b�v���
			RequestDispatcher dispatchar =
					request.getRequestDispatcher("/jsp/companyedit.jsp");
			dispatchar.forward(request, response);
		
		}catch (Exception e){
			logger.error(e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, constant.serverError);
		}
		
		logger.debug("doPost End");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int ret = 0;

		Connection conn = null;						//�f�[�^�x�[�X�ڑ�

		ServletContext context = getServletContext();
		DOMConfigurator.configure(context.getRealPath(constant.log4j));

		logger.debug("doPost Start");
		
		try {
			
			HttpSession session = ((HttpServletRequest)request).getSession();
			request.setCharacterEncoding("UTF-8");
			String userid = (String)session.getAttribute("userid");
			
			// ���f�[�^�x�[�X
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(constant.url, constant.user, constant.password);

			// �����R�~�b�g�E���[�h��ݒ�
			conn.setAutoCommit(false);
			
			String company_id = request.getParameter("company_id");
			String company_name = request.getParameter("company_name");
			String business_id = request.getParameter("business");
			String area_id = request.getParameter("area");
			String remarks = request.getParameter("remarks");
			String salary = request.getParameter("salary");
			
			// ��ƃe�[�u��
			companyDAO companydao = new companyDAO();
			ret = companydao.companyUpdate(conn, userid, company_id, company_name, business_id, area_id, salary, remarks);
			
			if (ret < 1)
			{
				throw new Exception("��ƃe�[�u���X�V �ُ�");
			}
			
			int icompany_id = Integer.parseInt(company_id);
			
			// �����e�[�u��
			characteristicDAO characteristicdao = new characteristicDAO();
			
			ret = characteristicdao.characteristicDelete(conn, icompany_id);
			if (ret < 0)
			{
				throw new Exception("�����e�[�u���폜 �ُ�");
			}
			
			String characteristics[] = request.getParameterValues("characteristics");
			ret = characteristicdao.characteristicInsert(conn, userid, icompany_id, characteristics);
			if (ret < 0)
			{
				throw new Exception("�����e�[�u���ǉ� �ُ�");
			}
			
			// ���ԑуe�[�u��
			timezoneDAO timezonedao = new timezoneDAO();
			
			ret = timezonedao.timezoneDelete(conn, icompany_id);
			if (ret < 0)
			{
				throw new Exception("���ԑуe�[�u���폜 �ُ�");
			}
			
			String timezones[] = request.getParameterValues("timezones");
			ret = timezonedao.timezoneInsert(conn, userid, icompany_id, timezones);
			
			if (ret < 0)
			{
				throw new Exception("���ԑуe�[�u���ǉ� �ُ�");
			}
			
			// ���ԃe�[�u��
			periodDAO perioddao = new periodDAO();
			ret = perioddao.periodDelete(conn, icompany_id);
			if (ret < 0)
			{
				throw new Exception("���ԃe�[�u���폜 �ُ�");
			}
			
			String periods[] = request.getParameterValues("periods");
			ret = perioddao.periodInsert(conn, userid, icompany_id, periods);
			
			if (ret < 0)
			{
				throw new Exception("���ԃe�[�u���ǉ� �ُ�");
			}
			
			// �ٗp�`�ԃe�[�u��
			employmentDAO employmentdao = new employmentDAO();
			ret = employmentdao.employmentDelete(conn, icompany_id);
			if (ret < 0)
			{
				throw new Exception("�ٗp�`�ԃe�[�u���폜 �ُ�");
			}
			
			String employments[] = request.getParameterValues("employments");
			ret = employmentdao.employmentInsert(conn, userid, icompany_id, employments);
			
			if (ret < 0)
			{
				throw new Exception("�ٗp�`�ԃe�[�u���ǉ� �ُ�");
			}
			
			//�R�~�b�g
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
