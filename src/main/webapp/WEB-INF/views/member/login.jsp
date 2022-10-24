<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/css/login.css" />
<link rel="stylesheet" type="text/css" href="/css/login2.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>

<div class="clear"></div>

<html>
<head>

<%-- favicon --%>
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
<link rel="apple-touch-icon" sizes="57x57" href="/favicon.ico/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="/favicon.ico/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="/favicon.ico/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="/favicon.ico/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="/favicon.ico/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="/favicon.ico/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="/favicon.ico/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="/favicon.ico/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="/favicon.ico/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"  href="/favicon.ico/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="/favicon.ico/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="/favicon.ico/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="/favicon.ico/favicon-16x16.png">
<link rel="manifest" href="/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">
<%-- favicon 끝 --%>
<title></title>
</head>
<body class="login">
<script src="/js/jquery.js"></script>
<script>
   function login_check() {
      if ($.trim($('#username').val()) == '') {//trim()함수는 양쪽 공백을 제거
         alert('아이디를 입력하세요!');
         $('#username').val('').focus();
         return false;
      }

      if ($.trim($('#password').val()) == '') {
         alert('비밀번호를 입력하세요!');
         $('#password').val('').focus();
         return false;
         
      }
   }
</script>
<c:if test="${empty id}"> <%--id가 비어 있다면~ 로그인 전 화면 --%>
<div class="loginForm">
<form method="post" class="loginFormin" action="/loginOk" onsubmit="return login_check();" enctype="application/x-www-form-urlencoded" >
<input type="text" name="username" id="username" class="textField" placeholder="아이디"><br>
<input type="password" name="password" id="password" class="textField" placeholder="비밀번호"><br>
<input type=submit class="submitBtn" value=로그인>
<input type=checkbox name="remember-me" id="remember-me" class="checkbox" value=로그인상태유지><span>로그인 상태 유지</span><br>
<br>
<hr></hr>
<br>

</form> 
<button name="googlelogin" id="googlelogin" class="googleloginBtn" onclick="location.href ='/oauth2/authorization/google'"><i class="fab fa-google"></i> 구글로 로그인</a></button>
<div class="links">
<input type="button" class="a" value="회원가입" onclick="location='/join';" />
<input type="button" class="a" value="비밀번호 찾기" onclick="location='/findPwd';" />
</div>
</div>
</c:if>

<c:if test="${!empty id}"> 
<div>
${id}님 로그인을 환영합니다.<br>
</div>
<div>
<a href="/logout">[로그아웃]</a>
<a href="/board_list">[게시판]</a><br>
<a href="/mypage">[마이페이지]</a><br>
<a href="/customer_main">[고객센터]</a><br>
</div>
</c:if>

</body>
</html>
