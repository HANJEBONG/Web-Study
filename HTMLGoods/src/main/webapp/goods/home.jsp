<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
 <%
 		 request.setCharacterEncoding("UTF-8");
 		 GoodsDAO dao=GoodsDAO.newInstance();
 		 
 		 int startPage;
 		 int endPage;
 		 String strpage=request.getParameter("page");
 		 if(strpage==null){
 			strpage="1";
 		 }
 		 int curpage=Integer.parseInt(strpage);
 		
 		 
 		 int totalpage=dao.goodsTotal();
 		 final int BLOCK=5;
 		 startPage=((curpage-1)/BLOCK*BLOCK)+1;
 		 endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
 		 
 		 if(endPage>totalpage){
 			 endPage=totalpage;
 		 }
 		String type=request.getParameter("type");
 	 	if(type==null){
 	 		type="1";
 	 	}
 	 	
 	 	List<GoodsVO> list=dao.goodsListData(Integer.parseInt(type),curpage);
 		
 	 	
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
	    <form method="post" action="Goods_list.jsp;">
	     <input type="text" name="find" size=20 class="input-sm" >
	     <input type="submit" value="검색" class="btn-sm btn-danger">
	    </form>
	  </div>
	  <div class="row">
      <div class="text-center">
        <a href="Goods_list.jsp?type=1" class="btn btn-lg btn-success">전체</a>
        <a href="Goods_list.jsp?type=2" class="btn btn-lg btn-info">베스트</a>
        <a href="Goods_list.jsp?type=3" class="btn btn-lg btn-warning">특가</a>
        <a href="Goods_list.jsp?type=4" class="btn btn-lg btn-primary">신상품</a>
      </div>
      </div>
      <div style="height: 20px"></div>
	  <div class="row">
	     <%
	       for (GoodsVO vo:list){
	     %>
		 <div class="col-sm-3">
		   <div class="thumnail">
		     <a href="Goods_detail.jsp?no=<%=vo.getNo()%>&type=<%=type%>">
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