<%@ page contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="/css/login.css" />
<div class="clear"></div>
<script src="/js/jquery.js"></script>
<script src="/js/member.js"></script>


<%-- 회원가입 페이지 --%>

<body class="join">

<form name="join_ok" method="post" action="join_ok" onsubmit="return join_check();">
<div class="joinForm">
<input type="text" name="username" id="username" class="textField" placeholder="아이디">
<input type=button class="CheckBtn" value=중복확인 onclick="id_Check();"/><br/><span id="idcheck"></span>
<br/><br/><input type="password" name="password" id="password" class="textField" placeholder="비밀번호"><br>
<input type="password" name="pwCheck" id="pwCheck" class="textField" placeholder="비밀번호 확인"><br>
<input type="tel" name="mPhone" class="textField" id="mPhone"pattern="^[0][1][0]-\d{4}-\d{4}$" placeholder="휴대폰 번호"><br>
<input type="email" name="mEmail" id="mEmail" class="textField" placeholder="이메일"><br>
<input type="button" class="CheckBtn" value=이메일인증>
<input type="radio" name="mGender" id="male_gender" class="gender" value="male"><label id="male_gender">남</label>
<input type="radio" name="mGender" id="female_gender" class="gender" value="female"><label id="female_gender">여</label>
<input type="submit" class="submitBtn" value=회원가입>
</div>
</form>
</body>

