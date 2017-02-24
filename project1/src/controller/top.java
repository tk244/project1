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
 * Servlet implementation class top
 */
@WebServlet("/top")
public class top extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(top.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public top() {
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

			//String company_name = request.getParameter("company_name");
			String area_id = request.getParameter("area");
			String characteristics[] = request.getParameterValues("characteristics");
			
			//�@�n��}�X�^�擾	
			areaDAO areadao = new areaDAO();
			List<areaMODEL> arealist = areadao.GetData();
			request.setAttribute("areaList", arealist);
			
			//�@�Ǝ�}�X�^�擾	
			businessDAO businessdao = new businessDAO();
			List<businessMODEL> businesslist = businessdao.GetData();
			request.setAttribute("businessList", businesslist);
			
			// �����}�X�^�擾
			characteristicDAO characteristicdao = new characteristicDAO();
			List<characteristicMODEL> characteristiclist = characteristicdao.GetData();
			request.setAttribute("characteristicList", characteristiclist);
			
			// ���ԑу}�X�^�擾
			timezoneDAO timezonedao = new timezoneDAO();
			List<timezoneMODEL> timezonelist = timezonedao.GetData();
			request.setAttribute("timezoneList", timezonelist);
			
			// ���ԃ}�X�^�擾
			periodDAO perioddao = new periodDAO();
			List<periodMODEL> periodlist = perioddao.GetData();
			request.setAttribute("periodList", periodlist);
			
			// �ٗp�`�ԃ}�X�^�擾
			employmentDAO employmentdao = new employmentDAO();
			List<employmentMODEL> employmentlist = employmentdao.GetData();
			request.setAttribute("employmentList", employmentlist);
			
			//�@��ƃe�[�u���擾		
			companyDAO companydao = new companyDAO();
			
			List<companyMODEL> companyList = companydao.GetData(area_id, characteristics);
			
			request.setAttribute("companyLists", companyList);
			
			// �g�b�v���
			RequestDispatcher dispatchar =
					request.getRequestDispatcher("/jsp/top.jsp");
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
		
		doGet(request, response);
	}

}
