<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.User, model.Mutter, java.util.List" %>
<%
	//セッションスコープからloginUserを取得
	User loginUser = (User) session.getAttribute("loginUser");

	//アプリケーションスコープから、つぶやきリストを取得
	List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
	
	//リクエストスコープからエラーメッセージを取得
	String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインページ</title>
</head>
<body>
<h1>どこつぶメイン</h1>
<p>
<%= loginUser.getName() %>さん、ログイン中
<a href ="/docoTsubu/Logout">ログアウト</a>
</p>
<p><a href = "/docoTsubu/Main">更新</a></p>
<form action = "/docoTsubu/Main" method = "post">
<input type = "text" name = "text">
<input type = "submit" value = "つぶやく">
</form>
<% if(errorMsg != null){%>
<p><%= errorMsg %></p>
<% } %>
<%	for(Mutter mutter: mutterList){ %>
<p><%= mutter.getUserName() %>: <%= mutter.getText() %></p>
<%	} %>
</body>
</html>