<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
    
<%
		request.setCharacterEncoding("UTF-8");
		String strPage=request.getParameter("page");
		BoardDAO dao=BoardDAO.newInstance();
		
		if(strPage==null){
			strPage="1";
		}
		int curpage=Integer.parseInt(strPage);
		
		List<BoardVO> list=dao.boardListData(curpage);
		
		
		int count=dao.boardTotalPage();
		int totalpage=count/10;
		if((count%10)!=0){
			totalpage++;
		}
		count=count-((curpage*10)-10);
		
		String type=request.getParameter("value");
		
		System.out.println(type);
		String find=request.getParameter("find");
		
		if(find!=null){
			list=dao.boardFindListData(Integer.parseInt(type), find, curpage);
		}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 30px;
}
.row{
	margin: 0px auto;
	width: 800px;
}
h3{
	text-align: center;
}
</style>
</head>
<body>
  <div class="container">
    <h3>자유게시판</h3>
      <div class="row">
        <table class="table">
          <tr>
            <td>
              <a href="board_insert.jsp" class="btn btn-xs btn-danger">새글</a>
            </td>
          </tr>
        </table>
        <table class="table table-striped">
          <tr>
            <th width="10%" class="text-center">번호</th>
            <th width="45%" class="text-center">제목</th>
            <th width="15%" class="text-center">이름</th>
            <th width="20%" class="text-center">작성일</th>
            <th width="10%" class="text-center">조회수</th>
          </tr>
          <%
          	for(BoardVO vo:list){

          %>
           <tr>
            <td width="10%" class="text-center"><%=count-- %></td>
            <td width="45%">
            <a href="board_detail.jsp?no=<%=vo.getNo()%>">
            <%=vo.getSubject() %></a>
            <%
              String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
              if (today.equals(vo.getDb_day())){
            %>
            	&nbsp;&nbsp;<sup style="color: red">New</sup>
            <%
              }
            %>
            </td>
            <td width="15%" class="text-center"><%=vo.getName() %></td>
            <td width="20%" class="text-center"><%=vo.getDb_day() %></td>
            <td width="10%" class="text-center"><%=vo.getHit() %></td>
          </tr>
          <%
          	}
          %>
        </table>
        <table class="table">
              <form method="post" action="board_list.jsp;">
          <tr>
            <td class="text-left">
              <select class="input-sm">
                <option value="1" >이름</option>
                <option value="2">제목</option>
                <option value="3">내용</option>
              </select>
              <input type=text size=15 class="input-sm" value="<%=find %>" name="find">
              <input type=submit value="검색" class="btn-sm btn-success">
            </td>
            <td class="text-right">
              
              <a href="board_list.jsp?page=<%=curpage>1?curpage-1:curpage %>" class="btn-sm btn-warning">이전</a>
              <%=curpage %>page / <%=totalpage %>page
              <a href="board_list.jsp?page=<%=curpage<totalpage?curpage+1:curpage %>" class="btn-sm btn-success">다음</a>
            </td>
            
          </tr>
               </form>
        </table>
      </div>
  </div>
</body>
</html>