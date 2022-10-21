<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
 <head><meta charset="UTF-8">
 <link rel="stylesheet" type="text/css" href="/css/common.css" />
 <link rel="stylesheet" type="text/css" href="/css/board.css" />
<jsp:include page="/WEB-INF/views/includes/header.jsp" /> 


 <%-- //현재 세션 상태 체크 
 	String id=null;
 	if(session.getAttribute("id") != null){
 		
 		id=(String)session.getAttribute("id");
 	}
 	
 	if(id ==null){
 		PrintWriter script =response.getWriter();
 		script.println("<script>"); 		
 	} 
 
 --%> 
 
  <%--섬머노트 구동을 위한 정보  --%>
 <!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js-->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
 
 <%--섬머노트 설정--%>
 
		<script>
	$(document).ready(function() {
	     $('#summernote').summernote({
	             height: 300,                 // 에디터 높이
	             minHeight: null,             // set minimum height of editor
	             maxHeight: null,             // set maximum height of editor
	             focus: true,                  // 에디터에 커서 이동 (input창의 autofocus)
	             lang: "ko-KR",
	             
	             toolbar: [  //toolbar 설정
	 			    // 글꼴 설정
	 			    ['fontname', ['fontname']],
	 			    // 글자 크기 설정
	 			    ['fontsize', ['fontsize']],
	 			    // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
	 			    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
	 			    // 글자색
	 			    ['color', ['forecolor','color']],
	 			    // 표만들기
	 			    ['table', ['table']],
	 			    // 글머리 기호, 번호매기기, 문단정렬
	 			    ['para', ['ul', 'ol', 'paragraph']],
	 			    // 줄간격
	 			    ['height', ['height']],
	 			    // 그림첨부, 링크만들기, 동영상첨부
	 			    ['insert',['picture','link','video']],
	 			    // 코드보기, 확대해서보기, 도움말
	 			    ['view', ['codeview','fullscreen', 'help']]
	 			  ],
	 			  // 추가한 글꼴
	 			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
	 			 // 추가한 폰트사이즈
	 			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
	            callbacks: {	//여기 부분이 이미지를 첨부하는 부분
						onImageUpload : function(files) {
							uploadSummernoteImageFile(files[0],this);
						},
						onPaste: function (e) {
							var clipboardData = e.originalEvent.clipboardData;
							if (clipboardData && clipboardData.items && clipboardData.items.length) {
								var item = clipboardData.items[0];
								if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
									e.preventDefault();
								}
							}
						}
					}
	             
	             
	     });
	     
	     

	 	/**
	 	* 이미지 파일 업로드
	 	*/
	 	function uploadSummernoteImageFile(file, editor) {
	 		data = new FormData();
	 		data.append("file", file);
	 		$.ajax({
	 			data : data,
	 			type : "POST",
	 			url : "/uploadSummernoteImageFile",
	 			contentType : false,
	 			processData : false,
	 			success : function(data) {
	             	//항상 업로드된 파일의 url이 있어야 한다.
	 				$(editor).summernote('insertImage', data.url);
	 			}
	 		});
	 	}
	 	
	 	
	 	$("div.note-editable").on('drop',function(e){
	         for(i=0; i< e.originalEvent.dataTransfer.files.length; i++){
	         	uploadSummernoteImageFile(e.originalEvent.dataTransfer.files[i],$("#summernote")[0]);
	         }
	        e.preventDefault();
	   })
	});

	</script>
 
	
	 
 <%--섬머노트 설정 끝 --%>
 
 <%--유효성 검사--%>
     
 <title>게시판 글쓰기</title>
 </head>
 <div class="clear"></div>
 <body class="tablebody">
<br><br>
<div id="bWrite_wrap" class="margin" style="margin-top: 200px" > <%--제목도 멀티폼으로 --%>
 <form method="post" action="board_write_ok" onsubmit="return bw_check();" enctype="multipart/form-data"> 
   <table id="bWrite_t" class="tablebox">
     <%--이름 - 로그인해서 입력하면 그냥 입력되게 히든으로 ! 처리하기 --%>
  	 <tr><td><input type="hidden" name="username" id="username" value="${id}"/></td></tr>
    
     <tr>
      <td>제목</td>
      <td class="bottom_line"><input name="b_title" class="textField" size="100%"/></td>
   
    
     <tr>
      <td>내용</td>
      <td class="bottom_line"><textarea name="b_cont" id="summernote" cols="70%" rows="100%" ></textarea></td>
     </tr>
     
   	<tr>
   	  <td></td>
      <td class="buttontd"><input type="submit" class="submitBtn" value="등록" />
       <input type="reset" class="CheckBtn" value="취소"/><input type="reset" class="CheckBtn" value="목록" onclick="location='board_list.do?page=${page}';"/></td>
     </tr>
    </table>    
</form>
</div> 
</body> 

<jsp:include page="/WEB-INF/views/includes/footer.jsp" /> 

</html>