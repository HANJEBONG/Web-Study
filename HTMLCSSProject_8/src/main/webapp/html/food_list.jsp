<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
 <%
 		FoodDAO dao=FoodDAO.newInstanece();
 		// 요청된 페이지 받기
 		String strpage=request.getParameter("page");
 		if(strpage==null){
 			strpage="1";
 		}
 		int curpage=Integer.parseInt(strpage);
 		List<FoodVO> list=dao.foodListData(curpage);
 		int totalpage=dao.foodTotalPage(); 
 		
 		 for(FoodVO vo:list)
 	    {
 	    	String img=vo.getPoster();
 	    	img=img.replace("https", "http");
 	    	vo.setPoster(img);
 	    }
 		
 		final int BLOCK=10;
 		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
 		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
 		
 		if(endPage>totalpage){
 			endPage=totalpage;
 		}
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
.container {
	margin-top:50px;
}
.row{
	margin: 0px auto;
}
.a{
	white-space:nowrap;
	overflow: hidden;
	text-overflow: ellipsis;

}

</style>
</head>
<body>
	<div class="container">
	  <div class="row">
	  <%
	  		for(FoodVO vo:list){
	  %>
	  	<div class="col-sm-3">
	      <div class="thumnail">
	        <a href="food_detail.jsp?fno=<%=vo.getFno()%>"><!-- 공백시 오류 -->
	          <img src="<%=vo.getPoster() %>" style="width: 240px; height: 200px" class="img-rounded">
	          <p class="a"><%=vo.getName().substring(0,vo.getName().indexOf("[")) %></p>
	        </a>
	      </div>
	    </div>
	    <%
	  		}
	    %>
	  </div>
	  <div style="height: 10px"></div>
	  <div class="row">
	    <div class="text-center">
	      <ul class="pagination">
	      <%
	      	if(startPage>1){
	      %>
	        <li><a href="food_list.jsp?page=<%=startPage-1%>">&lt</a></li>
	        <%
	      	}
	        %>
	        <%
	        	for(int i=startPage;i<=endPage;i++){
	        %>
	        <li class="<%= curpage==i?"active":""%>"><a href="food_list.jsp?page=<%=i%>"><%=i %></a></li>
	        <%
	        	}
	        %>
	        <%
	        	if(endPage<totalpage){
	        %>
	        <li><a href="food_list.jsp?page=<%=endPage+1%>">&gt</a></li>
	        <%
	        	}
	        %>
	      </ul>
	    </div>
	  </div>
	</div>
</body>
</html>