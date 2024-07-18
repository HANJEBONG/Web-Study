<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.change.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	String index=request.getParameter("type");
	if(index==null){
		index="1";
	}
	String jsp="";
	switch(Integer.parseInt(index)){
	case 1:
		jsp="../goods/home.jsp";
		break;
	case 2:
		jsp="../goods/detail.jsp";
		break;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <jsp:include page="header.jsp"></jsp:include>
 <div class="container">
   <jsp:include page="<%=jsp %>"></jsp:include>
 </div>
</body>
</html>