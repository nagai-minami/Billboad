package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;



@WebServlet(urlPatterns = { "/isWorking" })

public class IsWorkingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public IsWorkingServlet() {
        super();

    }

    @Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

	}

	@Override
	protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int userId = Integer.parseInt(request.getParameter("id"));

		int isWorking = Integer.parseInt(request.getParameter("isWorking"));

		if(isWorking == 1){
			isWorking = 0;
		}else{
			isWorking = 1;
		}

		System.out.println(isWorking);

		new UserService().isWorking(userId, isWorking);
		response.sendRedirect("manage");


	}


}
