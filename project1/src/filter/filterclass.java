package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class filterclass
 */
@WebFilter("/*")
public class filterclass implements Filter {

    /**
     * Default constructor. 
     */
    public filterclass() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String target = ((HttpServletRequest)request).getRequestURI();

//		if (!(target.equals("/project1/newregist"))){
//			if (!(target.equals("/project1/mailsend"))){
//				if (!(target.equals("/project1/login"))){
//					HttpSession session = ((HttpServletRequest)request).getSession();
//		
//					if (session == null){
//						session = ((HttpServletRequest)request).getSession(true);
//							RequestDispatcher dispatchar =
//										request.getRequestDispatcher("/jsp/login.jsp");
//								dispatchar.forward(request, response);
//					}else{
//						String loginCheck = (String)session.getAttribute("login");
//						if (loginCheck == null || !(loginCheck.equals("OK") || !(loginCheck.equals("NEW")))){
//							RequestDispatcher dispatchar =
//										request.getRequestDispatcher("/jsp/login.jsp");
//								dispatchar.forward(request, response);
//						}
//					}	
//				}
//			}
//		}

		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
