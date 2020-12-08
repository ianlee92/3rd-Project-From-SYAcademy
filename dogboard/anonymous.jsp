<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

$(function(){
	
	// 댓글 수정 공간
	$('.comment_Update_area').hide();
	$('.bring_comment_update_tab').click(function(){
		let no = $(this).attr('id')
		$('#comment_Update_area' + no).toggle();
	});
	
})


</script>
</head>
<body>
    <div class="hero-wrap js-fullheight" style="background-image: url('../images/dog_3.jpg');">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-center justify-content-center" data-scrollax-parent="true">
          <div class="col-md-9 ftco-animate text-center" data-scrollax=" properties: { translateY: '70%' }">
            <p class="breadcrumbs" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }"><span class="mr-2"><a href="../main/main.do">Home</a></span> <span>Board</span></p>
            <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">소통하기</h1>
          <div style="position: relative; left: 0px; top: 250px;">
        <button style='opacity: 0.7; font-size:24px; width:300pt; height:60pt;'><i class='far fa-calendar-alt'></i> 일정세우기</button>&nbsp;&nbsp;&nbsp;
        <button onclick="location.href='../dog/parkmain.do#yong'" style='opacity: 0.7; font-size:24px; width:300pt; height:60pt;'><i class='fas fa-dog'></i> 반려견 산책</button>
          </div>
          </div>
        </div>
      </div>
    </div>

	<a name="yong"></a>      
    <section class="ftco-section ftco-degree-bg">
      <div class="container">
      
      <td class="text-center">
          <div class="nav ftco-animate nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
		              <a href="list.do#yong" class="nav-link" aria-selected="true">같이가요</a>
		              <a href="../pet/list.do" class="nav-link" aria-selected="false">자랑해요</a>
		              <a href="anonymous.do#yong" class="nav-link active" aria-selected="false">익명게시판</a>
		  </div>
          </td>
          <div style="height:30px"></div>
          
          <table class="table">
          <tr>
            <td class="text-right">
            </td>
          </tr>
          </table>
          
        <div class="row">    
          <div class="col-md-8 ftco-animate">
              <h3 class="mb-5">글을 등록할 때는 네티켓을 지켜 비방 및 욕설은 삼가주세요.</h3>
              <c:forEach var="vo" items="${list }">
              <ul class="comment-list">
                <li class="comment">
                  <div class="vcard bio">
                    <img src="../images/dogicon1.jpg" alt="Image placeholder">
                  </div>
                  <div class="comment-body">
                    <h3>익명 ${vo.no }</h3>
                    <div class="meta">${vo.regdate }</div>
                    <p style="font-size:16px;">${vo.msg }</p>
                    
                    <c:if test="${sessionScope.id==vo.ip }">                    	
	                    <span type=button style="display: inline; border:none" class="reply bring_comment_update_tab" id=${vo.no } value="Update">Update</span>
                    	<form action="ano_delete.do" method="post" style="display: inline; border:none">
							<!-- <input type="password" size=10 placeholder="비밀번호 입력" name="pwd"> -->
							<input type="submit" class="reply deleteCommentButton board_button" id=${vo.no } style="display: inline; border:none" value="Delete">
							<input type=hidden name=no value=${vo.no }>
						</form>
                    </c:if>
                    
                    <div class="comment-form-wrap pt-5 comment_Update_area" id="comment_Update_area${vo.no }">
                    	<form action="ano_update.do" style="padding: 10px 10px 10px 10px;" method="post">
			                  <div class="form-group">
			                    <textarea id="message" cols="10" rows="5" style="height:100px; width:100%; line-height:160%; border:1px solid #ddd; width:100%;" name=msg>${vo.msg }</textarea>
			                    <input type=hidden name=no value=${vo.no }>
			                  </div>
			                  <div class="form-group" style="text-align:right">
			                    <input type="submit" value="수정" class="btn btn-primary">
			                  </div>
		                </form>
						</div>
                  </div>
                </li>
              </ul>
              </c:forEach>

          <!-- 페이지 -->
			<div class="row mt-5">
		          <div class="col text-center">
		            <div class="block-27">
		              <ul>
		              	<c:if test="${curpage>BLOCK }">
		                <li><a href="../dogboard/anonymous.do?page=${startPage-1 }#yong">&lt;</a></li>
		                </c:if>
		                <c:forEach var="i" begin="${startPage }" end="${endPage }">
		                 <c:if test="${i==curpage }">
		                 	<li class="active"><span><a href="../dogboard/anonymous.do?page=${i }#yong">${i }</a></span></li>
		                 </c:if>
		                 <c:if test="${i!=curpage }">
		                 	<li><a href="../dogboard/anonymous.do?page=${i }#yong">${i }</a></li>
		                 </c:if>
		                </c:forEach>
						<c:if test="${endPage<totalpage }">
		                <li><a href="../dogboard/anonymous.do?page=${endPage+1 }#yong">&gt;</a></li>
		                </c:if>
		              </ul>
		            </div>
		          </div>
		        </div>
          </div> 
          
          <!-- 사이드바 .col-md-8 -->
          <div class="col-md-4 sidebar ftco-animate">
          <div class="comment-form-wrap pt-5">
                <form method="post" action="ano_insert_ok.do" class="p-5 bg-light">
                  <div class="form-group">
                    <label for="message">내용 *</label>
                    <textarea name="msg" id="msg" cols="30" rows="10" class="form-control"></textarea>
                  </div>
                  <div class="form-group">
                    <label for="email">비밀번호 *</label>
                    <input type="password" class="form-control" name="pwd">
                  </div>
                  <div class="form-group">
                    <input type="submit" value="글쓰기" class="btn py-3 px-4 btn-primary">
                  </div>
                </form>
              </div>
      </div>
    </section> <!-- .section -->
  </body>
</html>