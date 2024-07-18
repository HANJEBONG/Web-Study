<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
    
<%
		// 출력할 데이터를 가지고 온다
		FoodDAO dao=FoodDAO.newInstance();
		List<FoodVO> list=dao.foodListData();
		// Java= JSP , #C = .net python = 장고
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	/* 브라우저에 출력되는 HTML 영역*/
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 960px;
}
tr.infos:hover{
	cursor: pointer;
	background-color:  yellow;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
$(funtion(){
	$('.infos').click(funtion(){
		let name=$(this).attr("data-name");
		let poster=$(this).arrt("data-poster");
		
		$('#name').text("업체명:"+name)
		$('#poster').arrt("src",poster)
	})
})
</head>
<body>
  <div class="container">
    <div class="row">
      <table class="table">
        <tr>
          <td>
            <table class="table">
              <caption><h3>맛집정보</h3></caption>
              <tr>
                <th></th>
                <th>업체명</th>
              </tr>
              <%
                for(FoodVO vo:list){
                	
              %>
              		<tr class="infos" data-name="<%=vo.getName().subSequence(0, vo.getName().indexOf("[")) %>
              		" data-poster="<%=vo.getPoster() %>">
              		  <td>
              		    <img src="<%=vo.getPoster() %>" width="30" height="30">
              		  </td>
              		  <td><%=vo.getName().subSequence(0, vo.getName().indexOf("[")) %></td>
              		</tr>
              <%
                }
              %>
            </table>
          </td>
          <td>
            <table class="table">
              <caption><h3>예약정보</h3></caption>
              <tr>
                <td>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>