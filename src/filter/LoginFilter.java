package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;


@WebFilter("/*")
public class LoginFilter implements Filter {

	 @Override
	 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
		    try{

		      HttpSession session = ((HttpServletRequest)request).getSession();
		      List<String> errorMessages = new ArrayList <String>();
		      String path = ((HttpServletRequest)request).getServletPath();

		      if(!path.equals("/login") && !path.contains("css")){
		        Object loginCheck = session.getAttribute("loginUser");

		        if (loginCheck == null){
		          /* まだ認証されていない */
		          errorMessages.add("ログインしてください");
		          session.setAttribute("errorMessages",errorMessages);

		          ((HttpServletResponse)response).sendRedirect("login");
		          return;

		        }
		      }

		      if(path.equals("/manage")){

			        User loginUser = (User) session.getAttribute("loginUser");
			        int positionId = loginUser.getPositionId();

			        if (positionId != 1){
			          /* まだ認証されていない */
			          errorMessages.add("このページにはアクセスできません");
			          session.setAttribute("errorMessages",errorMessages);

			          ((HttpServletResponse)response).sendRedirect("./");
			          return;

			        }
			      }

			      if(path.equals("/edit")){
				        User loginUser = (User) session.getAttribute("loginUser");

				        int positionId = loginUser.getPositionId();

				        if (positionId != 1){
				          /* まだ認証されていない */
				          errorMessages.add("このページにはアクセスできません");
				          session.setAttribute("errorMessages",errorMessages);

				          ((HttpServletResponse)response).sendRedirect("./");
				          return;

				        }
				      }

			      if(path.equals("/signup")){
				        User loginUser = (User) session.getAttribute("loginUser");

				        int positionId = loginUser.getPositionId();

				        if (positionId != 1){
				          /* まだ認証されていない */
				          errorMessages.add("このページにはアクセスできません");
				          session.setAttribute("errorMessages",errorMessages);

				          ((HttpServletResponse)response).sendRedirect("./");
				          return;

				        }
				      }

		      chain.doFilter(request, response);
		    }catch (ServletException se){
		    }catch (IOException e){
		    }
		  }



		  @Override
		public void init(FilterConfig filterConfig) throws ServletException{
		  }

		  @Override
		public void destroy(){
		  }

}