<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> </title>
<script src="/js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="/css/scorecard_del.css" />
</head>
<body>
<div class="scdel_ask">
	<h2>정말 삭제하겠습니까?</h2>
</div>
<div class="scdel_btn">
	<form method="post" action="admin_insertCard_del_ok">
		<input type="hidden" name="sc_no" value="${s_id }">
		<input type="hidden" name="sc_playdate" value="${s_date }">
		<input type="submit" class="del_btn" value="삭제">
		<input type="button" class="del_btn" value="아니오" onclick="'history.back()'">
	</form>
</div>
</body>
</html>