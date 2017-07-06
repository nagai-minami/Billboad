package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommentService;



@WebServlet(urlPatterns = { "/deleteComment" })

public class DeleteCommentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public DeleteCommentServlet() {
        super();

    }

    @Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//////削除を実行する社員の支店・役職

	    	int commentId = Integer.parseInt(request.getParameter("id"));
			new CommentService().deleteComment(commentId);
			response.sendRedirect("./");

	}

}
