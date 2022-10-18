<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>골프클래스 - 장바구니</title>
<script src="/js/jquery.js"></script>
<script src="/js/class_cart.js"></script>
<link rel="stylesheet" type="text/css" href="/css/class_cart.css" />
<jsp:include page="/WEB-INF/views/includes/header.jsp" /> 

</head>
<body>
	<%--<div id="header_body" style="width: 100%;"></div> --%>

	<%-- header와 footer를 제외한 전체 --%>
	<div id="wrap">
		<%-- 윗부분 --%>
		<div class="cart_wrap">
			<div class="cart">장바구니</div>
			<div class="cart_process">
				<strong>장바구니</strong>&emsp;>&emsp;결제하기&emsp;>&emsp;결제완료
			</div>
		</div>

		<%-- 아래 왼쪽 주문 정보 박스 --%>
		<div id="order_wrap"></div>

		<%-- 아래 오른쪽 bill 박스 --%>
		<div id="bill_wrap">
			<div id="bill_mini">
				<div class="order_info">주문정보</div>
				<div class="service_sum">
					<div class="service_hanguel">총 서비스 금액</div>
					<div class="service_money">원</div>
				</div>
				<div class="delivery">
					<div class="delivery_hanguel">＋총 배송비</div>
					<div class="delivery_money">원</div>
				</div>
				<div class="discount">
					<div class="discount_hanguel">－총 할인금액</div>
					<div class="discount_money">원</div>
				</div>
				<div class="line"></div>
				<div class="totalPay">
					<div class="total_hanguel">총 결제금액</div>
					<div class="total_money">원</div>
				</div>
				<div class="guide_box">
					<div class="guide01">이용안내</div>
					<div class="guide02">실 결제금액은 주문결제에서 프로모션/쿠폰 적용에 따라 달라질 수
						있습니다.</div>
					<div class="guide03">서비스 제공이 완료된 이후에 전문가에게 결제 대금이 전달됩니다.</div>
				</div>
				<button class="btn01" type="button" onclick="location.href='class_pay'">
					선택상품 주문
				</button>

			</div>

		</div>
	</div>

</body>
<div style="margin: 250px 0px 0px 0px;"></div>

<jsp:include page="/WEB-INF/views/includes/footer.jsp" /> 
</html>