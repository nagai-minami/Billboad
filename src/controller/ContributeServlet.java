package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.Contribute;
import beans.User;
import service.ContributeService;

@WebServlet("/contribute")
public class ContributeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HashSet<String> categorySet = new ContributeService().getCategory();
		request.setAttribute("categories", categorySet);
		request.getRequestDispatcher("contribute.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		Contribute contribute = new Contribute();
		HashSet<String> categorySet = new ContributeService().getCategory();

		User user = (User)session.getAttribute("loginUser");
		System.out.println(user);

		contribute.setSubject(request.getParameter("subject"));
		contribute.setText(request.getParameter("text"));
		contribute.setCategory(request.getParameter("category"));
		contribute.setCategory2(request.getParameter("category2"));
		contribute.setUserId(user.getId());

		System.out.println(request.getParameter("category2"));
		System.out.println(request.getParameter("category"));



		if (isValid(request, messages) == true) {

			new ContributeService().register(contribute);
			response.sendRedirect("./");

		} else {

			request.setAttribute("errorMessages", messages);
			request.setAttribute("contribute", contribute);
			request.setAttribute("categories", categorySet);
			request.getRequestDispatcher("/contribute.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String subject = request.getParameter("subject");
		String text = request.getParameter("text");
		String category = request.getParameter("category");
		String category2 = request.getParameter("category2");

		if (StringUtils.isEmpty(subject) == true) {
			messages.add("件名を入力してください");
		}

		if ( 50 < subject.length() ) {
			messages.add("件名は50字以内で入力してください");
		}

		if (StringUtils.isEmpty(text) == true) {
			messages.add("本文を入力してください");
		}
		if ( 10000 < text.length() ) {
			messages.add("本文は1000字以内で入力してください");
		}

		if (StringUtils.isEmpty(category) == true && StringUtils.isEmpty(category2)) {
			messages.add("カテゴリーを入力してください");
		}

		if ( 10 < category.length() ) {
			messages.add("カテゴリーは10字以内で入力してください");
		}
		if(!StringUtils.isEmpty(category) == true && !StringUtils.isEmpty(category2)){
			messages.add("カテゴリーは１つです");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}