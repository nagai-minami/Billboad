package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ContributeService;


@WebServlet(urlPatterns = { "/deleteContribute" })

public class DeleteContributeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public DeleteContributeServlet() {
        super();

    }

    @Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	//////削除を実行する社員の支店・役職

			int contributeId = Integer.parseInt(request.getParameter("id"));
			new ContributeService().deleteContribution(contributeId);
			response.sendRedirect("./");

	}

}



