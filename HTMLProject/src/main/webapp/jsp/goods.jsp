<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
    <%
    	String strPage=request.getParameter("page");
    	if(strPage==null)strPage="1";
    	
    	int curpage=Integer.parseInt(strPage);
    
    	GoodsDAO dao=GoodsDAO.newInstance();
    	List<GoodsVO> list=dao.goodsListData(curpage);
    	for(GoodsVO vo:list){
    		String s=vo.getName();
    		if(s.length()>25){
    			s=s.substring(0,25)+"...";
    			vo.setName(s);
    		}
    		vo.setName(s);
    	}
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type"text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 960px;
}
</style>
</head>
<body>
 <div class="container">
 <div class="row">
 <%
 	for(GoodsVO vo:list){
 	%>	
 		<div class="col-md-3">
    <div class="thumbnail">
      <a href="#">
        <img src="<%=vo.getPoster() %>" alt="Lights" style="width:100%">
        <div class="caption">
          <p></p>
        </div>
      </a>
    </div>
  </div>
 	<%
 	}
 %>
 </div>
 </div>
</body>
</html>