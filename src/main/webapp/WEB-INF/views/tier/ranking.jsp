<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*, java.util.*, javax.sql.*, javax.naming.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%		
	String prov = (String)request.getAttribute("prov");
	int mem = (Integer)request.getAttribute("mem");
	
	String[] fileaddr = new String[mem];
	
	String[] rankid = new String[mem];
	for(int i=0 ; i<mem ; ++i){
		rankid[i] = (String)request.getAttribute("rankid"+i);
	}
	
	int[] count = new int[mem];
	for(int i=0 ; i<mem ; ++i){
		count[i] = (Integer)request.getAttribute("count"+i);
	}
	
	int[] rankpoint = new int[mem];
	for(int i=0 ; i<mem ; ++i){
		rankpoint[i] = (Integer)request.getAttribute("rankpoint"+i);
	}
	
	int[] bestrange = new int[mem];
	for(int i=0 ; i<mem ; ++i){
		bestrange[i] = (Integer)request.getAttribute("bestrange"+i);
	}
	
	String[] province = new String[mem];
	for(int i=0 ; i<mem ; ++i){
		province[i] = (String)request.getAttribute("province"+i);
	}
%>

<jsp:include page="/WEB-INF/views/includes/header.jsp" /> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
<link rel="stylesheet" type="text/css" href="/css/ranking.css" />
<link rel="stylesheet" type="text/css" href="/css/common.css" />
<link rel="stylesheet" type="text/css" href="/css/board.css" />
<br/>

<%-- 랭킹 본문 --%>

<script src="/js/jquery.js"></script>




<article id="ranking_main">
	<br>
	<span class="rank_title_1" id="rank_tag_1">
		전체랭킹
	</span>	
	<hr style="padding:0.5px; background-color:grey; width:1300px; margin-left:	4%; border:0; margin-top:20px;">
	<div class="rank_title" id="rank_tag">
		<form method="get" action="ranking">
			
		<div class="search-box">
			<input type="text" name="searchtxt" class="search-txt" onkeyup="press();" id="search_txt" placeholder="아이디 입력">
			
			<button type="button" class="search-btn" onclick="searching();"><i class="fas fa-search"></i></button>	
				
		</div>
		
		</form>
	</div>
	
	
	<div class="category">
		<ul class="province_1">
			<li><a href="ranking">전체</a></li>
			<li>서울・경기・인천
				<ul class="province_2" id="province_2-3">
					<li><i class="fas fa-map-marker-alt"></i><a href="ranking?prov=서울"> 서울</a></li>
					<li><i class="fas fa-map-marker-alt"></i><a href="ranking?prov=경기"> 경기</a></li>
					<li><i class="fas fa-map-marker-alt"></i><a href="ranking?prov=인천"> 인천</a></li>
				</ul>
			</li>
			<li>강원
				<ul class="province_2" id="province_2-1">
					<li><i class="fas fa-map-marker-alt"></i><a href="ranking?prov=강원"> 강원</a></li>
				</ul>
			</li>
			<li>충청・대전・세종
				<ul class="province_2" id="province_2-3">
					<li><i class="fas fa-map-marker-alt"></i><a href="ranking?prov=충청"> 충청</a></li>
					<li><i class="fas fa-map-marker-alt"></i><a href="ranking?prov=대전"> 대전</a></li>
					<li><i class="fas fa-map-marker-alt"></i><a href="ranking?prov=세종"> 세종</a></li>
				</ul>
			</li>
			<li>전라・광주
				<ul class="province_2" id="province_2-2">
					<li><i class="fas fa-map-marker-alt"></i><a href="ranking?prov=전라"> 전라</a></li>
					<li><i class="fas fa-map-marker-alt"></i><a href="ranking?prov=광주"> 광주</a></li>
				</ul>
			</li>
			<li>경상・부산・대구・울산
				<ul class="province_2" id="province_2-4">
					<li><i class="fas fa-map-marker-alt"></i><a href="ranking?prov=경상"> 경상</a></li>
					<li><i class="fas fa-map-marker-alt"></i><a href="ranking?prov=부산"> 부산</a></li>
					<li><i class="fas fa-map-marker-alt"></i><a href="ranking?prov=대구"> 대구</a></li>
					<li><i class="fas fa-map-marker-alt"></i><a href="ranking?prov=울산"> 울산</a></li>
				</ul>
			</li>
		</ul>
	</div>
	<div class="rank_wrap"> 
		<table class="rankList" id="rank0">
			<tr>
				<th width="7%" id="Listno">#</th>
				<th width="11%" id="Listprofile">프로필</th>
				<th width="11%" id="Listtier">
					<select name="id_or_tier" class="searchtier" onchange="tierChange()">
						<option value="select_All">티어</option>
						<option value="select_bornze">브론즈</option>
						<option value="select_silver">실버</option>
						<option value="select_gold">골드</option>
						<option value="select_platinum">플레티넘</option>
						<option value="select_diamond">다이아몬드</option>
					</select>
				</th>
				<th width="25%" id="Listid">아이디</th>
				<th width="14%" id="Listpoint">점수</th>
				<th width="15%" id="Listdriver">최대비거리</th>				
				<th width="17%" id="Listcard">플레이 횟수</th>
			</tr>
		</table>	
		<div class="rank_wrap2">
		
		</div>

	</div>
	
	<script>
		var memberCount = "<c:out value='${mem}'/>"; // 회원 수.
		var divCount = $('.rank').length;  //rank class에 해당하는 div 개수(현재 0).
		
		//div 생성 반복문
		while(memberCount > divCount){ //while문은 돌아가고있음
			
			$(".rank_wrap2").append("<div class='rank' id='rank"+(divCount+1)+"'>");
					
			$("#rank"+(divCount+1)).html("<div class='rankno' id='rankno"+(divCount+1)+"'>"); //rankno클래스 div 생성
			
			var divrankprofile = $("<div></div>").addClass("rankprofile").attr('id',"rProfile_"+(divCount+1));
			$("#rankno"+(divCount+1)).after(divrankprofile); //rankprofile클래스 div 생성
			
			var divranktier = $("<div></div>").addClass("ranktier").attr('id',"rTier_"+(divCount+1));
			$("#rProfile_"+(divCount+1)).after(divranktier); //rankdriver클래스 div 생성
						
			var divrankid = $("<div></div>").addClass("rankid").attr('id',"rId"+(divCount+1));
			$("#rTier_"+(divCount+1)).after(divrankid); //rankid클래스 div 생성
			
			var divrankpoint = $("<div></div>").addClass("rankpoint").attr('id',"rPoint_"+(divCount+1));
			$("#rId"+(divCount+1)).after(divrankpoint); //rankpoint클래스 div 생성
			
			var divrankdriver = $("<div></div>").addClass("rankdriver").attr('id',"rDriver_"+(divCount+1));
			$("#rPoint_"+(divCount+1)).after(divrankdriver); //rankdriver클래스 div 생성
						
			var divrankcard = $("<div></div>").addClass("rankcard").attr('id',"rCard_"+(divCount+1));
			$("#rDriver_"+(divCount+1)).after(divrankcard); //rankcard클래스 div 생성
			
			var divrankprov = $("<div></div>").addClass("rankprov").attr('id',"rProv_"+(divCount+1));
			$("#rCard_"+(divCount+1)).after(divrankprov); //rankcard클래스 div 생성
			
			
			$("#rankpoint"+(divCount+1)).html("<span id='rPoint_"+(divCount+1)+"'></span>");
			
			document.getElementById('rankno'+(divCount+1)).innerHTML = (divCount+1)+"위";
			
						
			divCount = $('.rank').length; //div는 하나만 생기고있음. 
			
			
		}//while
		
	</script>
	
	
	<!-- div 내 정보 자동입력 -->
	<% 		
	for(int i=1 ; i<=mem ; ++i){
		
		%>
		<script>
		$('#rId'+<%=i%>).append("<%=rankid[i-1]%>");
		$('#rPoint_'+<%=i%>).append("<%=rankpoint[i-1]%>");
		$('#rProv_'+<%=i%>).append("<%=province[i-1]%>");
		$('#rCard_'+<%=i%>).append("<%=count[i-1]%>");
		$('#rDriver_'+<%=i%>).append("<%=bestrange[i-1]%>");
		</script>
		<%		
		//fileaddr[i-1] = mdao.fileinfo(rankid);
	}
		
	%>
	
	<%
	for(int i=0 ; i<mem ; ++i){
		//if(fileaddr[i] != null){
		%>
		<script>
		$("#rProfile_"+<%=i+1%>).css({
			"background-image": "url('/upload/member"+"<%=fileaddr[i] %>"+"')",
			"background-repeat" : "no-repeat",
			"background-size" : "50px 50px"
		});
		</script>
		<%
		//}
	}
	%>
	<script>
	//indivrank 링크, 티어 만드는 스크립트

		var tier = '';
		for(var i=1 ; i<=memberCount ; ++i){
			$("#rank"+i).attr('onclick',"location.href='indivrank?rId="+document.getElementById("rId"+i).innerHTML+"&rPoint_="+$("#rPoint_"+i).text()+"&rankno="+$("#rankno"+i).text()+"'");
			
			var tierNum = $("#rPoint_"+i).text();
			if(tierNum < -15){
				tier = 'd.png';
				document.getElementById("rTier_"+i).innerHTML = "<img class='tierPic' alt='다이아' src='/images/t_"+tier+"'>";
				$("#rTier_"+i).append("다이아몬드");
			}else if(tierNum >= -15 && tierNum < -10){
				tier = 'p.png';
				document.getElementById("rTier_"+i).innerHTML = "<img class='tierPic' alt='플레' src='/images/t_"+tier+"'>";
				$("#rTier_"+i).append("플레티넘");
			}else if(tierNum >= -10 && tierNum < -5){
				tier = 'g.png';
				document.getElementById("rTier_"+i).innerHTML = "<img class='tierPic' alt='골드' src='/images/t_"+tier+"'>";
				$("#rTier_"+i).append("골드");
			}else if(tierNum >= -5 && tierNum < 5){
				tier = 's.png';
				document.getElementById("rTier_"+i).innerHTML = "<img class='tierPic' alt='실버' src='/images/t_"+tier+"'>";
				$("#rTier_"+i).append("실버");
			}else{
				tier = 'b.png';
				document.getElementById("rTier_"+i).innerHTML = "<img class='tierPic' alt='브' src='/images/t_"+tier+"'>";
				$("#rTier_"+i).append("브론즈");
			}
			var profile = $('#rProfile_+i').text();

			
		}
		
		
	</script>
		
	<script>
	//검색 관련 스크립트

	var divCount = $('.rank').length;
		
	function getParameterByName(name){
		name = name.replace(/[\[]/,"\\[").replace(/[\]]/, "\\]");
		var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
			results = regex.exec(location.search);
		return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
	var getprov = getParameterByName('prov');
	
	function searchname(){
		var search = document.getElementById("search_txt").value;
		return search;
	}
	
	if("<c:out value='${prov}'/>" != null){
		for(var i=1 ; i<=memberCount ; ++i){
			if("<c:out value='${prov}'/>" == '서울'){
				if(!($('#rProv_'+i).text() == '서울')){
					$("#rank"+i).css({
						"display": "none"
					});
				}
			}else if("<c:out value='${prov}'/>" == '경기'){
				if(!($('#rProv_'+i).text() == '경기')){
					$("#rank"+i).css({
						"display": "none"
					});
				}
			}else if("<c:out value='${prov}'/>" == '인천'){
				if(!($('#rProv_'+i).text() == '인천')){
					$("#rank"+i).css({
						"display": "none"
					});
				}
			}
			
		}
		
		
		
	}
	
	
	function searching(){		
		$(".rank").css({
			"display" : "block"
		});
		for(var i=1 ; i<=divCount ; ++i){
			if(searchname() == ''){
				$(".rank").css({
					"display" : "block"
				});
			}else if(!(searchname() == document.getElementById("rId"+i).innerText)){
				$("#rank"+i).css({
					"display" : "none"
				});
			}
		}
	}
	
	//티어 selectbox제어 스크립트
	<%-- 다이아:상위memeber/5 플레:상위member/5*2 골드:상위member/5*3 실버:상위member/5*4 브론즈:나머지 --%>
	
	function tierChange(){
		
		$(".rank").css({
			"display" : "block"
		});
		
		if(getprov == ''){
			if($(".searchtier option:checked").text() == "티어"){
				$(".rank").css({
					"display" : "block"
				});
			}else{
				for(var i=1 ; i<=memberCount ; ++i){
					if(!($("#rTier_"+i).text() == $(".searchtier option:checked").text())){
						$("#rank"+i).css({
							"display" : "none"
						});
					}
				}		
			}	
		}else{ //prov가 있다면.
			if($(".searchtier option:checked").text() == "티어"){				
				$(".rank").css({
					"display" : "block"
				});
				for(var i=1 ; i<=memberCount ; ++i){
					if($('#rProv_'+i).text() != getprov){
						$("#rank"+i).css({
							"display" : "none"
						});
					}				
				}			
			}else{			
				for(var i=1 ; i<=memberCount ; ++i){
					if(!($("#rTier_"+i).text() == $(".searchtier option:checked").text())){
						$("#rank"+i).css({
							"display" : "none"
						});
					}
					if($('#rProv_'+i).text() != getprov){
						$("#rank"+i).css({
							"display" : "none"
						});
					}
				}		
			}	
		}				
	}
	
	function press(){
		if(window.event.keyCode == 13){ //13이 enter을 의미
			searching();
		}
	}
	
	</script>

</article>


<jsp:include page="/WEB-INF/views/includes/footer.jsp" /> 
<br>




























