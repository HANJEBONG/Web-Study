<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>

<%
	request.setCharacterEncoding("UTF-8");
	String no=request.getParameter("no");
	String pwd=request.getParameter("pwd");
	int nof=Integer.parseInt(no);
	
    BoardDAO dao=BoardDAO.newInstance();
    boolean bCheck=dao.boardPwdCheck(pwd, nof);
    if(bCheck==true){
    	dao.boardDelete(nof);
    	
    	response.sendRedirect("board_list.jsp");
    }else{
    	%>
    	   <script>
    	   alert("비밀번호가 틀립니다");
   		history.back();
    	   </script>
    	<%
    }
    
    
%>