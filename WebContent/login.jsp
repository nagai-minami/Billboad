<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">

	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="message" >
	            <font color="#ff0000"><c:out value="${message}" /></font>
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>


	<c:if test="${ not empty errorMessages2 }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages2}" var="message" >
	            <font color="#ff0000"><c:out value="${message}" /></font>
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages2" scope="session"/>
	</c:if>

<form action="login" method="post"><br />

<br>
<br>
    <p>
	<label for="login_id">ログインID</label>
	<input name="loginId" id="loginId" value="${loginUser.loginId}"><br/>
	</p>

    <p>
	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />
	</p>

	<input type= "hidden" name="isWorking" value = "${user.id}"></div>



    <p>
	<input type="submit" value="ログイン" class="sbt_1"/> <br />
	</p>
</form>
</div>
</body>
</html>
