package pack;

import java.io.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import model.Employee;

/**
 * Servlet implementation class main
 */
@WebServlet("/main")
public class main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 
		Employee emp = new Employee();
		
		String rrr = emp.getId();
		
	    response.setContentType("text/html; charset=Shift_JIS");
	    PrintWriter out = response.getWriter();

	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>データベーステスト</title>");
	    out.println("</head>");
	    out.println("<body>");

	    Connection conn = null;
	    String url = "jdbc:mysql://localhost/jdbctestdb";
	    String user = "test";
	    String password = "testpass";

	    try {
	      Class.forName("com.mysql.jdbc.Driver").newInstance();
	      conn = DriverManager.getConnection(url, user, password);

	      Statement stmt = conn.createStatement();
	      String sql = "SELECT * FROM kabukatable";
	      ResultSet rs = stmt.executeQuery(sql);

	      while(rs.next()){
	        int code = rs.getInt("code");
	        String company = rs.getString("company");
	        out.println("<p>");
	        out.println("コード:" + code + ", 会社名:" + company);
	        out.println("</p>");
	      }

	      rs.close();
	      stmt.close();
	      
	    }catch (ClassNotFoundException e){
	        out.println("ClassNotFoundException:" + e.getMessage());
	      }catch (SQLException e){
	        out.println("SQLException:" + e.getMessage());
	      }catch (Exception e){
	        out.println("Exception:" + e.getMessage());
	      }finally{
	        try{
	          if (conn != null){
	            conn.close();
	          }
	        }catch (SQLException e){
	          out.println("SQLException:" + e.getMessage());
	        }
	      }

	      out.println("</body>");
	      out.println("</html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
