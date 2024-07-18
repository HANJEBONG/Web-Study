<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
    
<%
	// 브라우저는 1바이트를 취급 => 자바는 2바이트를 취급 => 디코딩
	request.setCharacterEncoding("UTF-8");
	String addr=request.getParameter("addr");
	if(addr==null || addr.equals("")){
		addr="마포";
	}
	String strPage=request.getParameter("page");
	if(strPage==null){
		strPage="1";
	}
	int curpage=Integer.parseInt(strPage);
	
	FoodDAO dao=FoodDAO.newInstance();
	List<FoodVO> list=dao.foodFindData(addr, curpage);
	int totalfindpage=dao.foodFindTotalPage(addr);
	/*
	
			인코딩 디코딩 **
	*/
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
	    <form method="post" action="food_find.jsp">
	     <input type="text" name="addr" size=20 class="input-sm" value="<%=addr%>">
	     <input type="submit" value="검색" class="btn-sm btn-danger">
	    </form>
	  </div>
	  <div style="height: 20px"></div>
	  <div class="row">
	    <%
	  	for(FoodVO vo:list){
	    %>
	    <div class="col-sm-3">
	      <a href="#">
	        <div class="thumnail">
	          <img src="<%=vo.getPoster() %>" style="width: 240px;height: 240px" class="img-rounded">
	          <p class="a"><%=vo.getName() %></p>
	        </div>
	      </a>
	    </div>
	    <%
	    }
	    %>
	  </div>
	  <div style="height: 20px"></div>
	  <div class="row">
	    <div class="text-center">
	      <a href="food_find.jsp?addr=<%=addr %>&page=<%= curpage>1? curpage-1:curpage%>" class="btn btn-sm btn-primary">이전</a>
	      <%=curpage %> page / <%=totalfindpage %> pages
	      <a href="food_find.jsp?addr=<%=addr %>&page=<%= curpage<totalfindpage? curpage+1:curpage %>" class="btn btn-sm btn-primary">다음</a>
	    </div>
	  </div>
	</div>
</body>
</html>