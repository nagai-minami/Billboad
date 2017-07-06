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

import beans.Branch;
import beans.Position;
import beans.User;
import service.BranchService;
import service.PositionService;
import service.UserService;

/**
 * Servlet implementation class SignUpServlet

 */
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Branch> branches  = new BranchService().getBranch();
		List<Position> positions  = new PositionService().getPosition();
    	request.setAttribute("branches", branches);
    	request.setAttribute("positions", positions);

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		User user = new User();
		List<Branch> branches  = new BranchService().getBranch();
		List<Position> positions  = new PositionService().getPosition();

		user.setLoginId(request.getParameter("login_id"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));

		if(!(request.getParameter("branch")).isEmpty()){
			user.setBranchId(Integer.parseInt(request.getParameter("branch")));
		}
		if(!(request.getParameter("position")).isEmpty()){
			user.setPositionId(Integer.parseInt(request.getParameter("position")));
		}


		if(isValid(request, messages) == true){
			new UserService().register(user);
			response.sendRedirect("manage");

		} else {
			session.setAttribute("errorMessages", messages);

			request.setAttribute("user", user);
			request.setAttribute("branches", branches);
	    	request.setAttribute("positions", positions);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String loginId = request.getParameter("login_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String branch = request.getParameter("branch");
		String position = request.getParameter("position");

		HashSet<String> loginIdSet = new UserService().getLoginIdList();

		if(StringUtils.isEmpty(loginId) == true){
			messages.add("ログインIDを入力してください");

		}else if(loginId.length() < 6){
			messages.add("ログインIDは6文字以上で入力してください");

		}else if(20 < loginId.length()){
			messages.add("ログインIDは20文字以下で入力してください");

		}else if(!loginId.matches("^[a-zA-Z0-9]+$")){
			messages.add("ログインIDは半角英数字で入力してください");

		}if(loginIdSet.add(loginId) == false){
			messages.add("入力されたログインIDはすでに存在しています");
		}

		if(StringUtils.isEmpty(name) == true){
			messages.add("名前を入力してください");
		}else if(10 < loginId.length()){
			messages.add("名前は10文字以下で入力してください");
		}

		if(StringUtils.isEmpty(password) == true){
			messages.add("パスワードを入力してください");
		}else if(!(request.getParameter("password").equals(request.getParameter("password2")))){
			messages.add("パスワード(確認用)が一致しません");
		}else if(!password.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+$")){
			messages.add("パスワードは半角英数字(記号も含む)で入力してください");
		}else if(password.length() < 6){
			messages.add("パスワードは6文字以上で入力してください");
		}else if(255 < password.length()){
			messages.add("パスワードは255文字以下で入力してください");
		}

		if(StringUtils.isEmpty(branch) == true){
			messages.add("支店を選択してください");
		}

		if(StringUtils.isEmpty(position) == true){
			messages.add("部署・役職を選択してください");
		}


		if(branch.equals("1")){
			if(!position.equals("1") && !position.equals("2")){
			messages.add("支店の組み合わせが不正です");
			}
		}

		if(!branch.equals("1")){
			if(position.equals("1") || position.equals("2")){
			messages.add("支店の組み合わせが不正です");
			}
		}


		if(messages.size() == 0){
			return true;
		} else {
			return false;
		}

	}
}



