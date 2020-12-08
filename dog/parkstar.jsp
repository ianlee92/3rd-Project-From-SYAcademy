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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="hero-wrap js-fullheight" style="background-image: url('../images/dog_1.jpg');">
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
        	<div class="col-lg-3 sidebar ftco-animate">
        		<div class="sidebar-wrap bg-light ftco-animate">
        			<h3 class="heading mb-4">공원 찾기</h3>
        			<form action="#">
        				<div class="fields">
        				<form action="../dog/parksearch.do">
    					</form>
        				<form action="../dog/parksearch.do">
		              <div class="form-group">
		             	
		                <input type="text" class="form-control" placeholder="공원명" name="searchkey">
		                
		              </div>
		        
		              <div class="form-group">
		                <input type="submit" onclick="location.href='../dog/parksearch.do?searchkey=${searchkey}#yong'" value="Search" class="btn btn-primary py-3 px-5">
		              </div>
		              </form>
		            </div>
	            </form>
        		</div>
        		<div class="sidebar-wrap bg-light ftco-animate">
        			<h3 class="heading mb-4">산책 난이도</h3>
        			<form action="../dog/parkstar.do" class="star-rating">
							  <div class="form-check">
									<input type="checkbox" class="form-check-input" id="exampleCheck1">
									<label class="form-check-label" for="exampleCheck1">
										<p class="rate"><span><i class="icon-star"></i><i class="icon-star"></i><i class="icon-star"></i></span></p>
									</label>
							  </div>
							  <div class="form-check">
						      <input type="checkbox" class="form-check-input" id="exampleCheck1">
						      <label class="form-check-label" for="exampleCheck1">
						    	   <p class="rate"><span><i class="icon-star"></i><i class="icon-star"></i><i class="icon-star-o"></i></span></p>
						      </label>
							  </div>
							  <div class="form-check">
						      <input type="checkbox" class="form-check-input" id="exampleCheck1">
						      <label class="form-check-label" for="exampleCheck1">
						      	<p class="rate"><span><i class="icon-star"></i><i class="icon-star-o"></i><i class="icon-star-o"></i></span></p>
						     </label>
							  </div>
							  <div class="form-group">
							  <input type="submit" value="Search" class="btn btn-primary py-3 px-5">
							 </div>
					</form>
        		</div>
          </div>
          <div class="col-lg-9">
          <span style="text-align: left; height:50px; padding:0px 5px 50px"><b>"${searchkey }"</b> 검색 결과 총 <span style="color:#336699; font-weight:bold;">${searchcount }</span>개</span>
          	<div style="height:30px"></div>
          	<div class="row">
          	
          	
          	 <c:forEach var="vo" items="${list }">
          		<div class="col-md-4 ftco-animate">
		    		<div class="destination">
		    		<%-- <a href="#" class="img img-2 d-flex justify-content-center align-items-center"><img src="${vo.img }" style="background-size:cover;"></a> --%>
		    					<a href="parkdetail.do?no=${vo.no }#yong" class="img img-2 d-flex justify-content-center align-items-center" style="background-image: url(${vo.img });">
		    				<div class="icon d-flex justify-content-center align-items-center">
    							<span class="icon-search2"></span>
    						</div>
		    					</a>
		    			<div class="text p-3">
		    					<div class="d-flex">
		    						<div class="one">
				    					<h3><a href="parkdetail.do?no=${vo.no }#yong">${vo.name }</a></h3>
				    					<p class="rate">
				    					
				    					</p>
			    					</div>
			    					<div class="two">
			    						<span class="price" style="font-size:16px">${vo.time }</span>
		    						</div>
		    					</div>
		    					<p>${vo.content }</p>
		    					<p class="days"><span><i class="fa fa-eye" style="font-size:16px"></i> ${vo.hit }</span>&nbsp;&nbsp;|&nbsp;&nbsp;<span>난이도
									<c:if test="${vo.star == 1}">
						            <span class="rate"><i class="icon-star"></i>
									<i class="icon-star-o"></i>
									<i class="icon-star-o"></i></span>
						            </c:if>
						            
						            <c:if test="${vo.star == 2}">
									<span class="rate"><i class="icon-star"></i>
									<i class="icon-star"></i>
									<i class="icon-star-o"></i></span>
									</c:if>
									
									<c:if test="${vo.star == 3}">
									<span class="rate"><i class="icon-star"></i>
									<i class="icon-star"></i>
									<i class="icon-star"></i></span>
									</c:if>
					    			</span></p>
		    					<hr>
		    					<p class="bottom-area d-flex">
		    						<span><i class="icon-map-o"></i>&nbsp;&nbsp;${vo.zone}</span> 
		    						<span class="ml-auto"><a href="parkdetail.do?no=${vo.no }#yong">상세보기</a></span>
		    					</p>
		    			</div>
		    		</div>
		    	</div>
		    </c:forEach>		
		    			
          	</div>
          	<div class="row mt-5">
		          <div class="col text-center">
		            <div class="block-27">
		              <ul>
		              	<c:if test="${curpage>BLOCK }">
		                <li><a href="../dog/parkstar.do?page=${startPage-1 }&star=${star}#yong">&lt;</a></li>
		                </c:if>
		                <c:forEach var="i" begin="${startPage }" end="${endPage }">
		                 <c:if test="${i==curpage }">
		                 	<li class="active"><span><a href="../dog/parkstar.do?page=${i }&star=${star}#yong">${i }</a></span></li>
		                 </c:if>
		                 <c:if test="${i!=curpage }">
		                 	<li><a href="../dog/parkstar.do?page=${i }&star=${star}#yong">${i }</a></li>
		                 </c:if>
		                </c:forEach>
						<c:if test="${endPage<totalpage }">
		                <li><a href="../dog/parkstar.do?page=${endPage+1 }&star=${star}#yong">&gt;</a></li>
		                </c:if>
		              </ul>
		            </div>
		          </div>
		        </div>
          </div> <!-- .col-md-8 -->
        </div>
      </div>
    </section> <!-- .section -->
    
  </body>
</html>