<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>新規投稿</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">



	<script type="text/javascript"><!--
   function ShowLength( str ) {
      document.getElementById("inputlength").innerHTML = str.length + "文字";
   }
	// --></script>

	<script type="text/javascript"><!--
   function ShowLength2( str ) {
      document.getElementById("inputlength2").innerHTML = str.length + "文字";
   }
	// --></script>

	<script type="text/javascript"><!--
   function ShowLength3( str ) {
      document.getElementById("inputlength3").innerHTML = str.length + "文字";
   }
	// --></script>

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

	<form action="contribute" method="post"><br />
	<p><font style="padding:15px 100px; background:#000066; color:#ffffff; font-weight:bold; Align:left;">新規投稿</font>


		<p>
		<label for="text">件名(50字以内)</label>
		<br>
		<input type="text" name="subject" size="100" maxlength="50" onkeyup="ShowLength(value);" value="${contribute.subject}"><p id="inputlength">0文字
		<br />
		</p>


		<label for="text">本文(1000字以内)</label>
		<br>
		<textarea name="text" rows="20" cols="100" onkeyup="ShowLength2(value);"><c:out value="${contribute.text}"></c:out></textarea><p id="inputlength2">0文字
	    <p>


		<label for="text">カテゴリー(10字以内)</label>



		<select name="category2">
			    	<option value="">選択してください</option>
			    	<c:forEach items="${categories}" var="category">
						<option value= "${category}"><c:out value="${category}" /></option>
					</c:forEach>
				</select>
		<br>
			<input type="text" name="category" size="100" maxlength="10" onkeyup="ShowLength3(value);" value="${contribute.category}"><p id="inputlength3">0文字</p>




	    <p>
		<input type="submit" value="投稿" /> <br />
		</p>

		<p>
		<a href= "./">戻る</a>
		</p>

</form>
</div>
</body>
</html>
