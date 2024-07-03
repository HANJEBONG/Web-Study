<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
 <%
 		 GoodsDAO dao=GoodsDAO.newInstance();
 		 int startPage;
 		 int endPage;
 		 String strpage=request.getParameter("page");
 		 if(strpage==null){
 			strpage="1";
 		 }
 		 int curpage=Integer.parseInt(strpage);
 		 List<GoodsVO> list=dao.goodsListData(curpage);
 		 
 		 int totalpage=dao.goodsTotal();
 		 final int BLOCK=5;
 		 startPage=((curpage-1)/BLOCK*BLOCK)+1;
 		 endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
 		 
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
	       for (GoodsVO vo:list){
	     %>
		 <div class="col-sm-3">
		   <div class="thumnail">
		     <a href="Goods_detail.jsp?no=<%=vo.getNo()%>">
		       <img src="<%=vo.getGoods_poster() %>" style="width: 240px; height: 200px" class="img-rounded">
		       <p class="a"><%=vo.getGoods_name() %></p>
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
	     	<li><a href="Goods_list.jsp?page=<%=startPage-1%>">&lt</a></li>
	     <%
	     	}
	     	for (int i=startPage;i<=endPage;i++){
	     %>	
	     	<li class="<%= curpage==i?"active":""%>"><a href="Goods_list.jsp?page=<%=i%>"><%=i %></a></li>
	     <%
	     	}
	     	if(endPage<totalpage){
	     %>
	     	<li><a href="Goods_list.jsp?page=<%=endPage+1%>">&gt</a></li>
	     <%
	     	}	
	     %>
	     </ul>
	   </div>
	 </div>
	</div>
</body>
</html>