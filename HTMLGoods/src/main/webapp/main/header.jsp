<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
 <%
      String id=(String)session.getAttribute("id");
 	  
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.row{
   margin:0px auto;
   width:960px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse" >
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Food</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="../main/main.jsp?mode=0">Home</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="action_include.jsp?mode=1">회원가입</a></li>
          <li><a href="action_include.jsp?mode=2">아이디찾기</a></li>
          <li><a href="action_include.jsp?mode=3">비밀번호찾기</a></li>
        </ul>
      </li>
      <li><a href="action_include.jsp?mode=4">커뮤니티</a></li>
      <li><a href="action_include.jsp?mode=5">마이페이지</a></li>
    </ul>
  </div>
</nav>
 <div class="row">
  <% 
  	if(id==null){
  %>
   <form method="POST" action="../member/login_ok.jsp">
   <div class="text-right">
     ID: <input type=text name=id size=15 class="input-sm">
     &nbsp;
     Password : <input type=password name=pwd size=15 class="input-sm">
     <button class="btn-sm btn-danger">로그인</button>
   </div>
   </form>
   <%
  	}
  	else{
   %>
   <div class="text-right">
   <form method="POST" action="../member/logout.jsp">
     로그인을 환영합니다   <%=session.getAttribute("name") %>님!!
     <button class="btn-sm btn-danger">로그아웃</button>
   </div>
   </form>
   <%
  	}
   %>
   <div style=height:10px></div> 
 </div>
</body>
</html>