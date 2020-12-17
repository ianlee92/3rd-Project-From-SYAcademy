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
	// 검색창
	$('#Search').click(function(){
		let searchkey = $('#searchkey').val();
		if (searchkey.trim()==""){
				alert("검색어를 입력하세요!");
				$('#searchkey').focus();
				history.back();
				return;
		}
	});
	// 대댓글 작성 공간 
	$('.comment_Insert_area').hide();
	$('.bring_comment_tab').click(function(){
		let no = $(this).attr('id')
		$('#comment_Insert_area' + no).toggle();
	});
	
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
	<div class="hero-wrap js-fullheight" style="background-image: url('../images/dog_12.jpg');">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-center justify-content-center" data-scrollax-parent="true">
          <div class="col-md-9 ftco-animate text-center" data-scrollax=" properties: { translateY: '70%' }">
            <p class="breadcrumbs" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }"><span class="mr-2"><a href="../main/main.do">Home</a></span> <span>Board</span></p>
            <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">반려견 산책</h1>
          <div style="position: relative; left: 0px; top: 250px;">
        <button style='opacity: 0.7; font-size:24px; width:300pt; height:60pt;'><i class='far fa-calendar-alt'></i> 일정세우기</button>&nbsp;&nbsp;&nbsp;
        <button onclick="location.href='../dogboard/list.do#yong'" style='opacity: 0.7; font-size:24px; width:300pt; height:60pt;'><i class='fab fa-gratipay'></i> 소통하기</button>
          </div>
          </div>
        </div>
      </div>
    </div>
	<a name="yong"></a>
    <section class="ftco-section ftco-degree-bg">
      <div class="container">
        <div class="row">
          <div class="col-md-8 ftco-animate">
            <h2 class="mb-3"><span style="color:#FFB914;">공원명 | </span> ${vo.name }</h2>
            <p>${vo.content }</p>
            <br>
            <p style="text-align:center;">
              <img src="${vo.img }" style="filter: drop-shadow(3px 3px 3px #000); width:500px; height:300px;" class="img-fluid">
            </p>
            <br>
            <center><h5>난이도 : 
            <c:if test="${vo.star == 1}">
            <i style="color:#FFB914;" class="icon-star"></i>
			<i style="color:#FFB914;" class="icon-star-o"></i>
			<i style="color:#FFB914;" class="icon-star-o"></i>
            </c:if>
            
            <c:if test="${vo.star == 2}">
			<i style="color:#FFB914;" class="icon-star"></i>
			<i style="color:#FFB914;" class="icon-star"></i>
			<i style="color:#FFB914;" class="icon-star-o"></i>
			</c:if>
			
			<c:if test="${vo.star == 3}">
			<i style="color:#FFB914;" class="icon-star"></i>
			<i style="color:#FFB914;" class="icon-star"></i>
			<i style="color:#FFB914;" class="icon-star"></i>
			</c:if><br>
            산책 예상 시간 : ${vo.time }</h5></center>
            <br>
            <hr>
            <h2 class="mb-3"><span style="color:#FFB914;">오시는 길 | </span></h2>
            <pre>${vo.visit_road}</pre>
            <br>
            <h5>Map</h5>
            <div id="map" style="width:100%; height:350px;"></div>
            <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=앱키&libraries=services"></script>
			<script>
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = {
			        center: new kakao.maps.LatLng('${vo.latitude}', '${vo.longitude}'), // 지도의 중심좌표
			        level: 3 // 지도의 확대 레벨
			    };  
			
			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			
			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();
			
			// 주소로 좌표를 검색합니다
			geocoder.addressSearch('${vo.addr}', function(result, status) {
			
			    // 정상적으로 검색이 완료됐으면 
			     if (status === kakao.maps.services.Status.OK) {
			
			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			
			        // 결과값으로 받은 위치를 마커로 표시합니다
			        var marker = new kakao.maps.Marker({
			            map: map,
			            position: coords
			        });
			
			        // 인포윈도우로 장소에 대한 설명을 표시합니다
			        var infowindow = new kakao.maps.InfoWindow({
			            content: '<div style="width:150px;text-align:center;padding:6px 0;">${vo.name}</div>'
			        });
			        infowindow.open(map, marker);
			
			        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			        map.setCenter(coords);
			    } 
			});    
			</script>
            <div class="tag-widget post-tag-container mb-5 mt-5">
              <div class="tagcloud">
                <a href="#" class="tag-cloud-link">${vo.zone }</a>
                <a href="#" class="tag-cloud-link">${vo.name }</a>
                <!-- <a href="#" class="tag-cloud-link">태그3</a>
                <a href="#" class="tag-cloud-link">태그4</a> -->
              </div>
            </div>
            
			<div style="text-align:right;">
            <a href="../dog/parkmain.do#yong" class="btn py-2 px-3 btn-warning">돌아가기</a>
           </div>

          
          <div class="pt-5 mt-5">
          <h3 class="mb-5">댓글</h3>
          <c:forEach var="rvo" items="${rList }">

              <ul class="comment-list">
               
                <li class="comment" style="margin-bottom:15px; padding-top:5px">
               <c:if test="${rvo.getGroup_tab()>0 }">
              		<c:forEach var="i" begin="1" end="${rvo.getGroup_tab() }">
              		<ul class="children" style="padding-top:0px">
              		
              		</c:forEach>
              	</c:if>
                  <div class="vcard bio">
                    
                    <img src="../images/dogicon2.jpg" alt="Image placeholder">
                  </div>
                  <div class="comment-body">
                    <h3>${rvo.name} (${rvo.id })</h3>
                    <div class="meta">${rvo.regdate }</div>
                    <p style="font-size:16px;">${rvo.msg }</p>
                    	<span type=button style="display: inline; border:none" class="reply bring_comment_tab" id=${rvo.no } value="Reply">Reply</span>
                    <c:if test="${sessionScope.id==rvo.id }">                    	
	                    <span type=button style="display: inline; border:none" class="reply bring_comment_update_tab" id=${rvo.no } value="Update">Update</span>
                    	<form action="deleteReply.do" method="post" style="display: inline; border:none">
							<!-- <input type="password" size=10 placeholder="비밀번호 입력" name="pwd"> -->
							<input type="submit" class="reply deleteCommentButton board_button" id=${rvo.no } style="display: inline; border:none" value="Delete">
							<input type=hidden name=no value=${rvo.no } >
							<input type=hidden name=bno value=${vo.no }>
						</form>
                    </c:if>
                    	
						<div class="comment-form-wrap pt-5 comment_Insert_area" id="comment_Insert_area${rvo.no }">
						<form action="insert_replyReply.do" style="padding: 10px 10px 10px 10px;" method="post">
		                  <div class="form-group">
		                  	<input type="text" class="form-control" style="height:15px; width:200px; font-size:16px" name=name id="name" placeholder="닉네임"><br>
		                    <textarea id="message" cols="10" rows="5" style="height:100px; width:100%; font-size:16px; line-height:160%; border:1px solid #ddd; width:100%;" placeholder=" 내용을 입력하세요" name=msg></textarea>
				                    <input type=hidden name=no value=${rvo.no }>
				                    <input type=hidden name=parent_no value=${rvo.no }>
				                    <input type=hidden name=bno value=${vo.no }>
				           </div>
				                  <div class="form-group" style="text-align:right">
				                    <input type="submit" value="대댓글" class="btn btn-primary">
				                  </div>
			                </form>
			            </div>
                    	<div class="comment-form-wrap pt-5 comment_Update_area" id="comment_Update_area${rvo.no }">
                    	<form action="updateReply.do" style="padding: 10px 10px 10px 10px;" method="post">
			                  <div class="form-group">
			                    <textarea id="message" cols="10" rows="5" style="height:100px; width:100%; line-height:160%; border:1px solid #ddd; width:100%;" name=msg>${rvo.msg }</textarea>
			                    <input type=hidden name=bno value=${vo.no }>
			                    <input type=hidden name=no value=${rvo.no }>
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
             
              </ul>
              </div>
              
              <div class="comment-form-wrap pt-5">
                <h3 class="mb-5">댓글 남기기</h3>
                <form action="insert_reply.do" class="p-5 bg-light" method="post">
                  <div class="form-group">
                    <label for="name">닉네임 *</label>
                    <input type="text" class="form-control" name=name id="name">
                  </div>
                  <div class="form-group">
                    <label for="message">내용 *</label>
                    <textarea name=msg id="msg" cols="10" rows="3" class="form-control"></textarea>
                  	<input type=hidden name=bno value="${vo.no }">
                  	<input type=hidden name=no value="${rvo.no }">
                  	
                  </div>
                  <div class="form-group">
                    <input type="submit" value="댓글달기" class="btn py-3 px-4 btn-primary">
                  </div>

                </form>
              </div>
            </div>
            
          
          <!-- 사이드바 .col-md-8 -->
          <div class="col-md-4 sidebar ftco-animate">
            <div class="sidebar-box">
            
              <form action="../dog/parksearch.do" class="search-form">
                <div class="form-group">
                  <span class="icon fa fa-search"></span>
                  <input type="text" id="searchkey" name="searchkey" class="form-control" placeholder="공원명을 입력하세요.">
                </div>
              </form>
            </div>
            <div class="sidebar-box ftco-animate">
              <div class="categories">
                <h3>공원 검색평</h3>
                <td class="text-center">
                	<img src="../naverpark${vo.no }.png" width=300px height=300px>
                </td>
              </div>
            </div>
			<div class="sidebar-box ftco-animate">
              <div class="categories">
                <h3>최근 본 산책코스</h3>
                <c:forEach var="cvo" items="${cList }" varStatus="s">
                <c:if test="${s.index<3 }">
                <div class="block-21 mb-4 d-flex">
	                <a href="../dog/park_before.do?no=${cvo.no }" class="blog-img mr-4"><img src="${cvo.img}" width=130px height=80px></a>
	                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div class="text">
	                  <div class="meta">
	                  	<p style="color:#FFB914; font-weight:bold; margin:0px 0px 0px 0px;"><a href="../dog/park_before.do?no=${cvo.no }"><span style="color:#FFB914">${cvo.name }</span></a></p>
	                    <div><a href="../dog/park_before.do?no=${cvo.no }">
	                    <i class="icon-map-o"></i>&nbsp;&nbsp;${cvo.zone}
	                    </a></div>
	                    <div><a href="../dog/park_before.do?no=${cvo.no }"><i class="fa fa-eye" style="font-size:16px"></i>&nbsp;&nbsp;${cvo.hit }</a></div>
	                  </div>
	                </div>
	              </div>
	              </c:if>
      			</c:forEach>
              </div>
            </div>
			</div>
        </div>
      </div>
    </section> <!-- .section -->
  </body>
</html>
