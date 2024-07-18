<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.MemberDAO"></jsp:useBean>
<%
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	
	//MemberDAO dao=MemberDAO.newInstance();
	
	MemberVO vo=dao.isLogin(pwd, id);
	
	if(vo.getMsg().equals("noID")){
		out.write("<script>alret(\"아이디가 없당께\") history.back()</script>");
	}else if(vo.getMsg().equals("noPWD")){
		out.write("<script>alret(\"비밀번호가 틀렷당께\") history.back()</script>");
	}else{
		session.setAttribute("name", vo.getName());
		session.setAttribute("id", id);
		session.setAttribute("pwd", pwd);
		
		response.sendRedirect("../main/main.jsp?mode=1");
	}

%>