<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<div id="wrap">
		<header class="pageHeader">
			<a href="http://localhost:8081/main" class="logo">
				<img width="180px" height="100px" src="/img/logo_ex.jpg">
			</a>
		</header>
	<form id="boxes" method="post" action="signin">
		<div id="inputGroup">
			<div id="input">
				<div class="label-wrapper">
					<label for="id" id="lb">아이디</label><br>
				</div>
				<input type="text" name="id"><br>	
			</div>
			<div id="input">
				<div class="label-wrapper">
					<label for="pw" id="lb">비밀번호</label><br>
				</div>
				<input type="password" name="pw"><br>
			</div>
			<c:if test="${param.error ne null}">
				<span style="color:red">${errMessage }</span>
			</c:if>
   			<div id="find">
  				<button class="find-a" onclick="location.href='#'">아이디 찾기</button>&nbsp;<a id="divide">|</a>&nbsp;<button class="find-a" onclick="location.href='#'">비밀번호 찾기</button>
			</div>
   			<div id="input">
				<input type="submit" id="submit" value="로그인">
			</div>
			<div id="input">
				<input type="button" id="signup" value="회원가입" onclick="location.href='http://localhost:8081/signup'">
			</div>
		</div>
      </form>      
   </div>
   
<script>
</script>
</html>