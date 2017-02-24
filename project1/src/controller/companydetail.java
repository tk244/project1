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
import dao.companyDAO;
import model.companyMODEL;

import dao.characteristicDAO;							// ����
import model.characteristicMODEL;

import dao.timezoneDAO;									// ���ԑ�
import model.timezoneMODEL;

import dao.periodDAO;									// ����
import model.periodMODEL;

import dao.employmentDAO;								// �ٗp�`��
import model.employmentMODEL;


/**
 * Servlet implementation class companydetail
 * ��Əڍ�
 */
@WebServlet("/companydetail")
public class companydetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(companydetail.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public companydetail() {
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
			
			// �����e�[�u���擾
			characteristicDAO characteristicdao = new characteristicDAO();
			List<characteristicMODEL> characteristiclist = characteristicdao.GetKeyData(company_id);		
			request.setAttribute("characteristicList", characteristiclist);
	
			// ���ԑуe�[�u���擾
			timezoneDAO timezonedao = new timezoneDAO();
			List<timezoneMODEL> timezonelist = timezonedao.GetKeyData(company_id);
			request.setAttribute("timezoneList", timezonelist);
			
			// ���ԃe�[�u���擾
			periodDAO perioddao = new periodDAO();
			List<periodMODEL> periodlist = perioddao.GetKeyData(company_id);
			request.setAttribute("periodList", periodlist);
			
			// �ٗp�`�ԃe�[�u���擾
			employmentDAO employmentdao = new employmentDAO();		
			List<employmentMODEL> employmentlist = employmentdao.GetKeyData(company_id);
			request.setAttribute("employmentList", employmentlist);
			
			// �g�b�v���
			RequestDispatcher dispatchar =
					request.getRequestDispatcher("/jsp/companydetail.jsp");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
