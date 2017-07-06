<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー管理</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">

</head>
<body>

<div class="main-contents">

<header>

<h1>ユーザ管理</h1>

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

	<ul class = "menue">
        <li><a href = "signup"><font size="4">ユーザー新規登録</font></a></li>

        <li><a href= "./">戻る</a></li>
	</ul>
</header>

<script type="text/javascript">
<!--
function check(){
	if(window.confirm('本当にアカウントを削除しますか？\n削除されたアカウントは元に戻せません')){ // 確認ダイアログを表示

		return true; // 「OK」時は送信を実行
	}
	else{ // 「キャンセル」時の処理

		window.alert('キャンセルされました'); // 警告ダイアログを表示
		return false; // 送信を中止
	}
}

// -->
<!--
function working(){
	if(window.confirm('本当にアカウントを復活させますか？')){ // 確認ダイアログを表示

		return true; // 「OK」時は送信を実行
	}
	else{ // 「キャンセル」時の処理

		window.alert('キャンセルされました'); // 警告ダイアログを表示
		return false; // 送信を中止
	}
}

// -->

<!--
function stopping(){
	if(window.confirm('本当にアカウントを停止させますか？')){ // 確認ダイアログを表示

		return true; // 「OK」時は送信を実行
	}
	else{ // 「キャンセル」時の処理

		window.alert('キャンセルされました'); // 警告ダイアログを表示
		return false; // 送信を中止
	}
}

// -->
</script>


<table border="3"><c:forEach items="${users}" var="user">
 <tr>
  <th  bgcolor="#d0ffc9" width="200" height ="30">ログインID</th>
  <th  bgcolor="#d0ffc9" width="200">名前</th>
  <th  bgcolor="#d0ffc9" width="200">支店</th>
  <th  bgcolor="#d0ffc9" width="200">部署・役職</th>
  <th  bgcolor="#d0ffc9" width="100">編集</th>
  <th  bgcolor="#d0ffc9" width="100">アカウント<br>状態</th>
  <th  bgcolor="#d0ffc9" width="100">アカウント<br>削除</th>
 </tr>
 <tr align="center">
  <td  height ="50"><span class="login_id"><c:out value="${user.loginId}" /></span></td>
  <td><span class="name"><c:out value="${user.name}" /></span></td>
  <td><span class="branch"><c:forEach items="${branches}" var="branch"><c:if test = "${user.branchId == branch.id}"><c:out value="${branch.name}" /></c:if></c:forEach></span></td>
  <td><span class="position"><c:forEach items="${positions}" var="position"><c:if test = "${user.positionId == position.id}"><c:out value="${position.name}" /></c:if></c:forEach></span></td>

  <td><form action = "edit" method="get" >
      <input type= "hidden" name="id" value = "${user.id}">
      		<input type="submit"  value="編集" />
      </form>
  </td>


	<td>
		<c:if test = "${user.id != loginUser.id}">
			<form action = "isWorking" method="post" onSubmit="return stopping()">
				<c:if test = "${user.isWorking == 0}" >

			  		<input type= "hidden" name="id" value = "${user.id}">
			  		<input type= "hidden" name="isWorking" value = "0">
		  			<input type="submit" value="復活"  />
		  		</c:if>
		  	</form>
		  </c:if>


		<c:if test = "${user.id != loginUser.id}">
			<form action = "isWorking" method="post" onSubmit="return working()">
		  		<c:if test = "${user.isWorking == 1}" >
			  		<input type= "hidden" name="id" value = "${user.id}">
			  		<input type= "hidden" name="isWorking" value = "1" >
			  		<input type="submit" value="停止"  />
		  		</c:if>
	  		</form>
	  	</c:if>
  	</td>

   <td>
   		<form action = "manage" method="post" onSubmit="return check()">
   		<c:if test = "${user.id != loginUser.id}">
       	<input type= "hidden" name="id" value = "${user.id}">
       	<input type="submit" value="削除" />
       	</c:if>



       </form>
   </td>

 </tr>

</c:forEach></table>

</div>
</div>

</body>
</html>