<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<table>
    <thead>
        <tr>
            <th>id</th>
            <th>pw</th>
            <th>age</th>
            <th>preference</th>
            <th>email</th>
            <th>nickname</th>
            <th>rank</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.pw}"/></td>
                <td><c:out value="${user.age}"/></td>
                <td><c:out value="${user.preference}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.nickname}"/></td>
                <td><c:out value="${user.rank}"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
    <form method="post" action="/user">
    <label for="id">아이디 : </label>
    <input type="text" id="id" name="id" required><br>
    <label for="pw">비밀번호 : </label>
    <input type="password" id="pw" name="pw" required><br>
    <label for="pw">비밀번호 확인 : </label>
    <input type="password" id="pw-confirm" name="pw-confirm" required><br>
    <label for="age">나이 : </label>
    <input type="text" id="age" name="age" required><br>
    <label for="preference">선호 장르 : </label>
    <input type="text" id="preference" name="preference" required><br>
    <label for="email">이메일 : </label>
    <input type="text" id="email" name="email" required><br>
    <label for="nickname">닉네임 : </label>
    <input type="text" id="nickname" name="nickname" required><br>
    <button type="submit" id="submit">회원가입</button>
</form>
<script>
	
	document.querySelector("#submit").onclick = function(){
	let id = document.querySelector('input[name="id"]').value.trim();
	// 영문으로 시작하고 숫자를 포함하는 5자 이상인 정규식
	let idRegex = /^[a-zA-Z][a-zA-Z0-9]{5,10}$/;
	let password = document.querySelector('input[name="pw"]').value;
	let password_confirm = document.querySelector('input[name="pw-confirm"]').value.trim();
	// 영문,숫자,특수문자 최소 하나씩 포함하는 최소 8~최대 16 글자인 정규식
	let passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*\W)(?!.*\s).{8,16}$/; 
	let age = document.querySelector('input[name="age"]').value.trim();
	let ageRegex = /^([0-9]|[0-9][0-9]|1[01][0-9]|120)$/;
	let email = document.querySelector('input[name="email"]').value.trim();
	let emailRegex =  /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	let nickname = document.querySelector('input[name="nickname"]').value.trim();
	let nicknameRegex =  /^[가-힣a-zA-Z0-9]{2,10}$/;
	// 0부터 120까지 입력받는 정규식
		if ( id.length === 0 ) {
			alert("아이디를 입력해주세요.");
			event.preventDefault();
		} else if (!id.match(idRegex)){
			alert("아이디는 영문으로 시작하여 숫자를 포함한 5자 이상 10자 이하여야 합니다.");
			event.preventDefault();
		} else if ( password.length === 0) {
			alert("비밀번호를 입력해주세요.");
			event.preventDefault();
		} else if (!password.match(passwordRegex)){
			alert("비밀번호는 영문, 숫자, 특수문자를 최소 한 글자 이상 포함하는"
					+ "8자 이상 16자 이하여야 합니다.");
			event.preventDefault();
		} else if ( password !== password_confirm ){
			alert("비밀번호가 일치하지 않습니다.");
			event.preventDefault();
		} else if ( age.length === 0) {
			alert("나이를 입력해주세요.");
			event.preventDefault();
		} else if (!age.match(ageRegex)){
			alert("나이는 0부터 120까지 입력 가능합니다.");
			event.preventDefault();
		} else if ( email.length === 0 ){
			alert("이메일을 입력해주세요.");
			event.preventDefault();
		} else if (!email.match(emailRegex)){
			alert("이메일 형식이 올바르지 않습니다.");
			event.preventDefault();
		} else if ( nickname.length === 0 ){
			alert("닉네임을 입력해주세요.");
			event.preventDefault();
		} else if (!nickname.match(nicknameRegex)){
			alert("닉네임은 특수문자와 공백을 제외한 2~10자 이하여야합니다.");
			event.preventDefault();
		} 
		
	}
</script>

</body>
</html>