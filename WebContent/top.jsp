<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>わったい菜掲示板</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
<!--
	function check() {
		if (window.confirm('本当にこのコメントを削除しますか？\n削除されたコメントは元に戻せません')) { // 確認ダイアログを表示
			return true; // 「OK」時は送信を実行
		} else { // 「キャンセル」時の処理
			window.alert('キャンセルされました'); // 警告ダイアログを表示
			return false; // 送信を中止
		}
	}
// -->
</script>

<script type="text/javascript">
<!--
	function ShowLength(str) {
		document.getElementById("inputlength").innerHTML = str.length + "文字";
	}
// -->
</script>



</head>
<body>
	<div class="main-contents">

		<div class="main-contents">


			<header>
				<h1>わったい菜掲示板</h1>

				<c:if test="${ not empty errorMessages}">
					<div class="errorMessages">
						<ul>
							<c:forEach items="${errorMessages}" var="message">
								<c:out value="${message}" />
							</c:forEach>
						</ul>
					</div>
					<c:remove var="errorMessages" scope="session" />
				</c:if>


				<div class="profile">
					<h2>
						<u><c:out value="${loginUser.name}" /></u>
					</h2>
				</div>


				<ul class="menue">


					<li><a href="contribute"><font size="4"><b>新規投稿</b></font></a></li>

					<c:if test="${loginUser.positionId == 1}">
						<li><a href="manage"><font size="4"><b>ユーザー管理</b></font></a></li>
					</c:if>





					<li><a href="logout"><font size="4"><b>ログアウト</b></font></a>
					<li>
				</ul>




				<form method="get" action="index.jsp">

					<label for="category">カテゴリー</label> <select name="category">

						<option value="">選択してください</option>
						<c:forEach items="${categories}" var="category">
							<c:if test="${category.equals(selectedCategory)}">
								<option value="${category}" selected><c:out
										value="${category}" /></option>
							</c:if>
							<c:if test="${!category.equals(selectedCategory)}">
								<option value="${category}"><c:out value="${category}" /></option>
							</c:if>
						</c:forEach>

					</select> <label>期間:</label><input type="date" name="date1" value="${date1}">～<input
						type="date" name="date2" value="${date2}"> <input
						type="hidden" name="id" value="${user.id}"> <input
						type="submit" value="検索"> <a href="./">リセット</a>


				</form>


			</header>



			<div class="contributions">
				<c:forEach items="${contribution}" var="contribution">

					<div class="mybox">

						<div class="contribution">
							<div class="name">
								■名前■ <br>
								<c:out value="${contribution.name}" />
							</div>
							<br>
							<div class="subject">
								■件名■ <br>
								<c:out value="${contribution.subject}" />
							</div>
							<br>
							<div class="text">
								■本文■ <br>
								<c:out value="${contribution.text}" />
							</div>
							<br>
							<div class="category">
								■カテゴリー■ <br>
								<c:out value="${contribution.category}" />
							</div>
							<br>
							<div class="date">
								<br>
								<fmt:formatDate value="${contribution.insertDate}"
									pattern="yyyy/MM/dd HH:mm:ss" />
							</div>
						</div>

						<div>
							<c:if
								test="${contribution.userId == loginUser.id ||user.positionId == 2 || contribution.branchId == user.branchId &&  user.positionId == 3}">
								<form action="deleteContribute" method="post"
									onSubmit="return check()">
									<input type="hidden" name="id"
										value="${contribution.contributeId}"> <input
										type="hidden" name="branch" value="${contribution.branchId}">
									<input type="hidden" name="user.id"
										value="${contribution.userId}"> <input type="submit"
										value="削除" />
								</form>
						</div>
						</c:if>



						<p>
						<form action="index.jsp" method="post">
							<input type="hidden" name="id"
								value="${contribution.contributeId}">

							<textarea name="text" rows="10" cols="50"><c:out
									value="${comment.text}"></c:out></textarea>
							<input type="submit" value="コメント" />

						</form>
					</div>

					<c:forEach items="${comments}" var="comment">

						<c:if test="${comment.contributeId == contribution.contributeId}">
							<div class="mybox2">
								<div class="name">
									名前:
									<c:out value="${comment.name}" />
								</div>
								<div class="text">
									<pre>
										<c:out value="${comment.text}" />
									</pre>
								</div>
								<div class="date">
									<fmt:formatDate value="${comment.insertDate}"
										pattern="yyyy/MM/dd HH:mm:ss" />
								</div>
								<div>
									<c:if
										test="${comment.userId == loginUser.id || user.positionId == 2 || branchId == commentBranchId && positionId == 3}">
										<div class="arrow_box">
											<form action="deleteComment" method="post"
												onSubmit="return check()">
												<input type="hidden" name="id" value="${comment.id}">
												<input type="hidden" name="branch"
													value="${comment.branchId}"> <input type="hidden"
													name="userId" value="${comment.userId}"> <input
													type="submit" value="削除" />
											</form>
										</div>


									</c:if>
								</div>
						</c:if>
			</div>


			</c:forEach>
			</c:forEach>
		</div>
	</div>
	</div>
</body>
</html>