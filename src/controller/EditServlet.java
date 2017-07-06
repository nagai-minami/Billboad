package controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import beans.Branch;
import beans.Position;
import beans.User;
import service.BranchService;
import service.PositionService;
import service.UserService;


@WebServlet(urlPatterns = { "/edit" })
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

////////////doGetメソッド(idを引数に編集画面にユーザー情報を表示)
		@Override
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {

			List<String> messages = new ArrayList<String>();

			String id = request.getParameter("id");

			if(id == null){
				messages.add("不正なアクセスです");
				request.setAttribute("errorMessages", messages);
				request.getRequestDispatcher("/manage").forward(request, response);
				return;
			}


			if(!id.matches("^\\d+$") || id.isEmpty() || id.length() > 9){
				messages.add("不正なパラメーターです");
				request.setAttribute("errorMessages", messages);
				request.getRequestDispatcher("/manage").forward(request, response);
				return;
			}


			User user = new UserService().getUser(Integer.parseInt(id));

			if(user == null){

				messages.add("不正なパラメーターです");
				request.setAttribute("errorMessages", messages);
				request.getRequestDispatcher("/manage").forward(request, response);
				return;
			}

			if(user != null){

				List<Branch> branches  = new BranchService().getBranch();
				List<Position> positions  = new PositionService().getPosition();
		    	request.setAttribute("branches", branches);
		    	request.setAttribute("positions", positions);
		    	request.setAttribute("user", user);
				request.getRequestDispatcher("/edit.jsp").forward(request, response);
			}

		}

////////////doPostメソッド

		@Override
		protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {

			User user = new User();

			List<String> messages = new ArrayList<String>();
			List<Branch> branches  = new BranchService().getBranch();
			List<Position> positions  = new PositionService().getPosition();

			user.setId(Integer.parseInt(request.getParameter("id")));
			user.setLoginId(request.getParameter("login_id"));

			if(!(request.getParameter("password")).isEmpty()){
				user.setPassword(request.getParameter("password"));
			}

			user.setName(request.getParameter("name"));

			if(!(request.getParameter("branch")).isEmpty()){
				user.setBranchId(Integer.parseInt(request.getParameter("branch")));
			}
			if(!(request.getParameter("position")).isEmpty()){
				user.setPositionId(Integer.parseInt(request.getParameter("position")));
			}


			if (isValid(request, messages) == true) {

				new UserService().update(user);
				response.sendRedirect("manage");

			} else {

				request.setAttribute("errorMessages", messages);
				request.setAttribute("user", user);
				request.setAttribute("branches", branches);
		    	request.setAttribute("positions", positions);
				request.getRequestDispatcher("/edit.jsp").forward(request, response);

			}
		}

////////エラー

		private boolean isValid(HttpServletRequest request, List<String> messages) {

			String loginId = request.getParameter("login_id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String branch = request.getParameter("branch");
			String position = request.getParameter("position");

			User user = new UserService().getLoginId(loginId);

			String id = request.getParameter("id");
			User user2 = new UserService().getUser(Integer.parseInt(id));

			String checkLoginId = user2.getLoginId();

			if(!checkLoginId.equals(loginId)){

				if(user != null){
					messages.add("このログインIDはすでに使われています");

				}
			}

			if(StringUtils.isEmpty(loginId) == true){
				messages.add("ログインIDを入力してください");

			}else if(loginId.length() < 6){
				messages.add("ログインIDは6文字以上で入力してください");

			}else if(20 < loginId.length()){
				messages.add("ログインIDは20文字以下で入力してください");

			}else if(!loginId.matches("^[a-zA-Z0-9]+$")){
				messages.add("ログインIDは半角英数字で入力してください");
			}


			if(StringUtils.isEmpty(name) == true){
				messages.add("名前を入力してください");
			}else if(10 < loginId.length()){
				messages.add("名前は10文字以下で入力してください");
			}

			if(!StringUtils.isEmpty(password)){
				if(!(request.getParameter("password").equals(request.getParameter("password2")))){
					messages.add("パスワード(確認用)が一致しません");
					}else if(!password.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+$")){
						messages.add("パスワードは半角英数字(記号も含む)で入力してください");
					}else if(password.length() < 6){
						messages.add("パスワードは6文字以上で入力してください");
					}else if(255 < password.length()){
						messages.add("パスワードは255文字以下で入力してください");
					}
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






			// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
			if (messages.size() == 0) {
				return true;
			} else {
				return false;
			}
		}



}


