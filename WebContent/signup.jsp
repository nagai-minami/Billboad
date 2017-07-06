<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー新規登録</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="main-contents">
	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="message">
					<li><c:out value="${message}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>

<form action="signup" method="post"><br />
	<p><font style="padding:15px 100px; background:#000066; color:#ffffff; font-weight:bold; Align:left;">新規ユーザー登録</font></p>


    <br><br>
    <p>
	<label for="login_id">ログインID</label>
	<input name="login_id" value="${user.loginId}" size= "40" id="login_id"  /> (半角英数字、6～20文字以内)<br/>
	</p>

	<p>
	<label for="password">パスワード</label>
	<input name="password" type="password"  size= "30" id="password"/> (記号を含む全ての半角文字、6文字～255文字以内) <br />
	</p>

	<p>
	<label for="password2">パスワード(確認用)</label>
	<input name="password2" type="password" size= "30" id="password2"/>  <br />
	</p>

    <p>
	<label for="name">名前</label>
	<input name="name" value="${user.name}" size= "30"/> (10文字以内)<br />
	</p>

    <p>
     <label for="branch_id">支店</label>
	    <select name="branch">
	     <option value="">選択してください</option>
	     <c:forEach items="${branches}" var="branch">
				<c:if test = "${user.branchId == branch.id}">
					<option value="${branch.id}" selected><c:out value="${branch.name}" /></option>
				</c:if>

				<c:if test = "${user.branchId != branch.id}">
					<option value="${branch.id}"><c:out value="${branch.name}" /></option>
				</c:if>
			 </c:forEach>
		</select>

	</p>


	 <p>
     <label for="position_id">部署・役職</label>

	    <select name="position">

	     <option value="">選択してください</option>

		     <c:forEach items="${positions}" var="position">
				<c:if test = "${user.positionId == position.id}">
					<option value="${position.id}" selected><c:out value="${position.name}" /></option>
				</c:if>

				<c:if test = "${user.positionId != position.id}">
					<option value="${position.id}"><c:out value="${position.name}" /></option>
				</c:if>
			 </c:forEach>
		</select>
	</p>

    <p>
	<input type="submit" value="登録" /> <br />
	</p>

	<p>
	<a href= "manage" >戻る</a>
	</p>
</form>
</div>
</body>
</html>
