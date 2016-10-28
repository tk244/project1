package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import model.userMODEL;

/**
 * Servlet implementation class newregist
 */
@WebServlet("/newregist")
public class newregist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		request.setCharacterEncoding("UTF-8");
		
		String tkn = request.getParameter("tkn");
		tkn= "";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		//���N�G�X�g�p�����[�^�̎擾
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		String pass = request.getParameter("pass");
		String username = request.getParameter("username");
		
		
		userMODEL usermodel = new userMODEL();
		
		usermodel.SetUserid(userid);
		usermodel.SetPass(pass);
		usermodel.SetUsername(username);
	
		int ret = 0;
	
		//�V�K�o�^
		ret = usermodel.userRegist();
		
//		request.setAttribute("errorMessage",
//                "���͂Ɍ�肪����܂�");
//		
//		request.setAttribute("errorPass",
//                "�p�X���[�h�Ɍ�肪����܂�");
		
		if (ret != 0)
		{
			//�V�K�o�^���
			RequestDispatcher dispatchar =
					request.getRequestDispatcher("/jsp/newregist.jsp");
			dispatchar.forward(request, response);
		}
		else
		{
			HttpSession session = request.getSession(false);
			session.setAttribute("login", "OK");

			//���C�����
			RequestDispatcher dispatchar =
					request.getRequestDispatcher("/jsp/top.jsp");
			dispatchar.forward(request, response);
		}
	}

}
