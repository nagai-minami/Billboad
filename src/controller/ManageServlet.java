package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Branch;
import beans.Position;
import beans.User;
import service.BranchService;
import service.PositionService;
import service.UserService;


@WebServlet(urlPatterns = { "/manage" })
public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

    	List<User> users = new UserService().getUsers();
    	List<Branch> branches  = new BranchService().getBranch();
		List<Position> positions  = new PositionService().getPosition();

    	request.setAttribute("branches", branches);
    	request.setAttribute("positions", positions);
    	request.setAttribute("users", users);
		request.getRequestDispatcher("manage.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		int userId = Integer.parseInt(request.getParameter("id"));

		new UserService().delete(userId);

		response.sendRedirect("manage");
	}


}

