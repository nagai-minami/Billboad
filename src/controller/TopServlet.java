package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.Comment;
import beans.User;
import beans.UserContribute;
import service.CommentService;
import service.ContributeService;



@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		User user = (User) request.getSession().getAttribute("loginUser");


///////////新規投稿・コメント表示
		List<UserContribute> contributions = new ContributeService().getContribute();
		List<Comment> comments = new CommentService().getConmment();

		HashSet<String> categorySet = new ContributeService().getCategory();
		request.setAttribute("categories", categorySet);

		String date1 = request.getParameter("date1");
		String date2 = request.getParameter("date2");
		String category = request.getParameter("category");

		if(request.getParameter("category") == null){

			Collections.reverse(contributions);
			request.setAttribute("contribution", contributions);
			request.setAttribute("comments", comments);
			System.out.println(category);
			request.setAttribute("selectedCategory", category);
	    	request.setAttribute("user", user);
	    	request.getRequestDispatcher("top.jsp").forward(request, response);
	    	return;
		}



		if(request.getParameter("category").isEmpty()){

			if(request.getParameter("date1").isEmpty()){
			    date1 =	"2017-06-01";
			}
			if(request.getParameter("date2").isEmpty()){
				Date d = new Date();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

				date2 = (df.format(d));
			}

				contributions = new ContributeService().getDates(date1,date2);

				Collections.reverse(contributions);

				request.setAttribute("contributions", contributions);
				request.setAttribute("comments", comments);
				request.setAttribute("selectedCategory", category);
				request.setAttribute("date1", date1);
				request.setAttribute("date2", date2);
				request.getRequestDispatcher("top.jsp").forward(request, response);


		}else if(!request.getParameter("category").isEmpty()){

			System.out.println(category);

			if(request.getParameter("date1").isEmpty()){
			    date1 =	"2017-06-01";
			}
			if(request.getParameter("date2").isEmpty()){
				Date d = new Date();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

				date2 = (df.format(d));
			}

			contributions = new ContributeService().getSet(date1, date2, category);

			Collections.reverse(contributions);
			request.setAttribute("contributions", contributions);
			request.setAttribute("comments", comments);
			request.setAttribute("selectedCategory", category);
			request.setAttribute("date1", date1);
			request.setAttribute("date2", date2);
			request.getRequestDispatcher("top.jsp").forward(request, response);
		}

	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		Comment comment = new Comment();

		if (isValid(request, messages) == true) {

			User user = (User)session.getAttribute("loginUser");

			comment.setText(request.getParameter("text"));
			comment.setUserId(user.getId());
			comment.setContributeId(Integer.parseInt(request.getParameter("id")));

			new CommentService ().register(comment);
			response.sendRedirect("./");

		} else {

			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
		}
	}




	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String text = request.getParameter("text");

		if (StringUtils.isEmpty(text) == true) {
			messages.add("コメントを入力してください");
		}

		if ( 500 < text.length() ) {
			messages.add("件名は500字以内で入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
