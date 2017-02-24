package controller;

import java.io.IOException;

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

/**
 * Servlet implementation class companyedit
 * äÈã∆àÍóó çÌèú/åüçı
 */
@WebServlet("/companylist")
public class companylist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(companylist.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public companylist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String MyAction = request.getParameter("MySubmit"); 
		
		ServletContext context = getServletContext();
		DOMConfigurator.configure(context.getRealPath(constant.log4j));

		logger.debug("doPost Start");
		
		try {
		
			if (MyAction.equals("Delete")){
				// çÌèú
				String deletechks[] = request.getParameterValues("deletechks");
				
				companyDAO companydao = new companyDAO();
				
				companydao.companyDelete(deletechks);
				
				RequestDispatcher dispatch = request.getRequestDispatcher("/top");
				dispatch.forward(request, response);

			}else{
				// åüçı
				String company_name = request.getParameter("company_name");
				String area_id = request.getParameter("area");
				String characteristics[] = request.getParameterValues("characteristics");
				
				try {

					request.setAttribute("company_name", company_name);
					request.setAttribute("area", area_id);
					request.setAttribute("characteristics", characteristics);
					
					// ÉgÉbÉvâÊñ 
					RequestDispatcher dispatchar =
							request.getRequestDispatcher("/top");
					dispatchar.forward(request, response);

				}catch (Exception e){
					logger.error(e.getMessage());
				}
			}
		}catch (Exception e){
			logger.error(e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, constant.serverError);
		}
		
		logger.debug("doPost End");
	}
}
