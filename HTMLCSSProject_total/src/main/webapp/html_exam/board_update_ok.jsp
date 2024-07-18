<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
     request.setCharacterEncoding("UTF-8");
     String no=request.getParameter("no");
     String name=request.getParameter("name");
     String subject=request.getParameter("subject");
     String content=request.getParameter("content");
     String pwd=request.getParameter("pwd");
     
     BoardDAO dao=BoardDAO.newInstance();
     boolean bCheck=dao.boardPwdCheck(pwd, Integer.parseInt(no));
     if(bCheck==true){
    	 BoardVO vo=new BoardVO();
    	 vo.setName(name);
    	 vo.setSubject(subject);
    	 vo.setContent(content);
    	 
    	 dao.boardUpdate(vo, Integer.parseInt(no));
    	 response.sendRedirect("board_detail.jsp?no="+no);
     }else{
    	%>
    		
    		<script>
    		alert("비밀번호가 틀립니다");
    		history.back();
    		</script>
    	 <%
     }
%>